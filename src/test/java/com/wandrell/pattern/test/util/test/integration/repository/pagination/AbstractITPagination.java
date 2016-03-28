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

package com.wandrell.pattern.test.util.test.integration.repository.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.query.DefaultNamedParameterQueryData;
import com.wandrell.pattern.query.NamedParameterQueryData;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.pagination.DefaultPaginationData;
import com.wandrell.pattern.repository.pagination.PaginatedRepository;
import com.wandrell.pattern.repository.pagination.PaginationData;
import com.wandrell.pattern.test.util.model.TestEntity;

/**
 * Abstract integration tests for a {@link PaginatedRepository} testing
 * paginated query methods.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Retrieving a page for all the entities returns the number of entities set
 * for the page.</li>
 * <li>Retrieving a page for all the entities returns the correct entities.</li>
 * <li>Retrieving the last page for all the entities returns the number of
 * entities set for the page.</li>
 * <li>Retrieving a page for a subset of entities returns the number of entities
 * set for the page.</li>
 * <li>Retrieving a page for a subset of entities returns the correct entities.
 * </li>
 * <li>Retrieving the last page for a subset of entities returns the number of
 * entities set for the page.</li>
 * </ol>
 * <p>
 * This is meant to be used along a Spring context, which will set up the
 * repository and all of it's requirements.
 *
 * @author Bernardo Martínez Garrido
 * @see FilteredRepository
 */
public abstract class AbstractITPagination
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * The repository being tested.
     */
    @Autowired
    private PaginatedRepository<TestEntity, NamedParameterQueryData> repository;

    /**
     * Query for acquiring an entity by it's id.
     */
    @Value("${query.byId}")
    private String selectByIdQuery;

    /**
     * Query for acquiring the second set of five entities.
     */
    @Value("${query.secondFive}")
    private String selectSecondFiveQuery;

    /**
     * Default constructor.
     */
    public AbstractITPagination() {
        super();
        // TODO: Add a test for pages out of the collection
    }

    /**
     * Tests that retrieving the last page for all the entities returns the
     * number of entities set for the page.
     */
    @Test
    public final void testGetAll_LastPage_CorrectSize() {
        final PaginationData pagination; // Pagination data

        pagination = new DefaultPaginationData(7, 5);

        Assert.assertEquals(getRepository().getAll(pagination).size(), 2);
    }

    /**
     * Tests that retrieving a page for all the entities returns the correct
     * entities.
     */
    @Test
    public final void testGetAll_SecondPage_CorrectFirstId() {
        final PaginationData pagination; // Pagination data

        pagination = new DefaultPaginationData(5, 2);

        Assert.assertEquals(
                getRepository().getAll(pagination).iterator().next().getId(),
                (Integer) 6);
    }

    /**
     * Tests that retrieving a page for all the entities returns the number of
     * entities set for the page.
     */
    @Test
    public final void testGetAll_SecondPage_CorrectSize() {
        final PaginationData pagination; // Pagination data

        pagination = new DefaultPaginationData(5, 2);

        Assert.assertEquals((Integer) getRepository().getAll(pagination).size(),
                pagination.getPageSize());
    }

    /**
     * Tests that retrieving the last page for a subset of entities returns the
     * number of entities set for the page.
     */
    @Test
    public final void testGetCollection_LastPage_CorrectSize() {
        final PaginationData pagination; // Pagination data
        final NamedParameterQueryData query; // Query for the entity

        pagination = new DefaultPaginationData(2, 3);
        query = new DefaultNamedParameterQueryData(selectSecondFiveQuery);

        Assert.assertEquals(
                getRepository().getCollection(query, pagination).size(), 1);
    }

    /**
     * Tests that retrieving a page for a subset of entities returns the correct
     * entities.
     */
    @Test
    public final void testGetCollection_SecondPage_CorrectFirstId() {
        final PaginationData pagination; // Pagination data
        final NamedParameterQueryData query; // Query for the entity

        pagination = new DefaultPaginationData(2, 2);
        query = new DefaultNamedParameterQueryData(selectSecondFiveQuery);

        Assert.assertEquals(getRepository().getCollection(query, pagination)
                .iterator().next().getId(), (Integer) 8);
    }

    /**
     * Tests that retrieving a page for a subset of entities returns the number
     * of entities set for the page.
     */
    @Test
    public final void testGetCollection_SecondPage_CorrectSize() {
        final PaginationData pagination; // Pagination data
        final NamedParameterQueryData query; // Query for the entity

        pagination = new DefaultPaginationData(2, 2);
        query = new DefaultNamedParameterQueryData(selectSecondFiveQuery);

        Assert.assertEquals((Integer) getRepository()
                .getCollection(query, pagination).size(),
                pagination.getPageSize());
    }

    /**
     * Returns the repository being tested.
     *
     * @return the repository being tested.
     */
    protected final PaginatedRepository<TestEntity, NamedParameterQueryData> getRepository() {
        return repository;
    }

}
