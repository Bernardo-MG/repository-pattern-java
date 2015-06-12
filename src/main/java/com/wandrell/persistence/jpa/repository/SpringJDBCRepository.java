package com.wandrell.persistence.jpa.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.PersistenceEntity;

public abstract class SpringJDBCRepository<V extends PersistenceEntity>
        implements FilteredRepository<V, QueryData> {

    private final String                     deleteQuery;
    private final QueryData                  getAllQuery;
    private final SimpleJdbcInsert           insert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Class<V>                   type;
    private final String                     updateQuery;

    public SpringJDBCRepository(final Class<V> type,
            final DataSource dataSource, final String updateQuery,
            final String deleteQuery, final String table, final String... keys) {
        super();

        checkNotNull(type, "Received a null pointer as the class type");
        checkNotNull(dataSource, "Received a null pointer as the data source");
        checkNotNull(updateQuery, "Received a null pointer as the update query");
        checkNotNull(deleteQuery, "Received a null pointer as the delete query");
        checkNotNull(table, "Received a null pointer as the table");
        checkNotNull(keys, "Received a null pointer as the key columns");

        this.type = type;

        getAllQuery = new DefaultQueryData(String.format("SELECT * FROM %s",
                table));
        this.updateQuery = updateQuery;
        this.deleteQuery = deleteQuery;

        insert = new SimpleJdbcInsert(dataSource).withTableName(table)
                .usingGeneratedKeyColumns(keys);

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public final void add(final V entity) {
        final BeanPropertySqlParameterSource parameterSource;
        final Number newKey;

        checkNotNull(entity, "Received a null pointer as the entity");
        checkArgument(entity instanceof PersistenceEntity,
                "The entity is not an instance of PersistenceEntity");

        parameterSource = new BeanPropertySqlParameterSource(entity);

        if ((entity.getId() == null) || (entity.getId() < 0)) {
            newKey = getInsert().executeAndReturnKey(parameterSource);

            entity.setId(newKey.intValue());
        } else {
            getTemplate().update(getUpdateQuery(), parameterSource);
        }
    }

    @Override
    public final Collection<V> getAll() {
        return getCollection(getAllValuesQuery());
    }

    @Override
    public final Collection<V> getCollection(final QueryData filter) {
        checkNotNull(filter, "Received a null pointer as the filter");

        return getTemplate().query(filter.getQuery(), filter.getParameters(),
                BeanPropertyRowMapper.newInstance(getType()));
    }

    @Override
    public final V getEntity(final QueryData filter) {
        checkNotNull(filter, "Received a null pointer as the filter");

        return getTemplate().queryForObject(filter.getQuery(),
                filter.getParameters(),
                BeanPropertyRowMapper.newInstance(getType()));
    }

    @Override
    public final void remove(final V entity) {
        final BeanPropertySqlParameterSource parameterSource;

        parameterSource = new BeanPropertySqlParameterSource(entity);

        getTemplate().update(getDeleteQuery(), parameterSource);
    }

    @Override
    public final void update(final V entity) {
        add(entity);
    }

    private final QueryData getAllValuesQuery() {
        return getAllQuery;
    }

    private final String getDeleteQuery() {
        return deleteQuery;
    }

    private final SimpleJdbcInsert getInsert() {
        return insert;
    }

    private final NamedParameterJdbcTemplate getTemplate() {
        return namedParameterJdbcTemplate;
    }

    private final Class<V> getType() {
        return type;
    }

    private final String getUpdateQuery() {
        return updateQuery;
    }

}
