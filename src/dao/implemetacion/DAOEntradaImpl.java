package dao.implemetacion;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.Categoria;
import model.Entrada;
import model.Producto;

/**
 * Clase encargada de gestionar las operaciones de base de datos relacionadas con las entradas y productos.
 * Permite realizar operaciones CRUD sobre las tablas `productos` y `entradas`.
 * 
 * @author Elvis
 */
public class DAOEntradaImpl {
    
    private final Conexion conexion = new Conexion(); // Instancia de la clase Conexion para manejar la base de datos.
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Obtiene una lista de todos los productos en la tabla `productos`.
     *
     * @return una lista de objetos {@link Producto} que contiene los productos disponibles en la base de datos.
     */
    public List<Producto> listar() {
        String sql = "SELECT * FROM productos";
        List<Producto> lista = new ArrayList<>();
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt(1)); // ID del producto
                producto.setNombre(rs.getString(2)); // Nombre del producto
                Categoria cat = new Categoria();
                cat.setIdcat(rs.getInt(3)); // ID de la categoría
                producto.setCategoria(cat);
                producto.setUndmedida(rs.getString(4)); // Unidad de medida
                producto.setStock(rs.getInt(5)); // Stock disponible
                lista.add(producto); // Agregar producto a la lista
            }
        } catch (Exception e) {
            System.out.println("Error en listar: " + e);
        }
        return lista;
    }

    /**
     * Filtra productos según el nombre o ID proporcionado.
     *
     * @param input texto de búsqueda que puede coincidir con el nombre o ID del producto.
     * @return una lista de objetos {@link Producto} que coinciden con el criterio de búsqueda.
     */
    public List<Producto> filtrarProductoABuscar(String input) {
        String sql = "SELECT * FROM productos WHERE nombre LIKE ? OR CAST(id AS CHAR) LIKE ?";
        List<Producto> lista = new ArrayList<>();
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + input + "%");
            ps.setString(2, "%" + input + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                Categoria cat = new Categoria();
                cat.setIdcat(rs.getInt("idCategoria"));
                producto.setCategoria(cat);
                producto.setUndmedida(rs.getString("undMedida"));
                producto.setStock(rs.getInt("stock"));
                lista.add(producto);
            }
        } catch (Exception e) {
            System.out.println("Error en filtrarProducto: " + e);
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    /**
     * Agrega un nuevo registro a la tabla `entradas`.
     *
     * @param entrada objeto {@link Entrada} que contiene los datos del nuevo registro.
     * @throws SQLException si ocurre un error al insertar el registro.
     */
    public void agregar(Entrada entrada) throws SQLException {
        String sql = "INSERT INTO entradas(idProductos, nombreProductos, descripcionOperacion, fecha, cantidad, precioUnitario, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entrada.getIdproducto());
            ps.setString(2, entrada.getNombreproducto());
            ps.setString(3, entrada.getDescoperacion());
            ps.setString(4, entrada.getFecha());
            ps.setInt(5, entrada.getCantidad());
            ps.setDouble(6, entrada.getPreciounitario());
            ps.setDouble(7, entrada.getTotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en proceso de agregación EntradasDAO: " + e);
            throw e;
        } finally {
            cerrarConexiones();
        }
    }

    /**
     * Obtiene una lista de todas las entradas registradas en la tabla `entradas`.
     *
     * @return una lista de objetos {@link Entrada} que contiene las entradas registradas.
     */
    public List<Entrada> listarTablaEntrada() {
        String sql = "SELECT * FROM entradas";
        List<Entrada> lista = new ArrayList<>();
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Entrada entrada = new Entrada();
                entrada.setIdentrada(rs.getInt(1)); // ID de entrada
                entrada.setIdproducto(rs.getInt(2)); // ID del producto
                entrada.setNombreproducto(rs.getString(3)); // Nombre del producto
                entrada.setDescoperacion(rs.getString(4)); // Descripción de la operación
                entrada.setCantidad(rs.getInt(5)); // Cantidad
                entrada.setPreciounitario(rs.getDouble(6)); // Precio unitario
                entrada.setTotal(rs.getDouble(7)); // Total
                entrada.setFecha(rs.getString(8)); // Fecha
                lista.add(entrada);
            }
        } catch (Exception e) {
            System.out.println("Error en listarTablaEntrada: " + e);
        }
        return lista;
    }

    /**
     * Actualiza los datos de un registro existente en la tabla `entradas`.
     *
     * @param entrada objeto {@link Entrada} con los datos actualizados del registro.
     */
    public void editarEntrd(Entrada entrada) {
        String sql = "UPDATE entradas SET idProductos=?, nombreProductos=?, descripcionOperacion=?, fecha=?, cantidad=?, precioUnitario=?, total=? WHERE id_Entrada=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entrada.getIdproducto());
            ps.setString(2, entrada.getNombreproducto());
            ps.setString(3, entrada.getDescoperacion());
            ps.setString(4, entrada.getFecha());
            ps.setInt(5, entrada.getCantidad());
            ps.setDouble(6, entrada.getPreciounitario());
            ps.setDouble(7, entrada.getTotal());
            ps.setInt(8, entrada.getIdentrada());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en editar entrada - EntradasDAO: " + e);
        }
    }

    /**
     * Elimina un registro de la tabla `entradas` basado en su ID.
     *
     * @param id ID del registro que se desea eliminar.
     */
    public void eliminarEntrd(int id) {
        String sql = "DELETE FROM entradas WHERE id_Entrada=" + id;
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en eliminarEntrd - EntradasDAO: " + e);
        }
    }

    /**
     * Cierra las conexiones a la base de datos para liberar recursos.
     */
    private void cerrarConexiones() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexiones: " + e);
        }
    }
}
