package com.wandrell.testing.persistence.test.integration.repository;

import org.springframework.test.context.ContextConfiguration;

import com.wandrell.testing.persistence.util.test.repository.AbstractITModifyJPARepository;

@ContextConfiguration(locations = { "classpath:spring/persistence-jpa.xml" })
public final class ITModifyJPARepository extends AbstractITModifyJPARepository {

    public ITModifyJPARepository() {
        super("SELECT entity FROM TestEntity entity WHERE id = :id");
    }

}
