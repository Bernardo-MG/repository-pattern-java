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
 * Paths to the Hibernate dialect properties files.
 * <p>
 * These files contain the Hibernate dialect to be used for each database
 * vendor.
 *
 * @author Bernardo Martínez Garrido
 */
public class HibernateDialectPropertiesPaths {

    /**
     * H2 Hibernate dialect.
     */
    public static final String H2 = "classpath:config/persistence/hibernate/test-hibernate-dialect-h2.properties";

    /**
     * HSQLDB Hibernate dialect.
     */
    public static final String HSQLDB = "classpath:config/persistence/hibernate/test-hibernate-dialect-hsqldb.properties";

    /**
     * MySQL Hibernate dialect.
     */
    public static final String MYSQL = "classpath:config/persistence/hibernate/test-hibernate-dialect-mysql.properties";

    /**
     * PostgreSQL Hibernate dialect.
     */
    public static final String POSTGRESQL = "classpath:config/persistence/hibernate/test-hibernate-dialect-postgresql.properties";

    /**
     * Private constructor to avoid initialization.
     */
    private HibernateDialectPropertiesPaths() {
        super();
    }

}
