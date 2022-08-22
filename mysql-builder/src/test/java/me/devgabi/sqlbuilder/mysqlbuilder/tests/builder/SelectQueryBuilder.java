package me.devgabi.sqlbuilder.mysqlbuilder.tests.builder;

import me.devgabi.sqlbuilder.mysqlbuilder.MysqlConnection;
import me.devgabi.sqlbuilder.sqlapi.SqlBuilder;
import me.devgabi.sqlbuilder.sqlapi.SqlConnection;
import junit.framework.TestCase;

import java.sql.Connection;

import static org.mockito.Mockito.mock;

public final class SelectQueryBuilder extends TestCase {

    public void testShouldBuildSqlStringCorrectly() {
        Connection original = mock(Connection.class);
        SqlConnection connection = new MysqlConnection(original);

        SqlBuilder builder = connection
                .table("users")
                .select("username", "email")
                .where("username", "Some Name")
                .where("email", "Some Email")
                .orderBy("email")
                .limit(1);

        assertEquals("SELECT username, email FROM users WHERE username = ?, email = ? ORDER BY email LIMIT 1", builder.toString());
    }

    public void testShouldBuildSqlStringCorrectlyWithoutLimit() {
        Connection original = mock(Connection.class);
        SqlConnection connection = new MysqlConnection(original);

        SqlBuilder builder = connection
                .table("users")
                .select("username", "email")
                .where("username", "Some Name")
                .where("email", "Some Email")
                .orderBy("email");

        assertEquals("SELECT username, email FROM users WHERE username = ?, email = ? ORDER BY email", builder.toString());
    }

    public void testShouldBuildSqlStringCorrectlyWithoutOrderBy() {
        Connection original = mock(Connection.class);
        SqlConnection connection = new MysqlConnection(original);

        SqlBuilder builder = connection
                .table("users")
                .select("username", "email")
                .where("username", "Some Name")
                .where("email", "Some Email")
                .limit(1);

        assertEquals("SELECT username, email FROM users WHERE username = ?, email = ? LIMIT 1", builder.toString());
    }

    public void testShouldBuildSqlStringCorrectlyWithoutWhere() {
        Connection original = mock(Connection.class);
        SqlConnection connection = new MysqlConnection(original);

        SqlBuilder builder = connection
                .table("users")
                .select("username", "email")
                .orderBy("email")
                .limit(1);

        assertEquals("SELECT username, email FROM users ORDER BY email LIMIT 1", builder.toString());
    }

}
