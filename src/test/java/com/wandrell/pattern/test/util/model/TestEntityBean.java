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

package com.wandrell.pattern.test.util.model;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Test class serving as a persistence entity bean.
 * <p>
 * This is to be used on the non-JPA repositories tests.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class TestEntityBean implements TestEntity {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = 2002146016444401073L;

    /**
     * Entity's ID.
     */
    private Integer           id               = null;

    /**
     * Name of the entity.
     * <p>
     * This is to have additional data apart from the id, to be used on the
     * tests.
     */
    private String            name             = "";

    /**
     * Default constructor.
     */
    public TestEntityBean() {
        super();
    }

    /**
     * Default constructor.
     */
    public TestEntityBean(final Integer identifier) {
        super();

        id = identifier;
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

        final TestEntityBean other = (TestEntityBean) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public final void setName(final String name) {
        this.name = name;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).toString();
    }

}
