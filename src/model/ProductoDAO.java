/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;//------throw
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Elvis
 */
public class ProductoDAO {
    ConexionBD conexion = new ConexionBD();//llamanos a la clase conexionBD instanciamos
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        String sql = "SELECT * FROM productos";
        List<Producto>lista = new ArrayList<>();
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();// devuelve lista de la base de datos productos
            // la lista se guardara en el Result Set "rs"
            //Ahora del RS debemos pasarlo al arraylist mediante un while
            while (rs.next()){//iterara a toda la lista de la base de datos productos
                //hara uso del constructor vacioO
                Producto producto = new Producto();// obj Producto llamado producto// aqui traigo DTO PRODUCTO
                producto.setId(rs.getInt(1));// referencia por la posicion de la columna tambien puede ir el nombre de la columna BD
                producto.setNombre(rs.getString(2));
                producto.setIdcategoria(rs.getInt(3));
                producto.setUndmedida(rs.getString(4));
                producto.setStock(rs.getInt(5));
                
                lista.add(producto);// aqui se carga todos los productos ARRAYLIST
                
            }
            
            
        } catch (Exception e) {
            System.out.println("Error en listar: "+e);
        }
        return lista;// retorna la lista cargada
    }
    
    //TERMINA EL METODO LISTAR
    
    //Metodo agregar
    public void agregar (Producto producto) throws SQLException {// quitar el trow9999999999999999999999999999999999999999999999999999999999999999
        String sql = "INSERT INTO productos(nombre, idCategoria, undMedida, Stock) VALUES (?, ?, ?, ?)";
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getIdcategoria());
            ps.setString(3, producto.getUndmedida());
            ps.setInt(4, producto.getStock());

            ps.executeUpdate(); // Ejecuta la inserción
        } catch (SQLException e) {
            // Mostrar un mensaje en la consola para diagnóstico
            System.out.println("Error en proceso de agregación productoDAO: " + e);
            // Propaga la excepción para que el controlador la maneje
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        
        /*String sql = "INSERT INTO productos(nombre,idCategoria,undMedida,Stock) VALUES (?,?,?,?)";
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.setString(1,producto.getNombre());
            ps.setInt(2,producto.getIdcategoria());
            ps.setString(3,producto.getUndmedida());
            ps.setInt(4,producto.getStock());
            
            ps.executeUpdate();// EJECUTA LA CONSULTA O INSERCION SQL UPDATE
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"YA EXISTE EL PRODUCTO","ERROR", JOptionPane.ERROR_MESSAGE);
            //throw e;
            System.out.println("Error en proceso de agregacion productoDAO: "+e);
        }*/
    }//TERMINA EL METODO AGREGAR
    
    //METODO ACTUALIZAR
    public void actualizar (Producto producto){//Producto, recibe como parametro Product
        String sql="UPDATE productos SET nombre=?, idCategoria=?, undMedida=?, Stock=? WHERE id_productos=?";
        try {
            con=conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            //Se asigna a los comodines ????? sql
            ps.setString(1, producto.getNombre());
            ps.setInt(2,producto.getIdcategoria());
            ps.setString(3, producto.getUndmedida());
            ps.setInt(4,producto.getStock());
            
            ps.setInt(5,producto.getId());
            
            ps.executeUpdate();//Ejecuta una actualizacion modificacion 
            
        } catch (SQLException e) {
            System.out.println("Error en actualizar - productoDAO: " +e);
        }
        
        
    }
    
    //METODO BORRAR
    public void eliminar (int id){
        String sql="DELETE FROM productos WHERE id_productos="+id;
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error en eliminar -productoDAO: "+e);
        }
        
    }
    
    //FILTRAR ID o nombre de producto
    
    public List<Producto> filtrarProducto(String input) {
        String sql = "SELECT * FROM productos WHERE nombre LIKE ? OR CAST(id_productos AS CHAR) LIKE ?";
        List<Producto> lista = new ArrayList<>();
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            // Utilizamos el mismo valor para ambas condiciones (nombre e id_productos)
            ps.setString(1, "%" + input + "%");
            ps.setString(2, "%" + input + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id_productos"));
                producto.setNombre(rs.getString("nombre"));
                producto.setIdcategoria(rs.getInt("idCategoria"));
                producto.setUndmedida(rs.getString("undMedida"));
                producto.setStock(rs.getInt("stock"));
                lista.add(producto);
            }
        } catch (Exception e) {
            System.out.println("Error en filtrarProducto: " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e);
            }
        }
        return lista;
        
        /*List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE nombre LIKE ?";
                
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + input + "%"); // Usamos % para que coincida parcialmente
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id_productos"));
                producto.setNombre(rs.getString("nombre"));
                producto.setIdcategoria(rs.getInt("idCategoria"));
                producto.setUndmedida(rs.getString("undMedida"));
                producto.setStock(rs.getInt("stock"));
                lista.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error en proceso de filtrado: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return lista;
        */
    }
    
    
}
