package com.lorenzoog.sqlbuilder.sqlapi;

@FunctionalInterface
public interface ThrowableFunction<T, R> {

    R run(final T t) throws Exception;

}
