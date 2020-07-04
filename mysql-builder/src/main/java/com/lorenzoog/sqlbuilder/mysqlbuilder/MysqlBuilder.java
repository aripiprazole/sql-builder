package com.lorenzoog.sqlbuilder.mysqlbuilder;

import com.lorenzoog.sqlbuilder.sqlapi.SqlBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.ThrowableFunction;
import com.sun.istack.internal.Nullable;

import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class MysqlBuilder implements SqlBuilder {

    @Nullable
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
    public SqlBuilder where(String column, String operator, Object value) {
        return null;
    }

    @Override
    public SqlBuilder where(String column, Object value) {
        return null;
    }

    @Override
    public SqlBuilder insert(String... columns) {
        return null;
    }

    @Override
    public SqlBuilder replace(String... columns) {
        return null;
    }

    @Override
    public SqlBuilder values(Object... values) {
        return null;
    }

    @Override
    public SqlBuilder limit(int limit) {
        return null;
    }

    @Override
    public SqlBuilder select(String... columns) {
        return null;
    }

    @Override
    public SqlBuilder set(String column, Object value) {
        return null;
    }

    @Override
    public SqlBuilder orderBy(String column) {
        return null;
    }

    @Override
    public SqlBuilder orderByDesc(String column) {
        return null;
    }

    @Override
    public SqlBuilder groupBy(String... column) {
        return null;
    }

    @Override
    public <T> CompletableFuture<T> runAsync(ExecutorService executorService, ThrowableFunction<ResultSet, T> callback) {
        return null;
    }

    @Override
    public <T> CompletableFuture<T> runAsync(ThrowableFunction<ResultSet, T> callback) {
        return null;
    }

    @Override
    public <T> T runBlocking(ThrowableFunction<ResultSet, T> callback) {
        return null;
    }

    @Override
    public CompletableFuture<ResultSet> runAsync(ExecutorService executorService) {
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
        if(rawSql != null) return rawSql;

        return "";
    }
}
