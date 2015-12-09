/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.wandrell.persistence.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.PersistenceEntity;

/**
 * Implementation of {@code FilteredRepository} prepared to work with Spring's
 * JDBC API.
 * <p>
 * For this reason, it makes use of SQL queries, to which a simple JPA
 * templating mechanism will be applied.
 * <p>
 * For example, a query could be like this:
 * <p>
 * {@code SELECT name FROM employees WHERE id = :id}
 * <p>
 * Meaning that the query expects a parameter {@code id}, which will take the
 * place of the {@code :id} placeholder.
 * <p>
 * Both the query and the parameters will be received on a {@code QueryData}
 * object, which comes from the
 * <a href="https://github.com/Bernardo-MG/java-patterns">Java Patterns
 * library</a>.
 * <p>
 * A few initial queries are required. These are for updating and deleting
 * entities. These should be created by the user, following a simple pattern.
 * <p>
 * The delete query is simple, it will just require knowing the ID of the
 * entity, so it can be something like this:
 * <p>
 * {@code DELETE FROM employees WHERE id = :id"}
 * <p>
 * The update query requires all the columns which will be updated, and could be
 * something like this:
 * <p>
 * {@code UPDATE employees SET name = :name WHERE id = :id}
 * <p>
 * As note to take into consideration, when using the
 * {@link #add(PersistenceEntity) add} and the {@link #update(PersistenceEntity)
 * update} methods both will work the same. If the received entity lacks a code
 * it will be added into the database, otherwise the stored entity will be
 * updated.
 * 
 * @author Bernardo Martínez Garrido
 * @param <V>
 *            the type stored on the repository
 * @see QueryData
 * @see PersistenceEntity
 */
