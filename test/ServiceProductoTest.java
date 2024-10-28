import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import service.ServiceProducto;
import dao.implemetacion.DAOProductoImpl;
import dto.DTOProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import config.Conexion;
import dao.interfaz.DAOProducto;

import static org.junit.Assert.*;

import java.util.List;
import model.Producto;
import org.slf4j.Logger;
import utils.UtilLoggerManager;

/**
 * Clase de prueba para el servicio de productos.
 * Esta clase verifica que el servicio de productos funcione correctamente
 * mediante pruebas de agregar, actualizar, eliminar y obtener productos.
 * 
 * Autor: Emmanuel
 */
public class ServiceProductoTest {
    private final int ID_1 = 1;
    private final int ID_2 = 2;
    
    private ServiceProducto serviceProducto;
    private DAOProducto daoProductoSimulado;
    private Conexion conexion;
    private final Logger LOGGER = UtilLoggerManager.getLogger(ServiceProductoTest.class);

    private int lastIdBeforeTests;

    /**
     * Configuración inicial antes de cada prueba.
     * Se instancia la clase DAOProducto y ServiceProducto,
     * y se obtiene el último ID de productos en la base de datos
     * para manejar correctamente la limpieza después de las pruebas.
     */
    @Before
    public void setUp() {
        daoProductoSimulado = new DAOProductoImpl();
        serviceProducto = new ServiceProducto(daoProductoSimulado);
        conexion = new Conexion();

        // Obtener el último ID antes de cada prueba
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT MAX(id) FROM productos");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                lastIdBeforeTests = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Error al obtener el último ID antes de las pruebas.", e);
        }
    }
    
    /**
     * Limpieza después de cada prueba.
     * Se eliminan los productos que se agregaron durante las pruebas
     * para asegurar que cada prueba comience con un estado limpio.
     */
    @After
    public void tearDown() {
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM productos WHERE id > ?")) {
            ps.setInt(1, lastIdBeforeTests);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error al eliminar los productos agregados durante las pruebas.", e);
        }
    }

    /**
     * Test que verifica la funcionalidad de agregar un producto.
     * Se espera que el producto agregado esté presente en la lista
     * de productos del DAO simulado.
     */
    @Test
    public void testAgregarProducto() {
        Producto producto = new Producto();
        producto.setNombre("Producto de Prueba");
        producto.setIdcategoria(1);
        producto.setUndmedida("Unidad");
        producto.setStock(10);

        serviceProducto.agregarProducto(producto);

        List<DTOProducto> productos = daoProductoSimulado.obtenerTodosLosProductos();
        assertTrue(productos.stream().anyMatch(p -> "Producto de Prueba".equals(p.getNombre())));
    }

    /**
     * Test que verifica la funcionalidad de actualizar un producto.
     * Se espera que el nombre del producto se actualice correctamente
     * después de realizar la llamada al servicio de actualización.
     */
    @Test
    public void testActualizarProducto() {
        Producto producto = new Producto();
        producto.setNombre("Producto de Prueba");
        producto.setIdcategoria(1);
        producto.setUndmedida("Unidad");
        producto.setStock(10);

        serviceProducto.agregarProducto(producto);

        List<DTOProducto> productos = daoProductoSimulado.obtenerTodosLosProductos();
        int newProductId = productos.get(productos.size() - 1).getIdProducto();

        producto.setId(newProductId);
        producto.setNombre("Producto Actualizado");
        serviceProducto.actualizarProducto(producto);

        DTOProducto result = daoProductoSimulado.obtenerProductoPorId(newProductId);
        assertEquals("Producto Actualizado", result.getNombre());
    }

    /**
     * Test que verifica la funcionalidad de eliminar un producto.
     * Se espera que el producto eliminado no esté presente en la lista
     * de productos después de realizar la llamada al servicio de eliminación.
     */
    @Test
    public void testEliminarProducto() {
        Producto producto = new Producto();
        producto.setNombre("Producto de Prueba");
        producto.setIdcategoria(1);
        producto.setUndmedida("Unidad");
        producto.setStock(10);

        serviceProducto.agregarProducto(producto);

        List<DTOProducto> productos = daoProductoSimulado.obtenerTodosLosProductos();
        int newProductId = productos.get(productos.size() - 1).getIdProducto();

        serviceProducto.eliminarProducto(newProductId);

        DTOProducto result = daoProductoSimulado.obtenerProductoPorId(newProductId);
        assertNull(result);
    }

    /**
     * Test que verifica la funcionalidad de obtener un producto por su ID.
     * Se espera que el producto obtenido no sea nulo y que su nombre
     * coincida con el nombre del producto de prueba.
     */
    @Test
    public void testObtenerProductoPorId() {
        Producto producto = new Producto();
        producto.setNombre("Producto de Prueba");
        producto.setIdcategoria(1);
        producto.setUndmedida("Unidad");
        producto.setStock(10);

        serviceProducto.agregarProducto(producto);

        List<DTOProducto> productos = daoProductoSimulado.obtenerTodosLosProductos();
        int newProductId = productos.get(productos.size() - 1).getIdProducto();

        Producto result = serviceProducto.obtenerProductoPorId(newProductId);
        assertNotNull(result);
        assertEquals("Producto de Prueba", result.getNombre());
    }

    /**
     * Test que verifica la funcionalidad de obtener todos los productos.
     * Se espera que el tamaño de la lista de productos aumente en 2
     * después de agregar dos productos de prueba.
     */
    @Test
    public void testObtenerTodosLosProductos() {
        int initialSize = serviceProducto.obtenerTodosLosProductos().size();

        Producto producto1 = new Producto();
        producto1.setNombre("Producto de Prueba 1");
        producto1.setIdcategoria(1);
        producto1.setUndmedida("Unidad");
        producto1.setStock(10);

        Producto producto2 = new Producto();
        producto2.setNombre("Producto de Prueba 2");
        producto2.setIdcategoria(1);
        producto2.setUndmedida("Unidad");
        producto2.setStock(20);

        serviceProducto.agregarProducto(producto1);
        serviceProducto.agregarProducto(producto2);

        List<Producto> productos = serviceProducto.obtenerTodosLosProductos();
        assertEquals(initialSize + 2, productos.size());
    }
}
