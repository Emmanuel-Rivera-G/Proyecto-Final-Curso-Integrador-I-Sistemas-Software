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
 * @author Lenovo
 */
public class EntradasDAO {
    
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
    //BUSCAR POR ID O NOMBRE DE PRODUCTO
    public List<Producto> filtrarProductoABuscar(String input) {
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
    }
    
    //ARRIBA Solo con interacion producto
    
    //Metodo AGREGAR
    public void agregar (Entrada entrada) throws SQLException {
        String sql = "INSERT INTO entradas(idProductos, nombreProductos, descripcionOperacion, fecha, cantidad, precioUnitario, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, entrada.getIdproducto());
            ps.setString(2, entrada.getNombreproducto());
            ps.setString(3, entrada.getDescoperacion());
            ps.setString(4, entrada.getFecha());
            ps.setInt(5, entrada.getCantidad());
            ps.setDouble(6, entrada.getPreciounitario());
            ps.setDouble(7, entrada.getTotal());
            
            ps.executeUpdate(); // Ejecuta la inserción
        } catch (SQLException e) {
            // Mostrar un mensaje en la consola para diagnóstico
            System.out.println("Error en proceso de agregación EntradasDAO: " + e);
            // Propaga la excepción para que el controlador la maneje
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        
        
    }//TERMINA EL METODO AGREGAR
    
    //metodo listartablaentrada
    public List listarTablaEntrada(){
        String sql = "SELECT * FROM entradas";
        List<Entrada>lista = new ArrayList<>();
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();// devuelve lista de la base de datos productos
            // la lista se guardara en el Result Set "rs"
            //Ahora del RS debemos pasarlo al arraylist mediante un while
            while (rs.next()){//iterara a toda la lista de la base de datos productos
                //hara uso del constructor vacioO
                Entrada entrada = new Entrada();// obj Producto llamado producto// aqui traigo DTO PRODUCTO
                
                entrada.setIdentrada(rs.getInt(1));// 1 por la posicion de columna o nombre columna segun table BD
                entrada.setIdproducto(rs.getInt(2));
                entrada.setNombreproducto(rs.getString(3));
                entrada.setDescoperacion(rs.getString(4));
                entrada.setFecha(rs.getString(5));
                entrada.setCantidad(rs.getInt(6));
                entrada.setPreciounitario(rs.getDouble(7));
                entrada.setTotal(rs.getDouble(8));
                
                
                lista.add(entrada);// carga todas las entradas ARRAYLIST
                
            }
            
            
        } catch (Exception e) {
            System.out.println("Error en listarTablaEntrada: "+e);
        }
        return lista;// retorna la lista cargada
    }
    
    //METODO EDITAR REGISTRO DE ENTRADA
    public void editarEntrd (Entrada entrada){//Producto, recibe como parametro Product
        //String sql="UPDATE entradas SET nombre=?, idCategoria=?, undMedida=?, Stock=? WHERE id_productos=?";
        //String sql="UPDATE entradas SET nombre=?, idCategoria=?, undMedida=?, Stock=? WHERE id_productos=?";
        String sql="UPDATE entradas SET idProductos=?, nombreProductos=?, descripcionOperacion=?, fecha=?, cantidad=?, precioUnitario=?, total=? WHERE id_Entrada=?";
        
        try {
            con=conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            //Se asigna a los comodines ????? sql
            ps.setInt(1, entrada.getIdproducto());
            ps.setString(2,entrada.getNombreproducto());
            ps.setString(3, entrada.getDescoperacion());
            ps.setString(4,entrada.getFecha());
            ps.setInt(5,entrada.getCantidad());
            ps.setDouble(6,entrada.getPreciounitario());
            ps.setDouble(7,entrada.getTotal());
            
            ps.setInt(8,entrada.getIdentrada());
            
            ps.executeUpdate();//Ejecuta una actualizacion modificacion 
            
        } catch (SQLException e) {
            System.out.println("Error en editar entrada - EntradasDAO: " +e);
        }
        
        
    }
    //METODO BORRAR REGISTRO
    public void eliminarEntrd (int id){
        String sql="DELETE FROM entradas WHERE id_Entrada="+id;
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error en eliminarEntrd -EntradasDAO: "+e);
        }
        
    }
  
}
