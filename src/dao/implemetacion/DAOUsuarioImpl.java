/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.implemetacion;

import config.Conexion;
import dao.interfaz.DAOUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dto.DTOUsuario;

/**
 * Implementación de la interfaz {@code DAOUsuario} que gestiona las operaciones
 * de acceso a datos para los usuarios. Esta clase interactúa con la base de
 * datos para realizar operaciones CRUD (Create, Read, Update, Delete) sobre la
 * tabla de usuarios.
 *
 * @author Ralfph
 */
public class DAOUsuarioImpl implements DAOUsuario {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registroExitoso;
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }

    @Override
    public boolean eliminarUsuarioPorDocumento(String documento) throws SQLException {
        boolean eliminado = false;
        String sql = "DELETE FROM usuario WHERE documento_usuario = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, documento);
            int rowsAffected = ps.executeUpdate();
            eliminado = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eliminado;
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actualizado;
    }

    @Override
    public List<DTOUsuario> obtenerUsuarios() throws SQLException {
        List<DTOUsuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, documento, direccion, telefono, correo, idTipoUsuario, username, password FROM usuario";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
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
        }
        return usuarios;

    }
}
