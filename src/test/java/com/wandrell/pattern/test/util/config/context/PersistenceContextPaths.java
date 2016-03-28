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

package com.wandrell.pattern.test.util.config.context;

/**
 * Paths to the persistence context files.
 * <p>
 * Each of these files can be used to create a Spring context for a specific
 * persistence provider.
 *
 * @author Bernardo Martínez Garrido
 */
public class PersistenceContextPaths {

    /**
     * Eclipselink JPA persistence.
     */
    public static final String ECLIPSELINK = "classpath:context/persistence/jpa-eclipselink.xml";

    /**
     * Hibernate JPA persistence.
     */
    public static final String HIBERNATE = "classpath:context/persistence/jpa-hibernate.xml";

    /**
     * Spring JDBC persistence.
     */
    public static final String SPRING_JDBC = "classpath:context/persistence/spring-jdbc.xml";

    /**
     * Private constructor to avoid initialization.
     */
    private PersistenceContextPaths() {
        super();
    }

}
