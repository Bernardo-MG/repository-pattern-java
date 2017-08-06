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

package com.wandrell.pattern.test.unit.repository;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Predicate;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.test.util.model.TestEntityBean;

/**
 * Unit tests for {@link CollectionRepository} testing that the update methods
 * work correctly. For this test the repository will contain custom entities.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Entities are updated correctly</li>
 * <li>Updating a non existing entity does not add it</li>
 * </ol>
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see CollectionRepository
 */
public final class TestCustomUpdateCollectionRepository {

    /**
     * The repository being tested.
     */
    private FilteredRepository<TestEntityBean, Predicate<TestEntityBean>> repository;

    /**
     * Default constructor.
     */
    public TestCustomUpdateCollectionRepository() {
        super();
    }

    /**
     * Restores the repository state before each test.
     */
    @BeforeMethod
    public final void cleanUp() {
        for (final TestEntityBean entity : repository.getAll()) {
            repository.remove(entity);
        }

        repository.add(new TestEntityBean(1));
        repository.add(new TestEntityBean(2));
        repository.add(new TestEntityBean(3));
    }

    /**
     * Creates the repository being tested before any test is run.
     */
    @BeforeClass
    public final void initialize() {
        repository = new CollectionRepository<TestEntityBean>(
                new LinkedHashSet<TestEntityBean>());
    }

    /**
     * Tests that updating a non existing entity does not add it.
     */
    @Test
    public final void testUpdate_Existing_Update() {
        final Collection<TestEntityBean> entities; // All the entities
        final TestEntityBean entity; // The updated entity

        // TODO: Read and verify the updated entity
        entity = new TestEntityBean(1);
        entity.setName("name");

        repository.update(entity);

        entities = repository.getAll();

        Assert.assertEquals(entities.size(), 3);
        Assert.assertTrue(entities.contains(entity));
    }

    /**
     * Tests that updating a non existing entity does not add it.
     */
    @Test
    public final void testUpdate_NotExisting_NoAdd() {
        final Collection<TestEntityBean> entities; // All the entities
        final TestEntityBean entity; // The updated entity

        entity = new TestEntityBean(4);

        repository.update(new TestEntityBean(4));

        entities = repository.getAll();

        Assert.assertEquals(entities.size(), 3);
        Assert.assertTrue(!entities.contains(entity));
    }

}
