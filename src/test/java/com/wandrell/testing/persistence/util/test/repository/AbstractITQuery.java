package com.wandrell.testing.persistence.util.test.repository;

import java.util.Collection;
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
import com.wandrell.testing.persistence.util.model.TestEntity;
import com.wandrell.testing.persistence.util.model.TestEntityRepository;

@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractITQuery extends
        AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private TestEntityRepository repository;
    private final String         selectByIdQuery;

    public AbstractITQuery(final String selectByIdQuery) {
        super();

        this.selectByIdQuery = selectByIdQuery;
    }

    @Test
    public final void testGetAll() {
        final Collection<? extends TestEntity> entities;

        entities = repository.getAll();

        Assert.assertEquals(entities.size(), 4);
    }

    @Test
    public final void testGetEntity_Existing() {
        final QueryData query;
        final Map<String, Object> parameters;
        final Integer id;
        JPATestEntity entity;

        id = 1;

        parameters = new LinkedHashMap<>();
        parameters.put("id", id);

        query = new DefaultQueryData(selectByIdQuery, parameters);

        entity = getRepository().getEntity(query);

        Assert.assertEquals(entity.getId(), id);
    }

    @Test
    public final void testGetEntity_NotExisting() {
        final QueryData query;
        final Map<String, Object> parameters;
        final Integer id;
        JPATestEntity entity;

        id = 123;

        parameters = new LinkedHashMap<>();
        parameters.put("id", id);

        query = new DefaultQueryData(selectByIdQuery, parameters);

        entity = getRepository().getEntity(query);

        Assert.assertEquals(entity, null);
    }

    protected final TestEntityRepository getRepository() {
        return repository;
    }

}
