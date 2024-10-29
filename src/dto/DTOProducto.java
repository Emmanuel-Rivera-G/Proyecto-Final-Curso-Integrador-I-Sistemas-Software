package dto;

import model.Producto;


public class DTOProducto {
    private int idProducto;
    private String nombre;
    private int stock;
    private int idCategoría;
    private String undMedida;

    public DTOProducto() {
        this.idProducto = -1;
        this.nombre = null;
        this.stock = -1;
        this.idCategoría = -1;
        this.undMedida = null;
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

    public DTOProducto setIdProducto(int idProducto) {
        this.idProducto = idProducto;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public DTOProducto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public DTOProducto setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public int getIdCategoría() {
        return idCategoría;
    }

    public DTOProducto setIdCategoría(int idCategoría) {
        this.idCategoría = idCategoría;
        return this;
    }

    public String getUndMedida() {
        return undMedida;
    }

    public DTOProducto setUndMedida(String undMedida) {
        this.undMedida = undMedida;
        return this;
    }

    public Producto toProducto() {
        return new Producto(idProducto, nombre, idCategoría, undMedida, stock);
    }
}
