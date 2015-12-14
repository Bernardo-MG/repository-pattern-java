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
    public static final String JDBC_H2 = "classpath:context/test/h2/spring-jdbc-h2.xml";
    /**
     * JDBC context using an H2 database for modification tests.
     */
    public static final String JDBC_H2_MODIFY = "classpath:context/test/h2/spring-jdbc-h2-modify.xml";
    /**
     * JDBC context using an HSQLDB database.
     */
    public static final String JDBC_HSQLDB = "classpath:context/test/hsqldb/spring-jdbc-hsqldb.xml";
    /**
     * JDBC context using an HSQLDB database for modification tests.
     */
    public static final String JDBC_HSQLDB_MODIFY = "classpath:context/test/hsqldb/spring-jdbc-hsqldb-modify.xml";
    /**
     * JDBC context using a MySQL database.
     */
    public static final String JDBC_MYSQL = "classpath:context/test/mysql/spring-jdbc-mysql.xml";
    /**
     * JDBC context using a MySQL database for modification tests.
     */
    public static final String JDBC_MYSQL_MODIFY = "classpath:context/test/mysql/spring-jdbc-mysql-modify.xml";
    /**
     * JDBC context using a SQLite database.
     */
    public static final String JDBC_SQLITE = "classpath:context/test/sqlite/spring-jdbc-sqlite.xml";
    /**
     * JDBC context using a SQLite database for modification tests.
     */
    public static final String JDBC_SQLITE_MODIFY = "classpath:context/test/sqlite/spring-jdbc-sqlite-modify.xml";
    /**
     * JPA context using an H2 database and the Eclipselink framework.
     */
    public static final String JPA_ECLIPSELINK_H2 = "classpath:context/test/h2/jpa-eclipselink-h2.xml";
    /**
     * JPA context using an H2 database and the Eclipselink framework for
     * modification tests.
     */
    public static final String JPA_ECLIPSELINK_H2_MODIFY = "classpath:context/test/h2/jpa-eclipselink-h2-modify.xml";
    /**
     * JPA context using an HSQLDB database and the Eclipselink framework.
     */
    public static final String JPA_ECLIPSELINK_HSQLDB = "classpath:context/test/hsqldb/jpa-eclipselink-hsqldb.xml";
    /**
     * JPA context using an HSQLDB database and the Eclipselink framework for
     * modification tests.
     */
    public static final String JPA_ECLIPSELINK_HSQLDB_MODIFY = "classpath:context/test/hsqldb/jpa-eclipselink-hsqldb-modify.xml";
    /**
     * JPA context using a MySQL database and the Eclipselink framework.
     */
    public static final String JPA_ECLIPSELINK_MYSQL = "classpath:context/test/mysql/jpa-eclipselink-mysql.xml";
    /**
     * JPA context using a MySQL database and the Eclipselink framework for
     * modification tests.
     */
    public static final String JPA_ECLIPSELINK_MYSQL_MODIFY = "classpath:context/test/mysql/jpa-eclipselink-mysql-modify.xml";
    /**
     * JPA context using a SQLite database and the Eclipselink framework.
     */
    public static final String JPA_ECLIPSELINK_SQLITE = "classpath:context/test/sqlite/jpa-eclipselink-sqlite.xml";
    /**
     * JPA context using a SQLite database and the Eclipselink framework for
     * modification tests.
     */
    public static final String JPA_ECLIPSELINK_SQLITE_MODIFY = "classpath:context/test/sqlite/jpa-eclipselink-sqlite-modify.xml";
    /**
     * JPA context using an H2 database and the Hibernate framework.
     */
    public static final String JPA_HIBERNATE_H2 = "classpath:context/test/h2/jpa-hibernate-h2.xml";
    /**
     * JPA context using an H2 database and the Hibernate framework for
     * modification tests.
     */
    public static final String JPA_HIBERNATE_H2_MODIFY = "classpath:context/test/h2/jpa-hibernate-h2-modify.xml";
    /**
     * JPA context using an HSQLDB database and the Hibernate framework.
     */
    public static final String JPA_HIBERNATE_HSQLDB = "classpath:context/test/hsqldb/jpa-hibernate-hsqldb.xml";
    /**
     * JPA context using an HSQLDB database and the Hibernate framework for
     * modification tests.
     */
    public static final String JPA_HIBERNATE_HSQLDB_MODIFY = "classpath:context/test/hsqldb/jpa-hibernate-hsqldb-modify.xml";
    /**
     * JPA context using an H2 database and the Hibernate framework.
     */
    public static final String JPA_HIBERNATE_MYSQL = "classpath:context/test/mysql/jpa-hibernate-mysql.xml";
    /**
     * JPA context using an H2 database and the Hibernate framework.
     */
    public static final String JPA_HIBERNATE_MYSQL_MODIFY = "classpath:context/test/mysql/jpa-hibernate-mysql-modify.xml";
    /**
     * JPA context using a SQLite database and the Hibernate framework.
     */
    public static final String JPA_HIBERNATE_SQLITE = "classpath:context/test/sqlite/jpa-hibernate-sqlite.xml";
    /**
     * JPA context using a SQLite database and the Hibernate framework for
     * modification tests.
     */
    public static final String JPA_HIBERNATE_SQLITE_MODIFY = "classpath:context/test/sqlite/jpa-hibernate-sqlite-modify.xml";

    /**
     * Private constructor to avoid initialization.
     */
    private ContextConfig() {
        super();
    }

}
