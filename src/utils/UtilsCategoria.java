package utils;

import dto.DTOCategoria;
import model.Categoria;

/**
 *
 * @author Yinyer
 */
public class UtilsCategoria {
    
    public static DTOCategoria toDTOCategoria(Categoria categoria) {
        return new DTOCategoria(categoria);
    }
    
    public static Categoria toCategoria(DTOCategoria dtoCategoria) {
        return new Categoria(dtoCategoria.getIdCategoria(), dtoCategoria.getNombre());
    }
    
}
