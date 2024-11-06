package utils;

import dto.DTOProducto;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author USER
 */
public class FiltradorProducto {
    
    /**
     * Filtra una lista de DTOProducto por nombre con un prefijo específico.
     * @param productos Lista de productos a filtrar.
     * @param prefijo Prefijo del nombre a buscar.
     * @return Lista de productos cuyo nombre empieza con el prefijo.
     */
    public static List<DTOProducto> filtrarPorNombreConPrefijo(List<DTOProducto> productos, String prefijo) {
        return filtrarPorVariosCriterios(productos, new Predicate<DTOProducto>() {
            @Override
            public boolean evaluate(DTOProducto producto) {
                return StringUtils.startsWithIgnoreCase(producto.getNombre(), prefijo);
            }
        });
    }
    
    /**
     * Filtra una lista de DTOProducto por ID con un prefijo específico.
     * @param productos Lista de productos a filtrar.
     * @param prefijo Prefijo del ID a buscar.
     * @return Lista de productos cuyo ID empieza con el prefijo.
     */
    public static List<DTOProducto> filtrarPorIdConPrefijo(List<DTOProducto> productos, String prefijo) {
        return filtrarPorVariosCriterios(productos, new Predicate<DTOProducto>() {
            @Override
            public boolean evaluate(DTOProducto producto) {
                return StringUtils.startsWith(String.valueOf(producto.getIdProducto()), prefijo);
            }
        });
    }
    
    /**
     * Filtra una lista de DTOProducto utilizando un criterio personalizado.
     * @param productos Lista de productos a filtrar.
     * @param criterio Criterio de filtrado.
     * @return Lista de productos que cumplen con el criterio.
     */
    public static List<DTOProducto> filtrarPorVariosCriterios(List<DTOProducto> productos, Predicate<DTOProducto> criterio) {
        return new ArrayList<>(CollectionUtils.select(productos, criterio));
    }
    
    /**
     * Obtiene una lista de productos únicos entre dos listas de DTOProducto.
     * @param lista1 Primera lista de DTOProducto.
     * @param lista2 Segunda lista de DTOProducto.
     * @return Lista de productos únicos en la primera lista.
     */
    public static List<DTOProducto> filtrarProductosUnicos(List<DTOProducto> lista1, List<DTOProducto> lista2) {
        return new ArrayList<>(CollectionUtils.subtract(lista1, lista2));
    }
}
