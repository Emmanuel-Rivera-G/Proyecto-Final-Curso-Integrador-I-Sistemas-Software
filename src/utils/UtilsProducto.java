package utils;

import dto.DTOProducto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Producto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;

/**
 * Clase de utilidades para realizar operaciones y conversiones con objetos Producto y DTOProducto.
 * 
 * @author Emmanuel
 */
public class UtilsProducto {
    private final static Logger LOGGER = UtilsLoggerManager.getLogger(UtilsProducto.class);
    
    /**
     * Convierte una lista de productos en una matriz de objetos.
     * @param productos Lista de productos a convertir.
     * @return Matriz de objetos con los atributos del producto.
     */
    public static Object[][] convertirAMatriz(List<Producto> productos) {
        Object[][] data = new Object[productos.size()][];
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            data[i] = new Object[] {
                p.getId(),
                p.getNombre(),
                p.getStock(),
                p.getCategoria().getNombre(),
                p.getUndmedida()
            };
        }
        return data;
    }
    
    /**
     * Convierte un objeto Producto en un array de objetos.
     * @param producto Producto a convertir.
     * @return Array de objetos con los atributos del producto.
     * @throws NullPointerException Si el producto es nulo.
     */
    public static Object[] convertirAArray(Producto producto) throws NullPointerException {
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
    public static Object[] convertirAArray(DTOProducto producto) throws NullPointerException {
        return convertirAArray(producto.toProducto());
    }
    
    /**
     * Convierte un objeto Producto en un DTOProducto.
     * @param producto Producto a convertir.
     * @return DTOProducto correspondiente al Producto.
     */
    public static DTOProducto toDTOProducto(Producto producto) {
        return new DTOProducto(producto);
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
    
    /**
     * Obtiene una lista de productos únicos entre dos listas de DTOProducto.
     * @param lista1 Primera lista de DTOProducto.
     * @param lista2 Segunda lista de DTOProducto.
     * @return Lista de productos únicos en la primera lista.
     */
    public static List<DTOProducto> obtenerProductosUnicos(List<DTOProducto> lista1, List<DTOProducto> lista2) {
        return new ArrayList<>(CollectionUtils.subtract(lista1, lista2));
    }
    
    /**
     * Filtra una lista de DTOProducto por nombre con un prefijo específico.
     * @param productos Lista de productos a filtrar.
     * @param prefijo Prefijo del nombre a buscar.
     * @return Lista de productos cuyo nombre empieza con el prefijo.
     */
    public static synchronized List<DTOProducto> filtrarPorNombreConPrefijo(List<DTOProducto> productos, String prefijo) {
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
    public static synchronized List<DTOProducto> filtrarPorIdConPrefijo(List<DTOProducto> productos, String prefijo) {
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
    public static synchronized List<DTOProducto> filtrarPorVariosCriterios(List<DTOProducto> productos, Predicate<DTOProducto> criterio) {
        return new ArrayList<>(CollectionUtils.select(productos, criterio));
    }
    
    /**
     * Valida los campos necesarios para crear un DTOProducto en una vista.
     * @param vistaProductoNombre Nombre del producto.
     * @param vistaProductoIdCategoria ID de la categoría.
     * @param vistaProductoUndMedida Unidad de medida.
     * @param vistaProductoStock Stock del producto.
     * @return true si algún campo está vacío, de lo contrario false.
     */
    public static boolean validarCamposVistaParaDTOProducto(String vistaProductoNombre, String vistaProductoIdCategoria,
                                                            String vistaProductoUndMedida, String vistaProductoStock) {
        
        boolean alMenosUnCampoVacio = "".equals(vistaProductoNombre) || 
                               "".equals(vistaProductoIdCategoria) || 
                               "".equals(vistaProductoUndMedida)||
                               "".equals(vistaProductoStock);
        boolean validado = true;
        
        if (alMenosUnCampoVacio) {
            UtilsProducto.mostrarMensajeError("Debe llenar los campos");
            LOGGER.error("Llenar todos los campos de la vista para DTOProducto.");
            validado = false;
        }
        return validado;
    }
        
    /**
     * Carga datos desde componentes JTextField para crear un DTOProducto.
     * @param txtNombreProducto Campo de texto para el nombre.
     * @param txtCodCategoria Campo de texto para el ID de categoría.
     * @param txtUndMedida Campo de texto para la unidad de medida.
     * @param txtStockProducto Campo de texto para el stock.
     * @return DTOProducto si los datos son válidos; null en caso contrario.
     */
    public static DTOProducto cargarDatosDeLabelsADTOProducto(
            JTextField txtNombreProducto,
            JTextField txtCodCategoria,
            JTextField txtUndMedida,
            JTextField txtStockProducto
    ) {
        DTOProducto dtoProducto = new DTOProducto();
        if (!UtilsProducto.esNumerico(txtCodCategoria.getText())) {
            UtilsProducto.mostrarMensajeError("El campo ID CATEGORIA deben ser numérico");
            LOGGER.error("Id Categoria no es númerico");
            return null;
        }
        if (!UtilsProducto.esNumerico(txtStockProducto.getText())) {
            UtilsProducto.mostrarMensajeError("El campo STOCK deben ser numérico");
            LOGGER.error("Stock no es númerico");
            return null;
        }
        try {
            dtoProducto.setNombre(txtNombreProducto.getText());
            dtoProducto.setIdCategoria(Integer.parseInt(txtCodCategoria.getText()));
            dtoProducto.setUndMedida(txtUndMedida.getText());
            dtoProducto.setStock(Integer.parseInt(txtStockProducto.getText()));
        } catch (Exception e) {
            LOGGER.error("Error en la carga de datos: " + e.getMessage());
            return null;
        }
        return dtoProducto;
    }
    
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
        modeloTabla.addRow(UtilsProducto.convertirAArray(dtoProducto));
    }
    
    /**
    * Muestra un mensaje de error en un cuadro de diálogo con un título especificado.
    *
    * @param mensaje Texto del mensaje de error a mostrar.
    * @param title Título del cuadro de diálogo.
    */
    public static void mostrarMensajeError(String mensaje, String title) {
        JOptionPane.showMessageDialog(null, mensaje, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
    * Muestra un mensaje de error en un cuadro de diálogo con el título predeterminado "ERROR".
    *
    * @param mensaje Texto del mensaje de error a mostrar.
    */
    public static void mostrarMensajeError(String mensaje) {
        mostrarMensajeError(mensaje, "ERROR");
    }
    
    /**
    * Muestra un mensaje de éxito en un cuadro de diálogo con un título especificado.
    *
    * @param mensaje Texto del mensaje de éxito a mostrar.
    * @param title Título del cuadro de diálogo.
    */
    public static void mostrarMensajeExitoso(String mensaje, String title) {
        JOptionPane.showMessageDialog(null, mensaje, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
    * Muestra un mensaje de éxito en un cuadro de diálogo con el título predeterminado "INFORMACION".
    *
    * @param mensaje Texto del mensaje de éxito a mostrar.
    */
    public static void mostrarMensajeExitoso(String mensaje) {
        mostrarMensajeExitoso(mensaje, "INFORMACION");
    }
    
    /**
    * Verifica si una cadena representa un número válido.
    *
    * @param param Cadena a verificar.
    * @return true si la cadena es numérica; false en caso contrario.
    */
    public static boolean esNumerico(String param) {
        return NumberUtils.isCreatable(param);
    }
}
