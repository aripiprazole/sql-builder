package com.lorenzoog.sqlbuilder.sqlapi;

@FunctionalInterface
public interface ThrowableFunction<T, R> {

    R run(T t) throws Exception;

}
