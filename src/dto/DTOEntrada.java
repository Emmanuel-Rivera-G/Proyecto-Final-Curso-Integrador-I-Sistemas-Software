package dto;

import java.time.LocalDateTime;
import model.Entrada;
import utils.ConverterProducto;

/**
 * Clase DTO (Data Transfer Object) que representa una Entrada de producto.
 * Esta clase se utiliza para transferir datos de una entrada entre capas de la aplicación.
 * Contiene información relacionada con el producto, la cantidad, los valores
 * asociados, la fecha de la entrada, el proveedor y el usuario que registra
 * la entrada.
 * 
 * @author Yinyer
 */
public class DTOEntrada {

    /**
     * Identificador único de la entrada.
     */
    private int idEntrada;

    /**
     * DTO que representa el producto de la entrada.
     */
    private DTOProducto dtoProducto;

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
     * DTO que representa el proveedor del producto.
     */
    private DTOProveedor dtoProveedor;

    /**
     * DTO que representa el usuario que registra la entrada.
     */
    private DTOUsuario usuario;
    
    /**
     * Constructor vacío para inicialización con Setter.
     */
    public DTOEntrada() {
    }

    /**
     * Constructor completo para la clase DTOEntrada.
     * 
     * @param idEntrada     Identificador de la entrada.
     * @param dtoProducto   DTO del producto.
     * @param cantidad      Cantidad del producto.
     * @param valorUnitario Valor unitario del producto.
     * @param valorTotal    Valor total de la entrada.
     * @param fechaEntrada  Fecha de la entrada.
     * @param dtoProveedor  DTO del proveedor.
     * @param usuario       DTO del usuario que registra la entrada.
     */
    public DTOEntrada(int idEntrada, DTOProducto dtoProducto, int cantidad, double valorUnitario, double valorTotal, LocalDateTime fechaEntrada, DTOProveedor dtoProveedor, DTOUsuario usuario) {
        this.idEntrada = idEntrada;
        this.dtoProducto = dtoProducto;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.fechaEntrada = fechaEntrada;
        this.dtoProveedor = dtoProveedor;
        this.usuario = usuario;
    }

    /**
     * Constructor que convierte una entidad {@link Entrada} a su equivalente DTO.
     * 
     * @param entrada Objeto de tipo Entrada que será convertido a DTOEntrada.
     */
    public DTOEntrada(Entrada entrada) {
        this(entrada.getIdEntrada(), 
             ConverterProducto.toDTOProducto(entrada.getProducto()), 
             entrada.getCantidad(), entrada.getValorUnitario(), 
             entrada.getValorTotal(), entrada.getFechaEntrada(), 
             new DTOProveedor(entrada.getProveedor()), 
             new DTOUsuario(entrada.getUsuario()));
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
     * Obtiene el DTO del producto de la entrada.
     * 
     * @return dtoProducto
     */
    public DTOProducto getDtoProducto() {
        return dtoProducto;
    }

    /**
     * Establece el DTO del producto de la entrada.
     * 
     * @param dtoProducto Nuevo DTO del producto.
     */
    public void setDtoProducto(DTOProducto dtoProducto) {
        this.dtoProducto = dtoProducto;
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
     * Obtiene el DTO del proveedor del producto.
     * 
     * @return dtoProveedor
     */
    public DTOProveedor getDtoProveedor() {
        return dtoProveedor;
    }

    /**
     * Establece el DTO del proveedor del producto.
     * 
     * @param dtoProveedor Nuevo DTO del proveedor.
     */
    public void setDtoProveedor(DTOProveedor dtoProveedor) {
        this.dtoProveedor = dtoProveedor;
    }

    /**
     * Obtiene el DTO del usuario que registra la entrada.
     * 
     * @return usuario
     */
    public DTOUsuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el DTO del usuario que registra la entrada.
     * 
     * @param usuario Nuevo DTO del usuario.
     */
    public void setUsuario(DTOUsuario usuario) {
        this.usuario = usuario;
    }

}
