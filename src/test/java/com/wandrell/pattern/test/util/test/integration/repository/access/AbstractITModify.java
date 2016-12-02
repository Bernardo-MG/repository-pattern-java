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

package com.wandrell.pattern.test.util.test.integration.repository.access;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.query.DefaultNamedParameterQueryData;
import com.wandrell.pattern.query.NamedParameterQueryData;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.test.util.model.TestEntity;

/**
 * Abstract integration tests for a {@link FilteredRepository} testing modifier
 * methods.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Adding an entity changes the contents of the repository.</li>
 * <li>Removing an entity changes the contents of the repository.</li>
 * <li>Updating an entity changes it.</li>
 * </ol>
 * <p>
 * This is meant to be used along a Spring context, which will set up the
 * repository and all of it's requirements.
 *
 * @author Bernardo Martínez Garrido
 * @see FilteredRepository
 */
public abstract class AbstractITModify
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * The entity manager for the test context.
     */
    @Autowired(required = false)
    private EntityManager emanager;

    /**
     * Initial number of entities in the repository.
     */
    @Value("${entities.total}")
    private Integer entitiesCount;

    /**
     * Entity for the addition test.
     */
    @Autowired
    @Qualifier("newEntity")
    private TestEntity newEntity;

    /**
     * The repository being tested.
     */
    @Autowired
    private FilteredRepository<TestEntity, NamedParameterQueryData> repository;

    /**
     * Query for acquiring an entity by it's id.
     */
    @Value("${query.byId}")
    private String selectByIdQuery;

    /**
     * Default constructor.
     */
    public AbstractITModify() {
        super();
    }

    /**
     * Tests that adding an entity changes the contents of the repository.
     */
    @Test
    @Transactional
    public final void testAdd() {
        // Checks that the id has not been assigned
        Assert.assertNull(newEntity.getId());

        // Adds the entity
        getRepository().add(newEntity);

        if (emanager != null) {
            // Flushed to force updating ids
            emanager.flush();
        }

        // Checks the entity has been added
        Assert.assertEquals(getRepository().getAll().size(), entitiesCount + 1);

        // Checks that the id has been assigned
        Assert.assertNotNull(newEntity.getId());
        Assert.assertTrue(newEntity.getId() >= 0);
    }

    /**
     * Tests that removing an entity changes the contents of the repository.
     */
    @Test
    @Transactional
    public final void testRemove() {
        final TestEntity entity; // Entity being tested
        final Map<String, Object> parameters; // Params for the query
        final NamedParameterQueryData query; // Query for retrieving the entity

        // Acquires the entity
        parameters = new LinkedHashMap<>();
        parameters.put("id", 1);
        query = new DefaultNamedParameterQueryData(selectByIdQuery, parameters);
        entity = getRepository().getEntity(query);

        // Removes the entity
        getRepository().remove(entity);

        // Checks that the number of entities has decreased
        Assert.assertEquals(getRepository().getAll().size(), entitiesCount - 1);

        // Tries to retrieve the removed entity
        // The entity is now null
        Assert.assertNull(getRepository().getEntity(query));
    }

    /**
     * Tests that updating an entity changes it.
     */
    @Test
    public final void testUpdate() {
        final Map<String, Object> parameters; // Params for the query
        final NamedParameterQueryData query; // Query for retrieving the entity
        final String nameChange; // Name set on the entity
        TestEntity entity; // The entity being tested

        // Acquires the entity
        parameters = new LinkedHashMap<>();
        parameters.put("id", 1);
        query = new DefaultNamedParameterQueryData(selectByIdQuery, parameters);
        entity = getRepository().getEntity(query);

        // Changes the entity name
        nameChange = "The new name";
        entity.setName(nameChange);
        getRepository().update(entity);

        // Retrieves the entity again
        entity = getRepository().getEntity(query);

        // Checks the entity's name
        Assert.assertEquals(entity.getName(), nameChange);
    }

    /**
     * Returns the repository being tested.
     *
     * @return the repository being tested.
     */
    protected final FilteredRepository<TestEntity, NamedParameterQueryData> getRepository() {
        return repository;
    }

}
