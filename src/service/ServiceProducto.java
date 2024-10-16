package service;

import dao.implemetacion.DAOProductoImpl;
import dao.interfaz.DAOProducto;
import dto.DTOProducto;
import java.util.List;

public class ServiceProducto {
    private DAOProducto daoProducto;

    public ServiceProducto() {
        this.daoProducto = new DAOProductoImpl();
    }

    public void agregarProducto(DTOProducto productoDTO) {
        daoProducto.agregarProducto(productoDTO);
    }

    public void actualizarProducto(DTOProducto productoDTO) {
        daoProducto.actualizarProducto(productoDTO);
    }

    public void eliminarProducto(long idProducto) {
        daoProducto.eliminarProducto(idProducto);
    }

    public DTOProducto obtenerProductoPorId(long idProducto) {
        DTOProducto producto = daoProducto.obtenerProductoPorId(idProducto);
        return producto != null ? producto : null;
    }

    public List<DTOProducto> obtenerTodosLosProductos() {
        List<DTOProducto> productos = daoProducto.obtenerTodosLosProductos();
        return productos;
    }
}
