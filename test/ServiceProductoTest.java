import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import service.ServiceProducto;
import dao.implemetacion.DAOProductoImpl;
import dto.DTOProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.Conexion;

import static org.junit.Assert.*;

import java.util.List;
import org.slf4j.Logger;
import utils.UtilLoggerManager;

public class ServiceProductoTest {

    private ServiceProducto serviceProducto;
    private DAOProductoImpl daoProductoSimulado;
    private Conexion conexion;
    private final Logger LOGGER = UtilLoggerManager.getLogger(ServiceProductoTest.class);

    @Before
    public void setUp() {
        daoProductoSimulado = new DAOProductoImpl();
        serviceProducto = new ServiceProducto(daoProductoSimulado);
        conexion = new Conexion();
    }
    
    @After
    public void tearDown() {
        try {
            conexion.getConnection().close();
        } catch (Exception e) {
            LOGGER.error("Test fallido: Error al conectar con la base de datos.", e);
        }
    }

    private void limpiarBaseDeDatos() {
        String sql = "TRUNCATE TABLE productos";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            limpiarBaseDeDatos();
        }
    }

    @Test
    public void testAgregarProducto() {
        DTOProducto producto = new DTOProducto();
        producto.setIdProducto(1L);
        producto.setNombre("Producto 1");

        serviceProducto.agregarProducto(producto);

        DTOProducto result = daoProductoSimulado.obtenerProductoPorId(1L);
        assertNotNull(result);
        assertEquals("Producto 1", result.getNombre());
        limpiarBaseDeDatos();
    }

    @Test
    public void testActualizarProducto() {
        DTOProducto producto = new DTOProducto();
        producto.setIdProducto(1L);
        producto.setNombre("Producto 1");

        serviceProducto.agregarProducto(producto);

        producto.setNombre("Producto Actualizado");
        serviceProducto.actualizarProducto(producto);

        DTOProducto result = daoProductoSimulado.obtenerProductoPorId(1L);
        assertEquals("Producto Actualizado", result.getNombre());
        limpiarBaseDeDatos();
    }

    @Test
    public void testEliminarProducto() {
        DTOProducto producto = new DTOProducto();
        producto.setIdProducto(1L);
        producto.setNombre("Producto 1");

        serviceProducto.agregarProducto(producto);
        serviceProducto.eliminarProducto(1L);

        DTOProducto result = daoProductoSimulado.obtenerProductoPorId(1L);
        assertNull(result);
        limpiarBaseDeDatos();
    }

    @Test
    public void testObtenerProductoPorId() {
        DTOProducto producto = new DTOProducto();
        producto.setIdProducto(1L);
        producto.setNombre("Producto 1");

        serviceProducto.agregarProducto(producto);

        DTOProducto result = serviceProducto.obtenerProductoPorId(1L);
        assertNotNull(result);
        assertEquals("Producto 1", result.getNombre());
        limpiarBaseDeDatos();
    }

    @Test
    public void testObtenerTodosLosProductos() {
        DTOProducto producto1 = new DTOProducto();
        producto1.setIdProducto(1L);
        producto1.setNombre("Producto 1");

        DTOProducto producto2 = new DTOProducto();
        producto2.setIdProducto(2L);
        producto2.setNombre("Producto 2");

        serviceProducto.agregarProducto(producto1);
        serviceProducto.agregarProducto(producto2);

        List<DTOProducto> result = serviceProducto.obtenerTodosLosProductos();
        assertEquals(2, result.size());
        limpiarBaseDeDatos();
    }
}
