package dto;



import model.Producto;


public class DTOProducto {
    private int idProducto;
    private String nombre;
    private int idCategoría;
    private String undMedida;
    private int stock;

    public DTOProducto() {
    }
    
    public DTOProducto(String nombre, int idCategoría, String undMedida, int stock) {
        this.idProducto = -1;
        this.nombre = nombre;
        this.idCategoría = idCategoría;
        this.undMedida = undMedida;
        this.stock = stock;
    }

    public DTOProducto(int idProducto, String nombre, int idCategoría, String undMedida, int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.idCategoría = idCategoría;
        this.undMedida = undMedida;
        this.stock = stock;
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
        return new Producto(idProducto, nombre, idCategoría, undMedida, stock);
    }
    
    public static DTOProducto toDTOProducto(Producto producto) {
        return new DTOProducto(producto);
    }
}
