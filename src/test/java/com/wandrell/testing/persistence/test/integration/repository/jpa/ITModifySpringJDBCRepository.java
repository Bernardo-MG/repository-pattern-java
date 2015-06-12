package com.wandrell.testing.persistence.test.integration.repository.jpa;

import org.springframework.test.context.ContextConfiguration;

import com.wandrell.testing.persistence.util.test.repository.AbstractITModifyJPARepository;

@ContextConfiguration(
        locations = { "classpath:spring/persistence-spring-jdbc.xml" })
public final class ITModifySpringJDBCRepository extends
        AbstractITModifyJPARepository {

    public ITModifySpringJDBCRepository() {
        super();
    }

}