public final class SpringJDBCRepository<V extends PersistenceEntity>
        implements FilteredRepository<V, QueryData> {

    /**
     * The class of the objects to be returned.
     */
    private final Class<V>                   classType;
    /**
     * Query for deleting entities.
     */
    private final String                     deleteQuery;
    /**
     * Query for acquiring all the entities.
     */
    private final QueryData                  getAllQuery;
    /**
     * Query for inserting entities.
     */
    private final SimpleJdbcInsert           insert;
    /**
     * Template for running the queries.
     */
    private final NamedParameterJdbcTemplate jdbcTemplate;
    /**
     * Query for updating entities.
     */
    private final String                     updateQuery;

    /**
     * Constructs a {@code JPARepository} with the specified data and queries.
     * <p>
     * It will require a templated query both for updating and deleting an
     * entity.
     * <p>
     * An additional query, used for acquiring all the entities, will be built
     * from the received table. And the keys, representing the table's primary
     * keys, will be used to prepare the insert query for new entities.
     * 
     * @param type
     *            the class of the objects to be returned
     * @param dataSource
     *            JPA source of the data
     * @param update
     *            query for updating an entity on the database
     * @param delete
     *            query for deleting an entity on the database
     * @param table
     *            table where the repository will execute the queries
     * @param keys
     *            primary keys of the table
     */
    public SpringJDBCRepository(final Class<V> type,
            final DataSource dataSource, final String update,
            final String delete, final String table, final String... keys) {
        super();

        checkNotNull(type, "Received a null pointer as the class type");
        checkNotNull(dataSource, "Received a null pointer as the data source");
        checkNotNull(update, "Received a null pointer as the update query");
        checkNotNull(delete, "Received a null pointer as the delete query");
        checkNotNull(table, "Received a null pointer as the table");
        checkNotNull(keys, "Received a null pointer as the key columns");

        this.classType = type;

        // Queries
        getAllQuery = new DefaultQueryData(
                String.format("SELECT * FROM %s", table));
        this.updateQuery = update;
        this.deleteQuery = delete;

        insert = new SimpleJdbcInsert(dataSource).withTableName(table)
                .usingGeneratedKeyColumns(keys);

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Adds an entity to the repository.
     * <p>
     * Note that both the {@code add} and the {@link #update(PersistenceEntity)
     * update} methods work the same, as if the entity does not exist it will be
     * added, but if it already exists then it will be updated.
     * 
     * @param entity
     *            the entity to add
     */
    @Override
    public final void add(final V entity) {
        final BeanPropertySqlParameterSource parameterSource; // Bean-based
        // parameters
        // source
        final Number newKey;    // Key assigned to the new entity

        checkNotNull(entity, "Received a null pointer as the entity");

        parameterSource = new BeanPropertySqlParameterSource(entity);

        if ((entity.getId() == null) || (entity.getId() < 0)) {
            newKey = getInsert().executeAndReturnKey(parameterSource);

            entity.setId(newKey.intValue());
        } else {
            getTemplate().update(getUpdateQuery(), parameterSource);
        }
    }

    /**
     * Returns all the entities contained in the repository.
     * 
     * @return all the entities contained in the repository
     */
    @Override
    public final Collection<V> getAll() {
        return getCollection(getAllValuesQuery());
    }

    /**
     * Queries the entities in the repository and returns a subset of them.
     * <p>
     * The collection is created by building a query from the received
     * {@code QueryData} and executing it.
     * 
     * @param query
     *            the query user to acquire the entities
     * @return the queried subset of entities
     */
    @Override
    public final Collection<V> getCollection(final QueryData query) {
        checkNotNull(query, "Received a null pointer as the query");

        return getTemplate().query(query.getQuery(), query.getParameters(),
                BeanPropertyRowMapper.newInstance(getType()));
    }

    /**
     * Queries the entities in the repository and returns a single one.
     * <p>
     * The entity is acquired by building a query from the received
     * {@code QueryData} and executing it.
     * 
     * @param query
     *            the query user to acquire the entities
     * @return the queried entity
     */
    @Override
    public final V getEntity(final QueryData query) {
        V entity;  // Entity acquired from the query

        checkNotNull(query, "Received a null pointer as the query");

        // Tries to acquire the entity
        try {
            entity = getTemplate().queryForObject(query.getQuery(),
                    query.getParameters(),
                    BeanPropertyRowMapper.newInstance(getType()));
        } catch (final EmptyResultDataAccessException exception) {
            entity = null;
        }

        return entity;
    }

    /**
     * Removes an entity from the repository.
     * 
     * @param entity
     *            the entity to remove
     */
    @Override
    public final void remove(final V entity) {
        final BeanPropertySqlParameterSource parameterSource; // Bean-based
        // parameters
        // source

        parameterSource = new BeanPropertySqlParameterSource(entity);

        getTemplate().update(getDeleteQuery(), parameterSource);
    }

    /**
     * Adds an entity to the repository.
     * <p>
     * Note that both the {@link #add(PersistenceEntity) add} and the
     * {@code update} methods work the same, as if the entity does not exist it
     * will be added, but if it already exists then it will be updated.
     * 
     * @param entity
     *            the entity to add
     */
    @Override
    public final void update(final V entity) {
        add(entity);
    }

    /**
     * Returns the query used to retrieving all the entities.
     * 
     * @return the query for retrieving all the entities
     */
    private final QueryData getAllValuesQuery() {
        return getAllQuery;
    }

    /**
     * Returns the query used for deleting an entity.
     * 
     * @return the query for deleting an entity
     */
    private final String getDeleteQuery() {
        return deleteQuery;
    }

    /**
     * Returns the query used for inserting an entity.
     * 
     * @return the query for inserting an entity
     */
    private final SimpleJdbcInsert getInsert() {
        return insert;
    }

    /**
     * Returns the template used for executing the queries.
     * 
     * @return the template for executing the queries
     */
    private final NamedParameterJdbcTemplate getTemplate() {
        return jdbcTemplate;
    }

    /**
     * Returns the class of the objects returned by the repository.
     * 
     * @return the class of the objects returned by the repository
     */
    private final Class<V> getType() {
        return classType;
    }

    /**
     * Returns the query used for updating an entity.
     * 
     * @return the query for updating an entity
     */
    private final String getUpdateQuery() {
        return updateQuery;
    }

}
