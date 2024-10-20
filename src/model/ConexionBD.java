/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Elvis
 */
public class ConexionBD {
    
    private static final String driver = "com.mysql.cj.jdbc.Driver"; 
    private static final String dbName = "bdlogisticsmanagement";
    private static final String url = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC";//para no cifrar la conexion con mysql false y tomar la zonahoraria user en fechas time
    private static final String usuario = "root"; // usuario comun root
    private static final String clave = "";      // no tiene password

    // Metodo para obtener la conexion
    public Connection conectarBaseDatos() {
        
        Connection con = null;
        
        try {
            // Establece conexion
            con = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexion BD exitosa");
        } catch (SQLException e) {
            // Mejor manejo de excepciones
            System.err.println("Error en conexion BD: " + e.getMessage());
            e.printStackTrace();
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