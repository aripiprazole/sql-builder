package com.lorenzoog.sqlbuilder.mysqlbuilder;

import com.lorenzoog.sqlbuilder.sqlapi.SqlBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlConnection implements SqlConnection {

    private final Connection connection;

    MysqlConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SqlBuilder table(String name) {
        return null;
    }

    @Override
    public PreparedStatement prepare(SqlBuilder sqlBuilder) throws SQLException {
        return connection.prepareStatement(sqlBuilder.toString());
    }

    @Override
    public PreparedStatement prepare(String statement) throws SQLException {
        return connection.prepareStatement(statement);
    }

    @Override
    public Connection getOriginalConnection() {
        return connection;
    }
}
