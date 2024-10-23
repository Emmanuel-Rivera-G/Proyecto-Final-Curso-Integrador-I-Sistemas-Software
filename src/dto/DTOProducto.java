package dto;

//import model.Producto;
import model.Producto;

public class DTOProducto {

    private long idProducto;
    private String nombre;
    private int stock;
    private int idCategoría;

    public DTOProducto() {
    }

    public DTOProducto(Producto producto) {
        this.idProducto = producto.getId();
        this.nombre = producto.getNombre();
        this.stock = producto.getStock();
        this.idCategoría = producto.getIdcategoria();

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

    public void setIdCategoría(int idCategoría) {
        this.idCategoría = idCategoría;
    }

    public Producto toProducto() {
        return new Producto(nombre, idCategoría, null, stock);
    }
}
