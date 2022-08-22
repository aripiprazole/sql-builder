package me.devgabi.sqlbuilder.sqlapi;

public interface SqlProvider<T extends SqlCredentials> {

    SqlConnection createConnection(T credentials);

    SqlConnection createConnection(T credentials, int maxConnections);

}
