package com.lorenzoog.sqlbuilder.mysqlbuilder.builder;

import com.lorenzoog.sqlbuilder.mysqlbuilder.QueryBuilder;
import com.lorenzoog.sqlbuilder.mysqlbuilder.object.Where;
import com.lorenzoog.sqlbuilder.sqlapi.SqlBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.SqlConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SelectQueryBuilder extends QueryBuilder {

    private String rawSql = "";

    private final SqlConnection connection;

    private final String table;
    private final String[] columns;
    private final Set<Where> wheres;

    private String orderBy = null;
    private String limit = null;

    public SelectQueryBuilder(SqlConnection connection, String table, String... columns) {
        this.connection = connection;
        this.table = table;
        this.columns = columns;
        this.wheres = new HashSet<>();
    }

    @Override
    public SqlBuilder where(String column, Object value) {
        return where(column, "=", value);
    }

    @Override
    public SqlBuilder where(String column, String operator, Object value) {
        wheres.add(new Where(column, operator, value));

        return this;
    }

    @Override
    public SqlBuilder orderBy(String column) {
        orderBy = String.format("ORDER BY %s", column);

        return this;
    }

    @Override
    public SqlBuilder orderByDesc(String column) {
        orderBy = String.format("ORDER BY DESC %s", column);

        return this;
    }

    @Override
    public SqlBuilder limit(int limit) {
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
        StringBuilder stringBuilder = new StringBuilder();

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

        for (Where where : wheres) {
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
