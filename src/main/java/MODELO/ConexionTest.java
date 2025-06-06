package MODELO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionTest {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Intentar conectar
            String url = "jdbc:mysql://localhost:3306/partidos?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String pass = ""; // si tienes contraseña, ponla aquí
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("¡Conexión exitosa a la BD!");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el driver JDBC. Verifica que el JAR esté en el classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}
