package dao.interfaz;

import dto.DTOProducto;
import java.util.List;

public interface DAOProducto {

    public void agregarProducto(DTOProducto producto);
    
    public void actualizarProducto(DTOProducto producto);
    
    public void eliminarProducto(long idProducto);
    
    public DTOProducto obtenerProductoPorId(long idProducto);
    
    public List<DTOProducto> obtenerTodosLosProductos();

}
