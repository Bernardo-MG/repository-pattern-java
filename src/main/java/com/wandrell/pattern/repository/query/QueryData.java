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

package com.wandrell.pattern.repository.query;

import java.util.Map;

/**
 * Interface for filtering a
 * {@link com.wandrell.pattern.repository.FilteredRepository FilteredRepository}
 * when queries are required.
 * <p>
 * Usually this will be a {@code FilteredRepository} taking data from an SQL
 * database.
 * <p>
 * The query and it's parameters are separated. That way the actual query to be
 * used will be built inside the {@code FilteredRepository}, using whatever API
 * or implementation it requires.
 * <p>
 * Parameters will be used to substitute codes on the query string. Each key
 * should match a code on the string, and the value for that key will be used to
 * create the string which will take the code's place.
 * 
 * @author Bernardo Martínez Garrido
 * @see com.wandrell.pattern.repository.Repository Repository
 */
public interface QueryData {

    /**
     * Adds a parameter.
     * <p>
     * If a parameter with the specified key already exists the new one will
     * take it's place.
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
     * one will take it's place.
     * 
     * @param parameters
     *            {@code Map} with all the parameter pairs
     */
    public void addParameters(final Map<String, Object> parameters);

    /**
     * The parameters to be applied to the query.
     * <p>
     * The parameters are a collection of pairs, where the key is a code in the
     * query, and the value the object which will take that code's place.
     * 
     * @return the query's parameters
     */
    public Map<String, Object> getParameters();

    /**
     * The query for creating a subset of the {@code Repository} entities.
     * 
     * @return the query for acquiring the entities subset
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
