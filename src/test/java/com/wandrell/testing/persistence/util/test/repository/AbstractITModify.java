package com.wandrell.testing.persistence.util.test.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.testing.persistence.util.model.JPATestEntity;
import com.wandrell.testing.persistence.util.model.TestEntityRepository;

@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractITModify extends
        AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private TestEntityRepository repository;
    private final String         selectByIdQuery;

    public AbstractITModify(final String selectByIdQuery) {
        super();

        this.selectByIdQuery = selectByIdQuery;
    }

    @Test
    public final void testAdd_Remove() {
        final JPATestEntity entity;
        final Integer size;

        entity = new JPATestEntity();
        entity.setName("test_entity");

        getRepository().add(entity);

        size = getRepository().getAll().size();

        Assert.assertEquals(entity.getId(), size);

        getRepository().remove(entity);

        Assert.assertEquals(repository.getAll().size(), size - 1);
    }

    @Test
    public final void testRemove_NotPersisted() {
        final JPATestEntity entity;
        final Integer size;

        entity = new JPATestEntity();
        entity.setName("test_entity");

        size = getRepository().getAll().size();

        getRepository().remove(entity);

        Assert.assertEquals((Integer) repository.getAll().size(), size);
    }

    @Test
    public final void testUpdate() {
        final QueryData query;
        final Map<String, Object> parameters;
        final String nameChange;
        JPATestEntity entity;

        parameters = new LinkedHashMap<>();
        parameters.put("id", 1);

        query = new DefaultQueryData(selectByIdQuery, parameters);

        entity = getRepository().getEntity(query);

        nameChange = "The new name";
        entity.setName(nameChange);

        getRepository().update(entity);

        entity = getRepository().getEntity(query);

        Assert.assertEquals(entity.getName(), nameChange);
    }

    protected final TestEntityRepository getRepository() {
        return repository;
    }

}
