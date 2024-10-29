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
import utils.UtilsLoggerManager;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 * @author Elvis
 */
public class DAOProductoImpl implements DAOProducto {
    private final Logger LOGGER = UtilsLoggerManager.getLogger(DAOProductoImpl.class);
    private final String TABLA = "productos";
    
    Conexion conexion = new Conexion();//llamanos a la clase conexionBD instanciamos
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List<DTOProducto> obtenerTodosLosProductos() {
        String sql = "SELECT * FROM " + TABLA;
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
                dtoProducto.setIdCategoria(rs.getInt(3));
                dtoProducto.setUndMedida(rs.getString(4));
                dtoProducto.setStock(rs.getInt(5));
                
                lista.add(dtoProducto);// aqui se carga todos los productos ARRAYLIST
                
            }
            cerrarConexion();
            
        } catch (Exception e) {
            LOGGER.error("Error en listar en la tabla {} : {}", TABLA, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
        return lista;// retorna la lista cargada
    }
    
    //TERMINA EL METODO LISTAR
    
    //Metodo agregar
    @Override
    public void agregarProducto(DTOProducto producto) {
        String sql = "INSERT INTO " + TABLA + " (nombre,idCategoria,undMedida,Stock) VALUES (?,?,?,?)";
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);
            
            ps.setString(1,producto.getNombre());
            ps.setInt(2,producto.getIdCategoria());
            ps.setString(3,producto.getUndMedida());
            ps.setInt(4,producto.getStock());
            
            ps.executeUpdate();// EJECUTA LA CONSULTA O INSERCION SQL UPDATE
            cerrarConexion();
            
        } catch (SQLException e) {
            LOGGER.error("Error en agregar en la tabla {} : {}", TABLA, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
    }//TERMINA EL METODO AGREGAR

    @Override
    public void actualizarProducto(DTOProducto producto) {
        String sql = "UPDATE " + TABLA + " SET nombre = ?, idCategoria = ?, undMedida = ?, Stock = ? WHERE id = ?;";
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);
            
            ps.setString(1,producto.getNombre());
            ps.setInt(2,producto.getIdCategoria());
            ps.setString(3,producto.getUndMedida());
            ps.setInt(4,producto.getStock());
            
            ps.setInt(5, producto.getIdProducto());
            
            ps.executeUpdate();
            cerrarConexion();
            
        } catch (SQLException e) {
            LOGGER.error("Error en actualizar la tabla {} : {}", TABLA, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public void eliminarProducto(int idProducto) {
        String sql = "DELETE FROM " + TABLA + " WHERE id = ?;";
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idProducto);
            
            ps.executeUpdate();
            cerrarConexion();
        } catch (SQLException e) {
            LOGGER.error("Error en eleminar de la tabla {} : {}", TABLA, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
    }
    
    @Override
    public DTOProducto obtenerProductoPorNombre(String param) {
        String sql = "SELECT * FROM " + TABLA + " WHERE nombre=?;";
        DTOProducto dtoProducto = null;
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);

            ps.setString(1, param);
            rs = ps.executeQuery();

            if (rs.next()) {
                dtoProducto = new DTOProducto();
                dtoProducto.setIdProducto(rs.getInt("id"));
                dtoProducto.setNombre(rs.getString("nombre"));
                dtoProducto.setIdCategoria(rs.getInt("idCategoria"));
                dtoProducto.setUndMedida(rs.getString("undMedida"));
                dtoProducto.setStock(rs.getInt("Stock"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error en obtener un producto por nombre. ", ExceptionUtils.getStackTrace(e));
        } finally {
            cerrarConexion();
        }
        return dtoProducto;
    }

    @Override
    public DTOProducto obtenerProductoPorId(int idProducto) {
        String sql = "SELECT * FROM " + TABLA + " WHERE id=?;";
        DTOProducto dtoProducto = null;
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                dtoProducto = new DTOProducto()
                        .setIdProducto(rs.getInt("id"))
                        .setNombre(rs.getString("nombre"))
                        .setIdCategoria(rs.getInt("idCategoria"))
                        .setUndMedida(rs.getString("undMedida"))
                        .setStock(rs.getInt("Stock"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error en obtener un producto por Id. ", ExceptionUtils.getStackTrace(e));
        } finally {
            cerrarConexion();
        }
        return dtoProducto;
    }
    
    private void cerrarConexion() {
        try {
            con.close();
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Error en cerrar conexión. ", ExceptionUtils.getStackTrace(e));
        }
    }

}
