package utils;

import dto.DTOProducto;
import java.util.ArrayList;
import java.util.List;
import model.Producto;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author Emmanuel
 */
public class ConverterProducto {
    /**
     * Convierte un objeto Producto en un DTOProducto.
     * @param producto Producto a convertir.
     * @return DTOProducto correspondiente al Producto.
     */
    public static DTOProducto toDTOProducto(Producto producto) {
        return new DTOProducto(producto);
    }
    
    public Producto toProducto(DTOProducto dtoProducto) {
        return dtoProducto.toProducto();
    }
    
    /**
     * Convierte una lista de DTOProducto a una lista de Producto.
     * @param dtoProductos Lista de DTOProducto a convertir.
     * @return Lista de objetos Producto.
     */
    public static List<Producto> convertirDTOAProducto(List<DTOProducto> dtoProductos) {
        return new ArrayList<>(CollectionUtils.collect(dtoProductos, DTOProducto::toProducto));
    }
    
    /**
     * Convierte una lista de Producto a una lista de DTOProducto.
     * @param productos Lista de Producto a convertir.
     * @return Lista de objetos DTOProducto.
     */
    public static List<DTOProducto> convertirProductosADTO(List<Producto> productos) {
        return new ArrayList<>(CollectionUtils.collect(productos, UtilsProducto::toDTOProducto));
    }
}
