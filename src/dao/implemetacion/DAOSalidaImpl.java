package dao.implemetacion;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import dao.interfaz.DAOSalida;
import dto.DTOSalida;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yinyer
 */
public class DAOSalidaImpl implements DAOSalida {
    
    private Connection connection;

    public DAOSalidaImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<DTOSalida> obtenerTodasLasSalidas() {
        List<DTOSalida> salidas = new ArrayList<>();
        String sql = "SELECT * FROM Salida";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                salidas.add(mapResultSetToSalida(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salidas;
    }

    @Override
    public DTOSalida obtenerSalidaPorId(int idSalida) {
        String sql = "SELECT * FROM Salida WHERE idSalida = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSalida);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSalida(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int guardarSalida(DTOSalida dtoSalida) {
        String sql = "INSERT INTO Salida (idSalida, idProducto, cantidad, valorUnitario, valorTotal, fechaSalida, idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, dtoSalida.getIdSalida());
            stmt.setInt(2, dtoSalida.getDtoProducto().getIdProducto());
            stmt.setInt(3, dtoSalida.getCantidad());
            stmt.setDouble(4, dtoSalida.getValorUnitario());
            stmt.setDouble(5, dtoSalida.getValorTotal());
            stmt.setTimestamp(6, Timestamp.valueOf(dtoSalida.getFechaSalida()));
            stmt.setInt(7, dtoSalida.getDtoUsuario().getIdUsuario());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean actualizarSalida(DTOSalida dtoSalida) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarSalida(int idSalida) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
