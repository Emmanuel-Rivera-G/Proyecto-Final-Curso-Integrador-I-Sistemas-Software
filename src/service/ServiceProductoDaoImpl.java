package service;

import dao.implemetacion.DAOProductoImpl;
import dao.interfaz.DAOProducto;
import dto.DTOProducto;
import java.util.List;
import model.Producto;
import service.interfaz.ServiceProducto;
import utils.UtilsProducto;

/**
 * La clase ServiceProductoDaoImpl proporciona servicios para gestionar productos,
 incluyendo operaciones CRUD y búsquedas avanzadas.
 * 
 * @author Emmanuel
 */
public class ServiceProductoDaoImpl implements ServiceProducto {
    private DAOProducto daoProducto;

    /**
     * Constructor que inicializa el DAO con la implementación por defecto.
     */
    public ServiceProductoDaoImpl() {
        daoProducto = new DAOProductoImpl();
    }
    
    /**
     * Constructor que permite inyectar una implementación específica de DAOProducto.
     *
     * @param daoProducto la implementación de DAOProducto a utilizar
     */
    public ServiceProductoDaoImpl(DAOProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    /**
     * Agrega un nuevo producto a la base de datos.
     *
     * @param dtoProducto el DTO del producto a agregar
     */
    public void agregarProducto(DTOProducto dtoProducto) {
        daoProducto.agregarProducto(dtoProducto);
    }

    /**
     * Actualiza un producto existente en la base de datos.
     *
     * @param dtoProducto el DTO del producto a actualizar
     */
    public void actualizarProducto(DTOProducto dtoProducto) {
        daoProducto.actualizarProducto(dtoProducto);
    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param idProducto el ID del producto a eliminar
     */
    public void eliminarProducto(int idProducto) {
        daoProducto.eliminarProducto(idProducto);
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param idProducto el ID del producto
     * @return el DTO del producto si se encuentra; de lo contrario, null
     */
    public DTOProducto obtenerProductoPorId(int idProducto) {
        return daoProducto.obtenerProductoPorId(idProducto);
    }
    
    /**
     * Obtiene un producto por su nombre.
     *
     * @param nombre el nombre del producto
     * @return el DTO del producto si se encuentra; de lo contrario, null
     */
    public DTOProducto obtenerProductoPorNombre(String nombre) {
        return daoProducto.obtenerProductoPorNombre(nombre);
    }
    
    /**
     * Obtiene un producto por su nombre o ID dado un parámetro.
     * Si el parámetro no corresponde a un nombre, se intenta convertir en un ID.
     *
     * @param param el nombre o ID del producto como cadena de texto
     * @return el DTO del producto si se encuentra; de lo contrario, null
     */
    public DTOProducto obtenerProductoPorParametro(String param) {
        try {
            DTOProducto dtoProducto = obtenerProductoPorNombre(param);
            if (dtoProducto == null)
                try {
                    int id = Integer.parseInt(param);
                    return obtenerProductoPorId(id);
                } catch (NumberFormatException e) {
                    return null;
                }
            return dtoProducto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Obtiene una lista de todos los productos en la base de datos.
     *
     * @return una lista de DTOProducto con todos los productos
     */
    public List<DTOProducto> obtenerTodosLosProductos() {
        return daoProducto.obtenerTodosLosProductos();
    }

    /**
     * Obtiene una lista de todos los productos convertidos al modelo Producto.
     *
     * @return una lista de Producto con todos los productos
     */
    public List<Producto> obtenerTodosLosToProductos() {
        return UtilsProducto.convertirDTOAProducto(this.obtenerTodosLosProductos());
    }
    
    /**
     * Busca productos cuyo nombre empieza con un prefijo dado.
     *
     * @param prefijo el prefijo a buscar en los nombres de productos
     * @return una lista de DTOProducto que cumple con el prefijo en el nombre
     */
    public List<DTOProducto> buscarProductosNombrePorPrefijo(String prefijo) {
        return UtilsProducto.filtrarPorNombreConPrefijo(obtenerTodosLosProductos(), prefijo);
    }
    
    /**
     * Busca productos cuyos IDs empiezan con un prefijo dado.
     *
     * @param prefijo el prefijo a buscar en los IDs de productos
     * @return una lista de DTOProducto que cumple con el prefijo en el ID
     */
    public List<DTOProducto> buscarProductosIdPorPrefijo(String prefijo) {
        return UtilsProducto.filtrarPorIdConPrefijo(obtenerTodosLosProductos(), prefijo);
    }
    
    /**
     * Busca productos por nombre o ID cuyo valor empieza con un prefijo dado.
     * Primero intenta con nombres, y si no hay resultados, intenta con IDs.
     *
     * @param prefijo el prefijo a buscar en nombres o IDs de productos
     * @return una lista de DTOProducto que cumple con el prefijo en nombre o ID
     */
    public List<DTOProducto> buscarProductosPorNombreOIdConPrefijo(String prefijo) {
        List<DTOProducto> productosPorNombre = buscarProductosNombrePorPrefijo(prefijo);
        
        if (productosPorNombre.isEmpty())
            return buscarProductosIdPorPrefijo(prefijo);
        
        return productosPorNombre;
    }
}
