package com.lorenzoog.sqlbuilder.sqlapi;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface SqlConnection {

    SqlBuilder table(String name);

    PreparedStatement prepare(SqlBuilder sqlBuilder);
    PreparedStatement prepare(String statement);

    Connection getOriginalConnection();

}
