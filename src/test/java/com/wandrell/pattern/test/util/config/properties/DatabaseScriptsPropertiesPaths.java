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

package com.wandrell.pattern.test.util.config.properties;

/**
 * Paths to the database scripts properties files.
 * <p>
 * These files contain the scripts used to set up the test databases.
 *
 * @author Bernardo Martínez Garrido
 */
public class DatabaseScriptsPropertiesPaths {

    /**
     * MSSQL database scripts.
     */
    public static final String MSSQL = "classpath:config/db/script/test-script-mssql.properties";

    /**
     * MySQL database scripts.
     */
    public static final String MYSQL = "classpath:config/db/script/test-script-mysql.properties";

    /**
     * Plain SQL database scripts.
     */
    public static final String PLAIN = "classpath:config/db/script/test-script-plain.properties";

    /**
     * PostgreSQL database scripts.
     */
    public static final String POSTGRESQL = "classpath:config/db/script/test-script-postgresql.properties";

    /**
     * Private constructor to avoid initialization.
     */
    private DatabaseScriptsPropertiesPaths() {
        super();
    }

}
