package dto;

import java.time.LocalDateTime;
import model.Salida;
import utils.ConverterProducto;

/**
 * Clase que representa una transferencia de datos (DTO) de una salida de producto. 
 * Contiene la información simplificada de una salida que será utilizada para 
 * transferir datos entre diferentes capas de la aplicación.
 * 
 * Esta clase toma los datos de una entidad de modelo {@link Salida} y los convierte 
 * en un formato que puede ser utilizado en la capa de presentación o en servicios externos.
 * 
 * @author Yinyer
 */
public class DTOSalida {

    /**
     * Identificador único de la salida.
     */
    private int idSalida;

    /**
     * Producto transferido, representado como un DTO.
     */
    private DTOProducto dtoProducto;

    /**
     * Cantidad de producto transferido en la salida.
     */
    private int cantidad;

    /**
     * Valor unitario del producto transferido.
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
     * Usuario que realiza la salida, representado como un DTO.
     */
    private DTOUsuario dtoUsuario;

    /**
     * Destino del producto retirado.
     */
    private String destino;
    
    /**
     * Constructor vacío de DTOSalida.
     */
    public DTOSalida() {
    }

    /**
     * Constructor que inicializa todos los atributos de la clase DTOSalida.
     * 
     * @param idSalida     Identificador único de la salida.
     * @param dtoProducto  DTO del producto que se retira.
     * @param cantidad     Cantidad de producto retirado.
     * @param valorUnitario Valor unitario del producto retirado.
     * @param valorTotal   Valor total de la salida.
     * @param fechaSalida  Fecha en que se realizó la salida.
     * @param dtoUsuario   DTO del usuario que realizó la salida.
     * @param destino      Destino del producto retirado.
     */
    public DTOSalida(int idSalida, DTOProducto dtoProducto, int cantidad, double valorUnitario, double valorTotal, LocalDateTime fechaSalida, DTOUsuario dtoUsuario, String destino) {
        this.idSalida = idSalida;
        this.dtoProducto = dtoProducto;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.fechaSalida = fechaSalida;
        this.dtoUsuario = dtoUsuario;
        this.destino = destino;
    }

    /**
     * Constructor que convierte una entidad de tipo {@link Salida} en un DTO.
     * Convierte los objetos de tipo {@link Producto} y {@link Usuario} en sus
     * respectivos DTOs para una transferencia de datos simplificada.
     * 
     * @param salida Entidad de tipo {@link Salida} del modelo.
     */
    public DTOSalida(Salida salida) {
        this(salida.getIdSalida(),
                ConverterProducto.toDTOProducto(salida.getProducto()),
                salida.getCantidad(),
                salida.getValorUnitario(),
                salida.getValorTotal(),
                salida.getFechaSalida(),
                new DTOUsuario(salida.getUsuario()),
                salida.getDestino());
    }

    /**
     * Obtiene el identificador único de la salida.
     * 
     * @return idSalida Identificador único.
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
     * Obtiene el DTO del producto retirado.
     * 
     * @return dtoProducto DTO del producto.
     */
    public DTOProducto getDtoProducto() {
        return dtoProducto;
    }

    /**
     * Establece el DTO del producto retirado.
     * 
     * @param dtoProducto Nuevo DTO del producto.
     */
    public void setDtoProducto(DTOProducto dtoProducto) {
        this.dtoProducto = dtoProducto;
    }

    /**
     * Obtiene la cantidad de producto retirado.
     * 
     * @return cantidad Cantidad de producto.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de producto retirado.
     * 
     * @param cantidad Nueva cantidad de producto.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el valor unitario del producto retirado.
     * 
     * @return valorUnitario Valor unitario del producto.
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Establece el valor unitario del producto retirado.
     * 
     * @param valorUnitario Nuevo valor unitario del producto.
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * Obtiene el valor total de la salida (cantidad * valor unitario).
     * 
     * @return valorTotal Valor total de la salida.
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
     * @return fechaSalida Fecha de la salida.
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
     * Obtiene el DTO del usuario que realizó la salida.
     * 
     * @return dtoUsuario DTO del usuario.
     */
    public DTOUsuario getDtoUsuario() {
        return dtoUsuario;
    }

    /**
     * Establece el DTO del usuario que realizó la salida.
     * 
     * @param dtoUsuario Nuevo DTO del usuario.
     */
    public void setDtoUsuario(DTOUsuario dtoUsuario) {
        this.dtoUsuario = dtoUsuario;
    }

    /**
     * Obtiene el destino del producto retirado.
     * 
     * @return destino Destino del producto.
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Establece el destino del producto retirado.
     * 
     * @param destino Nuevo destino del producto.
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }
}
