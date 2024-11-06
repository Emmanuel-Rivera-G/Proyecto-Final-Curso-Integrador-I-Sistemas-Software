package utils;

import dto.DTOProducto;
import javax.swing.JTextField;
import org.slf4j.Logger;

/**
 *
 * @author Emmanuel
 */
public class CargadorProducto {
    private final static Logger LOGGER = UtilsLoggerManager.getLogger(CargadorProducto.class);
    
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
        if (!ValidatorProducto.esNumerico(txtCodCategoria.getText())) {
            MessageUtils.mostrarMensajeError("El campo ID CATEGORIA deben ser numérico");
            LOGGER.error("Id Categoria no es númerico");
            return null;
        }
        if (!ValidatorProducto.esNumerico(txtStockProducto.getText())) {
            MessageUtils.mostrarMensajeError("El campo STOCK deben ser numérico");
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
}
