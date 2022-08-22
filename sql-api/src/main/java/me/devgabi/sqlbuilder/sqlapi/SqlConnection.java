package me.devgabi.sqlbuilder.sqlapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlConnection {

    SqlBuilder table(String name);

    PreparedStatement prepare(SqlBuilder sqlBuilder) throws SQLException;
    PreparedStatement prepare(String statement) throws SQLException;

    Connection getOriginalConnection();

}
