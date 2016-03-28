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

package com.wandrell.pattern.test.integration.repository.access.hsqldb.eclipselink;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.wandrell.pattern.test.util.config.context.PersistenceContextConfig;
import com.wandrell.pattern.test.util.config.context.RepositoryContextConfig;
import com.wandrell.pattern.test.util.config.context.TestContextConfig;
import com.wandrell.pattern.test.util.config.properties.DatabaseScriptsPropertiesConfig;
import com.wandrell.pattern.test.util.config.properties.JDBCPropertiesConfig;
import com.wandrell.pattern.test.util.config.properties.JPAPropertiesConfig;
import com.wandrell.pattern.test.util.config.properties.PersistenceProviderPropertiesConfig;
import com.wandrell.pattern.test.util.config.properties.QueryPropertiesConfig;
import com.wandrell.pattern.test.util.config.properties.RepositoryPropertiesConfig;
import com.wandrell.pattern.test.util.config.properties.UserPropertiesConfig;
import com.wandrell.pattern.test.util.test.integration.repository.access.AbstractITQuery;

/**
 * Integration tests for
 * {@link com.wandrell.pattern.repository.jpa.JPARepository JPARepository}
 * implementing {@code AbstractITQuery}, using an HSQLDB in-memory database and
 * Eclipselink-based JPA.
 *
 * @author Bernardo Martínez Garrido
 * @see com.wandrell.pattern.repository.jpa.JPARepository JPARepository
 */
@ContextConfiguration(locations = { TestContextConfig.DEFAULT,
        PersistenceContextConfig.ECLIPSELINK, RepositoryContextConfig.JPA })
@TestPropertySource(
        locations = { QueryPropertiesConfig.JPA_QUERY,
                RepositoryPropertiesConfig.JPA,
                PersistenceProviderPropertiesConfig.ECLIPSELINK,
                UserPropertiesConfig.DEFAULT,
                DatabaseScriptsPropertiesConfig.MSSQL,
                JDBCPropertiesConfig.HSQLDB, JPAPropertiesConfig.HSQLDB },
        properties = {
                "jpa.persistenceUnitName=test_model_jpa_eclipselink_hsqldb",
                "jdbc.url=jdbc:hsqldb:mem:test_jpa_eclipselink" })
public final class ITQueryHSQLDBEclipselinkJPARepository
        extends AbstractITQuery {

    /**
     * Default constructor.
     */
    public ITQueryHSQLDBEclipselinkJPARepository() {
        super();
    }

}