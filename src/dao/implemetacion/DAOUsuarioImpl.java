package dao.implemetacion;

import config.Conexion;
import dao.interfaz.DAOUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dto.DTOUsuario;
import org.slf4j.Logger;
import utils.UtilLoggerManager;

/**
 * Implementación de la interfaz {@code DAOUsuario} que gestiona las operaciones
 * de acceso a datos para los usuarios. Esta clase interactúa con la base de
 * datos para realizar operaciones CRUD (Create, Read, Update, Delete) sobre la
 * tabla de usuarios.
 *
 * @author Ralfph
 */
public class DAOUsuarioImpl implements DAOUsuario {

    private final Logger LOGGER = UtilLoggerManager.getLogger(DAOUsuario.class);
    private Conexion conexion;
    private Connection connection;

    /**
     * Constructor de la clase {@code DAOUsuarioImpl}. Establece una conexión a
     * la base de datos mediante la clase {@code Conexion}.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión con la
     * base de datos.
     */
    public DAOUsuarioImpl() throws SQLException {
        conexion = new Conexion();
        connection = conexion.getConnection();
    }

    /**
     * Inicia sesión en el sistema verificando las credenciales del usuario.
     *
     * @param usuario el nombre de usuario
     * @param contrasena la contraseña del usuario
     * @param tipoUsuario el tipo de usuario (1 para Administrador, 2 para
     * Empleado)
     * @return true si las credenciales son válidas, false en caso contrario
     * @throws SQLException si ocurre un error durante la consulta
     */
    @Override
    public boolean login(String usuario, String contrasena, int tipoUsuario) throws SQLException {
        boolean resultado = false;
        String sql = "SELECT * FROM usuario WHERE username=? AND password_usuario=? AND id_tipo_usuario=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ps.setInt(3, tipoUsuario);
            ResultSet rs = ps.executeQuery();
            resultado = rs.next();
            rs.close();
            if (resultado) {
                LOGGER.info("Se registro un nuevo inicio de sesión.");
            } else {
                LOGGER.error("El usuario no se ha autenticado correctamente.");
            }
        } catch (SQLException e) {
            LOGGER.error("Hubo un problema en la autenticación del usuario. " + e.getMessage(), e);
        }
        return resultado;
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param usuario el objeto DTOUsuario que contiene los datos del usuario a
     * registrar
     * @return true si el registro fue exitoso, false en caso contrario
     * @throws SQLException si ocurre un error durante la inserción
     */
    @Override
    public boolean registrarUsuario(DTOUsuario usuario) throws SQLException {
        boolean registroExitoso = false;
        String sql = "INSERT INTO usuario (id_usuario, nombre_usuario, apellido_usuario, documento_usuario, direccion_usuario, telefono_usuario, correo_usuario, id_tipo_usuario, username, password_usuario) "
                + "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getDocumento());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getCorreo());
            ps.setInt(7, usuario.getIdTipoUsuario());
            ps.setString(8, usuario.getUsername());
            ps.setString(9, usuario.getPassword());

            int rowsAffected = ps.executeUpdate();
            registroExitoso = rowsAffected > 0;
            LOGGER.info("Se registro exitosamente un usuario.");
        } catch (SQLException e) {
            LOGGER.error("Hubo un problema en el registro de usuario. " + e.getMessage(), e);
        }
        return registroExitoso;
    }

    /**
     * Busca usuarios en la base de datos según los criterios proporcionados.
     *
     * @param nombre el nombre del usuario (opcional)
     * @param apellido el apellido del usuario (opcional)
     * @param documento el documento del usuario (opcional)
     * @param correo el correo del usuario (opcional)
     * @return una lista de DTOUsuario que coinciden con los criterios de
     * búsqueda
     * @throws SQLException si ocurre un error durante la consulta
     */
    @Override
    public List<DTOUsuario> buscarUsuarioPorCriterios(String nombre, String apellido, String documento, String correo) throws SQLException {
        List<DTOUsuario> listaUsuarios = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM usuario WHERE 1=1");

        List<Object> parametros = new ArrayList<>();
        if (nombre != null && !nombre.isEmpty()) {
            sql.append(" AND nombre_usuario LIKE ?");
            parametros.add("%" + nombre + "%");
        }
        if (apellido != null && !apellido.isEmpty()) {
            sql.append(" AND apellido_usuario LIKE ?");
            parametros.add("%" + apellido + "%");
        }
        if (documento != null && !documento.isEmpty()) {
            sql.append(" AND documento_usuario = ?");
            parametros.add(documento);
        }
        if (correo != null && !correo.isEmpty()) {
            sql.append(" AND correo_usuario = ?");
            parametros.add(correo);
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            // Asignar los parámetros al PreparedStatement
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DTOUsuario usuario = new DTOUsuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("apellido_usuario"),
                        rs.getString("documento_usuario"),
                        rs.getString("direccion_usuario"),
                        rs.getString("telefono_usuario"),
                        rs.getString("correo_usuario"),
                        rs.getInt("id_tipo_usuario"),
                        rs.getString("username"),
                        rs.getString("password_usuario")
                );
                listaUsuarios.add(usuario);
            }
            rs.close();
            LOGGER.info("Se recuperó correctamente los usuarios.");
        } catch (SQLException e) {
            LOGGER.error("Hubo un problema en recuperar los usuarios por criterio. " + e.getMessage(), e);
        }

        return listaUsuarios;
    }

    /**
     * Elimina un usuario de la base de datos según el documento proporcionado.
     *
     * @param documento el documento del usuario a eliminar
     * @return true si el usuario fue eliminado, false en caso contrario
     * @throws SQLException si ocurre un error durante la eliminación
     */
    @Override
    public boolean eliminarUsuarioPorDocumento(String documento) throws SQLException {
        boolean eliminado = false;
        String sql = "DELETE FROM usuario WHERE documento_usuario = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, documento);
            int rowsAffected = ps.executeUpdate();
            eliminado = rowsAffected > 0;
            LOGGER.info("Se elimino correctamente a un usuario.");
        } catch (SQLException e) {
            LOGGER.error("Hubo un problema en eliminar un usuario por documento. " + e.getMessage(), e);
        }

        return eliminado;
    }

    /**
     * Actualiza los datos de un usuario en la base de datos.
     *
     * @param usuario el objeto DTOUsuario con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws SQLException si ocurre un error durante la actualización
     */
    @Override
    public boolean editarUsuario(DTOUsuario usuario) throws SQLException {
        boolean actualizado = false;
        String sql = "UPDATE usuario SET nombre_usuario = ?, apellido_usuario = ?, direccion_usuario = ?, telefono_usuario = ?, correo_usuario = ?, id_tipo_usuario = ?, username = ?, password_usuario = ? WHERE documento_usuario = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getDireccion());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getCorreo());
            ps.setInt(6, usuario.getIdTipoUsuario());
            ps.setString(7, usuario.getUsername());
            ps.setString(8, usuario.getPassword());
            ps.setString(9, usuario.getDocumento());

            int rowsAffected = ps.executeUpdate();
            actualizado = rowsAffected > 0;
            LOGGER.info("Se actualizo correctamente a un usuario.");
        } catch (SQLException e) {
            LOGGER.error("Hubo un problema en actualizar un usuario en específico. " + e.getMessage(), e);
        }

        return actualizado;
    }

    /**
     * Obtiene todos los usuarios registrados en la base de datos.
     *
     * @return una lista de DTOUsuario que representan todos los usuarios
     * @throws SQLException si ocurre un error durante la consulta
     */
    @Override
    public List<DTOUsuario> obtenerUsuarios() throws SQLException {
        List<DTOUsuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre_usuario AS nombre, apellido_usuario AS apellido, documento_usuario AS documento, direccion_usuario AS direccion, telefono_usuario AS telefono, correo_usuario AS correo, id_tipo_usuario AS idTipoUsuario, username, password_usuario AS password \n"
                + "FROM usuario;";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String documento = resultSet.getString("documento");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");
                int idTipoUsuario = resultSet.getInt("idTipoUsuario");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                DTOUsuario usuario = new DTOUsuario(id, nombre, apellido, documento, direccion, telefono, correo, idTipoUsuario, username, password);
                usuarios.add(usuario);
            }
            LOGGER.info("Se recuperó correctamente a la lista de usuarios completa.");
        } catch (SQLException e) {
            LOGGER.error("Hubo un problema al recuperar todos los usuarios de la base de datos. " + e.getMessage(), e);
        }
        return usuarios;

    }
}
