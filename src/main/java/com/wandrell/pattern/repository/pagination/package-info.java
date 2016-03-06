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
/**
 * Provides interfaces and default implementations for handling pagination.
 * <p>
 * These are thought to be used with the
 * {@link com.wandrell.pattern.repository.pagination.PaginatedRepository
 * PaginatedRepository}, allowing acquiring just a section of the data kept in
 * the repository, so this can be shown or handled in parts, and not all at
 * once.
 * <h2>Interfaces</h2>
 * <p>
 * The {@link com.wandrell.pattern.repository.pagination.PaginationData
 * PaginationData} represents the data required to generate a page.
 * <h2>Implementations</h2>
 * <p>
 * A basic implementation of the interface, the
 * {@link com.wandrell.pattern.repository.pagination.DefaultPaginationData
 * DefaultPaginationData}, is contained in the package.
 */

package com.wandrell.pattern.repository.pagination;
