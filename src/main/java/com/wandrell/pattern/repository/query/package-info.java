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
 * Provides interfaces and basic implementations for a query class, meant to
 * ease creating filters for the
 * {@link com.wandrell.pattern.repository.FilteredRepository FilteredRepository}
 * .
 * <h2>Interfaces</h2> The
 * {@link com.wandrell.pattern.repository.query.QueryData QueryData} interface
 * is the root for these queries, and helps to store data for generating queries
 * similar to the SQL ones.
 * <h2>Implementations</h2>
 * <p>
 * There is a default implementation of {@code QueryData},
 * {@link com.wandrell.pattern.repository.query.DefaultQueryData
 * DefaultQueryData}, which just serves to ease using said interface.
 */
package com.wandrell.pattern.repository.query;
