package com.wandrell.testing.persistence.test.integration.repository.jpa;

import org.springframework.test.context.ContextConfiguration;

import com.wandrell.testing.persistence.util.test.repository.AbstractITQueryJPARepository;

@ContextConfiguration(locations = { "classpath:spring/persistence-jpa.xml" })
public final class ITQuerySpringJDBCRepository extends
        AbstractITQueryJPARepository {

    public ITQuerySpringJDBCRepository() {
        super();
    }

}
