package com.wandrell.testing.persistence.util.model;

import org.springframework.stereotype.Repository;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.persistence.repository.JPARepository;

@Repository
public final class JPATestEntityRepository extends JPARepository<JPATestEntity>
        implements TestEntityRepository {

    public JPATestEntityRepository() {
        super(new DefaultQueryData("SELECT entity FROM TestEntity entity"));
    }

}
