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

package com.wandrell.testing.persistence.util.config;

/**
 * Configuration class for the test context files.
 *
 * @author Bernardo Martínez Garrido
 */
public final class ContextConfig {

    /**
     * JDBC context using an H2 database.
     */
    public static final String JDBC_H2 = "classpath:spring/persistence-spring-jdbc-h2.xml";
    /**
     * JDBC context using an H2 database for modification tests.
     */
    public static final String JDBC_H2_MODIFIABLE = "classpath:spring/persistence-spring-jdbc-h2-modify.xml";
    /**
     * JDBC context using a SQLite database.
     */
    public static final String JDBC_SQLITE = "classpath:spring/persistence-spring-jdbc-sqlite.xml";
    /**
     * JDBC context using a SQLite database for modification tests.
     */
    public static final String JDBC_SQLITE_MODIFIABLE = "classpath:spring/persistence-spring-jdbc-sqlite-modify.xml";
    /**
     * JPA context using an H2 database.
     */
    public static final String JPA_H2 = "classpath:spring/persistence-jpa-h2.xml";
    /**
     * JPA context using an H2 database for modification tests.
     */
    public static final String JPA_H2_MODIFIABLE = "classpath:spring/persistence-jpa-h2-modify.xml";
    /**
     * JPA context using a SQLite database.
     */
    public static final String JPA_SQLITE = "classpath:spring/persistence-jpa-sqlite.xml";

    /**
     * Private constructor to avoid initialization.
     */
    private ContextConfig() {
        super();
    }

}
