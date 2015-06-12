package com.wandrell.testing.persistence.util.model;

import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;

public interface TestEntityRepository extends
        FilteredRepository<JPATestEntity, QueryData> {

}
