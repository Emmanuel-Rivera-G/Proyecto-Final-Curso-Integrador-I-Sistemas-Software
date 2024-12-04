package dao.implemetacion;

import com.google.common.base.Preconditions;
import config.Conexion;
import dao.interfaz.DAOCategoria;
import dto.DTOCategoria;
import java.util.List;
import org.slf4j.Logger;
import utils.UtilsLoggerManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Implementación de la interfaz DAOCategoria para la gestión de categorías en la base de datos.
 * Esta clase proporciona las operaciones CRUD sobre la tabla "categorias".
 * 
 * <p>Utiliza conexiones a la base de datos a través de la clase {@link config.Conexion} 
 * y registra errores con el logger {@link org.slf4j.Logger}.
 * </p>
 * 
 * @author Yinyer
 */
public class DAOCategoriaImpl implements DAOCategoria {
    /** Logger para registrar eventos y errores de la clase. */
    private final Logger LOGGER = UtilsLoggerManager.getLogger(DAOCategoriaImpl.class);

    /** Nombre de la tabla en la base de datos para las categorías. */
    private final String TABLA = "categorias";
    
    /** Conexión a la base de datos a través de la clase {@link config.Conexion}. */
    private Conexion conexion = new Conexion();
    
    /** Objeto Connection para gestionar la conexión con la base de datos. */
    private Connection con;
    
    /** Objeto PreparedStatement para ejecutar sentencias SQL preparadas. */
    private PreparedStatement ps;
    
    /** Objeto ResultSet para almacenar los resultados de las consultas SQL. */
    private ResultSet rs;
    
    /**
     * Inserta una nueva categoría en la tabla "categorias".
     * 
     * @param categoria El objeto {@link DTOCategoria} que contiene los datos de la categoría a insertar.
     * @throws NullPointerException si la conexión a la base de datos es nula.
     * 
     * <p>Registra un error en caso de ocurrir una {@link SQLException} durante la inserción.</p>
     */
    @Override
    public void agregarCategoria(DTOCategoria categoria) {
        String sql = "INSERT INTO " + TABLA + " (nombre) VALUES (?);";
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);
            
