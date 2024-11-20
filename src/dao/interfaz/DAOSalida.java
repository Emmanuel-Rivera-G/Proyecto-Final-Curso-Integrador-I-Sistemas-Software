package dao.interfaz;

import dto.DTOSalida;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para manejar las salidas de productos en la base de datos.
 * Proporciona los métodos necesarios para la persistencia, actualización, eliminación y obtención
 * de información sobre las salidas.
 * 
 * @author Yinyer
 */
public interface DAOSalida {

    /**
     * Obtiene todas las salidas registradas en la base de datos.
     * 
     * @return Lista de {@link DTOSalida} que representan las salidas registradas.
     */
    public List<DTOSalida> obtenerTodasLasSalidas();

    /**
     * Obtiene una salida de producto específica mediante su identificador.
     * 
     * @param idSalida El identificador único de la salida.
     * @return Un objeto {@link DTOSalida} que representa la salida encontrada, o null si no se encuentra.
     */
    public DTOSalida obtenerSalidaPorId(int idSalida);

    /**
     * Guarda una nueva salida de producto en la base de datos.
     * 
     * @param dtoSalida El objeto {@link DTOSalida} que contiene los datos de la salida a guardar.
     * @return El identificador único de la salida guardada.
     */
    public int guardarSalida(DTOSalida dtoSalida);

    /**
     * Actualiza los datos de una salida de producto en la base de datos.
     * 
     * @param dtoSalida El objeto {@link DTOSalida} que contiene los nuevos datos de la salida.
     * @return {@code true} si la actualización fue exitosa, {@code false} si no se realizó ningún cambio.
     */
    public boolean actualizarSalida(DTOSalida dtoSalida);

    /**
     * Elimina una salida de producto de la base de datos.
     * 
     * @param idSalida El identificador único de la salida que se desea eliminar.
     * @return {@code true} si la eliminación fue exitosa, {@code false} si no se encontró la salida.
     */
    public boolean eliminarSalida(int idSalida);
}
