package com.wandrell.testing.persistence.util.model;

import com.wandrell.persistence.PersistenceEntity;

public interface TestEntity extends PersistenceEntity {

    public String getName();

    public void setName(final String name);

}
