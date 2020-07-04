package com.lorenzoog.sqlbuilder.mysqlbuilder;

import com.lorenzoog.sqlbuilder.sqlapi.SqlBuilder;
import com.lorenzoog.sqlbuilder.sqlapi.ThrowableFunction;

import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public abstract class QueryBuilder implements SqlBuilder {
    private static final ExecutorService asyncPool = ForkJoinPool.commonPool();

    @Override
    public SqlBuilder raw(String rawSql) {
        return null;
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
    public SqlBuilder values(Object... values) {
        return null;
    }

    @Override
    public SqlBuilder replace(String... columns) {
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
    public final <T> CompletableFuture<T> runAsync(ExecutorService executorService, ThrowableFunction<ResultSet, T> callback) {
        return runAsync().thenApplyAsync(resultSet -> {
            try {
                return callback.run(resultSet);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return null;
        }, executorService);
    }

    @Override
    public final <T> CompletableFuture<T> runAsync(ThrowableFunction<ResultSet, T> callback) {
        return runAsync(asyncPool, callback);
    }

    @Override
    public final <T> T runBlocking(ThrowableFunction<ResultSet, T> callback) {
        try {
            return callback.run(runBlocking());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public final CompletableFuture<ResultSet> runAsync(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(this::runBlocking, executorService);
    }

    @Override
    public final CompletableFuture<ResultSet> runAsync() {
        return runAsync(asyncPool);
    }

    @Override
    public final CompletableFuture<Boolean> executeAsync() {
        return CompletableFuture.supplyAsync(this::execute);
    }

    @Override
    public ResultSet runBlocking() {
        return null;
    }

    @Override
    public boolean execute() {
        return false;
    }
}
