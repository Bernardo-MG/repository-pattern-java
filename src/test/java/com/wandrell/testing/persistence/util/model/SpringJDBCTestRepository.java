package com.wandrell.testing.persistence.util.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.wandrell.persistence.repository.SpringJDBCRepository;

@org.springframework.stereotype.Repository
public final class SpringJDBCTestRepository extends
        SpringJDBCRepository<JPATestEntity> implements TestEntityRepository {

    @Autowired
    public SpringJDBCTestRepository(final DataSource dataSource) {
        super(JPATestEntity.class, dataSource,
                "UPDATE test_entities SET name=:name",
                "DELETE FROM test_entities WHERE id=:id", "test_entities", "id");
    }

}
