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

package com.wandrell.testing.persistence.util.test.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.query.DefaultQueryData;
import com.wandrell.pattern.repository.query.QueryData;
import com.wandrell.testing.persistence.util.model.TestEntity;

/**
 * Abstract integration tests for a {@link FilteredRepository} testing query
 * methods.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Retrieving all the entities gives the correct number of them.</li>
 * <li>Retrieving an existing entity returns it.</li>
 * <li>Retrieving a not existing entity does nothing.</li>
 * </ol>
 * <p>
 * This is meant to be used along a Spring context, which will set up the
 * repository and all of it's requirements.
 *
 * @author Bernardo Martínez Garrido
 * @see FilteredRepository
 */
public abstract class AbstractITQuery
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * The repository being tested.
     */
    @Autowired
    private FilteredRepository<TestEntity, QueryData> repository;
    /**
     * Query for acquiring an entity by it's id.
     */
    @Value("${query.byId}")
    private String                                    selectByIdQuery;

    /**
     * Default constructor.
     */
    public AbstractITQuery() {
        super();
    }

    /**
     * Tests that retrieving all the entities gives the correct number of them.
     */
    @Test
    public final void testGetAll() {
        Assert.assertEquals(repository.getAll().size(), 4);
    }

    /**
     * Tests that retrieving an existing entity returns it.
     */
    @Test
    public final void testGetEntity_Existing() {
        final QueryData query;                // Query for the entity
        final Map<String, Object> parameters; // Query params
        final Integer id;                     // Entity ID
        final TestEntity entity;              // Tested entity

        // Entity's id
        id = 1;

        // Acquires the entity
        parameters = new LinkedHashMap<>();
        parameters.put("id", id);
        query = new DefaultQueryData(selectByIdQuery, parameters);
        entity = getRepository().getEntity(query);

        // The entity's id is the correct one
        Assert.assertEquals(entity.getId(), id);
    }

    /**
     * Tests that retrieving a not existing entity does nothing.
     */
    @Test
    public final void testGetEntity_NotExisting() {
        final QueryData query;                // Query for the entity
        final Map<String, Object> parameters; // Query params
        final Integer id;                     // Invalid entity ID
        final TestEntity entity;              // Tested entity

        // Invalid entity id
        id = 123;

        // Tries to acquire the entity
        parameters = new LinkedHashMap<>();
        parameters.put("id", id);
        query = new DefaultQueryData(selectByIdQuery, parameters);
        entity = getRepository().getEntity(query);

        // The entity is null
        Assert.assertEquals(entity, null);
    }

    /**
     * Returns the repository being tested.
     *
     * @return the repository being tested.
     */
    protected final FilteredRepository<TestEntity, QueryData> getRepository() {
        return repository;
    }

}
