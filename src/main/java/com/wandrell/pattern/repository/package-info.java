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
/**
 * Interfaces and basic implementations for the repository pattern.
 * <p>
 * This pattern is similar to a DAO, as it allows accessing data through CRUD
 * operations, hiding the source of this data.
 * <p>
 * It can be hard to find differences between a DAO and a repository, but the
 * first is used as an interface between the application and a table on a
 * database, while the second is a domain driven pattern, which may or not be
 * linked to a database.
 * <h2>Interfaces</h2>
 * <p>
 * The {@link com.wandrell.pattern.repository.Repository Repository} offers the
 * most basic implementation of this pattern, using the general CRUD operations.
 * <p>
 * But the {@link com.wandrell.pattern.repository.FilteredRepository
 * FilteredRepository} additionally allows acquiring just a subset of data from
 * the repository, with the use of a filter class.
 * <h2>Implementations</h2>
 * <p>
 * A basic implementation of the {@code FilteredRepository},
 * {@link com.wandrell.pattern.repository.CollectionRepository
 * CollectionRepository}, serves as a working stub, allowing to use a repository
 * witout setting up any persistence system.
 */

package com.wandrell.pattern.repository;
