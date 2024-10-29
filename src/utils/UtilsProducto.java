package utils;

import dto.DTOProducto;
import java.util.ArrayList;
import java.util.List;
import model.Producto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

public class UtilsProducto {
    public static Object[][] convertirAMatriz(List<Producto> productos) {
        Object[][] data = new Object[productos.size()][];
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            data[i] = new Object[] {
                p.getId(),
                p.getNombre(),
                p.getStock(),
                p.getIdcategoria(),
                p.getUndmedida()
            };
        }
        return data;
    }
    
    public static Object[] convertirAArray(Producto producto) {
        return new Object[]{
            producto.getId(),
            producto.getNombre(),
            producto.getIdcategoria(),
            producto.getUndmedida(),
            producto.getStock()
        };
    }
    
    public static Object[] convertirAArray(DTOProducto producto) {
        return convertirAArray(producto.toProducto());
    }
    
    public static DTOProducto toDTOProducto(Producto producto) {
        return new DTOProducto(producto);
    }
    
    public static List<Producto> convertirDTOaProducto(List<DTOProducto> dtoProductos) {
        return new ArrayList<>(CollectionUtils.collect(dtoProductos, DTOProducto::toProducto));
    }
    
    public static List<DTOProducto> convertirProductoaDTO(List<Producto> productos) {
        return new ArrayList<>(CollectionUtils.collect(productos, UtilsProducto::toDTOProducto));
    }
    
    public static List<DTOProducto> filtrarPorNombreConPrefijo(List<DTOProducto> productos, String prefijo) {
        return new ArrayList<>(CollectionUtils.select(productos, new Predicate<DTOProducto>() {
            @Override
            public boolean evaluate(DTOProducto producto) {
                return StringUtils.startsWithIgnoreCase(producto.getNombre(), prefijo);
            }
        }));
    }
    
    public static List<DTOProducto> filtrarPorIdConPrefijo(List<DTOProducto> productos, String prefijo) {
        return new ArrayList<>(CollectionUtils.select(productos, new Predicate<DTOProducto>() {
            @Override
            public boolean evaluate(DTOProducto producto) {
                return StringUtils.startsWith(String.valueOf(producto.getIdProducto()), prefijo);
            }
        }));
    }
}
