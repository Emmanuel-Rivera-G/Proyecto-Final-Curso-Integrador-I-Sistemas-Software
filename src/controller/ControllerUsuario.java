package controller;

import dto.DTOUsuario;
import java.io.File;
import service.ServiceUsuario;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

/**
 * La clase {@code ControllerUsuario} es el controlador que gestiona las
 * operaciones relacionadas con los usuarios, incluyendo login, registro,
 * edición y exportación de datos. Se comunica con {@code ServiceUsuario} para
 * ejecutar la lógica de negocio.
 *
 * @author Ralfph
 */
public class ControllerUsuario {

    private ServiceUsuario serviceUsuario;

    /**
     * Constructor que inicializa el controlador de usuario y crea una instancia
     * de {@code ServiceUsuario}.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión con la
     * base de datos.
     */
    public ControllerUsuario() throws SQLException {
        serviceUsuario = new ServiceUsuario();
    }

    /**
     * Verifica el inicio de sesión del usuario mediante el servicio.
     *
     * @param usuario El nombre de usuario.
     * @param contrasena La contraseña del usuario.
     * @param tipoUsuario El tipo de usuario administrador=1 , empleado = 2
     * @return {@code true} si las credenciales son correctas, {@code false} en
     * caso contrario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean login(String usuario, String contrasena, int tipoUsuario) throws SQLException {
        return serviceUsuario.login(usuario, contrasena, tipoUsuario);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param nombre El nombre del usuario.
     * @param apellido El apellido del usuario.
     * @param documento El documento de identificación del usuario.
     * @param direccion La dirección del usuario.
     * @param telefono El número de teléfono del usuario.
     * @param correo El correo electrónico del usuario.
     * @param idTipoUsuario El ID del tipo de usuario administrador=1 , empleado
     * = 2
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return {@code true} si el registro fue exitoso, {@code false} en caso
     * contrario.
     * @throws SQLException Si ocurre un error al insertar el usuario en la base
     * de datos.
     */
    public boolean registrarUsuario(String nombre, String apellido, String documento, String direccion, String telefono, String correo, int idTipoUsuario, String username, String password) throws SQLException {
        DTOUsuario nuevoUsuario = new DTOUsuario(0, nombre, apellido, documento, direccion, telefono, correo, idTipoUsuario, username, password);
        return serviceUsuario.registrarUsuario(nuevoUsuario);
    }

    /**
     * Busca usuarios según los criterios de búsqueda proporcionados.
     *
     * @param nombre El nombre del usuario (puede ser parcial).
     * @param apellido El apellido del usuario (puede ser parcial).
     * @param documento El documento del usuario (puede ser parcial).
     * @param correo El correo del usuario (puede ser parcial).
     * @return Una lista de usuarios que coinciden con los criterios de
     * búsqueda.
     * @throws SQLException Si ocurre un error al realizar la consulta en la
     * base de datos.
     */
    public List<DTOUsuario> buscarUsuarioPorCriterios(String nombre, String apellido, String documento, String correo) throws SQLException {
        return serviceUsuario.buscarUsuarioPorCriterios(nombre, apellido, documento, correo);
    }

    /**
     * Elimina un usuario de la base de datos basado en su documento de
     * identificación.
     *
     * @param documento El documento del usuario a eliminar.
     * @return {@code true} si el usuario fue eliminado exitosamente,
     * {@code false} en caso contrario.
     * @throws SQLException Si ocurre un error al eliminar el usuario en la base
     * de datos.
     */
    public boolean eliminarUsuarioPorDocumento(String documento) throws SQLException {
        return serviceUsuario.eliminarUsuarioPorDocumento(documento);
    }

    /**
     * Edita la información de un usuario existente.
     *
     * @param nombre El nuevo nombre del usuario.
     * @param apellido El nuevo apellido del usuario.
     * @param documento El documento de identificación del usuario.
     * @param direccion La nueva dirección del usuario.
     * @param telefono El nuevo número de teléfono del usuario.
     * @param correo El nuevo correo electrónico del usuario.
     * @param idTipoUsuario El ID del nuevo tipo de usuario.
     * @param username El nuevo nombre de usuario.
     * @param password La nueva contraseña del usuario.
     * @return {@code true} si la edición fue exitosa, {@code false} en caso
     * contrario.
     * @throws SQLException Si ocurre un error al actualizar los datos del
     * usuario en la base de datos.
     */
    public boolean editarUsuario(String nombre, String apellido, String documento, String direccion, String telefono, String correo, int idTipoUsuario, String username, String password) throws SQLException {
        DTOUsuario usuario = new DTOUsuario(0, nombre, apellido, documento, direccion, telefono, correo, idTipoUsuario, username, password);
        return serviceUsuario.editarUsuario(usuario);
    }

    /**
     * Obtiene la lista de todos los usuarios registrados en la base de datos.
     *
     * @return Una lista de usuarios.
     * @throws SQLException Si ocurre un error al consultar la base de datos.
     */
    public List<DTOUsuario> obtenerUsuarios() throws SQLException {
        return serviceUsuario.obtenerUsuarios();
    }

    /**
     * Exporta los datos de una tabla a un archivo de Excel.
     *
     * @param archivo El archivo de Excel donde se exportarán los datos.
     * @param table La tabla {@code JTable} cuyos datos se exportarán.
     * @return Un mensaje que indica si la exportación fue exitosa o fallida.
     */
    public String exportarExcel(File archivo, JTable table) {
        return serviceUsuario.exportarExcel(archivo, table);
    }

}
