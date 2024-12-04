package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    // configurações para a conexão
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "tudiulisti";
    private static final String URL = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC", HOST, PORT, DB_NAME);

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection = null;
    
    // função para conectar
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Carregar o driver do MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (Exception e) {
                System.out.println("Falha na conexão com o banco de dados.");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void disconnect(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Error disconnection the database", e);
        }
    }
}