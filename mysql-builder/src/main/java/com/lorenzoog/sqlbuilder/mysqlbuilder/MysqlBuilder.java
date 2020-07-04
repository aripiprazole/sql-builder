package com.lorenzoog.sqlbuilder.mysqlbuilder;

import com.lorenzoog.sqlbuilder.mysqlbuilder.builder.InsertQueryBuilder;
import com.lorenzoog.sqlbuilder.mysqlbuilder.builder.SelectQueryBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.SqlBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.SqlConnection;
import com.lorenzoog.sqlbuilder.sqlapi.ThrowableFunction;

import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public final class MysqlBuilder implements SqlBuilder {

    private final SqlConnection connection;
    private final String table;

    public MysqlBuilder(SqlConnection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    private String rawSql = null;

    @Override
    public SqlBuilder raw(String rawSql) {
        this.rawSql = rawSql;

        return this;
    }

    @Override
    public SqlBuilder update() {
        return null;
    }

    @Override
    public SqlBuilder alter() {
        return null;
    }

    @Override
    public SqlBuilder union() {
        return null;
    }

    @Override
    public SqlBuilder delete() {
        return null;
    }

    @Override
    public SqlBuilder drop() {
        return null;
    }

    @Override
    public SqlBuilder where(final String column, final String operator, final Object value) {
        return null;
    }

    @Override
    public SqlBuilder where(final String column, final Object value) {
        return null;
    }

    @Override
    public SqlBuilder insert(final String... columns) {
        return new InsertQueryBuilder(connection, table, columns);
    }

    @Override
    public SqlBuilder replace(final String... columns) {
        return null;
    }

    @Override
    public SqlBuilder values(final Object... values) {
        return null;
    }

    @Override
    public SqlBuilder limit(final int limit) {
        return null;
    }

    @Override
    public SqlBuilder select(final String... columns) {
        return new SelectQueryBuilder(connection, table, columns);
    }

    @Override
    public SqlBuilder set(final String column, final Object value) {
        return null;
    }

    @Override
    public SqlBuilder orderBy(final String column) {
        return null;
    }

    @Override
    public SqlBuilder orderByDesc(final String column) {
        return null;
    }

    @Override
    public SqlBuilder groupBy(final String... column) {
        return null;
    }

    @Override
    public <T> CompletableFuture<T> runAsync(final ExecutorService executorService, final ThrowableFunction<ResultSet, T> callback) {
        return null;
    }

    @Override
    public <T> CompletableFuture<T> runAsync(final ThrowableFunction<ResultSet, T> callback) {
        return null;
    }

    @Override
    public <T> T runBlocking(final ThrowableFunction<ResultSet, T> callback) {
        return null;
    }

    @Override
    public CompletableFuture<ResultSet> runAsync(final ExecutorService executorService) {
        return null;
    }

    @Override
    public CompletableFuture<ResultSet> runAsync() {
        return null;
    }

    @Override
    public ResultSet runBlocking() {
        return null;
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public CompletableFuture<Boolean> executeAsync() {
        return null;
    }

    @Override
    public String toString() {
        if (rawSql != null) return rawSql;

        return "";
    }
}
