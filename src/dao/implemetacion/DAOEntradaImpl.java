package dao.implemetacion;

import dao.interfaz.DAOEntrada;
import dto.DTOEntrada;
import dto.DTOProducto;
import dto.DTOProveedor;
import dto.DTOUsuario;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Emmanuel
 */
public class DAOEntradaImpl implements DAOEntrada {

    private Connection connection;

    public DAOEntradaImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public boolean agregarEntrada(DTOEntrada entrada) {
        String sql = "INSERT INTO Entrada (idEntrada, idProducto, cantidad, valorUnitario, valorTotal, fechaEntrada, idProveedor, idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entrada.getIdEntrada());
            stmt.setInt(2, entrada.getDtoProducto().getIdProducto());
            stmt.setInt(3, entrada.getCantidad());
            stmt.setDouble(4, entrada.getValorUnitario());
            stmt.setDouble(5, entrada.getValorTotal());
            stmt.setTimestamp(6, Timestamp.valueOf(entrada.getFechaEntrada()));
            stmt.setInt(7, entrada.getDtoProveedor().getIdProveedor());
            stmt.setInt(8, entrada.getUsuario().getIdUsuario());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarEntrada(DTOEntrada entrada) {
         String sql = "UPDATE Entrada SET idProducto = ?, cantidad = ?, valorUnitario = ?, valorTotal = ?, fechaEntrada = ?, idProveedor = ?, idUsuario = ? WHERE idEntrada = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entrada.getDtoProducto().getIdProducto());
            stmt.setInt(2, entrada.getCantidad());
            stmt.setDouble(3, entrada.getValorUnitario());
            stmt.setDouble(4, entrada.getValorTotal());
            stmt.setTimestamp(5, Timestamp.valueOf(entrada.getFechaEntrada()));
            stmt.setInt(6, entrada.getDtoProveedor().getIdProveedor());
            stmt.setInt(7, entrada.getUsuario().getIdUsuario());
            stmt.setInt(8, entrada.getIdEntrada());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEntrada(int idEntrada) {
        String sql = "DELETE FROM Entrada WHERE idEntrada = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEntrada);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DTOEntrada obtenerEntradaPorId(int idEntrada) {
        String sql = "SELECT * FROM Entrada WHERE idEntrada = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEntrada);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEntrada(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DTOEntrada> obtenerTodasLasEntradas() {
        List<DTOEntrada> entradas = new ArrayList<>();
        String sql = "SELECT * FROM Entrada";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                entradas.add(mapResultSetToEntrada(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entradas;
    }

    private DTOEntrada mapResultSetToEntrada(ResultSet rs) throws SQLException {
        DTOEntrada entrada = new DTOEntrada();
        entrada.setIdEntrada(rs.getInt("idEntrada"));
        
        DTOProducto producto = new DTOProducto();
        producto.setIdProducto(rs.getInt("idProducto"));
        entrada.setDtoProducto(producto);
        
        DTOProveedor proveedor = new DTOProveedor();
        proveedor.setIdProveedor(rs.getInt("idProveedor"));
        entrada.setDtoProveedor(proveedor);
        
        DTOUsuario usuario = new DTOUsuario();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        entrada.setUsuario(usuario);
        
        entrada.setCantidad(rs.getInt("cantidad"));
        entrada.setValorUnitario(rs.getDouble("valorUnitario"));
        entrada.setValorTotal(rs.getDouble("valorTotal"));
        entrada.setFechaEntrada(rs.getTimestamp("fechaEntrada").toLocalDateTime());
        
        return entrada;
    }
}
