/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2014-2015 the original author or authors.
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

package com.wandrell.pattern.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * Collection-based implementation of
 * {@link com.wandrell.pattern.repository.FilteredRepository FilteredRepository}
 * .
 * <p>
 * This is meant to be the most basic form of {@code Repository}, meant to be
 * used as a stub. It works like any {@code Repository} is expected to work, but
 * lacks any optimization, and all the entities will be kept in memory.
 * <p>
 * For this reason it is not recommended using it for any other reason than
 * testing or stubbing.
 * <p>
 * The filters required by the {@code FilteredRepository} interfaces are
 * instances of the Guava <a href=
 * "http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/base/Predicate.html">
 * Predicate</a> class. When using the query methods all the entities validating
 * the predicate received as filter will be returned.
 * 
 * @author Bernardo Martínez Garrido
 * @param <V>
 *            the type stored on the repository
 */
public final class CollectionRepository<V>
        implements FilteredRepository<V, Predicate<V>> {

    /**
     * The entities stored in the repository.
     * <p>
     * This is just a collection kept in memory. There is no optimization
     * applied by default.
     */
    private final Collection<V> data;

    /**
     * Constructs a {@code CollectionRepository} using a {@code LinkedList} as
     * the {@code Collection}.
     */
    public CollectionRepository() {
        this(new LinkedList<V>());
    }

    /**
     * Constructs a {@code CollectionRepository} with the specified
     * {@code Collection}.
     * <p>
     * The repository will use the received {@code Collection} to keep all the
     * entities. If this {@code Collection} already contains any entity these
     * will be kept.
     * 
     * @param collection
     *            the {@code Collection} to use for storing the entities
     */
    public CollectionRepository(final Collection<V> collection) {
        super();

        checkNotNull(collection, "Received a null pointer as collection");

        this.data = collection;
    }

    @Override
    public final void add(final V entity) {
        checkNotNull(entity, "Received a null pointer as entity");

        getData().add(entity);
    }

    @Override
    public final Collection<V> getAll() {
        return new LinkedList<V>(getData());
    }

    @Override
    public final Collection<V> getCollection(final Predicate<V> filter) {
        final Collection<V> result;

        checkNotNull(filter, "Received a null pointer as filter");

        result = new LinkedList<V>();
        for (final V entity : getData()) {
            if (filter.test(entity)) {
                result.add(entity);
            }
        }

        return result;
    }

    @Override
    public final V getEntity(final Predicate<V> filter) {
        final Collection<V> entities;
        final V entity;

        checkNotNull(filter, "Received a null pointer as filter");

        entities = getCollection(filter);

        if (entities.isEmpty()) {
            entity = null;
        } else {
            entity = entities.iterator().next();
        }

        return entity;
    }

    @Override
    public final void remove(final V entity) {
        getData().remove(entity);
    }

    @Override
    public final void update(final V entity) {
        checkNotNull(entity, "Received a null pointer as entity");

        if (getData().contains(entity)) {
            remove(entity);
            add(entity);
        }
    }

    /**
     * Returns the entities being stored.
     * 
     * @return the entities being stored.
     */
    private final Collection<V> getData() {
        return data;
    }

}
