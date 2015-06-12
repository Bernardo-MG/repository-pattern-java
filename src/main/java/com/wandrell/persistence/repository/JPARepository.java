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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.PersistenceEntity;

/**
 * Extension of {@code FilteredRepository} prepared to work with basic JPA
 * classes.
 * 
 * @author Bernardo Martínez Garrido
 * @param <V>
 *            the type stored on the repository
 */
public abstract class JPARepository<V> implements
        FilteredRepository<V, QueryData> {

    /**
     * Query for acquiring all the entities.
     */
    private final QueryData allValuesQuery;
    /**
     * Entity manager in charge of handling the persistence process.
     */
    @PersistenceContext
    private EntityManager   em;

    /**
     * Constructs a {@code JPARepository} with the specified all-data query.
     * 
     * @param allQuery
     *            query for retrieving all the entities from the repository
     */
    public JPARepository(final QueryData allQuery) {
        super();

        checkNotNull(allQuery,
                "Received a null pointer as the all-values query");

        allValuesQuery = allQuery;
    }

    @Override
    public final void add(final V entity) {
        final PersistenceEntity persist; // The entity casted

        checkNotNull(entity, "Received a null pointer as the entity");
        checkArgument(entity instanceof PersistenceEntity,
                "The entity is not an instance of PersistenceEntity");

        if (entity instanceof PersistenceEntity) {
            persist = (PersistenceEntity) entity;

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
    }

    @Override
    public final Collection<V> getAll() {
        return getCollection(getAllValuesQuery());
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Collection<V> getCollection(final QueryData filter) {
        final Query query;      // Query created from the query data

        checkNotNull(filter, "Received a null pointer as the filter");

        // Builds the query
        query = buildQuery(filter);

        // Processes the query
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final V getEntity(final QueryData filter) {
        final Query query;      // Query created from the query data
        V entity;               // Entity acquired from the query

        checkNotNull(filter, "Received a null pointer as the filter");

        // Builds the query
        query = buildQuery(filter);

        // Tries to acquire the entity
        try {
            entity = (V) query.getSingleResult();
        } catch (final NoResultException exception) {
            entity = null;
        }

        return entity;
    }

    @Override
    public final void remove(final V entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public final void update(final V entity) {
        add(entity);
    }

    private final Query buildQuery(final QueryData filter) {
        final Query query;      // Query created from the query data

        // Builds the base query
        query = getEntityManager().createQuery(filter.getQuery());

        // Applies the parameters
        for (final Entry<String, Object> entry : filter.getParameters()
                .entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query;
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
        return em;
    }

}
