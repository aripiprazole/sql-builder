package me.devgabi.sqlbuilder.mysqlbuilder.builder;

import me.devgabi.sqlbuilder.mysqlbuilder.QueryBuilder;
import me.devgabi.sqlbuilder.sqlapi.SqlBuilder;
import me.devgabi.sqlbuilder.sqlapi.SqlConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class InsertQueryBuilder extends QueryBuilder {

    private final SqlConnection connection;

    private final String table;
    private final String[] columns;

    private Object[] values;

    public InsertQueryBuilder(final SqlConnection connection, final String table, final String... columns) {
        this.connection = connection;
        this.table = table;
        this.columns = columns;
    }

    @Override
    public SqlBuilder values(final Object... values) {
        this.values = values;

        return this;
    }

    @Override
    public ResultSet runBlocking() {
        try (final PreparedStatement preparedStatement = connection.prepare(this)) {
            for (int index = 0; index < values.length; index++) {
                preparedStatement.setObject(index + 1, values[index]);
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

        stringBuilder.append("INSERT INTO ")
                .append(table)
                .append('(');

        boolean isFirst = true;

        for (final String column : columns) {
            if (!isFirst) stringBuilder.append(", ");

            stringBuilder.append(column);

            isFirst = false;
        }

        stringBuilder.append(") VALUES(");

        isFirst = true;

        for (final Object ignored : columns) {
            if (!isFirst) stringBuilder.append(", ");

            stringBuilder.append('?');

            isFirst = false;
        }

        stringBuilder.append(')');

        return stringBuilder.toString();
    }
}
