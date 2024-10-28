package dto;

//import model.Producto;
import model.Producto;

import model.Producto;


public class DTOProducto {
    private int idProducto;
    private String nombre;
    private int stock;
    private int idCategoría;
    private String undMedida;

    public DTOProducto() {
    }

    public DTOProducto(Producto producto) {
        this.idProducto = producto.getId();
        this.nombre = producto.getNombre();
        this.stock = producto.getStock();
        this.idCategoría = producto.getIdcategoria();
        this.undMedida = producto.getUndmedida();
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdCategoría() {
        return idCategoría;
    }

    public void setIdCategoría(int idCategoría) {
        this.idCategoría = idCategoría;
    }

    public String getUndMedida() {
        return undMedida;
    }

    public void setUndMedida(String undMedida) {
        this.undMedida = undMedida;
    }

    public Producto toProducto() {
        return new Producto(nombre, idCategoría, null, stock);
    }
}
