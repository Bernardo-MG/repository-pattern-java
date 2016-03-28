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

package com.wandrell.pattern.repository.jpa;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.wandrell.pattern.query.NamedParameterQueryData;
import com.wandrell.pattern.repository.entity.PersistenceEntity;
import com.wandrell.pattern.repository.pagination.PaginatedRepository;
import com.wandrell.pattern.repository.pagination.PaginationData;

/**
 * {@code Repository} for working with JPA classes and allowing filtering and
 * paginating results.
 * <p>
 * Entities are acquired with the use of JPQL queries such as this:
 * <p>
 * {@code SELECT employee FROM Employee employee WHERE employee.id = :id}
 * <p>
 * Where the {@code :id} placeholder will be swapped for an {@code id}
 * parameter.
 * <p>
 * For these queries a {@code NamedParameterQueryData} object will be received
 * by the repository. This will contain both the query to be used and the
 * parameters to apply.
 * <p>
 * When using the {@link #add(PersistenceEntity) add} and the
 * {@link #update(PersistenceEntity) update} methods it should be noted that
 * both will work the same way. If the received entity lacks an identifier said
 * entity will be added into the database, otherwise the entity will be updated
 * in the data source.
 *
 * @author Bernardo Martínez Garrido
 * @param <V>
 *            the type stored on the repository
 * @see NamedParameterQueryData
 * @see PersistenceEntity
 */
public final class JpaRepository<V extends PersistenceEntity>
        implements PaginatedRepository<V, NamedParameterQueryData> {

    /**
     * Entity manager in charge of handling the persistence process.
     */
    private final EntityManager eManager;

    /**
     * JPQL query for acquiring all the entities.
     * <p>
     * It will be received by the constructor, and would be something like:
     * <p>
     * {@code SELECT employee FROM Employee employee}
     */
    private final String selectAllQuery;

    /**
     * Constructs a {@code JPARepository} with the specified all-data query.
     * <p>
     * The query for retrieving all the entities should be something like this:
     * <p>
     * {@code SELECT employee FROM Employee employee}
     *
     * @param entityManager
     *            {@code EntityManager} for the repository
     * @param allQuery
     *            query for retrieving all the entities from the repository
     */
    public JpaRepository(final EntityManager entityManager,
            final String allQuery) {
        super();

        checkNotNull(entityManager,
                "Received a null pointer as the entity manager");
        checkNotNull(allQuery,
                "Received a null pointer as the all-values query");

        eManager = entityManager;
        selectAllQuery = allQuery;
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

        checkNotNull(entity, "Received a null pointer as the entity");

        if ((entity.getId() == null) || (entity.getId() < 0)) {
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
     * <p>
     * The query used for this operation is the one received by the constructor.
     *
     * @return all the entities contained in the repository
     */
    @SuppressWarnings("unchecked")
    @Override
    public final Collection<V> getAll() {
        final Query builtQuery; // Query created from the query data

        // Builds the query
        builtQuery = getEntityManager().createQuery(getAllValuesQuery());

        // Processes the query
        return builtQuery.getResultList();
    }

    /**
     * Returns all the entities contained in the repository paginated.
     * <p>
     * The query used for this operation is the one received by the constructor.
     *
     * @return all the entities contained in the repository paginated
     */
    @SuppressWarnings("unchecked")
    @Override
    public final Collection<V> getAll(final PaginationData pagination) {
        final Query builtQuery; // Query created from the query data

        checkNotNull(pagination,
                "Received a null pointer as the pagination data");

        // Builds the query
        builtQuery = getEntityManager().createQuery(getAllValuesQuery());

        // Sets the pagination
        applyPagination(builtQuery, pagination);

        // Processes the query
        return builtQuery.getResultList();
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
    public final Collection<V> getCollection(
            final NamedParameterQueryData query) {

        checkNotNull(query, "Received a null pointer as the query");

        // Processes the query
        return buildQuery(query).getResultList();
    }

    /**
     * Queries the entities in the repository and returns a paginated subset of
     * them.
     * <p>
     * The collection is created by building a query from the received
     * {@code QueryData} and executing it.
     *
     * @param query
     *            the query user to acquire the entities
     * @return the queried and paginated subset of entities
     */
    @SuppressWarnings("unchecked")
    @Override
    public final Collection<V> getCollection(
            final NamedParameterQueryData query,
            final PaginationData pagination) {
        final Query builtQuery; // Query created from the query data

        checkNotNull(query, "Received a null pointer as the query");
        checkNotNull(pagination,
                "Received a null pointer as the pagination data");

        builtQuery = buildQuery(query);

        // Sets the pagination
        applyPagination(builtQuery, pagination);

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
    public final V getEntity(final NamedParameterQueryData query) {
        final Query builtQuery; // Query created from the query data
        V entity; // Entity acquired from the query

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

    private final void applyPagination(final Query query,
            final PaginationData pagination) {
        query.setFirstResult(
                (pagination.getPageNumber() - 1) * pagination.getPageSize());
        query.setMaxResults(pagination.getPageSize());
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
    private final Query buildQuery(final NamedParameterQueryData query) {
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
     * Returns the query used for retrieving all the entities on the repository.
     *
     * @return the query for retrieving all the entities
     */
    private final String getAllValuesQuery() {
        return selectAllQuery;
    }

    /**
     * Returns the {@code EntityManager} in charge of the persistence.
     *
     * @return the {@code EntityManager} in charge of the persistence
     */
    private final EntityManager getEntityManager() {
        return eManager;
    }

}
