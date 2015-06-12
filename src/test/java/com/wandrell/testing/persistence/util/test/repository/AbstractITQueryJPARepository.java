package com.wandrell.testing.persistence.util.test.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.testing.persistence.util.model.TestEntity;
import com.wandrell.testing.persistence.util.model.TestEntityRepository;

@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractITQueryJPARepository extends
        AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    TestEntityRepository repository;

    public AbstractITQueryJPARepository() {
        super();
    }

    @Test
    public final void testGetAll() {
        final Collection<? extends TestEntity> entities;

        entities = repository.getAll();

        Assert.assertEquals(entities.size(), 4);
    }

}
