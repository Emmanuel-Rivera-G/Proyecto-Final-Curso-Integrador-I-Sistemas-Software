package controller;

import dto.DTOUsuario;
import java.io.File;
import java.io.IOException;
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

    public boolean login(String usuario, String contrasena, int tipoUsuario) throws SQLException {
        return serviceUsuario.login(usuario, contrasena, tipoUsuario);
    }

    public boolean registrarUsuario(String nombre, String apellido, String documento, String direccion, String telefono, String correo, int idTipoUsuario, String username, String password) throws SQLException {
        DTOUsuario nuevoUsuario = new DTOUsuario(0, nombre, apellido, documento, direccion, telefono, correo, idTipoUsuario, username, password);
        return serviceUsuario.registrarUsuario(nuevoUsuario);
    }

    public List<DTOUsuario> buscarUsuarioPorCriterios(String nombre, String apellido, String documento, String correo) throws SQLException {
        return serviceUsuario.buscarUsuarioPorCriterios(nombre, apellido, documento, correo);
    }

    public boolean eliminarUsuarioPorDocumento(String documento) throws SQLException {
        return serviceUsuario.eliminarUsuarioPorDocumento(documento);
    }

    public boolean editarUsuario(String nombre, String apellido, String documento, String direccion, String telefono, String correo, int idTipoUsuario, String username, String password) throws SQLException {
        DTOUsuario usuario = new DTOUsuario(0, nombre, apellido, documento, direccion, telefono, correo, idTipoUsuario, username, password);
        return serviceUsuario.editarUsuario(usuario);
    }

    public List<DTOUsuario> obtenerUsuarios() throws SQLException {
        return serviceUsuario.obtenerUsuarios();
    }
    
    public String exportarExcel(File archivo, JTable table) {
        return serviceUsuario.exportarExcel(archivo, table);
    }

}