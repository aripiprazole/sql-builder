package me.devgabi.sqlbuilder.mysqlbuilder.builder;

import me.devgabi.sqlbuilder.mysqlbuilder.QueryBuilder;
import me.devgabi.sqlbuilder.mysqlbuilder.object.Where;
import me.devgabi.sqlbuilder.sqlapi.SqlBuilder;
import me.devgabi.sqlbuilder.sqlapi.SqlConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public final class SelectQueryBuilder extends QueryBuilder {

    private final SqlConnection connection;

    private final String table;
    private final String[] columns;
    private final Set<Where> wheres;

    private String orderBy = null;
    private String limit = null;

    public SelectQueryBuilder(final SqlConnection connection, final String table, final String... columns) {
        this.connection = connection;
        this.table = table;
        this.columns = columns;
        this.wheres = new LinkedHashSet<>();
    }

    @Override
    public SqlBuilder where(final String column, final Object value) {
        return where(column, "=", value);
    }

    @Override
    public SqlBuilder where(final String column, final String operator, final Object value) {
        wheres.add(new Where(column, operator, value));

        return this;
    }

    @Override
    public SqlBuilder orderBy(final String column) {
        orderBy = String.format("ORDER BY %s", column);

        return this;
    }

    @Override
    public SqlBuilder orderByDesc(final String column) {
        orderBy = String.format("ORDER BY DESC %s", column);

        return this;
    }

    @Override
    public SqlBuilder limit(final int limit) {
        this.limit = String.format("LIMIT %s", limit);

        return this;
    }

    @Override
    public ResultSet runBlocking() {
        try (PreparedStatement preparedStatement = connection.prepare(this)) {
            int index = 1;

            for (Where where : wheres) {
                preparedStatement.setObject(index, where.getValue());

                index++;
            }

            return preparedStatement.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

            return null;
        }
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT ");

        boolean isFirst = true;

        for (String column : columns) {
            if (!isFirst) stringBuilder.append(", ");

            stringBuilder.append(column);

            isFirst = false;
        }

        stringBuilder.append(" FROM ")
                .append(table);

        isFirst = true;

        for (final Where where : wheres) {
            if (!isFirst) stringBuilder.append(", ");
            else stringBuilder.append(" WHERE ");

            stringBuilder.append(where.getColumn())
                    .append(' ')
                    .append(where.getOperator())
                    .append(' ')
                    .append('?');

            isFirst = false;
        }

        if (orderBy != null) {
            stringBuilder.append(' ')
                    .append(orderBy);
        }

        if (limit != null) {
            stringBuilder.append(' ')
                    .append(limit);
        }

        return stringBuilder.toString();
    }
}
