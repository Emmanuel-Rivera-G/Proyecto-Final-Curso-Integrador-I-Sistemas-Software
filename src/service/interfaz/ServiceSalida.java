package service.interfaz;

import dto.DTOSalida;
import java.util.List;

/**
 * Interfaz para los servicios relacionados con las salidas de productos.
 * Proporciona métodos para gestionar las salidas, incluyendo operaciones CRUD y búsquedas avanzadas.
 * 
 * @author Yinyer
 */
public interface ServiceSalida {

    /**
     * Agrega una nueva salida a la base de datos.
     *
     * @param dtoSalida el DTO de la salida a agregar
     */
    public void agregarSalida(DTOSalida dtoSalida);

    /**
     * Actualiza una salida existente en la base de datos.
     *
     * @param dtoSalida el DTO de la salida a actualizar
     */
    public void actualizarSalida(DTOSalida dtoSalida);

    /**
     * Elimina una salida de la base de datos.
     *
     * @param idSalida el ID de la salida a eliminar
     */
    public void eliminarSalida(int idSalida);

    /**
     * Obtiene una salida por su ID.
     *
     * @param idSalida el ID de la salida
     * @return el DTO de la salida si se encuentra; de lo contrario, null
     */
    public DTOSalida obtenerSalidaPorId(int idSalida);

    /**
     * Obtiene una lista de todas las salidas en la base de datos.
     *
     * @return una lista de DTOSalida con todas las salidas
     */
    public List<DTOSalida> obtenerTodasLasSalidas();

    /**
     * Busca salidas cuyo ID empieza con un prefijo dado.
     *
     * @param prefijo el prefijo a buscar en los IDs de salidas
     * @return una lista de DTOSalida que cumple con el prefijo en el ID
     */
    public List<DTOSalida> buscarSalidasIdPorPrefijo(String prefijo);

    /**
     * Busca salidas por un nombre o descripción cuyo valor empieza con un prefijo dado.
     *
     * @param prefijo el prefijo a buscar en nombres o descripciones
     * @return una lista de DTOSalida que cumple con el prefijo
     */
    public List<DTOSalida> buscarSalidasPorNombreODescripcionConPrefijo(String prefijo);
}
