package utils;

import dto.DTOProducto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emmanuel
 */
public class TableModelProducto {
    
    /**
    * Crea un modelo de tabla completo con los títulos especificados y los datos de la lista de DTOProducto.
    *
    * @param titulos Array de títulos para las columnas de la tabla.
    * @param lista Lista de DTOProducto que se añadirá a la tabla.
    * @return DefaultTableModel con los datos y títulos proporcionados.
    */
    public static DefaultTableModel crearTablaCompleta(Object[] titulos, List<DTOProducto> lista) {
        DefaultTableModel tabla = new DefaultTableModel(titulos, 0);
        for (DTOProducto dtoProducto : lista) {
            agregarFilaDTOProductoATabla(tabla, dtoProducto);
        }
        return tabla;
    }
    
    /**
    * Actualiza el modelo de tabla con la lista completa de DTOProducto, limpiando el contenido anterior.
    *
    * @param modeloTabla DefaultTableModel que se actualizará con los datos de la lista.
    * @param lista Lista de DTOProducto que se usará para llenar la tabla.
    * @throws NullPointerException Si el producto es nulo.
    */
    public static void actualizarTablaCompleta(DefaultTableModel modeloTabla, List<DTOProducto> lista) throws NullPointerException {
        modeloTabla.setRowCount(0);
        for (DTOProducto dtoProducto : lista) {
            agregarFilaDTOProductoATabla(modeloTabla, dtoProducto);
        }
    }
    
    /**
    * Actualiza el modelo de tabla agregando un solo DTOProducto, limpiando el contenido anterior.
    *
    * @param modeloTabla DefaultTableModel que se actualizará con el DTOProducto proporcionado.
    * @param dtoProducto DTOProducto que se añadirá a la tabla.
    * @throws NullPointerException Si el producto es nulo.
    */
    public static void actualizarTablaCompleta(DefaultTableModel modeloTabla, DTOProducto dtoProducto) throws NullPointerException {
        if (dtoProducto == null) throw new NullPointerException("No debe ser nulo.");
        List<DTOProducto> lista = new ArrayList<DTOProducto>();
        lista.add(dtoProducto);
        actualizarTablaCompleta(modeloTabla, lista);
    }
    
    /**
    * Agrega una fila al modelo de tabla utilizando los datos de un DTOProducto.
    *
    * @param modeloTabla DefaultTableModel al que se añadirá la fila.
    * @param dtoProducto DTOProducto cuyos datos se agregarán como nueva fila.
    * @throws NullPointerException Si el producto es nulo.
    */
    public static void agregarFilaDTOProductoATabla(DefaultTableModel modeloTabla, DTOProducto dtoProducto) throws NullPointerException {
        modeloTabla.addRow(ConversorProducto.toArray(dtoProducto));
    }
}
