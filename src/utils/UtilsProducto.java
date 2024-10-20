package utils;

import java.util.List;
import model.Producto;

public class UtilsProducto {
    public static Object[][] convertirAArray(List<Producto> productos) {
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
}
