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

package com.wandrell.pattern.query;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Default implementation of {@link NamedParameterQueryData}.
 * <p>
 * This is a partially immutable class, as the base query used to build the
 * final query can't be edited, but parameters may be modified.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class DefaultNamedParameterQueryData
        implements NamedParameterQueryData {

    /**
     * Parameters for the query.
     * <p>
     * These are the parameters for a named query, and will be used to replace
     * placeholders on the query string when building the final string.
     */
    private final Map<String, Object> params;
    /**
     * The base query.
     * <p>
     * If there is any parameter, these will be applied to this string to build
     * the final query.
     */
    private final String              queryStr;

    /**
     * Constructs a {@code DefaultQuery} with no parameters.
     * 
     * @param query
     *            the query string
     */
    public DefaultNamedParameterQueryData(final String query) {
        this(query, new LinkedHashMap<String, Object>());
    }

    /**
     * Constructs a {@code DefaultQuery} with the specified query and
     * parameters.
     * <p>
     * The parameters are the parameters for a named query, and will be used to
     * replace placeholders on the query string when building the final string.
     * 
     * @param query
     *            the query string
     * @param parameters
     *            the query's parameters
     */
    public DefaultNamedParameterQueryData(final String query,
            final Map<String, Object> parameters) {
        super();

        checkNotNull(query, "Received a null pointer as query");
        checkNotNull(parameters, "Received a null pointer as parameters");

        queryStr = query;
        params = parameters;
    }

    @Override
    public final void addParameter(final String key, final Object value) {
        checkNotNull(key, "Received a null pointer as key");
        checkNotNull(value, "Received a null pointer as value");

        params.put(key, value);
    }

    @Override
    public final void addParameters(final Map<String, Object> parameters) {
        checkNotNull(parameters, "Received a null pointer as parameters");

        params.putAll(parameters);
    }

    @Override
    public final Map<String, Object> getParameters() {
        return Collections.unmodifiableMap(params);
    }

    @Override
    public final String getQuery() {
        return queryStr;
    }

    @Override
    public final void removeParameter(final String key) {
        params.remove(key);
    }

}
