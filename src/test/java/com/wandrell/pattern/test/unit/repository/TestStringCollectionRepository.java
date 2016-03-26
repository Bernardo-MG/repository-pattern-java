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

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;

/**
 * Unit tests for {@link CollectionRepository}. For this test the repository
 * will contain {@code String} entities.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Entities are added correctly</li>
 * <li>Entities are removed correctly</li>
 * <li>Entities are updated correctly</li>
 * <li>Updating a non existing entity does not add it</li>
 * <li>The {@code getCollection} method filters the entities correctly</li>
 * <li>The {@code getEntity} method filters the entities correctly</li>
 * <li>The {@code getEntity} method returns {@code null} when the repository is
 * empty</li>
 * <li>Modifying the {@code Collection} returned by {@code getAll} does not
 * modify the repository's internal collection</li>
 * <li>Modifying the {@code Collection} returned by {@code getCollection} does
 * not modify the repository's internal collection</li>
 * </ol>
 * <p>
 * Note that due to the way the testing is configured, Maven will run these test
 * parallelized.
 * 
 * @author Bernardo Martínez Garrido
 * @see CollectionRepository
 */
public final class TestStringCollectionRepository {

    /**
     * The repository being tested.
     */
    private FilteredRepository<String, Predicate<String>> repository;

    /**
     * Default constructor.
     */
    public TestStringCollectionRepository() {
        super();
    }

    /**
     * Restores the repository state before each test.
     */
    @BeforeMethod
    public final void cleanUp() {
        for (final String entity : repository
                .getCollection(new Predicate<String>() {

                    @Override
                    final public boolean apply(final String entity) {
                        return true;
                    }

                })) {
            repository.remove(entity);
        }

        repository.add("a");
        repository.add("b");
        repository.add("c");
    }

    /**
     * Creates the repository being tested before any test is run.
     */
    @BeforeClass
    public final void initialize() {
        repository = new CollectionRepository<String>(
                new LinkedHashSet<String>());
    }

    /**
     * Tests that entities are added correctly.
     */
    @Test
    public final void testAdd_Adds() {
        final Collection<String> entities; // All the entities

        repository.add("d");

        entities = repository.getCollection(new Predicate<String>() {

            @Override
            final public boolean apply(final String entity) {
                return true;
            }

        });

        Assert.assertEquals(entities.size(), 4);
        Assert.assertTrue(entities.contains("d"));
    }

    /**
     * Tests that modifying the {@code Collection} returned by {@code getAll}
     * does not modify the repository's internal collection.
     */
    @Test
    public final void testGetAll_Remove_OriginalNotChanges() {
        final Collection<String> entities; // Filtered entities

        entities = repository.getAll();

        entities.clear();

        Assert.assertEquals(entities.size(), 0);
        Assert.assertEquals(repository.getAll().size(), 3);
    }

    /**
     * Test that the {@code getCollection} method filters the entities
     * correctly.
     */
    @Test
    public final void testGetCollection_Filter_Filters() {
        final Collection<String> entities; // Filtered entities

        entities = repository.getCollection(new Predicate<String>() {

            @Override
            final public boolean apply(final String entity) {
                return entity.equals("b");
            }

        });

        Assert.assertEquals(entities.size(), 1);
        Assert.assertTrue(entities.contains("b"));
    }

    /**
     * Tests that modifying the {@code Collection} returned by
     * {@code getCollection} does not modify the repository's internal
     * collection.
     */
    @Test
    public final void testGetCollection_Remove_OriginalNotChanges() {
        final Collection<String> entities; // Filtered entities

        entities = repository.getCollection(new Predicate<String>() {

            @Override
            final public boolean apply(final String entity) {
                return entity.equals("b");
            }

        });

        entities.remove("b");

        Assert.assertEquals(entities.size(), 0);
        Assert.assertEquals(repository.getCollection(new Predicate<String>() {

            @Override
            final public boolean apply(final String entity) {
                return true;
            }

        }).size(), 3);
    }

    /**
     * Tests that the {@code getEntity} method returns {@code null} when the
     * repository is empty.
     */
    @Test
    public final void testGetEntity_Empty() {
        final String entity; // Filtered entity

        for (final String ent : repository.getAll()) {
            repository.remove(ent);
        }

        entity = repository.getEntity(new Predicate<String>() {

            @Override
            final public boolean apply(final String entity) {
                return entity.equals("b");
            }

        });

        Assert.assertEquals(entity, null);
    }

    /**
     * Test that the {@code getEntity} method filters the entities correctly.
     */
    @Test
    public final void testGetEntity_Filter_Filters() {
        final String entity; // Filtered entity

        entity = repository.getEntity(new Predicate<String>() {

            @Override
            final public boolean apply(final String entity) {
                return entity.equals("b");
            }

        });

        Assert.assertEquals(entity, "b");
    }

    /**
     * Tests that entities are removed correctly.
     */
    @Test
    public final void testRemove_Removes() {
        final Collection<String> entities; // Filtered entities

        repository.remove("b");

        entities = repository.getCollection(new Predicate<String>() {

            @Override
            final public boolean apply(final String entity) {
                return true;
            }

        });

        Assert.assertEquals(entities.size(), 2);
        Assert.assertTrue(!entities.contains("b"));
    }

    /**
     * Tests that entities are updated correctly.
     */
    @Test
    public final void testUpdate_Existing_Update() {
        final Collection<String> entities; // All the entities

        repository.update("c");

        entities = repository.getCollection(new Predicate<String>() {

            @Override
            final public boolean apply(final String entity) {
                return true;
            }

        });

        Assert.assertEquals(entities.size(), 3);
        Assert.assertTrue(entities.contains("c"));
    }

    /**
     * Tests that updating a non existing entity does not add it.
     */
    @Test
    public final void testUpdate_NotExisting_NoAdd() {
        final Collection<String> entities; // All the entities

        repository.update("d");

        entities = repository.getCollection(new Predicate<String>() {

            @Override
            final public boolean apply(final String entity) {
                return true;
            }

        });

        Assert.assertEquals(entities.size(), 3);
        Assert.assertTrue(!entities.contains("d"));
    }

}
