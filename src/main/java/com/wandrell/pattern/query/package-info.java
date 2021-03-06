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
 * Interfaces and basic implementations for a query class, meant to ease sending
 * and creating data queries.
 * <p>
 * These queries are meant to be something similar to a SQL query.
 * <h2>Interfaces</h2> The
 * {@link com.wandrell.pattern.query.NamedParameterQueryData
 * NamedParameterQueryData} interface gives the methods required for storing the
 * data needed to build a named query.
 * <h2>Implementations</h2>
 * <p>
 * {@link com.wandrell.pattern.query.DefaultNamedParameterQueryData
 * DefaultNamedParameterQueryData} is the default implementation of
 * {@code NamedParameterQueryData}.
 */

package com.wandrell.pattern.query;
