package com.wandrell.testing.persistence.util.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TestEntity")
@Table(name = "test_entities")
public final class JPATestEntity implements TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String  name;

    public JPATestEntity() {
        super();
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public final void setName(final String name) {
        this.name = name;
    }

}
