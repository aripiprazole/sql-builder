package com.lorenzoog.sqlbuilder.mysqlbuilder;

import com.lorenzoog.sqlbuilder.sqlapi.SqlBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlConnection implements SqlConnection {

    private final Connection connection;

    public MysqlConnection(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public SqlBuilder table(final String name) {
        return new MysqlBuilder(this, name);
    }

    @Override
    public PreparedStatement prepare(final SqlBuilder sqlBuilder) throws SQLException {
        return connection.prepareStatement(sqlBuilder.toString());
    }

    @Override
    public PreparedStatement prepare(final String statement) throws SQLException {
        return connection.prepareStatement(statement);
    }

    @Override
    public Connection getOriginalConnection() {
        return connection;
    }
}
