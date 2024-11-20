package config;

import java.sql.Connection;
import java.sql.DriverManager;
import org.slf4j.Logger;
import utils.UtilsLoggerManager;

/**
 * Clase de configuración para la conexión a la base de datos.
 * Esta clase se encarga de gestionar la conexión a una base de datos MySQL,
 * utilizando SLF4J para registrar eventos de conexión.
 */
public class Conexion {
    /**
     * Logger para registrar información y errores de la conexión.
     */
    private final Logger LOGGER = UtilsLoggerManager.getLogger(Conexion.class);
    
    /**
     * URL base para la conexión a la base de datos.
     */
    private final String URL = "jdbc:mysql://localhost:";
    
    /**
     * Puerto de conexión a la base de datos.
     */
    private final int PUERTO = 3306;
    
    /**
     * Nombre de la base de datos a la que se conectará.
     */
    private final String NOMBRE_BD = "control_stock";
    
    /**
     * Nombre de usuario para la autenticación en la base de datos.
     */
    private final String USUARIO = "root";
    
    /**
     * Contraseña para la autenticación en la base de datos.
     */
    private final String CONTRASEÑA = "123456";
    
    /**
     * URL completa generada a partir de la URL base, el puerto y el nombre de la base de datos.
     */
    private final String URL_COMPLETA = URL + PUERTO + "/" + NOMBRE_BD;
    
    /**
     * Obtiene una conexión a la base de datos.
     * <p>
     * Intenta conectar a la base de datos MySQL especificada en los atributos de
     * clase, y registra el proceso mediante el logger. Si ocurre un error, se
     * registra y devuelve {@code null}.
     * </p>
     *
     * @return un objeto {@link Connection} si la conexión es exitosa; {@code null} si ocurre un error.
     */
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
