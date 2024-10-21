package dto;

//import model.Producto;

import model.Producto;


public class DTOProducto {
    private long idProducto;
    private String nombre;
    private int stock;
    private long idCategoría;
    private double precioVenta;

    public DTOProducto() {
    }

    public DTOProducto(Producto producto) {
        this.idProducto = producto.getId();
        this.nombre = producto.getNombre();
        this.stock = producto.getStock();
        this.idCategoría = producto.getIdcategoria();
        //this.precioVenta = producto.getPrecioVenta();
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
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

    public long getIdCategoría() {
        return idCategoría;
    }

    public void setIdCategoría(long idCategoría) {
        this.idCategoría = idCategoría;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    public Producto toProducto() {
        //return new Producto(idProducto, nombre, stock, idCategoría, precioVenta);
        return null;
    }
}
