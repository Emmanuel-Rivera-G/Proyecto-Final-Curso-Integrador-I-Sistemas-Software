package model;

import java.time.LocalDateTime;

/**
 * Clase que representa una salida de producto en el sistema de inventario.
 * Contiene información sobre el producto que se retira, la cantidad, valores
 * monetarios asociados, la fecha de la salida, el usuario que la realiza y 
 * el destino de los productos.
 * 
 * @author Yinyer
 */
public class Salida {

    /**
     * Identificador único de la salida.
     */
    private int idSalida;

    /**
     * Producto que está siendo retirado en la salida.
     */
    private Producto producto;

    /**
     * Cantidad de producto que se retira.
     */
    private int cantidad;

    /**
     * Valor unitario del producto.
     */
    private double valorUnitario;

    /**
     * Valor total de la salida (cantidad * valor unitario).
     */
    private double valorTotal;

    /**
     * Fecha en que se realizó la salida.
     */
    private LocalDateTime fechaSalida;

    /**
     * Usuario que realiza la salida.
     */
    private Usuario usuario;

    /**
     * Destino de los productos retirados.
     */
    private String destino;

    /**
     * Constructor vacío para crear una instancia de Salida sin inicializar sus atributos.
     */
    public Salida() {
    }

    /**
     * Constructor completo para inicializar todos los atributos de la clase Salida.
     * 
     * @param idSalida     Identificador de la salida.
     * @param producto     Producto que está siendo retirado.
     * @param cantidad     Cantidad de producto.
     * @param valorUnitario Valor unitario del producto.
     * @param valorTotal   Valor total de la salida.
     * @param fechaSalida  Fecha en que se realizó la salida.
     * @param usuario      Usuario que realiza la salida.
     * @param destino      Destino de los productos retirados.
     */
    public Salida(int idSalida, Producto producto, int cantidad, double valorUnitario, double valorTotal, LocalDateTime fechaSalida, Usuario usuario, String destino) {
        this.idSalida = idSalida;
        this.producto = producto;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.fechaSalida = fechaSalida;
        this.usuario = usuario;
        this.destino = destino;
    }

    /**
     * Obtiene el identificador único de la salida.
     * 
     * @return idSalida
     */
    public int getIdSalida() {
        return idSalida;
    }

    /**
     * Establece el identificador único de la salida.
     * 
     * @param idSalida Nuevo identificador de la salida.
     */
    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }

    /**
     * Obtiene el producto que se retira en la salida.
     * 
     * @return producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto que se retira en la salida.
     * 
     * @param producto Nuevo producto de la salida.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad de producto que se retira.
     * 
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de producto que se retira.
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
     * Obtiene el valor total de la salida.
     * 
     * @return valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Establece el valor total de la salida.
     * 
     * @param valorTotal Nuevo valor total de la salida.
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Obtiene la fecha en que se realizó la salida.
     * 
     * @return fechaSalida
     */
    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Establece la fecha en que se realizó la salida.
     * 
     * @param fechaSalida Nueva fecha de la salida.
     */
    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * Obtiene el usuario que realizó la salida.
     * 
     * @return usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realizó la salida.
     * 
     * @param usuario Nuevo usuario que realiza la salida.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el destino de los productos retirados.
     * 
     * @return destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Establece el destino de los productos retirados.
     * 
     * @param destino Nuevo destino de los productos.
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

}
