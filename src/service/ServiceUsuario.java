package service;

import dao.implemetacion.DAOUsuarioImpl;
import dto.DTOUsuario;
import java.sql.*;
import java.util.List;

/**
 *
 * @author rasmx
 */
public class ServiceUsuario {

    private DAOUsuarioImpl daoUsuario;

    public ServiceUsuario() throws SQLException {
        daoUsuario = new DAOUsuarioImpl();
    }

    public boolean login(String usuario, String contrasena, int tipoUsuario) throws SQLException {
        return daoUsuario.login(usuario, contrasena, tipoUsuario);
    }

    public boolean registrarUsuario(DTOUsuario usuario) throws SQLException {
        return daoUsuario.registrarUsuario(usuario);
    }

    public List<DTOUsuario> buscarUsuarioPorCriterios(String nombre, String apellido, String documento, String correo) throws SQLException {
        return daoUsuario.buscarUsuarioPorCriterios(nombre, apellido, documento, correo);
    }

    public boolean eliminarUsuarioPorDocumento(String documento) throws SQLException {
        return daoUsuario.eliminarUsuarioPorDocumento(documento);
    }

    public boolean editarUsuario(DTOUsuario usuario) throws SQLException {
        return daoUsuario.editarUsuario(usuario);
    }

    public List<DTOUsuario> obtenerUsuarios() throws SQLException {
        return daoUsuario.obtenerUsuarios();
    }

}
