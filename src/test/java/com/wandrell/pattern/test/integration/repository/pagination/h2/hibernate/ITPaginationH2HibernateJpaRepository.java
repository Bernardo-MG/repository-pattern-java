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

package com.wandrell.pattern.test.integration.repository.pagination.h2.hibernate;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.wandrell.pattern.test.util.config.context.PersistenceContextPaths;
import com.wandrell.pattern.test.util.config.context.RepositoryContextPaths;
import com.wandrell.pattern.test.util.config.context.TestContextPaths;
import com.wandrell.pattern.test.util.config.properties.DatabaseScriptsPropertiesPaths;
import com.wandrell.pattern.test.util.config.properties.HibernateDialectPropertiesPaths;
import com.wandrell.pattern.test.util.config.properties.JdbcPropertiesPaths;
import com.wandrell.pattern.test.util.config.properties.JpaPropertiesPaths;
import com.wandrell.pattern.test.util.config.properties.PersistenceProviderPropertiesPaths;
import com.wandrell.pattern.test.util.config.properties.QueryPropertiesPaths;
import com.wandrell.pattern.test.util.config.properties.RepositoryPropertiesPaths;
import com.wandrell.pattern.test.util.config.properties.UserPropertiesPaths;
import com.wandrell.pattern.test.util.test.integration.repository.pagination.AbstractITPagination;

/**
 * Integration tests checking pagination for
 * {@link com.wandrell.pattern.repository.jpa.JpaRepository JPARepository}
 * implementing {@code AbstractITModify}, using an H2 in-memory database and
 * Hibernate-based JPA.
 *
 * @author Bernardo Martínez Garrido
 * @see com.wandrell.pattern.repository.jpa.JpaRepository JPARepository
 */
@ContextConfiguration(locations = { TestContextPaths.DEFAULT,
        PersistenceContextPaths.HIBERNATE, RepositoryContextPaths.JPA })
@TestPropertySource(locations = { QueryPropertiesPaths.JPA_QUERY,
        RepositoryPropertiesPaths.JPA,
        PersistenceProviderPropertiesPaths.HIBERNATE,
        UserPropertiesPaths.DEFAULT, DatabaseScriptsPropertiesPaths.MSSQL,
        JdbcPropertiesPaths.H2, JpaPropertiesPaths.H2,
        HibernateDialectPropertiesPaths.H2 }, properties = {
                "jpa.persistenceUnitName=test_model_jpa_hibernate_h2",
                "jdbc.url=jdbc:h2:mem:test_jpa_hibernate;DB_CLOSE_ON_EXIT=FALSE" })
public final class ITPaginationH2HibernateJpaRepository
        extends AbstractITPagination {

    /**
     * Default constructor.
     */
    public ITPaginationH2HibernateJpaRepository() {
        super();
    }

}
