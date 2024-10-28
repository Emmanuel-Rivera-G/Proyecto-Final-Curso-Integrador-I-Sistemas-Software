package dao.interfaz;

import dto.DTOProducto;
import java.util.List;

/**
 * Interfaz que define los métodos CRUD para interactuar con objetos de tipo {@link DTOProducto}.
 * Permite realizar operaciones básicas como agregar, actualizar, eliminar y consultar productos
 * en la base de datos.
 * 
 * @author Emmanuel
 */
public interface DAOProducto {

    /**
     * Agrega un nuevo producto a la base de datos.
     *
     * @param producto el objeto {@link DTOProducto} que representa el producto a agregar
     */
    public void agregarProducto(DTOProducto producto);
    
    /**
     * Actualiza los datos de un producto existente en la base de datos.
     *
     * @param producto el objeto {@link DTOProducto} con los datos actualizados del producto
     */
    public void actualizarProducto(DTOProducto producto);
    
    /**
     * Elimina un producto de la base de datos según su identificador único.
     *
     * @param idProducto el identificador único del producto a eliminar
     */
    public void eliminarProducto(int idProducto);
    
    /**
     * Obtiene un producto de la base de datos basado en su identificador único.
     *
     * @param idProducto el identificador único del producto a consultar
     * @return el objeto {@link DTOProducto} que representa el producto encontrado, o {@code null} si no existe
     */
    public DTOProducto obtenerProductoPorId(int idProducto);
    
    /**
     * Obtiene una lista de todos los productos almacenados en la base de datos.
     *
     * @return una lista de objetos {@link DTOProducto} que representan todos los productos almacenados
     */
    public List<DTOProducto> obtenerTodosLosProductos();
}
