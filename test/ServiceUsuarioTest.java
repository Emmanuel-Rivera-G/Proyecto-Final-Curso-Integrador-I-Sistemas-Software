
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import service.ServiceUsuario;
import dto.DTOUsuario;
import config.Conexion;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.slf4j.Logger;
import utils.UtilsLoggerManager;

/**
 * Clase de prueba para el servicio de usuario. Esta clase contiene pruebas
 * unitarias para verificar el funcionamiento de las operaciones relacionadas
 * con el registro, edición, eliminación y búsqueda de usuarios.
 *
 * @author Emmanuel
 */
public class ServiceUsuarioTest {

    private ServiceUsuario serviceUsuario;
    private Conexion conexion;
    private final Logger LOGGER = UtilsLoggerManager.getLogger(ServiceUsuarioTest.class);

    /**
     * Método que se ejecuta antes de cada prueba. Inicializa el servicio de
     * usuario y la conexión a la base de datos.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    @Before
    public void setUp() throws SQLException {
        conexion = new Conexion();
        serviceUsuario = new ServiceUsuario();
    }

    /**
     * Método que se ejecuta después de cada prueba. Limpia los registros de
     * usuario creados durante las pruebas para evitar interferencias en pruebas
     * futuras.
     */
    @After
    public void tearDown() {
        try {
            Connection con = conexion.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM usuario WHERE documento_usuario IN ('123456', '654321')");
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Error al cerrar la conexión con la base de datos.", e);
        }
    }

    /**
     * Prueba para verificar el registro de un nuevo usuario. Se crea un
     * DTOUsuario y se llama al método registrarUsuario. Se comprueba que el
     * usuario se haya registrado correctamente.
     *
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Test
    public void testRegistrarUsuario() throws SQLException {
        DTOUsuario usuario = new DTOUsuario();
        usuario.setDocumento("123456");
        usuario.setNombre("Usuario 1");
        usuario.setApellido("Apellido 1");
        usuario.setDireccion("Calle 1");
        usuario.setTelefono("9998456631");
        usuario.setCorreo("usuario1@example.com");
        usuario.setUsername("user1");
        usuario.setPassword("password123");
        usuario.setIdTipoUsuario(1);

        boolean registrado = serviceUsuario.registrarUsuario(usuario);
        assertTrue(registrado); // Verifica que el registro fue exitoso

        List<DTOUsuario> result = serviceUsuario.obtenerUsuarios();
        assertTrue(result.stream().anyMatch(u -> "123456".equals(u.getDocumento())));
    }

    /**
     * Prueba para verificar la edición de un usuario existente. Se registra un
     * usuario y luego se edita su nombre. Se verifica que el nombre se haya
     * actualizado correctamente.
     *
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Test
    public void testEditarUsuario() throws SQLException {
        DTOUsuario usuario = new DTOUsuario();
        usuario.setDocumento("123456");
        usuario.setNombre("Usuario 1");
        usuario.setApellido("Apellido 1");
        usuario.setDireccion("Calle 1");
        usuario.setTelefono("9998456631");
        usuario.setCorreo("usuario1@example.com");
        usuario.setUsername("user1");
        usuario.setPassword("password123");
        usuario.setIdTipoUsuario(1);

        serviceUsuario.registrarUsuario(usuario);

        usuario.setNombre("Usuario Editado");
        boolean editado = serviceUsuario.editarUsuario(usuario);
        assertTrue(editado); // Verifica que la edición fue exitosa

        DTOUsuario result = serviceUsuario.buscarUsuarioPorCriterios("Usuario Editado", null, null, null).get(0);
        assertEquals("Usuario Editado", result.getNombre()); // Verifica que el nombre haya cambiado
    }

    /**
     * Prueba para verificar la eliminación de un usuario por su documento. Se
     * registra un usuario y luego se elimina. Se comprueba que el usuario ya no
     * esté en la lista de usuarios.
     *
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Test
    public void testEliminarUsuarioPorDocumento() throws SQLException {
        DTOUsuario usuario = new DTOUsuario();
        usuario.setDocumento("123456");
        usuario.setNombre("Usuario 1");
        usuario.setApellido("Apellido 1");
        usuario.setCorreo("usuario1@example.com");
        usuario.setDireccion("Calle 1");
        usuario.setTelefono("9998456631");
        usuario.setUsername("user1");
        usuario.setPassword("password123");
        usuario.setIdTipoUsuario(1);

        serviceUsuario.registrarUsuario(usuario);

        boolean eliminado = serviceUsuario.eliminarUsuarioPorDocumento("123456");
        assertTrue(eliminado); // Verifica que la eliminación fue exitosa

        List<DTOUsuario> result = serviceUsuario.obtenerUsuarios();
        assertFalse(result.stream().anyMatch(u -> "123456".equals(u.getDocumento())));
    }

    /**
     * Prueba para buscar un usuario por criterios específicos. Se registra un
     * usuario y luego se busca utilizando su nombre. Se verifica que el usuario
     * esté presente en los resultados.
     *
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Test
    public void testBuscarUsuarioPorCriterios() throws SQLException {
        DTOUsuario usuario = new DTOUsuario();
        usuario.setDocumento("123456");
        usuario.setNombre("Usuario 1");
        usuario.setApellido("Apellido 1");
        usuario.setCorreo("usuario1@example.com");
        usuario.setDireccion("Calle 1");
        usuario.setTelefono("9998456631");
        usuario.setUsername("user1");
        usuario.setPassword("password123");
        usuario.setIdTipoUsuario(1);

        serviceUsuario.registrarUsuario(usuario);

        List<DTOUsuario> result = serviceUsuario.buscarUsuarioPorCriterios("Usuario 1", null, null, null);
        assertTrue(result.stream().anyMatch(u -> "123456".equals(u.getDocumento())));
    }

    /**
     * Prueba para obtener todos los usuarios registrados en el sistema. Se
     * registran dos usuarios y se verifica que ambos estén presentes en la
     * lista de usuarios.
     *
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Test
    public void testObtenerUsuarios() throws SQLException {
        DTOUsuario usuario1 = new DTOUsuario();
        usuario1.setDocumento("123456");
        usuario1.setNombre("Usuario 1");
        usuario1.setApellido("Apellido 1");
        usuario1.setDireccion("Calle 1");
        usuario1.setTelefono("9998456631");
        usuario1.setCorreo("usuario1@example.com");
        usuario1.setUsername("user1");
        usuario1.setPassword("password123");
        usuario1.setIdTipoUsuario(1);

        DTOUsuario usuario2 = new DTOUsuario();
        usuario2.setDocumento("654321");
        usuario2.setNombre("Usuario 2");
        usuario2.setApellido("Apellido 2");
        usuario2.setDireccion("Calle 1");
        usuario2.setTelefono("9998456631");
        usuario2.setCorreo("usuario2@example.com");
        usuario2.setUsername("user2");
        usuario2.setPassword("password456");
        usuario2.setIdTipoUsuario(2);

        serviceUsuario.registrarUsuario(usuario1);
        serviceUsuario.registrarUsuario(usuario2);

        List<DTOUsuario> result = serviceUsuario.obtenerUsuarios();
        assertTrue(result.stream().anyMatch(u -> "123456".equals(u.getDocumento())));
        assertTrue(result.stream().anyMatch(u -> "654321".equals(u.getDocumento())));
    }
}
