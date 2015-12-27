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

import java.util.Map;

/**
 * Interface for storing the data required to build a query with named
 * parameters. The query which can be built from this is meant to be something
 * similar to a SQL query, which will contain the body of said query and a set
 * of parameters.
 * <p>
 * The parameters are the parameters for a named query, and will be used to
 * replace placeholders on the query string when building the final string.
 * <p>
 * Classes implementing this interface are not expected to build the final
 * query, just to keep the data for building it wherever it may be required.
 * 
 * @author Bernardo Martínez Garrido
 */
public interface NamedParameterQueryData {

    /**
     * Adds a parameter.
     * <p>
     * If a parameter with the specified key already exists the new one will
     * take its place.
     * 
     * @param key
     *            key for the parameter
     * @param value
     *            value for the parameter
     */
    public void addParameter(final String key, final Object value);

    /**
     * Adds a collection of parameters.
     * <p>
     * If any parameter with one of the specified keys already exists the new
     * one will take its place.
     * 
     * @param parameters
     *            {@code Map} with all the parameter pairs
     */
    public void addParameters(final Map<String, Object> parameters);

    /**
     * The parameters to be applied to the query.
     * <p>
     * This is a collection of named parameters, where the names are not allowed
     * to be repeated.
     * 
     * @return the query's parameters
     */
    public Map<String, Object> getParameters();

    /**
     * The base query.
     * <p>
     * If there are no parameters this should be an useable query. Otherwise the
     * parameters should be applied when building the final query.
     * 
     * @return the base query for building the final query
     */
    public String getQuery();

    /**
     * Removes the parameter for the specified key.
     * 
     * @param key
     *            the key for the parameter to remove
     */
    public void removeParameter(final String key);

}
