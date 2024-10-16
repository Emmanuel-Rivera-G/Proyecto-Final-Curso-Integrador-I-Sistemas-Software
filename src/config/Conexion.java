package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String URL = "jdbc:mysql://localhost:";
    private final int PUERTO = 3306;
    private final String NOMBRE_BD = "base_de_datos";
    private final String USUARIO = "tu_usuario";
    private final String CONTRASEÑA = "tu_contraseña";
    
    private final String URL_COMPLETA = URL + PUERTO + "/" + NOMBRE_BD;
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_COMPLETA, USUARIO, CONTRASEÑA);
    }
}
