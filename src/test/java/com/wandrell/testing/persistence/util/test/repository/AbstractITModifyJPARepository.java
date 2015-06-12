package com.wandrell.testing.persistence.util.test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.testing.persistence.util.model.JPATestEntity;
import com.wandrell.testing.persistence.util.model.TestEntityRepository;

@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractITModifyJPARepository extends
        AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    TestEntityRepository repository;

    public AbstractITModifyJPARepository() {
        super();
    }

    @Test
    public final void testAdd_Remove() {
        final JPATestEntity entity;
        final Integer size;

        entity = new JPATestEntity();
        entity.setName("test_entity");

        repository.add(entity);

        size = repository.getAll().size();

        Assert.assertEquals(entity.getId(), size);

        repository.remove(entity);

        Assert.assertEquals(repository.getAll().size(), size - 1);
    }

    @Test(dependsOnMethods = { "testAdd_Remove" })
    public final void testUpdate_Remove() {
        final JPATestEntity entity;
        final Integer size;

        entity = new JPATestEntity();
        entity.setName("test_entity");

        repository.update(entity);

        size = repository.getAll().size();

        Assert.assertEquals(entity.getId(), (Integer) (size + 1));

        repository.remove(entity);

        Assert.assertEquals(repository.getAll().size(), size - 1);
    }

}
