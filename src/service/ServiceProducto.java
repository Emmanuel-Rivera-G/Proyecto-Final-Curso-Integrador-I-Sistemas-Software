package service;

import dao.implemetacion.DAOProductoImpl;
import dao.interfaz.DAOProducto;
import dto.DTOProducto;
import java.util.List;
import model.Producto;
import utils.UtilsProducto;

public class ServiceProducto {
    private DAOProducto daoProducto;

    public ServiceProducto() {
        daoProducto = new DAOProductoImpl();
    }
    
    public ServiceProducto(DAOProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    public void agregarProducto(DTOProducto dtoProducto) {
        daoProducto.agregarProducto(dtoProducto);
    }

    public void actualizarProducto(DTOProducto dtoProducto) {
        daoProducto.actualizarProducto(dtoProducto);
    }

    public void eliminarProducto(int idProducto) {
        daoProducto.eliminarProducto(idProducto);
    }

    public DTOProducto obtenerProductoPorId(int idProducto) {
        return daoProducto.obtenerProductoPorId(idProducto);
    }
    
    public DTOProducto obtenerProductoPorNombre(String nombre) {
        return daoProducto.obtenerProductoPorNombre(nombre);
    }
    
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
    
    public List<DTOProducto> obtenerTodosLosProductos() {
        return daoProducto.obtenerTodosLosProductos();
    }

    public List<Producto> obtenerTodosLosToProductos() {
        return UtilsProducto.convertirDTOaProducto(this.obtenerTodosLosProductos());
    }
    
    public List<DTOProducto> buscarProductosNombrePorPrefijo(String prefijo) {
        return UtilsProducto.filtrarPorNombreConPrefijo(obtenerTodosLosProductos(), prefijo);
    }
    
    public List<DTOProducto> buscarProductosIdPorPrefijo(String prefijo) {
        return UtilsProducto.filtrarPorIdConPrefijo(obtenerTodosLosProductos(), prefijo);
    }
    
    public List<DTOProducto> buscarProductosPorNombreOIdConPrefijo(String prefijo) {
        List<DTOProducto> productosPorNombre = buscarProductosNombrePorPrefijo(prefijo);
        
        if (productosPorNombre.isEmpty())
            return buscarProductosIdPorPrefijo(prefijo);
        
        return productosPorNombre;
    }
}
