package dao.implemetacion;

import config.Conexion;
import dao.interfaz.DAOProducto;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import dao.interfaz.DAOSalida;
import dto.DTOProducto;
import dto.DTOSalida;
import dto.DTOUsuario;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import service.ServiceUsuario;
import utils.UtilsLoggerManager;

/**
 *
 * @author Yinyer
 */
public class DAOSalidaImpl implements DAOSalida {
    
    private static final Logger LOGGER = UtilsLoggerManager.getLogger(DAOSalida.class);
    
    private Connection connection;
    
    private DAOProducto daoProducto = new DAOProductoImpl();
    private ServiceUsuario serviceUsuario;
    private List<DTOUsuario> ususarioList;

    public DAOSalidaImpl() {
        this.connection = new Conexion().getConnection();
        LOGGER.info("Instancia de DAOSalidaImpl creada con conexión a la base de datos.");
    
        try {
            this.serviceUsuario = new ServiceUsuario();
            newThreadToUpdateUsersList();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<DTOSalida> obtenerTodasLasSalidas() {
        LOGGER.info("Obteniendo todas las salidas de la base de datos.");
        List<DTOSalida> salidas = new ArrayList<>();
        String sql = "SELECT * FROM Salidas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                salidas.add(mapResultSetToSalida(rs));
            }
            LOGGER.info("Se obtuvieron {} salidas correctamente.", salidas.size());
        } catch (SQLException e) {
            LOGGER.error("Error al obtener todas las salidas: {}", e.getMessage(), e);
        }
        return salidas;
    }

    @Override
    public DTOSalida obtenerSalidaPorId(int idSalida) {
        LOGGER.info("Obteniendo la salida con ID: {}", idSalida);
        String sql = "SELECT * FROM Salidas WHERE idSalida = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSalida);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LOGGER.info("Se encontró la salida con ID: {}", idSalida);
                return mapResultSetToSalida(rs);
            } else {
                LOGGER.warn("No se encontró ninguna salida con el ID: {}", idSalida);
            }
        } catch (SQLException e) {
            LOGGER.error("Error al obtener la salida con ID {}: {}", idSalida, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public int guardarSalida(DTOSalida dtoSalida) {
        LOGGER.info("Insertando una nueva salida con ID: {}", dtoSalida.getIdSalida());
        String sql = "INSERT INTO Salidas (idSalida, idProducto, cantidad, valorUnitario, valorTotal, fechaSalida, idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
                    int generatedId = generatedKeys.getInt(1);
                    LOGGER.info("Salida insertada correctamente con ID generado: {}", generatedId);
                    return generatedId;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error al insertar la salida: {}", e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public boolean actualizarSalida(DTOSalida dtoSalida) {
        LOGGER.info("Actualizando la salida con ID: {}", dtoSalida.getIdSalida());
        String sql = "UPDATE Salidas SET idProducto = ?, cantidad = ?, valorUnitario = ?, valorTotal = ?, fechaSalida = ?, idUsuario = ? WHERE idSalida = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, dtoSalida.getDtoProducto().getIdProducto());
            stmt.setInt(2, dtoSalida.getCantidad());
            stmt.setDouble(3, dtoSalida.getValorUnitario());
            stmt.setDouble(4, dtoSalida.getValorTotal());
            stmt.setTimestamp(5, Timestamp.valueOf(dtoSalida.getFechaSalida()));
            stmt.setInt(6, dtoSalida.getDtoUsuario().getIdUsuario());
            stmt.setInt(7, dtoSalida.getIdSalida());
            boolean updated = stmt.executeUpdate() > 0;
            if (updated) {
                LOGGER.info("Salida con ID {} actualizada correctamente.", dtoSalida.getIdSalida());
            } else {
                LOGGER.warn("No se encontró ninguna salida para actualizar con el ID: {}", dtoSalida.getIdSalida());
            }
            return updated;
        } catch (SQLException e) {
            LOGGER.error("Error al actualizar la salida con ID {}: {}", dtoSalida.getIdSalida(), e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean eliminarSalida(int idSalida) {
        LOGGER.info("Eliminando la salida con ID: {}", idSalida);
        String sql = "DELETE FROM Salidas WHERE idSalida = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSalida);
            boolean deleted = stmt.executeUpdate() > 0;
            if (deleted) {
                LOGGER.info("Salida con ID {} eliminada correctamente.", idSalida);
            } else {
                LOGGER.warn("No se encontró ninguna salida para eliminar con el ID: {}", idSalida);
            }
            return deleted;
        } catch (SQLException e) {
            LOGGER.error("Error al eliminar la salida con ID {}: {}", idSalida, e.getMessage(), e);
        }
        return false;
    }
    
    private DTOSalida mapResultSetToSalida(ResultSet rs) throws SQLException {
        DTOSalida salida = new DTOSalida();
        salida.setIdSalida(rs.getInt("idSalida"));
        
        int idProducto = rs.getInt("idProducto");
        DTOProducto producto = daoProducto.obtenerProductoPorId(idProducto);
        salida.setDtoProducto(producto);
        
        int idUsuario = rs.getInt("idUsuario");
        DTOUsuario usuario = buscarUsarioId(idUsuario);
        salida.setDtoUsuario(usuario);
        
        salida.setCantidad(rs.getInt("cantidad"));
        salida.setValorUnitario(rs.getDouble("valorUnitario"));
        salida.setValorTotal(rs.getDouble("valorTotal"));
        salida.setFechaSalida(rs.getTimestamp("fechaSalida").toLocalDateTime());
        
        LOGGER.debug("Mapeado ResultSet a DTOSalida: {}", salida);
        return salida;
    }
    
    private DTOUsuario buscarUsarioId(int idUsuario) {
        DTOUsuario userRes = null;
        for (DTOUsuario dtou : ususarioList) {
            if (dtou.getIdUsuario() == idUsuario) {
                userRes = dtou;
                break;
            }
        }
        return userRes;
    }
    
    private void newThreadToUpdateUsersList() {
        Runnable runnable = () -> {
            try {
                this.ususarioList = serviceUsuario.obtenerUsuarios();
                Thread.sleep(10000);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        };
        new Thread(runnable)
                .start();
    }
}
