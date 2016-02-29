/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 the original author or authors.
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

package com.wandrell.testing.persistence.util.config.properties;

/**
 * Configuration class for the test username and password properties files.
 *
 * @author Bernardo Martínez Garrido
 */
public class UserPropertiesConfig {

    /**
     * Properties file for the default test username and password.
     */
    public static final String DEFAULT  = "classpath:config/db/user-default.properties";
    /**
     * Properties file for the MySQL test username and password.
     */
    public static final String MYSQL    = "classpath:config/db/user-mysql.properties";
    /**
     * Properties file for the Postgres test username and password.
     */
    public static final String POSTGRES = "classpath:config/db/user-postgres.properties";

    /**
     * Private constructor to avoid initialization.
     */
    private UserPropertiesConfig() {
        super();
    }

}
