package com.lorenzoog.sqlbuilder.mysqlbuilder.tests.builder;

import com.lorenzoog.sqlbuilder.mysqlbuilder.MysqlConnection;
import com.lorenzoog.sqlbuilder.sqlapi.SqlBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.SqlConnection;
import junit.framework.TestCase;

import java.sql.Connection;

import static org.mockito.Mockito.*;

public final class InsertQueryBuilder extends TestCase {

    public void testShouldBuildSqlStringCorrectly() {
        Connection original = mock(Connection.class);
        SqlConnection connection = new MysqlConnection(original);

        SqlBuilder builder = connection
                .table("users")
                .insert("username")
                .values("Name");

        assertEquals("INSERT INTO users(username) VALUES(?)", builder.toString());
    }

}
