package dao.interfaz;

import dto.DTOProveedor;
import java.util.List;

/**
 * Interfaz DAO (Data Access Object) para la entidad Proveedor.
 * Define las operaciones básicas de acceso a datos para la entidad Proveedor.
 * 
 * Esta interfaz permite realizar operaciones como insertar, actualizar, eliminar
 * y obtener proveedores desde la base de datos.
 * 
 * @author Yinyer
 */
public interface DAOProveedor {

    /**
     * Inserta un nuevo proveedor en la base de datos.
     * 
     * @param proveedor Objeto DTOProveedor que contiene la información del proveedor a insertar.
     * @return El identificador único del proveedor insertado.
     */
    public int insertarProveedor(DTOProveedor proveedor);

    /**
     * Actualiza la información de un proveedor existente en la base de datos.
     * 
     * @param proveedor Objeto DTOProveedor que contiene los nuevos datos del proveedor a actualizar.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarProveedor(DTOProveedor proveedor);

    /**
     * Elimina un proveedor de la base de datos por su identificador único.
     * 
     * @param idProveedor El identificador único del proveedor a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarProveedor(int idProveedor);

    /**
     * Obtiene un proveedor de la base de datos por su identificador único.
     * 
     * @param idProveedor El identificador único del proveedor a obtener.
     * @return Un objeto DTOProveedor con la información del proveedor, o null si no se encuentra.
     */
    public DTOProveedor obtenerProveedorPorId(int idProveedor);

    /**
     * Obtiene todos los proveedores de la base de datos.
     * 
     * @return Una lista de objetos DTOProveedor que representan todos los proveedores.
     */
    public List<DTOProveedor> obtenerTodosProveedores();
}
