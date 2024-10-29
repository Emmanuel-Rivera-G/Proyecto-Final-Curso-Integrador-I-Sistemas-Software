package dao.interfaz;

import dto.DTOCategoria;
import java.util.List;

/**
 *
 * @author Yinyer
 */
public interface DAOCategoria {
    /**
     * Agrega una nueva categoria a la base de datos.
     *
     * @param categoria el objeto {@link DTOCategoria} que representa la categoria a agregar
     */
    public void agregarCategoria(DTOCategoria categoria);
    
    /**
     * Actualiza los datos de una categoria existente en la base de datos.
     *
     * @param categoria el objeto {@link DTOCategoria} con los datos actualizados de la categoria
     */
    public void actualizarCategoria(DTOCategoria categoria);
    
    /**
     * Elimina una categoria de la base de datos según su identificador único.
     *
     * @param idCategoria el identificador único de la categoria a eliminar
     */
    public void eliminarCategoria(int idCategoria);
    
    /**
     * Obtiene una categoria de la base de datos basado en su identificador único.
     *
     * @param idCategoria el identificador único de la categoria a consultar
     * @return el objeto {@link DTOCategoria} que representa la categoria encontrada, o {@code null} si no existe
     */
    public DTOCategoria obtenerCategoriaPorId(int idCategoria);
    
    /**
     * Obtiene una categoria de la base de datos basado en su nombre.
     *
     * @param nombre el nombre de la categoria a consultar
     * @return el objeto {@link DTOCategoria} que representa la categoria encontrada, o {@code null} si no existe
     */
    public DTOCategoria obtenerCategoriaPorNombre(String nombre);
    
    /**
     * Obtiene una lista de todas las categorias almacenadas en la base de datos.
     *
     * @return una lista de objetos {@link DTOCategoria} que representan todos las categorias almacenadas
     */
    public List<DTOCategoria> obtenerTodosLasCategorias();
}
