# sql-builder

This project consists in a simple query builder for Java, and future I will build some extensions for Kotlin.

### My points in create the project

- Make easier mock data
- Make easier change dialects
- Make development easier
- Fun :)

### Examples

Fetching data:

```java

@AllArgsConstructor
@Getter
public final class User {
    private String email, password;
}

public final class Main { 
    public static void main(String[] args) {
        SqlConnection connection = new MysqlConnection();

        CompleatableFuture<User> userFuture = sqlConnection.table("users")
                .select("email", "password")
                .where("email", "your-email@domain.com")
                .limit(1)
                .runAsync(resultSet -> new User(
                    resultSet.getString("email"),
                    resultSet.getString("password")
                ));
    }
}

```

Schemas:

```java
public final class Main { 
    public static void main(String[] args) {
        SqlConnection connection = new MysqlConnection();
        SqlSchema schema = connection.schema();

        schema.create("users", builder -> {
            builder.name("email").varchar(32).primaryKey();
            builder.name("password").varchar(72);
        }).run();

        schema.drop("users").run();
    }
}

```
