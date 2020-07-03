package com.lorenzoog.sqlbuilder.sqlapi;

@FunctionalInterface
public interface ThrowableFunction<R, T> {

    R run(T t);

}
