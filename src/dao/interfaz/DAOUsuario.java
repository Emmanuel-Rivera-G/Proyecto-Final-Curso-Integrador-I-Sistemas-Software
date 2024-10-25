package dao.interfaz;

import dto.DTOUsuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rasmx
 */
public interface DAOUsuario {

    boolean login(String usuario, String contrasena, int tipoUsuario) throws SQLException;

    boolean registrarUsuario(DTOUsuario usuario) throws SQLException;

    public List<DTOUsuario> buscarUsuarioPorCriterios(String nombre, String apellido, String documento, String correo) throws SQLException;

    public boolean eliminarUsuarioPorDocumento(String documento) throws SQLException;

    public boolean editarUsuario(DTOUsuario usuario) throws SQLException;

    public List<DTOUsuario> obtenerUsuarios() throws SQLException;
}
