package dao.interfaz;

import dto.DTOUsuario;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz que define los métodos para la gestión de usuarios en el sistema.
 * Proporciona operaciones para iniciar sesión, registrar, buscar, eliminar y
 * editar usuarios.
 *
 * @author Ralfph
 */
public interface DAOUsuario {

    /**
     * Verifica las credenciales del usuario para iniciar sesión.
     *
     * @param usuario el nombre de usuario
     * @param contrasena la contraseña del usuario
     * @param tipoUsuario el tipo de usuario (1 para Administrador, 2 para
     * Empleado)
     * @return true si las credenciales son válidas, false en caso contrario
     * @throws SQLException si ocurre un error durante la consulta
     */
    public boolean login(String usuario, String contrasena, int tipoUsuario) throws SQLException;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario el objeto DTOUsuario que contiene los datos del usuario a
     * registrar
     * @return true si el registro fue exitoso, false en caso contrario
     * @throws SQLException si ocurre un error durante la inserción
     */
    public boolean registrarUsuario(DTOUsuario usuario) throws SQLException;

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
    public List<DTOUsuario> buscarUsuarioPorCriterios(String nombre, String apellido, String documento, String correo) throws SQLException;

    /**
     * Elimina un usuario de la base de datos según el documento proporcionado.
     *
     * @param documento el documento del usuario a eliminar
     * @return true si el usuario fue eliminado, false en caso contrario
     * @throws SQLException si ocurre un error durante la eliminación
     */
    public boolean eliminarUsuarioPorDocumento(String documento) throws SQLException;

    /**
     * Actualiza los datos de un usuario en la base de datos.
     *
     * @param usuario el objeto DTOUsuario con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws SQLException si ocurre un error durante la actualización
     */
    public boolean editarUsuario(DTOUsuario usuario) throws SQLException;

    /**
     * Obtiene todos los usuarios registrados en la base de datos.
     *
     * @return una lista de DTOUsuario que representan todos los usuarios
     * @throws SQLException si ocurre un error durante la consulta
     */
    public List<DTOUsuario> obtenerUsuarios() throws SQLException;
}