            ps.setString(1, categoria.getNombre());
            
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Error en agregar en la tabla {} : {}", TABLA, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
    }

    /**
    * Actualiza una categoría en la tabla "categorias" en la base de datos.
    * 
    * <p>Este método recibe un objeto {@link DTOCategoria} y actualiza el campo "nombre"
    * en la tabla correspondiente en la base de datos.</p>
    * 
    * @param categoria El objeto {@link DTOCategoria} que contiene los nuevos datos de la categoría a actualizar.
    * @throws NullPointerException si la conexión a la base de datos es nula.
    * 
    * <p>Si ocurre una {@link SQLException}, el error es registrado en el {@link Logger} con un detalle del stack trace.</p>
    */
    @Override
    public void actualizarCategoria(DTOCategoria categoria) {
        String sql = "UPDATE " + TABLA + " SET nombre = ? WHERE id_categoria = ?;";
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);
            
            ps.setString(1,categoria.getNombre());
            ps.setInt(2,categoria.getIdCategoria());
            
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException e) {
            LOGGER.error("Error en actualizar la tabla {} : {}", TABLA, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
    }

    /**
    * Elimina una categoría de la tabla "categorias" en la base de datos.
    *
    * <p>Este método recibe el ID de una categoría y ejecuta una sentencia SQL para eliminar 
    * el registro correspondiente en la tabla "categorias". Antes de ejecutar la operación, 
    * verifica que la conexión a la base de datos no sea nula usando 
    * {@link Preconditions#checkNotNull}. En caso de que ocurra un error durante la eliminación, 
    * se registra un mensaje de error en el log.</p>
    *
    * @param idCategoria El ID de la categoría a eliminar.
    */
    @Override
    public void eliminarCategoria(int idCategoria) {
        String sql = "DELETE FROM " + TABLA + " WHERE id_categoria = ?;";
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idCategoria);
            
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Error en eleminar de la tabla {} : {}", TABLA, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
    }

    /**
    * Obtiene una categoría de la base de datos por su ID.
    *
    * <p>Este método busca una categoría en la tabla "categorias" utilizando el ID proporcionado. 
    * Si el registro es encontrado, se mapea a un objeto {@link DTOCategoria} y se retorna. 
    * Antes de ejecutar la operación, verifica que la conexión a la base de datos no sea nula 
    * utilizando {@link Preconditions#checkNotNull}. En caso de que ocurra un error durante la 
    * consulta, se registra un mensaje de error en el log.</p>
    *
    * @param idCategoria El ID de la categoría que se desea obtener.
    * @return Un objeto {@link DTOCategoria} si la categoría es encontrada, de lo contrario, 
    *         retorna {@code null}.
    */
    @Override
    public DTOCategoria obtenerCategoriaPorId(int idCategoria) {
        String sql = "SELECT * FROM " + TABLA + " WHERE id_categoria = ?;";
        DTOCategoria dtoCategoria = null;
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                dtoCategoria = new DTOCategoria();
                dtoCategoria.setIdCategoria(rs.getInt("id_categoria"));
                dtoCategoria.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error en obtener una categoria por Id. ", ExceptionUtils.getStackTrace(e));
        }
        return dtoCategoria;
    }

    /**
    * Obtiene una categoría de la base de datos por su nombre.
    *
    * <p>Este método busca una categoría en la tabla "categorias" utilizando el nombre proporcionado. 
    * Si se encuentra un registro con el nombre correspondiente, se mapea a un objeto 
    * {@link DTOCategoria} y se retorna. Antes de ejecutar la operación, verifica que la conexión 
    * a la base de datos no sea nula utilizando {@link Preconditions#checkNotNull}. En caso de que 
    * ocurra un error durante la consulta, se registra un mensaje de error en el log.</p>
    *
    * @param nombre El nombre de la categoría que se desea obtener.
    * @return Un objeto {@link DTOCategoria} si la categoría es encontrada, de lo contrario, 
    *         retorna {@code null}.
    */
    @Override
    public DTOCategoria obtenerCategoriaPorNombre(String nombre) {
        String sql = "SELECT * FROM " + TABLA + " WHERE nombre = ?;";
        DTOCategoria dtoCategoria = null;
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                dtoCategoria = new DTOCategoria();
                dtoCategoria.setIdCategoria(rs.getInt("idCategoria"));
                dtoCategoria.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error en obtener una categoria por nombre. ", ExceptionUtils.getStackTrace(e));
        }
        return dtoCategoria;
    }

    /**
    * Obtiene una lista de todas las categorías almacenadas en la base de datos.
    *
    * <p>Este método ejecuta una consulta en la tabla "categorias" para recuperar todas las categorías 
    * y las retorna en una lista de objetos {@link DTOCategoria}. Antes de ejecutar la operación, 
    * se asegura de que la conexión a la base de datos no sea nula mediante el uso de 
    * {@link Preconditions#checkNotNull}. Si ocurre algún error durante la consulta, 
    * se registra en los logs utilizando SLF4J y se captura la traza de la excepción.</p>
    *
    * @return Una lista de objetos {@link DTOCategoria} que representa todas las categorías de la 
    *         base de datos. Si no hay categorías, se devuelve una lista vacía.
    */
    @Override
    public List<DTOCategoria> obtenerTodosLasCategorias() {
        String sql = "SELECT * FROM " + TABLA;
        List<DTOCategoria> categorias = new ArrayList<>();
        try {
            con = conexion.getConnection();
            Preconditions.checkNotNull(con, "La conexión no debe de ser nula.");
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                DTOCategoria dtoCategoria = new DTOCategoria();
                dtoCategoria.setIdCategoria(rs.getInt(1));
                dtoCategoria.setNombre(rs.getString(2));
                
                categorias.add(dtoCategoria);
            }
            
        } catch (Exception e) {
            LOGGER.error("Error en listar en la tabla {} : {}", TABLA, e.getMessage(), ExceptionUtils.getStackTrace(e));
        }
        return categorias;
    }
    
}
