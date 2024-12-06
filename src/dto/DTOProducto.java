package dto;

import model.Producto;
import utils.UtilsCategoria;


public class DTOProducto {
    private int idProducto = 1;
    private String nombre;
    private int stock;
    private DTOCategoria categoria;
    private String undMedida;

    public DTOProducto() {
        this.idProducto = 1;
        this.nombre = null;
        this.stock = -1;
        this.categoria = null;
        this.undMedida = null;
    }

    public DTOProducto(Producto producto) {
        this.idProducto = producto.getId();
        this.nombre = producto.getNombre();
        this.stock = producto.getStock();
        this.categoria = UtilsCategoria.toDTOCategoria(producto.getCategoria());
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

    public int getIdCategoria() {
        return categoria.getIdCategoria();
    }

    public DTOProducto setIdCategoria(int idCategoría) {
        if (this.categoria == null) this.categoria = new DTOCategoria();
        this.categoria.setIdCategoria(idCategoría);
        return this;
    }

    public DTOCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(DTOCategoria categoria) {
        this.categoria = categoria;
    }
    
    public String getUndMedida() {
        return undMedida;
    }

    public DTOProducto setUndMedida(String undMedida) {
        this.undMedida = undMedida;
        return this;
    }

    public Producto toProducto() {
        return new Producto(idProducto, nombre, UtilsCategoria.toCategoria(categoria), undMedida, stock);
    }
}
