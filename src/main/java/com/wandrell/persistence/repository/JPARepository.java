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
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.PersistenceEntity;

/**
 * Implementation of {@code FilteredRepository} prepared to work with basic JPA
 * classes.
 * <p>
 * This repository uses JPQL queries, applying a simple JPA templating
 * mechanism.
 * <p>
 * For example, a query could be like this:
 * <p>
 * {@code SELECT employee FROM Employee employee WHERE id = :id}
 * <p>
 * Meaning that the query expects a parameter {@code id}, which will take the
 * place of the {@code :id} placeholder.
 * <p>
 * Both the query and the parameters will be received on a {@code QueryData}
 * object, which comes from the <a
 * href="https://github.com/Bernardo-MG/java-patterns">Java Patterns
 * library</a>.
 * <p>
 * An initial query is required, this will be used when acquiring all the
 * values, and could be something like:
 * <p>
 * {@code SELECT employee FROM Employee employee"}
 * <p>
 * As note to take into consideration, when using the
 * {@link #add(PersistenceEntity) add} and the
 * {@link #update(PersistenceEntity) update} methods both will work the same. If
 * the received entity lacks a code it will be added into the database,
 * otherwise the stored entity will be updated.
 * 
 * @author Bernardo Martínez Garrido
 * @param <V>
 *            the type stored on the repository
 * @see QueryData
 * @see PersistenceEntity
 */
public final class JPARepository<V extends PersistenceEntity> implements
        FilteredRepository<V, QueryData> {

    /**
     * Query for acquiring all the entities.
     */
    private final QueryData     allValuesQuery;
    /**
     * Entity manager in charge of handling the persistence process.
     */
    private final EntityManager manager;

    /**
     * Constructs a {@code JPARepository} with the specified all-data query.
     * 
     * @param entityManager
     *            {@code EntityManager} for the repository
     * @param allQuery
     *            query for retrieving all the entities from the repository
     */
    public JPARepository(final EntityManager entityManager,
            final QueryData allQuery) {
        super();

        checkNotNull(entityManager,
                "Received a null pointer as the entity manager");
        checkNotNull(allQuery,
                "Received a null pointer as the all-values query");

        manager = entityManager;
        allValuesQuery = allQuery;
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
        final PersistenceEntity persist; // The entity casted

        checkNotNull(entity, "Received a null pointer as the entity");

        persist = entity;

        if ((persist.getId() == null) || (persist.getId() < 0)) {
            // No ID has been assigned
            // It is a new entity
            getEntityManager().persist(entity);
        } else {
            // ID already assigned
            // It is an existing entity
            getEntityManager().merge(entity);
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
    @SuppressWarnings("unchecked")
    @Override
    public final Collection<V> getCollection(final QueryData query) {
        final Query builtQuery;      // Query created from the query data

        checkNotNull(query, "Received a null pointer as the query");

        // Builds the query
        builtQuery = buildQuery(query);

        // Processes the query
        return builtQuery.getResultList();
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
    @SuppressWarnings("unchecked")
    @Override
    public final V getEntity(final QueryData query) {
        final Query builtQuery; // Query created from the query data
        V entity;               // Entity acquired from the query

        checkNotNull(query, "Received a null pointer as the query");

        // Builds the query
        builtQuery = buildQuery(query);

        // Tries to acquire the entity
        try {
            entity = (V) builtQuery.getSingleResult();
        } catch (final NoResultException exception) {
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
        getEntityManager().remove(entity);
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
     * Creates a {@code Query} from the data contained on the received
     * {@code QueryData}.
     * <p>
     * The string query contained on the {@code QueryData} will be transformed
     * into the {@code Query}, to which the parameters contained on that same
     * received object will be applied.
     * 
     * @param query
     *            the base query
     * @return a {@code Query} created from the received {@code QueryData}
     */
    private final Query buildQuery(final QueryData query) {
        final Query builtQuery; // Query created from the query data

        // Builds the base query
        builtQuery = getEntityManager().createQuery(query.getQuery());

        // Applies the parameters
        for (final Entry<String, Object> entry : query.getParameters()
                .entrySet()) {
            builtQuery.setParameter(entry.getKey(), entry.getValue());
        }

        return builtQuery;
    }

    /**
     * Returns the {@code QueryData} used to acquire all the entities on the
     * repository.
     * 
     * @return the {@code QueryData} used to acquire all the entities
     */
    private final QueryData getAllValuesQuery() {
        return allValuesQuery;
    }

    /**
     * Returns the {@code EntityManager} in charge of the persistence.
     * 
     * @return the {@code EntityManager} in charge of the persistence
     */
    private final EntityManager getEntityManager() {
        return manager;
    }

}
