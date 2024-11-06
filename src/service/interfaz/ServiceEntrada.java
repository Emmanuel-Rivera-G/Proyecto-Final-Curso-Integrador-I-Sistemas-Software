package service.interfaz;

import dto.DTOEntrada;
import java.util.List;

/**
 * Interfaz para los servicios relacionados con las entradas de productos.
 * Proporciona métodos para gestionar las entradas, incluyendo operaciones CRUD y búsquedas avanzadas.
 * 
 * @author Yinyer
 */
public interface ServiceEntrada {

    /**
     * Agrega una nueva entrada a la base de datos.
     *
     * @param dtoEntrada el DTO de la entrada a agregar
     */
    public void agregarEntrada(DTOEntrada dtoEntrada);

    /**
     * Actualiza una entrada existente en la base de datos.
     *
     * @param dtoEntrada el DTO de la entrada a actualizar
     */
    public void actualizarEntrada(DTOEntrada dtoEntrada);

    /**
     * Elimina una entrada de la base de datos.
     *
     * @param idEntrada el ID de la entrada a eliminar
     */
    public void eliminarEntrada(int idEntrada);

    /**
     * Obtiene una entrada por su ID.
     *
     * @param idEntrada el ID de la entrada
     * @return el DTO de la entrada si se encuentra; de lo contrario, null
     */
    public DTOEntrada obtenerEntradaPorId(int idEntrada);

    /**
     * Obtiene una lista de todas las entradas en la base de datos.
     *
     * @return una lista de DTOEntrada con todas las entradas
     */
    public List<DTOEntrada> obtenerTodasLasEntradas();

    /**
     * Busca entradas cuyo ID empieza con un prefijo dado.
     *
     * @param prefijo el prefijo a buscar en los IDs de entradas
     * @return una lista de DTOEntrada que cumple con el prefijo en el ID
     */
    public List<DTOEntrada> buscarEntradasIdPorPrefijo(String prefijo);

    /**
     * Busca entradas por un nombre o descripción cuyo valor empieza con un prefijo dado.
     *
     * @param prefijo el prefijo a buscar en nombres o descripciones
     * @return una lista de DTOEntrada que cumple con el prefijo
     */
    public List<DTOEntrada> buscarEntradasPorNombreODescripcionConPrefijo(String prefijo);
}
