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

package com.wandrell.pattern.repository.pagination;

import java.util.Collection;

import com.wandrell.pattern.repository.FilteredRepository;

/**
 * Extension of {@link com.wandrell.pattern.repository.FilteredRepository
 * FilteredRepository} allowing paginating it's contents to get a subset of
 * them.
 * <p>
 * The pagination is added to all the methods which return any set of entities,
 * meaning that it is possible paginate a subset created with the methods from
 * {@code FilteredRepository}.
 * 
 * @author Bernardo Martínez Garrido
 *
 * @param <V>
 *            the type stored on the repository
 * @param <F>
 *            the type being used to filter the entities
 */
public interface PaginatedRepository<V, F> extends FilteredRepository<V, F> {

    /**
     * Returns all the entities contained in the repository paginated.
     * 
     * @param pagination
     *            pagination data
     * @return all the entities contained in the repository paginated
     */
    public Collection<V> getAll(final PaginationData pagination);

    /**
     * Queries the entities in the repository and returns a paginated subset of
     * them.
     * <p>
     * The collection is created filtering the stored data with the specified
     * filter.
     * <p>
     * How this filter exactly work will depend on the implementation, it may be
     * an structure containing information to build an SQL query, or may just be
     * a predicate which the entities should validate.
     * 
     * @param filter
     *            the filter which discriminates the entities to be returned
     * @param pagination
     *            pagination data
     * @return the filtered and paginated subset of entities
     */
    public Collection<V> getCollection(final F filter,
            final PaginationData pagination);

}
