package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

import static java.lang.String.format;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor() {
        this.properties = getProperties();
        initConnection();
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor();
        String tableName = "table3";
        tableEditor.createTable(tableName);
        tableEditor.addColumn(tableName, "id", "serial");
        tableEditor.addColumn(tableName, "name", "varchar(7)");
        tableEditor.renameColumn(tableName, "name", "name_new");
        tableEditor.dropColumn(tableName, "name_new");
        tableEditor.dropTable(tableName);
        tableEditor.close();
    }

    public void createTable(String tableName) {
        executeSql(format("CREATE TABLE %s()", tableName), tableName);
    }

    public void dropTable(String tableName) {
        executeSql(format("DROP TABLE %s", tableName), tableName);
    }

    public void addColumn(String tableName, String columnName, String type) {
        executeSql(format("ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type), tableName);
    }

    public void dropColumn(String tableName, String columnName) {
        executeSql(format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName), tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        executeSql(format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName), tableName);
    }

    private void executeSql(String sql, String tableName) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            if (!sql.startsWith("DROP TABLE")) {
                System.out.println(getTableScheme(tableName));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    private static Properties getProperties() {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = properties.getProperty("db.url");
        String login = properties.getProperty("db.login");
        String password = properties.getProperty("db.password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}