package model;

/**
 *
 * @author Elvis
 */
public class Categoria {
    private int idcat;
    private String nombre;
    
    //constructor Vacio
    public Categoria() {
    }
    
    
    //por polimorfismo en estos constructores no generara error
    // constructor para agregar sin ID.

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    //contructor para actualizar
    public Categoria(int idcat, String nombre) {
        this.idcat = idcat;
        this.nombre = nombre;
    }
    //getter y setter
    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
