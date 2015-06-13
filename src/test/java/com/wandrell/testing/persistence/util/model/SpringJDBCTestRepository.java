package com.wandrell.testing.persistence.util.model;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.repository.SpringJDBCRepository;

@org.springframework.stereotype.Repository
public final class SpringJDBCTestRepository implements TestEntityRepository {

    private final FilteredRepository<JPATestEntity, QueryData> baseRepo;

    @Autowired
    public SpringJDBCTestRepository(final DataSource dataSource) {
        super();

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
    public final Collection<JPATestEntity> getCollection(final QueryData query) {
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
