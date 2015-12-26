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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;

import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.PersistenceEntity;

/**
 * {@code FilteredRepository} for working with Spring's JDBC framework and Java
 * beans.
 * <p>
 * Entities are acquired with the use of templated SQL queries such as this:
 * <p>
 * {@code SELECT name FROM employees WHERE id = :id}
 * <p>
 * Where the {@code :id} placeholder will be swapped for an {@code id}
 * parameter. The use of named parameters instead of the {@code ?} placeholder
 * is thanks to Spring's classes.
 * <p>
 * For these queries a {@code QueryData} object, which comes from the <a
 * href="https://github.com/Bernardo-MG/java-patterns">Java Patterns
 * library</a>, will be received by the repository. This will contain both the
 * query to be used and the parameters to apply.
 * <p>
 * When using the {@link #add(PersistenceEntity) add} and the
 * {@link #update(PersistenceEntity) update} methods it should be noted that
 * both will work the same. If the received entity lacks an identifier said
 * entity will be added into the database, otherwise the entity will be updated
 * in the data source.
 *
 * @author Bernardo Martínez Garrido
 * @param <V>
 *            the type stored on the repository
 * @see QueryData
 * @see PersistenceEntity
 */
public final class SpringJDBCRepository<V extends PersistenceEntity> implements
        FilteredRepository<V, QueryData> {

    /**
     * The class of the objects to be returned by the repository.
     * <p>
     * This is used by Spring's classes to transform query results.
     */
    private final Class<V> classType;
    /**
     * SQL query template for deleting entities.
     * <p>
     * This is a string template for generating delete SQL queries. For example,
     * it could be something like:
     * <p>
     * {@code DELETE FROM employees WHERE id = :id}
     * <p>
     * The use of named parameters instead of the {@code ?} placeholder is
     * thanks to Spring's classes.
     * <p>
     * The template will be used, along a received entity, to build and execute
     * the actual query.
     */
    private final String deleteQueryTemplate;
    /**
     * Insert operation handler.
     * <p>
     * This takes care of inserting entities into the database, and is generated
     * from the parameters received by the constructor.
     */
    private final SimpleJdbcInsertOperations insertHandler;
    /**
     * Named JDBC operations handler.
     * <p>
     * This takes care of the JDBC operations, allowing the use of named
     * parameters instead of the {@code ?} placeholder.
     */
    private final NamedParameterJdbcOperations jdbcTemplate;
    /**
     * SQL query for acquiring all the entities.
     * <p>
     * It will be generated from the parameters received by the constructor, and
     * would be something like:
     * <p>
     * {@code SELECT * FROM employees}
     */
    private final String selectAllQuery;
    /**
     * SQL query template for updating entities.
     * <p>
     * This is a string template for generating update SQL queries. For example,
     * it could be something like:
     * <p>
     * {@code UPDATE table SET column = :col WHERE id = :id}
     * <p>
     * The use of named parameters instead of the {@code ?} placeholder is
     * thanks to Spring's classes.
     * <p>
     * The template will be used, along a received entity, to build and execute
     * the actual query.
     */
    private final String updateQueryTemplate;

    /**
     * Constructs a {@code SpringJDBCRepository} with the specified data and
     * queries.
     * <p>
     * It will require templated queries for the update and delete operations.
     * The parameters for these queries will be acquired automatically from the
     * entity received for each of the operations.
     * <p>
     * The recommended delete query just requires knowing the ID of the entity,
     * so it can be similar to this:
     * <p>
     * {@code DELETE FROM employees WHERE id = :id"}
     * <p>
     * The update query requires all the columns which will be updated:
     * <p>
     * {@code UPDATE employees SET name = :name WHERE id = :id}
     * <p>
     * Any additional query which may be required, such as one for acquiring all
     * the entities, will be built from the received data.
     *
     * @param type
     *            the class of the objects to be returned
     * @param source
     *            source of the data
     * @param update
     *            query template for updating an entity on the database
     * @param delete
     *            query template for deleting an entity on the database
     * @param table
     *            table linked to the repository's entities
     * @param keys
     *            primary keys of the table
     */
    public SpringJDBCRepository(final Class<V> type, final DataSource source,
            final String update, final String delete, final String table,
            final String... keys) {
        super();

        checkNotNull(type, "Received a null pointer as the class type");
        checkNotNull(source, "Received a null pointer as the data source");
        checkNotNull(update, "Received a null pointer as the update query");
        checkNotNull(delete, "Received a null pointer as the delete query");
        checkNotNull(table, "Received a null pointer as the table");
        checkNotNull(keys, "Received a null pointer as the key columns");

        classType = type;

        // Queries
        selectAllQuery = String.format("SELECT * FROM %s", table);
        updateQueryTemplate = update;
        deleteQueryTemplate = delete;

        insertHandler = new SimpleJdbcInsert(source).withTableName(table)
                .usingGeneratedKeyColumns(keys);

        jdbcTemplate = new NamedParameterJdbcTemplate(source);
    }

    /**
     * Constructs a {@code SpringJDBCRepository} with the specified data and
     * queries.
     * <p>
     * It will require templated queries for the update and delete operations.
     * The parameters for these queries will be acquired automatically from the
     * entity received for each of the operations.
     * <p>
     * The recommended delete query just requires knowing the ID of the entity,
     * so it can be similar to this:
     * <p>
     * {@code DELETE FROM employees WHERE id = :id"}
     * <p>
     * The update query requires all the columns which will be updated:
     * <p>
     * {@code UPDATE employees SET name = :name WHERE id = :id}
     * <p>
     * Any additional query which may be required, such as one for acquiring all
     * the entities, will be built from the received data.
     *
     * @param type
     *            the class of the objects to be returned
     * @param template
     *            JDBC template with access to the data
     * @param update
     *            query template for updating an entity on the database
     * @param delete
     *            query template for deleting an entity on the database
     * @param table
     *            table linked to the repository's entities
     * @param keys
     *            primary keys of the table
     */
    public SpringJDBCRepository(final Class<V> type,
            final JdbcTemplate template, final String update,
            final String delete, final String table, final String... keys) {
        super();

        checkNotNull(type, "Received a null pointer as the class type");
        checkNotNull(template, "Received a null pointer as the JDBC template");
        checkNotNull(update, "Received a null pointer as the update query");
        checkNotNull(delete, "Received a null pointer as the delete query");
        checkNotNull(table, "Received a null pointer as the table");
        checkNotNull(keys, "Received a null pointer as the key columns");

        classType = type;

        // Queries
        selectAllQuery = String.format("SELECT * FROM %s", table);
        updateQueryTemplate = update;
        deleteQueryTemplate = delete;

        insertHandler = new SimpleJdbcInsert(template).withTableName(table)
                .usingGeneratedKeyColumns(keys);

        jdbcTemplate = new NamedParameterJdbcTemplate(template);
    }

    /**
     * Adds an entity to the repository, or updates it if it already exists.
     * <p>
     * Note that both the {@code add} and the {@link #update(PersistenceEntity)
     * update} methods work the same. If the entity does not exist it will be
     * added, but if it already exists then it will be updated.
     * <p>
     * If the entity is to be updated, then the update query received on the
     * constructor will be used.
     * <p>
     * If it is inserted, a query generated from the data received by the
     * constructor will be used.
     *
     * @param entity
     *            the entity to add
     */
    @Override
    public final void add(final V entity) {
        final SqlParameterSource parameterSource; // Parameters source
        final Number newKey; // Key assigned to the new
                             // entity

        checkNotNull(entity, "Received a null pointer as the entity");

        parameterSource = new BeanPropertySqlParameterSource(entity);

        if ((entity.getId() == null) || (entity.getId() < 0)) {
            // No ID has been assigned
            // It is a new entity
            newKey = getInsertHandler().executeAndReturnKey(parameterSource);

            entity.setId(newKey.intValue());
        } else {
            // ID already assigned
            // It is an existing entity
            getTemplate().update(getUpdateQueryTemplate(), parameterSource);
        }
    }

    /**
     * Returns all the entities contained in the repository.
     * <p>
     * The query used for this operation just queries the table received by the
     * constructor.
     *
     * @return all the entities contained in the repository
     */
    @Override
    public final Collection<V> getAll() {
        return getTemplate().query(getSelectAllValuesQuery(),
                BeanPropertyRowMapper.newInstance(getType()));
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
        V entity; // Entity acquired from the query

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
     * <p>
     * For this operation the delete query received on the constructor will be
     * used.
     *
     * @param entity
     *            the entity to remove
     */
    @Override
    public final void remove(final V entity) {
        final SqlParameterSource parameterSource; // Parameters source

        parameterSource = new BeanPropertySqlParameterSource(entity);

        getTemplate().update(getDeleteQueryTemplate(), parameterSource);
    }

    /**
     * Updates an entity on the repository, or adds it if missing.
     * <p>
     * Note that both the {@link #add(PersistenceEntity) add} and the
     * {@code update} methods work the same, as if the entity does not exist it
     * will be added, but if it already exists then it will be updated.
     * <p>
     * If the entity is to be updated, then the update query received on the
     * constructor will be used.
     * <p>
     * If it is inserted, a query generated from the data received by the
     * constructor will be used.
     *
     * @param entity
     *            the entity to add
     */
    @Override
    public final void update(final V entity) {
        add(entity);
    }

    /**
     * Returns the SQL query template used for deleting an entity.
     * <p>
     * Thanks to Spring's classes this query can make use of named parameters
     * such as this:
     * <p>
     * {@code DELETE FROM employees WHERE id = :id}
     *
     * @return the query template for deleting an entity
     */
    private final String getDeleteQueryTemplate() {
        return deleteQueryTemplate;
    }

    /**
     * Returns the handler of the insert operations.
     * <p>
     * This takes care of inserting entities into the database.
     *
     * @return the handler of the insert operations
     */
    private final SimpleJdbcInsertOperations getInsertHandler() {
        return insertHandler;
    }

    /**
     * Returns the query used for retrieving all the entities on the repository.
     *
     * @return the query for retrieving all the entities
     */
    private final String getSelectAllValuesQuery() {
        return selectAllQuery;
    }

    /**
     * Returns the template used for executing the queries.
     *
     * @return the template for executing the queries
     */
    private final NamedParameterJdbcOperations getTemplate() {
        return jdbcTemplate;
    }

    /**
     * Returns the class of the objects returned by the repository.
     * <p>
     * This is to be used when executing a query, to transform the query
     * results.
     *
     * @return the class of the objects returned by the repository
     */
    private final Class<V> getType() {
        return classType;
    }

    /**
     * Returns the query used for updating an entity.
     * <p>
     * Thanks to Spring's classes this query can make use of named parameters
     * such as this:
     * <p>
     * {@code UPDATE table SET column = :col WHERE id = :id}
     *
     * @return the query for updating an entity
     */
    private final String getUpdateQueryTemplate() {
        return updateQueryTemplate;
    }

}
