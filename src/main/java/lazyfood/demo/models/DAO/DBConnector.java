package lazyfood.demo.models.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import lazyfood.demo.configs.*;

class DBConnector {
    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbConfig.driverClassName);
        dataSource.setUrl(dbConfig.dbUrl);
        dataSource.setUsername(dbConfig.username);
        dataSource.setPassword(dbConfig.password);

        // Set the maximum number of connections in the pool
        dataSource.setMaxTotal(20);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // public static void closeConnection(Connection connection) {
    // try {
    // if (connection != null && !connection.isClosed()) {
    // // Close the connection, returning it to the pool
    // connection.close();
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
}
