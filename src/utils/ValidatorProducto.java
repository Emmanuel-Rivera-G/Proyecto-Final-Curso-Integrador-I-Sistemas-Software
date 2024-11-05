package utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;

/**
 *
 * @author Emmanuel
 */
public class ValidatorProducto {
    private final static Logger LOGGER = UtilsLoggerManager.getLogger(UtilsProducto.class);
    
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
    * Verifica si una cadena representa un número válido.
    *
    * @param param Cadena a verificar.
    * @return true si la cadena es numérica; false en caso contrario.
    */
    public static boolean esNumerico(String param) {
        return NumberUtils.isCreatable(param);
    }
}
