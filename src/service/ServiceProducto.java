package service;

import dao.implemetacion.DAOProductoImpl;
import dao.interfaz.DAOProducto;
import dto.DTOProducto;
import java.util.List;
import model.Producto;
import org.apache.commons.collections4.CollectionUtils;

public class ServiceProducto {
    private DAOProducto daoProducto;

    public ServiceProducto() {
        daoProducto = new DAOProductoImpl();
    }
    
    public ServiceProducto(DAOProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    public void agregarProducto(Producto producto) {
        DTOProducto productoDTO = new DTOProducto(producto);
        daoProducto.agregarProducto(productoDTO);
    }

    public void actualizarProducto(Producto producto) {
        DTOProducto productoDTO = new DTOProducto(producto);
        daoProducto.actualizarProducto(productoDTO);
    }

    public void eliminarProducto(int idProducto) {
        daoProducto.eliminarProducto(idProducto);
    }

    public Producto obtenerProductoPorId(int idProducto) {
        Producto producto = daoProducto.obtenerProductoPorId(idProducto).toProducto();
        return producto != null ? producto : null;
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = (List<Producto>) CollectionUtils.collect(
            daoProducto.obtenerTodosLosProductos(),
            DTOProducto::toProducto
        );
        return productos;
    }
}
