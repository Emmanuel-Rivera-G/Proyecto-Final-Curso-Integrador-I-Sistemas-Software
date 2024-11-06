package model;

import java.time.LocalDateTime;

/**
 * Clase que representa una entrada de producto en el sistema de inventario.
 * Contiene información relacionada con el producto, la cantidad, los valores
 * asociados, la fecha de la entrada, el proveedor y el usuario que registra
 * la entrada.
 * 
 * @author Yinyer
 */
public class Entrada {

    /**
     * Identificador único de la entrada.
     */
    private int idEntrada;

    /**
     * Producto que se ingresa en la entrada.
     */
    private Producto producto;

    /**
     * Cantidad de producto que se ingresa.
     */
    private int cantidad;

    /**
     * Valor unitario del producto.
     */
    private double valorUnitario;

    /**
     * Valor total de la entrada (cantidad * valor unitario).
     */
    private double valorTotal;

    /**
     * Fecha en la que se realizó la entrada.
     */
    private LocalDateTime fechaEntrada;

    /**
     * Proveedor que suministra el producto.
     */
    private Proveedor proveedor;

    /**
     * Usuario que registra la entrada.
     */
    private Usuario usuario;

    /**
     * Constructor vacío para la clase Entrada.
     */
    public Entrada() {
    }

    /**
     * Constructor completo para la clase Entrada.
     * 
     * @param idEntrada     Identificador de la entrada.
     * @param producto      Producto de la entrada.
     * @param cantidad      Cantidad del producto.
     * @param valorUnitario Valor unitario del producto.
     * @param valorTotal    Valor total de la entrada.
     * @param fechaEntrada  Fecha de la entrada.
     * @param proveedor     Proveedor del producto.
     * @param usuario       Usuario que registra la entrada.
     */
    public Entrada(int idEntrada, Producto producto, int cantidad, double valorUnitario, double valorTotal, LocalDateTime fechaEntrada, Proveedor proveedor, Usuario usuario) {
        this.idEntrada = idEntrada;
        this.producto = producto;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.fechaEntrada = fechaEntrada;
        this.proveedor = proveedor;
        this.usuario = usuario;
    }

    /**
     * Obtiene el identificador de la entrada.
     * 
     * @return idEntrada
     */
    public int getIdEntrada() {
        return idEntrada;
    }

    /**
     * Establece el identificador de la entrada.
     * 
     * @param idEntrada Nuevo identificador de la entrada.
     */
    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    /**
     * Obtiene el producto de la entrada.
     * 
     * @return producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto de la entrada.
     * 
     * @param producto Nuevo producto para la entrada.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad de producto de la entrada.
     * 
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de producto en la entrada.
     * 
     * @param cantidad Nueva cantidad del producto.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el valor unitario del producto.
     * 
     * @return valorUnitario
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Establece el valor unitario del producto.
     * 
     * @param valorUnitario Nuevo valor unitario del producto.
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * Obtiene el valor total de la entrada.
     * 
     * @return valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Establece el valor total de la entrada.
     * 
     * @param valorTotal Nuevo valor total de la entrada.
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Obtiene la fecha en que se realizó la entrada.
     * 
     * @return fechaEntrada
     */
    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    /**
     * Establece la fecha en que se realizó la entrada.
     * 
     * @param fechaEntrada Nueva fecha de la entrada.
     */
    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * Obtiene el proveedor del producto.
     * 
     * @return proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * Establece el proveedor del producto.
     * 
     * @param proveedor Nuevo proveedor del producto.
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Obtiene el usuario que registra la entrada.
     * 
     * @return usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que registra la entrada.
     * 
     * @param usuario Nuevo usuario que registra la entrada.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
