package service.interfaz;

import dto.DTOProducto;
import model.Producto;
import java.util.List;

/**
 * Interfaz para los servicios relacionados con productos.
 * Proporciona métodos para gestionar productos, incluyendo operaciones CRUD y búsquedas avanzadas.
 * 
 * @author Emmanuel
 */
public interface ServiceProducto {

    /**
     * Agrega un nuevo producto a la base de datos.
     *
     * @param dtoProducto el DTO del producto a agregar
     */
    public void agregarProducto(DTOProducto dtoProducto);

    /**
     * Actualiza un producto existente en la base de datos.
     *
     * @param dtoProducto el DTO del producto a actualizar
     */
    public void actualizarProducto(DTOProducto dtoProducto);

    /**
     * Elimina un producto de la base de datos.
     *
     * @param idProducto el ID del producto a eliminar
     */
    public void eliminarProducto(int idProducto);

    /**
     * Obtiene un producto por su ID.
     *
     * @param idProducto el ID del producto
     * @return el DTO del producto si se encuentra; de lo contrario, null
     */
    public DTOProducto obtenerProductoPorId(int idProducto);

    /**
     * Obtiene un producto por su nombre.
     *
     * @param nombre el nombre del producto
     * @return el DTO del producto si se encuentra; de lo contrario, null
     */
    public DTOProducto obtenerProductoPorNombre(String nombre);

    /**
     * Obtiene un producto por su nombre o ID dado un parámetro.
     *
     * @param param el nombre o ID del producto como cadena de texto
     * @return el DTO del producto si se encuentra; de lo contrario, null
     */
    public DTOProducto obtenerProductoPorParametro(String param);

    /**
     * Obtiene una lista de todos los productos en la base de datos.
     *
     * @return una lista de DTOProducto con todos los productos
     */
    public List<DTOProducto> obtenerTodosLosProductos();

    /**
     * Obtiene una lista de todos los productos convertidos al modelo Producto.
     *
     * @return una lista de Producto con todos los productos
     */
    public List<Producto> obtenerTodosLosToProductos();

    /**
     * Busca productos cuyo nombre empieza con un prefijo dado.
     *
     * @param prefijo el prefijo a buscar en los nombres de productos
     * @return una lista de DTOProducto que cumple con el prefijo en el nombre
     */
    public List<DTOProducto> buscarProductosNombrePorPrefijo(String prefijo);

    /**
     * Busca productos cuyos IDs empiezan con un prefijo dado.
     *
     * @param prefijo el prefijo a buscar en los IDs de productos
     * @return una lista de DTOProducto que cumple con el prefijo en el ID
     */
    public List<DTOProducto> buscarProductosIdPorPrefijo(String prefijo);

    /**
     * Busca productos por nombre o ID cuyo valor empieza con un prefijo dado.
     *
     * @param prefijo el prefijo a buscar en nombres o IDs de productos
     * @return una lista de DTOProducto que cumple con el prefijo en nombre o ID
     */
    public List<DTOProducto> buscarProductosPorNombreOIdConPrefijo(String prefijo);
}
