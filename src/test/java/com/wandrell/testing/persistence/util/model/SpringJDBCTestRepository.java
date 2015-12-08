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

package com.wandrell.testing.persistence.util.model;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.repository.SpringJDBCRepository;

/**
 * Test repository based on the {@link SpringJDBCRepository}, and using
 * {@link JPATestEntity} as the stored entities.
 * <p>
 * It is also prepared to work on Spring, to ease setting up the environment.
 * 
 * @author Bernardo Martínez Garrido
 */
@org.springframework.stereotype.Repository
public final class SpringJDBCTestRepository implements TestEntityRepository {

    /**
     * Base repository. This is the actual class to be tested.
     */
    private final FilteredRepository<JPATestEntity, QueryData> baseRepo;

    /**
     * Constructs a {@code SpringJDBCTestRepository}.
     * 
     * @param dataSource
     *            the data source
     */
    @Autowired
    public SpringJDBCTestRepository(final DataSource dataSource) {
        super();

        // Creates the repository to be tested
        baseRepo = new SpringJDBCRepository<JPATestEntity>(JPATestEntity.class,
                dataSource,
                "UPDATE test_entities SET name = :name WHERE id = :id",
                "DELETE FROM test_entities WHERE id = :id", "test_entities",
                "id");
    }

    @Override
    public final void add(final JPATestEntity entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<JPATestEntity> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final Collection<JPATestEntity>
            getCollection(final QueryData query) {
        return getBaseRepository().getCollection(query);
    }

    @Override
    public final JPATestEntity getEntity(final QueryData query) {
        return getBaseRepository().getEntity(query);
    }

    @Override
    public final void remove(final JPATestEntity entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final JPATestEntity entity) {
        getBaseRepository().update(entity);
    }

    private final FilteredRepository<JPATestEntity, QueryData>
            getBaseRepository() {
        return baseRepo;
    }

}
