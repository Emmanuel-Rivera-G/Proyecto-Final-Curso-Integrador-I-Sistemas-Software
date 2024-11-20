package dao.interfaz;

import dto.DTOEntrada;
import java.util.List;

/**
 * La interfaz {@code EntradaDAO} define las operaciones CRUD 
 * para interactuar con la base de datos relacionadas con las entradas de productos.
 * Cada método debe ser implementado para realizar operaciones específicas 
 * en la tabla de entradas.
 * 
 * @autor Yinyer
 */
public interface DAOEntrada {

    /**
     * Inserta una nueva entrada en la base de datos.
     * 
     * @param entrada Objeto {@code DTOEntrada} que contiene los datos de la entrada a insertar.
     * @return {@code true} si la operación de inserción fue exitosa, {@code false} en caso contrario.
     */
    public boolean agregarEntrada(DTOEntrada entrada);

    /**
     * Actualiza la información de una entrada existente en la base de datos.
     * 
     * @param entrada Objeto {@code DTOEntrada} que contiene los datos actualizados de la entrada.
     * @return {@code true} si la operación de actualización fue exitosa, {@code false} en caso contrario.
     */
    public boolean actualizarEntrada(DTOEntrada entrada);

    /**
     * Elimina una entrada de la base de datos por su identificador.
     * 
     * @param idEntrada Identificador único de la entrada a eliminar.
     * @return {@code true} si la operación de eliminación fue exitosa, {@code false} en caso contrario.
     */
    public boolean eliminarEntrada(int idEntrada);

    /**
     * Obtiene una entrada de la base de datos por su identificador.
     * 
     * @param idEntrada Identificador único de la entrada.
     * @return Objeto {@code DTOEntrada} que contiene los datos de la entrada encontrada, o {@code null} si no se encuentra.
     */
    public DTOEntrada obtenerEntradaPorId(int idEntrada);

    /**
     * Obtiene una lista de todas las entradas en la base de datos.
     * 
     * @return Lista de objetos {@code DTOEntrada} que representan todas las entradas almacenadas.
     */
    public List<DTOEntrada> obtenerTodasLasEntradas();
}
