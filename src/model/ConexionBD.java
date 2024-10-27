/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//para logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.log4j.Logger;
/**
 *
 * @author Elvis
 */
public class ConexionBD {
    
    private static final Logger logger = LoggerFactory.getLogger(ConexionBD.class);
    //private static final Logger log = Logger.logger(ConexionBD.class);
    
    private static final String driver = "com.mysql.cj.jdbc.Driver"; 
    private static final String dbName = "bdlogisticsmanagement";
    private static final String url = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC";//para no cifrar la conexion con mysql false y tomar la zonahoraria user en fechas time
    private static final String usuario = "root"; // usuario comun root
    private static final String clave = "";      // no tiene password

    // Metodo para obtener la conexion.
    public Connection conectarBaseDatos() {
        
        Connection con = null;
        
        try {
            // Establece Conexion
            con = DriverManager.getConnection(url, usuario, clave);
            //System.out.println("Conexion BD exitosa");
            logger.info("Conexion BD exitosa");
        } catch (SQLException e) {
            // Mejor manejo de excepciones
            //System.err.println("Error en conexion BD: " + e.getMessage());
            //e.printStackTrace();
            
            // Manejo de excepciones con logger
            logger.error("Error en conexion BD: {}", e.getMessage());
            logger.debug("Stack trace:", e); // El stack trace (seguimiento pila) lo muestra solo si el nivel de log es DEBUG o menor
            
        }
        return con;
    
    }
}
/*class PruebaaaaDeConexion{
        public static void main (String[] args){
            Connection con;
            ConexionBD conexion = new ConexionBD();
            con = conexion.conectarBaseDatos();
        }
}*/