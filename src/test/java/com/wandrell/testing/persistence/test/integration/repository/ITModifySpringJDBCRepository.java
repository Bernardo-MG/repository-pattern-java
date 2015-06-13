package com.wandrell.testing.persistence.test.integration.repository;

import org.springframework.test.context.ContextConfiguration;

import com.wandrell.testing.persistence.util.test.repository.AbstractITModify;

@ContextConfiguration(
        locations = { "classpath:spring/persistence-spring-jdbc.xml" })
public final class ITModifySpringJDBCRepository extends AbstractITModify {

    public ITModifySpringJDBCRepository() {
        super("SELECT * FROM test_entities WHERE id = :id");
    }

}
