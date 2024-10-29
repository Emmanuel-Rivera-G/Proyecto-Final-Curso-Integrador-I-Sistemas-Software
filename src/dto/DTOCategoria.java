package dto;

import model.Categoria;

/**
 *
 * @author Yinyer
 */
public class DTOCategoria {
    private int idCategoria;
    private String nombre;

    public DTOCategoria() {
        this.idCategoria = -1;
        this.nombre = null;
    }
    
    public DTOCategoria(Categoria categoria) {
        this.idCategoria = categoria.getIdcat();
        this.nombre = categoria.getNombre();
    }
    
    public int getIdCategoria() {
        return idCategoria;
    }
    public DTOCategoria setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
        return this;
    }
    public String getNombre() {
        return nombre;
    }
    public DTOCategoria setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    
}
