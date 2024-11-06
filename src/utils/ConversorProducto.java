package utils;

import dto.DTOProducto;
import java.util.List;
import model.Producto;

/**
 * Clase de utilidades para realizar operaciones y conversiones con objetos Producto y DTOProducto.
 * 
 * @author Emmanuel
 */
public class ConversorProducto {
    
    /**
     * Convierte una lista de productos en una matriz de objetos.
     * @param productos Lista de productos a convertir.
     * @return Matriz de objetos con los atributos del producto.
     */
    public static Object[][] toMatriz(List<Producto> productos) {
        Object[][] data = new Object[productos.size()][];
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            data[i] = ConversorProducto.toArray(p);
        }
        return data;
    }
    
    /**
     * Convierte un objeto Producto en un array de objetos.
     * @param producto Producto a convertir.
     * @return Array de objetos con los atributos del producto.
     * @throws NullPointerException Si el producto es nulo.
     */
    public static Object[] toArray(Producto producto) throws NullPointerException {
        return new Object[]{
            producto.getId(),
            producto.getNombre(),
            producto.getCategoria().getIdcat(),
            producto.getUndmedida(),
            producto.getStock()
        };
    }
    
    /**
     * Convierte un objeto DTOProducto en un array de objetos.
     * @param producto DTOProducto a convertir.
     * @return Array de objetos con los atributos del producto.
     * @throws NullPointerException Si el producto es nulo.
     */
    public static Object[] toArray(DTOProducto producto) throws NullPointerException {
        return ConversorProducto.toArray(ConverterProducto.toProducto(producto));
    }
}
