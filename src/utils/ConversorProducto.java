package utils;

import dto.DTOProducto;
import java.util.List;
import model.Producto;
import org.slf4j.Logger;

/**
 * Clase de utilidades para realizar operaciones y conversiones con objetos Producto y DTOProducto.
 * 
 * @author Emmanuel
 */
public class ConversorProducto {

    private static final Logger LOGGER = UtilsLoggerManager.getLogger(ConversorProducto.class);

    /**
     * Convierte una lista de productos en una matriz de objetos.
     * @param productos Lista de productos a convertir.
     * @return Matriz de objetos con los atributos del producto.
     */
    public static Object[][] toMatriz(List<Producto> productos) {
        try {
            if (productos == null || productos.isEmpty()) {
                LOGGER.info("La lista de productos está vacía o es nula.");
                return new Object[0][];
            }
            LOGGER.info("Iniciando conversión de lista de productos a matriz de objetos. Tamaño de la lista: {}", productos.size());
            Object[][] data = new Object[productos.size()][];
            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                data[i] = ConversorProducto.toArray(p);
            }
            LOGGER.info("Conversión de lista de productos completada con éxito.");
            return data;
        } catch (Exception e) {
            LOGGER.error("Error durante la conversión de lista de productos a matriz de objetos: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Convierte un objeto Producto en un array de objetos.
     * @param producto Producto a convertir.
     * @return Array de objetos con los atributos del producto.
     * @throws NullPointerException Si el producto es nulo.
     */
    public static Object[] toArray(Producto producto) throws NullPointerException {
        try {
            if (producto == null) {
                LOGGER.error("El producto proporcionado es nulo.");
                throw new NullPointerException("El producto proporcionado es nulo.");
            }
            LOGGER.info("Convirtiendo producto con ID: {} a un array de objetos.", producto.getId());
            Object[] data = new Object[]{
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria().getIdcat(),
                producto.getUndmedida(),
                producto.getStock()
            };
            LOGGER.info("Producto convertido con éxito a un array de objetos.");
            return data;
        } catch (Exception e) {
            LOGGER.error("Error durante la conversión del producto a array de objetos: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Convierte un objeto DTOProducto en un array de objetos.
     * @param producto DTOProducto a convertir.
     * @return Array de objetos con los atributos del producto.
     * @throws NullPointerException Si el producto es nulo.
     */
    public static Object[] toArray(DTOProducto producto) throws NullPointerException {
        try {
            if (producto == null) {
                LOGGER.error("El DTOProducto proporcionado es nulo.");
                throw new NullPointerException("El DTOProducto proporcionado es nulo.");
            }
            LOGGER.info("Convirtiendo DTOProducto a Producto para posterior conversión.");
            Object[] data = ConversorProducto.toArray(ConverterProducto.toProducto(producto));
            LOGGER.info("DTOProducto convertido con éxito a un array de objetos.");
            return data;
        } catch (Exception e) {
            LOGGER.error("Error durante la conversión de DTOProducto a array de objetos: {}", e.getMessage(), e);
            throw e;
        }
    }
}
