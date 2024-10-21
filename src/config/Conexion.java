package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    /*
    private final String URL = "jdbc:mysql://localhost:";
    private final int PUERTO = 3306;
    private final String NOMBRE_BD = "db_control_stock";
    private final String USUARIO = "root";
    private final String CONTRASEÑA = "123456";
    
    private final String URL_COMPLETA = URL + PUERTO + "/" + NOMBRE_BD;
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_COMPLETA, USUARIO, CONTRASEÑA);
    }
     */
    // Constantes para la configuración de la base de datos
    private final String URL = "jdbc:mysql://localhost:";
    private final int PUERTO = 3306;  // Puedes cambiar el puerto si es necesario
    private final String NOMBRE_BD = "db_control_stock";  // Nombre de la base de datos
    private final String USUARIO = "root";  // Usuario de la base de datos
    private final String CONTRASEÑA = "123456";  // Contraseña de la base de datos

    // URL completa para la conexión
    private final String URL_COMPLETA = URL + PUERTO + "/" + NOMBRE_BD;

    // Método para obtener la conexión
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_COMPLETA, USUARIO, CONTRASEÑA);
    }

    // Método opcional para cerrar la conexión
    public void cerrarConexion(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al cerrar la conexión.");
        }
    }

}
