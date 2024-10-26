import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import service.ServiceUsuario;
import dto.DTOUsuario;
import config.Conexion;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import utils.UtilLoggerManager;

public class ServiceUsuarioTest {

    private ServiceUsuario serviceUsuario;
    private Conexion conexion;
    private final Logger LOGGER = UtilLoggerManager.getLogger(ServiceUsuarioTest.class);

    @Before
    public void setUp() throws SQLException {
        conexion = new Conexion();
        serviceUsuario = new ServiceUsuario(); // La implementación de DAOUsuario está incluida en ServiceUsuario.
    }

    @After
    public void tearDown() {
        try {
            conexion.getConnection().close();
        } catch (SQLException e) {
            LOGGER.error("Error al cerrar la conexión con la base de datos.", e);
        }
    }

    private void limpiarBaseDeDatos() {
    try (Connection con = conexion.getConnection();
         Statement stmt = con.createStatement()) {

        // Desactivar temporalmente las verificaciones de claves foráneas
        stmt.execute("SET FOREIGN_KEY_CHECKS = 0");

        // Usar TRUNCATE en lugar de DELETE para vaciar la tabla por completo
        stmt.execute("TRUNCATE TABLE usuario");

        // Reactivar las verificaciones de claves foráneas
        stmt.execute("SET FOREIGN_KEY_CHECKS = 1");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    @Test
    public void testRegistrarUsuario() throws SQLException {
        DTOUsuario usuario = new DTOUsuario();
        usuario.setDocumento("123456");
        usuario.setNombre("Usuario 1");
        usuario.setApellido("Apellido 1"); // No debe ser nulo
        usuario.setCorreo("usuario1@example.com");
        usuario.setUsername("user1");
        usuario.setPassword("password123");
        usuario.setIdTipoUsuario(1);

        boolean registrado = serviceUsuario.registrarUsuario(usuario);
        assertTrue(registrado);

        List<DTOUsuario> result = serviceUsuario.obtenerUsuarios();
        assertEquals(1, result.size());
        limpiarBaseDeDatos();
    }

    @Test
    public void testEditarUsuario() throws SQLException {
        DTOUsuario usuario = new DTOUsuario();
        usuario.setDocumento("123456");
        usuario.setNombre("Usuario 1");
        usuario.setApellido("Apellido 1"); // No debe ser nulo
        usuario.setCorreo("usuario1@example.com");
        usuario.setUsername("user1");
        usuario.setPassword("password123");
        usuario.setIdTipoUsuario(1);

        serviceUsuario.registrarUsuario(usuario);

        usuario.setNombre("Usuario Editado");
        boolean editado = serviceUsuario.editarUsuario(usuario);
        assertTrue(editado);

        DTOUsuario result = serviceUsuario.buscarUsuarioPorCriterios("Usuario Editado", null, null, null).get(0);
        assertEquals("Usuario Editado", result.getNombre());
        limpiarBaseDeDatos();
    }

    @Test
    public void testEliminarUsuarioPorDocumento() throws SQLException {
        DTOUsuario usuario = new DTOUsuario();
        usuario.setDocumento("123456");
        usuario.setNombre("Usuario 1");
        usuario.setApellido("Apellido 1"); // No debe ser nulo
        usuario.setCorreo("usuario1@example.com");
        usuario.setUsername("user1");
        usuario.setPassword("password123");
        usuario.setIdTipoUsuario(1);

        serviceUsuario.registrarUsuario(usuario);

        boolean eliminado = serviceUsuario.eliminarUsuarioPorDocumento("123456");
        assertTrue(eliminado);

        List<DTOUsuario> result = serviceUsuario.obtenerUsuarios();
        assertEquals(0, result.size());
        limpiarBaseDeDatos();
    }

    @Test
    public void testBuscarUsuarioPorCriterios() throws SQLException {
        DTOUsuario usuario = new DTOUsuario();
        usuario.setDocumento("123456");
        usuario.setNombre("Usuario 1");
        usuario.setApellido("Apellido 1"); // No debe ser nulo
        usuario.setCorreo("usuario1@example.com");
        usuario.setUsername("user1");
        usuario.setPassword("password123");
        usuario.setIdTipoUsuario(1);

        serviceUsuario.registrarUsuario(usuario);

        List<DTOUsuario> result = serviceUsuario.buscarUsuarioPorCriterios("Usuario 1", null, null, null);
        assertEquals(1, result.size());
        limpiarBaseDeDatos();
    }

    @Test
    public void testObtenerUsuarios() throws SQLException {
        DTOUsuario usuario1 = new DTOUsuario();
        usuario1.setDocumento("123456");
        usuario1.setNombre("Usuario 1");
        usuario1.setApellido("Apellido 1"); // No debe ser nulo
        usuario1.setCorreo("usuario1@example.com");
        usuario1.setUsername("user1");
        usuario1.setPassword("password123");
        usuario1.setIdTipoUsuario(1);

        DTOUsuario usuario2 = new DTOUsuario();
        usuario2.setDocumento("654321");
        usuario2.setNombre("Usuario 2");
        usuario2.setApellido("Apellido 2"); // No debe ser nulo
        usuario2.setCorreo("usuario2@example.com");
        usuario2.setUsername("user2");
        usuario2.setPassword("password456");
        usuario2.setIdTipoUsuario(2);

        serviceUsuario.registrarUsuario(usuario1);
        serviceUsuario.registrarUsuario(usuario2);

        List<DTOUsuario> result = serviceUsuario.obtenerUsuarios();
        assertEquals(2, result.size());
        limpiarBaseDeDatos();
    }
}
