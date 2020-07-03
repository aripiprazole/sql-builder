package com.lorenzoog.sqlbuilder.sqlapi;

import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public interface SqlBuilder {

    SqlBuilder update();
    SqlBuilder alter();
    SqlBuilder union();
    SqlBuilder delete();
    SqlBuilder drop();
    SqlBuilder where(String column, String operator, Object value);
    SqlBuilder where(String column, Object value);
    SqlBuilder insert(String... columns);
    SqlBuilder replace(String... columns);
    SqlBuilder select(String... columns);
    SqlBuilder set(String column, Object value);
    SqlBuilder orderBy(String column);
    SqlBuilder orderByDesc(String column);
    SqlBuilder groupBy(String... column);

    <T> CompletableFuture<T> runAsync(ExecutorService executorService, ThrowableFunction<ResultSet, T> callback);
    <T> CompletableFuture<T> runAsync(ThrowableFunction<ResultSet, T> callback);

    <T> T runBlocking(ThrowableFunction<ResultSet, T> callback);

    CompletableFuture<ResultSet> runAsync(ExecutorService executorService);
    CompletableFuture<ResultSet> runAsync();

    ResultSet runBlocking();

    boolean execute();
    CompletableFuture<Boolean> executeAsync();

}
