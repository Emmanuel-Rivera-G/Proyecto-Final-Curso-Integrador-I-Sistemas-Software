/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dto.DTOUsuario;
import service.ServiceUsuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rasmx
 */
public class ControllerUsuario {

    private ServiceUsuario serviceUsuario;

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

}
