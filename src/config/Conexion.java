package config;

import java.sql.Connection;
import java.sql.DriverManager;
import org.slf4j.Logger;
import utils.UtilLoggerManager;

public class Conexion {
    private final Logger LOGGER = UtilLoggerManager.getLogger(Conexion.class);
    
    private final String URL = "jdbc:mysql://localhost:";
    private final int PUERTO = 3306;
    private final String NOMBRE_BD = "control_stock";
    private final String USUARIO = "root";
    private final String CONTRASEÑA = "123456";
    
    private final String URL_COMPLETA = URL + PUERTO + "/" + NOMBRE_BD;
    
    public Connection getConnection() {
        try {
            LOGGER.info("Intentando conectar a la base de datos {} en el puerto {}", NOMBRE_BD, PUERTO);
            Connection con = DriverManager.getConnection(URL_COMPLETA, USUARIO, CONTRASEÑA);
            LOGGER.info("Conexión a la base de datos {} establecida correctamente.", NOMBRE_BD);
            return con;
        } catch (Exception e) {
            LOGGER.error("Error al conectar a la base de datos {}: {}", NOMBRE_BD, e.getMessage(), e);
            return null;
        }
    }
}
