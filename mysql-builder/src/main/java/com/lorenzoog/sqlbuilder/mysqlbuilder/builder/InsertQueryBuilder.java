package com.lorenzoog.sqlbuilder.mysqlbuilder.builder;

import com.lorenzoog.sqlbuilder.mysqlbuilder.QueryBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.SqlBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.SqlConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertQueryBuilder extends QueryBuilder {

    private final SqlConnection connection;

    private final String table;
    private final String[] columns;

    private Object[] values;

    public InsertQueryBuilder(SqlConnection connection, String table, String... columns) {
        this.connection = connection;
        this.table = table;
        this.columns = columns;
    }

    @Override
    public SqlBuilder values(Object... values) {
        this.values = values;

        return this;
    }

    @Override
    public ResultSet runBlocking() {
        try(PreparedStatement preparedStatement = connection.prepare(this)) {
            for (int index = 1; index < values.length + 1; index ++) {
                preparedStatement.setObject(index, values[index]);
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

        stringBuilder.append("INSERT INTO")
                .append(table)
                .append('(');

        boolean isFirst = true;

        for(String column : columns) {
            if(!isFirst) stringBuilder.append(", ");

            stringBuilder.append(column);

            isFirst = false;
        }

        stringBuilder.append(") VALUES (");

        isFirst = true;

        for(Object ignored : columns) {
            if(!isFirst) stringBuilder.append(", ");

            stringBuilder.append('?');

            isFirst = false;
        }

        stringBuilder.append(')');

        return stringBuilder.toString();
    }
}
