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
import java.util.Objects;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;

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
 * @author Bernardo Martínez Garrido
 * @see CollectionRepository
 */
public final class TestCustomUpdateCollectionRepository {

    /**
     * The repository being tested.
     */
    private FilteredRepository<TestClass, Predicate<TestClass>> repository;

    /**
     * This is a test class for testing the {@code Repository} using a class
     * with an internal state.
     * 
     * In this case, the state will be the stored value, while the name
     * identifies it.
     */
    private final class TestClass {

        /**
         * Name of the class, which will identify it.
         */
        private final String name;

        /**
         * Constructs a test class with the specified data.
         * 
         * @param name
         *            the id
         */
        public TestClass(final String name) {
            super();

            this.name = name;
        }

        @Override
        public final boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (getClass() != obj.getClass()) {
                return false;
            }

            final TestClass other;

            other = (TestClass) obj;
            return Objects.equals(name, other.name);
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(name);
        }

        @Override
        public final String toString() {
            return MoreObjects.toStringHelper(this).add("name", name)
                    .toString();
        }

    }

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
        for (final TestClass entity : repository
                .getCollection(new Predicate<TestClass>() {

                    @Override
                    final public boolean apply(final TestClass entity) {
                        return true;
                    }

                })) {
            repository.remove(entity);
        }

        repository.add(new TestClass("a"));
        repository.add(new TestClass("b"));
        repository.add(new TestClass("c"));
    }

    /**
     * Creates the repository being tested before any test is run.
     */
    @BeforeClass
    public final void initialize() {
        repository = new CollectionRepository<TestClass>(
                new LinkedHashSet<TestClass>());
    }

    /**
     * Tests that updating a non existing entity does not add it.
     */
    @Test
    public final void testUpdate_Existing_Update() {
        final Collection<TestClass> entities; // All the
        // entities
        final TestClass entity; // The updated entity

        entity = new TestClass("a");

        repository.update(entity);

        entities = repository.getCollection(new Predicate<TestClass>() {

            @Override
            final public boolean apply(final TestClass entity) {
                return true;
            }

        });

        Assert.assertEquals(entities.size(), 3);
        Assert.assertTrue(entities.contains(entity));
    }

    /**
     * Tests that updating a non existing entity does not add it.
     */
    @Test
    public final void testUpdate_NotExisting_NoAdd() {
        final Collection<TestClass> entities; // All the entities
        final TestClass entity; // The updated entity

        entity = new TestClass("d");

        repository.update(new TestClass("d"));

        entities = repository.getCollection(new Predicate<TestClass>() {

            @Override
            final public boolean apply(final TestClass entity) {
                return true;
            }

        });

        Assert.assertEquals(entities.size(), 3);
        Assert.assertTrue(!entities.contains(entity));
    }

}
