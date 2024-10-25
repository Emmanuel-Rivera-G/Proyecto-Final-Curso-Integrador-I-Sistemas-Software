package dao.implemetacion;
import com.google.common.base.Preconditions;
import config.Conexion;
import dao.interfaz.DAOProducto;
import dto.DTOProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import utils.UtilLoggerManager;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 * @author Elvis
 */
public class DAOProductoImpl implements DAOProducto {
    private final Logger LOGGER = UtilLoggerManager.getLogger(DAOProductoImpl.class);
    
    Conexion conexion = new Conexion();//llamanos a la clase conexionBD instanciamos
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<DTOProducto> obtenerTodosLosProductos() {
        String tabla = "productos";
        String sql = "SELECT * FROM " + tabla;
        List<DTOProducto> lista = new ArrayList<>();
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();// devuelve lista de la base de datos productos
            // la lista se guardara en el Result Set "rs"
            //Ahora del RS debemos pasarlo al arraylist mediante un while
            while (rs.next()){//iterara a toda la lista de la base de datos productos
                //hara uso del constructor vacioO
                DTOProducto dtoProducto = new DTOProducto();// obj Producto llamado producto// aqui traigo DTO PRODUCTO
                dtoProducto.setIdProducto(rs.getInt(1));// referencia por la posicion de la columna tambien puede ir el nombre de la columna BD
                dtoProducto.setNombre(rs.getString(2));
                dtoProducto.setIdCategoría(rs.getInt(3));
                dtoProducto.setUndMedida(rs.getString(4));
                dtoProducto.setStock(rs.getInt(5));
                
                lista.add(dtoProducto);// aqui se carga todos los productos ARRAYLIST
                
            }
            con.close();
            
        } catch (Exception e) {
            LOGGER.error("Error en listar en la tabla {} : {}", tabla, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
        return lista;// retorna la lista cargada
    }
    
    //TERMINA EL METODO LISTAR
    
    //Metodo agregar
    public void agregarProducto(DTOProducto producto) {
        String tabla = "productos";
        String sql = "INSERT INTO " + tabla + " (nombre,idCategoria,undMedida,Stock) VALUES (?,?,?,?)";
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);
            
            ps.setString(1,producto.getNombre());
            ps.setInt(2,producto.getIdCategoría());
            ps.setString(3,producto.getUndMedida());
            ps.setInt(4,producto.getStock());
            
            ps.executeUpdate();// EJECUTA LA CONSULTA O INSERCION SQL UPDATE
            con.close();
            
        } catch (SQLException e) {
            LOGGER.error("Error en agregar en la tabla {} : {}", tabla, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
    }//TERMINA EL METODO AGREGAR

    @Override
    public void actualizarProducto(DTOProducto producto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarProducto(long idProducto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DTOProducto obtenerProductoPorId(long idProducto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
