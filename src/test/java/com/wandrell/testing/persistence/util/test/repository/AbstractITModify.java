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

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.xml.NotValidatedXMLFileParser;
import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.testing.persistence.util.model.JPATestEntity;
import com.wandrell.testing.persistence.util.model.TestEntityRepository;

/**
 * Abstract integration tests for persistence repositories checking modifier
 * methods.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Adding and then removing an entity changes the contents of the
 * repository.</li>
 * <li>Removing an entity not in the repository does nothing.</li>
 * <li>Updating an entity changes it.</li>
 * </ol>
 * <p>
 * This is meant to be used along a Spring context, which will set up the
 * repository and all it's requirements.
 * 
 * @author Bernardo Martínez Garrido
 * @see NotValidatedXMLFileParser
 */
public abstract class AbstractITModify
        extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * The repository being tested.
     */
    @Autowired
    private TestEntityRepository repository;
    /**
     * Query for acquiring an entity by it's id.
     */
    private final String         selectByIdQuery;

    /**
     * Constructs an {@code AbstractITModify} with the specified query.
     * 
     * @param selectByIdQuery
     *            a query for acquiring an entity by it's id
     */
    public AbstractITModify(final String selectByIdQuery) {
        super();

        checkNotNull(selectByIdQuery, "Received a null pointer as query");

        this.selectByIdQuery = selectByIdQuery;
    }

    /**
     * Tests that adding and then removing an entity changes the contents of the
     * repository.
     */
    @Test
    public final void testAdd_Remove() {
        final JPATestEntity entity;        // Entity being tested
        final JPATestEntity entityQueried; // Entity taken from the repo
        final Integer size;                // Total number of entities
        final Map<String, Object> parameters; // Params for the query
        final QueryData query;             // Query for retrieving the entity

        // Creates the test entity
        entity = new JPATestEntity();
        entity.setName("test_entity");

        // Adds the entity
        getRepository().add(entity);

        // Checks the ID set to the entity
        size = getRepository().getAll().size();
        Assert.assertEquals(entity.getId(), size);

        // Removes the entity
        getRepository().remove(entity);

        // Checks that the entity has been removed
        Assert.assertEquals(repository.getAll().size(), size - 1);

        // Tries to retrieve the removed entity
        parameters = new LinkedHashMap<>();
        parameters.put("id", size);
        query = new DefaultQueryData(selectByIdQuery, parameters);
        entityQueried = getRepository().getEntity(query);

        // The entity is now null
        Assert.assertNull(entityQueried);
    }

    /**
     * Tests that removing an entity not in the repository does nothing.
     */
    @Test
    public final void testRemove_NotPersisted() {
        final JPATestEntity entity; // Entity being tested
        final Integer size;         // Total number of entities

        // Creates the entity
        entity = new JPATestEntity();
        entity.setName("test_entity");

        // Retrieves the number of entities
        size = getRepository().getAll().size();

        // Tries to remove the entity
        getRepository().remove(entity);

        // Checks the number of entities
        Assert.assertEquals((Integer) repository.getAll().size(), size);
    }

    /**
     * Tests that updating an entity changes it.
     */
    @Test
    public final void testUpdate() {
        final Map<String, Object> parameters; // Params for the query
        final QueryData query;    // Query for retrieving the entity
        final String nameChange;  // Name set on the entity
        JPATestEntity entity;     // The entity being tested

        // Acquires the entity
        parameters = new LinkedHashMap<>();
        parameters.put("id", 1);
        query = new DefaultQueryData(selectByIdQuery, parameters);
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
    protected final TestEntityRepository getRepository() {
        return repository;
    }

}
