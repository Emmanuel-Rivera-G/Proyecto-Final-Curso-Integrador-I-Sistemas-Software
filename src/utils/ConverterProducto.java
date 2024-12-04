package utils;

import dto.DTOProducto;
import java.util.ArrayList;
import java.util.List;
import model.Producto;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;

/**
 *
 * @author Emmanuel
 */
public class ConverterProducto {

    private static final Logger LOGGER = UtilsLoggerManager.getLogger(ConverterProducto.class);

    /**
     * Convierte un objeto Producto en un DTOProducto.
     * @param producto Producto a convertir.
     * @return DTOProducto correspondiente al Producto.
     */
    public static DTOProducto toDTOProducto(Producto producto) {
        LOGGER.info("Convirtiendo Producto a DTOProducto.");
        return new DTOProducto(producto);
    }

    /**
     * Convierte un objeto DTOProducto en un Producto.
     * @param dtoProducto DTOProducto a convertir.
     * @return Producto correspondiente al DTOProducto.
     */
    public static Producto toProducto(DTOProducto dtoProducto) {
        LOGGER.info("Convirtiendo DTOProducto a Producto.");
        return dtoProducto.toProducto();
    }

    /**
     * Convierte una lista de DTOProducto a una lista de Producto.
     * @param dtoProductos Lista de DTOProducto a convertir.
     * @return Lista de objetos Producto.
     */
    public static List<Producto> convertirDTOAProducto(List<DTOProducto> dtoProductos) {
        LOGGER.info("Convirtiendo lista de DTOProducto a lista de Producto.");
        return new ArrayList<>(CollectionUtils.collect(dtoProductos, DTOProducto::toProducto));
    }

    /**
     * Convierte una lista de Producto a una lista de DTOProducto.
     * @param productos Lista de Producto a convertir.
     * @return Lista de objetos DTOProducto.
     */
    public static List<DTOProducto> convertirProductosADTO(List<Producto> productos) {
        LOGGER.info("Convirtiendo lista de Producto a lista de DTOProducto.");
        return new ArrayList<>(CollectionUtils.collect(productos, ConverterProducto::toDTOProducto));
    }
}
