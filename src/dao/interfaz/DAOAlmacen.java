package dao.interfaz;

import dto.DTOAlmacen;
import java.util.List;

/**
 * La interfaz {@code AlmacenDAO} define las operaciones CRUD 
 * para interactuar con la base de datos relacionada con la entidad {@code Almacen}.
 * Cada método debe ser implementado para realizar operaciones específicas
 * en la tabla de almacenes.
 * 
 * @author Yinyer
 */
public interface DAOAlmacen {

    /**
     * Inserta un nuevo almacén en la base de datos.
     * 
     * @param almacen Objeto {@code DTOAlmacen} que contiene los datos del almacén a insertar.
     * @return {@code true} si la operación de inserción fue exitosa, {@code false} en caso contrario.
     */
    public boolean agregarAlmacen(DTOAlmacen almacen);

    /**
     * Actualiza la información de un almacén existente en la base de datos.
     * 
     * @param almacen Objeto {@code DTOAlmacen} que contiene los datos actualizados del almacén.
     * @return {@code true} si la operación de actualización fue exitosa, {@code false} en caso contrario.
     */
    public boolean actualizarAlmacen(DTOAlmacen almacen);

    /**
     * Elimina un almacén de la base de datos por su identificador.
     * 
     * @param idAlmacen Identificador único del almacén a eliminar.
     * @return {@code true} si la operación de eliminación fue exitosa, {@code false} en caso contrario.
     */
    public boolean eliminarAlmacen(int idAlmacen);

    /**
     * Obtiene un almacén de la base de datos por su identificador.
     * 
     * @param idAlmacen Identificador único del almacén.
     * @return Objeto {@code DTOAlmacen} que contiene los datos del almacén encontrado, o {@code null} si no se encuentra.
     */
    public DTOAlmacen obtenerAlmacenPorId(int idAlmacen);

    /**
     * Obtiene una lista de todos los almacenes en la base de datos.
     * 
     * @return Lista de objetos {@code DTOAlmacen} que representan todos los almacenes almacenados.
     */
    public List<DTOAlmacen> obtenerTodosAlmacenes();
}
