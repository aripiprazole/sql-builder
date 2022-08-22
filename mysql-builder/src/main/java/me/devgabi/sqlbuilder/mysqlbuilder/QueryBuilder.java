package me.devgabi.sqlbuilder.mysqlbuilder;

import me.devgabi.sqlbuilder.sqlapi.SqlBuilder;
import me.devgabi.sqlbuilder.sqlapi.ThrowableFunction;

import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
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
    public SqlBuilder where(final String column, final String operator, Object value) {
        return null;
    }

    @Override
    public SqlBuilder where(final String column, final Object value) {
        return null;
    }

    @Override
    public SqlBuilder insert(final String... columns) {
        return null;
    }

    @Override
    public SqlBuilder values(final Object... values) {
        return null;
    }

    @Override
    public SqlBuilder replace(final String... columns) {
        return null;
    }

    @Override
    public SqlBuilder limit(final int limit) {
        return null;
    }

    @Override
    public SqlBuilder select(final String... columns) {
        return null;
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
    public final <T> CompletableFuture<T> runAsync(final ExecutorService executorService, final ThrowableFunction<ResultSet, T> callback) {
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
    public final <T> CompletableFuture<T> runAsync(final ThrowableFunction<ResultSet, T> callback) {
        return runAsync(asyncPool, callback);
    }

    @Override
    public final <T> T runBlocking(final ThrowableFunction<ResultSet, T> callback) {
        try {
            return callback.run(runBlocking());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public final CompletableFuture<ResultSet> runAsync(final ExecutorService executorService) {
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
