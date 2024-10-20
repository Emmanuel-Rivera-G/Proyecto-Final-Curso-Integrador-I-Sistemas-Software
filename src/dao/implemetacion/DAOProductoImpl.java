package dao.implemetacion;
import config.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.Producto;

/**
 *
 * @author Elvis
 */
public class DAOProductoImpl {
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
    public void agregar (Producto producto){
        String sql = "INSERT INTO productos(nombre,idCategoria,undMedida,Stock) VALUES (?,?,?,?)";
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.setString(1,producto.getNombre());
            ps.setInt(2,producto.getIdcategoria());
            ps.setString(3,producto.getUndmedida());
            ps.setInt(4,producto.getStock());
            
            ps.executeUpdate();// EJECUTA LA CONSULTA O INSERCION SQL UPDATE
            
        } catch (SQLException e) {
            System.out.println("Error en proceso de agregacion productoDAO: "+e);
        }
    }//TERMINA EL METODO AGREGAR
    
    
}
