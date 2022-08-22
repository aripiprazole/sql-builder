package me.devgabi.sqlbuilder.mysqlbuilder.tests.builder;

import me.devgabi.sqlbuilder.mysqlbuilder.MysqlConnection;
import me.devgabi.sqlbuilder.sqlapi.SqlBuilder;
import me.devgabi.sqlbuilder.sqlapi.SqlConnection;
import junit.framework.TestCase;

import java.sql.Connection;

import static org.mockito.Mockito.*;

public final class InsertQueryBuilder extends TestCase {

    public void testShouldBuildSqlStringCorrectly() {
        Connection original = mock(Connection.class);
        SqlConnection connection = new MysqlConnection(original);

        SqlBuilder builder = connection
                .table("users")
                .insert("username", "email")
                .values("Name", "Email");

        assertEquals("INSERT INTO users(username, email) VALUES(?, ?)", builder.toString());
    }

}
