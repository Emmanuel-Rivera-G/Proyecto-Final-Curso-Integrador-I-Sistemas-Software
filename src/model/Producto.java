package model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Elvis
 */
public class Producto {
    private int id;
    private String nombre;
    private int idcategoria;
    private String undmedida;
    private int stock;
    
    //vacio
    public Producto() {
    }

    //constructor para agregar sin ID ID

    public Producto(String nombre, int idcategoria, String undmedida, int stock) {
        this.nombre = nombre;
        this.idcategoria = idcategoria;
        this.undmedida = undmedida;
        this.stock = stock;
    }
    
    //constructor para actualizar (se esta usando Controller - ControladorProducto)
    public Producto(int id, String nombre, int idcategoria, String undmedida, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.idcategoria = idcategoria;
        this.undmedida = undmedida;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getUndmedida() {
        return undmedida;
    }

    public void setUndmedida(String undmedida) {
        this.undmedida = undmedida;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("ID", this.id)
                .append("Nombre", this.nombre)
                .append("Id Categoría", this.idcategoria)
                .append("Stock", this.stock)
                .append("Unidad de Medida", this.undmedida)
                .toString();
    }
    
}
