package dto;

import model.Proveedor;

/**
 * La clase {@code DTOProveedor} es una representación de transferencia de datos (DTO) 
 * que encapsula los detalles de un proveedor. Se utiliza para transferir información 
 * entre capas de la aplicación sin exponer directamente la entidad del modelo {@code Proveedor}.
 * 
 * Este DTO contiene los mismos atributos que el modelo {@code Proveedor}, permitiendo 
 * la conversión sencilla entre ambos objetos.
 * 
 * @author Yinyer
 */
public class DTOProveedor {

    /**
     * Identificador único del proveedor.
     */
    private int idProveedor;

    /**
     * Razón social del proveedor (nombre de la empresa).
     */
    private String razonSocial;

    /**
     * Dirección física del proveedor.
     */
    private String direccion;

    /**
     * Número de contacto del proveedor.
     */
    private String numeroContacto;

    /**
     * Dirección de correo electrónico del proveedor.
     */
    private String email;

    /**
     * Constructor que inicializa todos los atributos del {@code DTOProveedor}.
     * 
     * @param idProveedor    Identificador único del proveedor.
     * @param razonSocial    Razón social del proveedor.
     * @param direccion      Dirección del proveedor.
     * @param numeroContacto Número de contacto del proveedor.
     * @param email          Correo electrónico del proveedor.
     */
    public DTOProveedor(int idProveedor, String razonSocial, String direccion, String numeroContacto, String email) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.numeroContacto = numeroContacto;
        this.email = email;
    }

    /**
     * Constructor que convierte un objeto {@code Proveedor} a un {@code DTOProveedor}.
     * 
     * @param proveedor Objeto de la clase {@code Proveedor} que será convertido a DTO.
     */
    public DTOProveedor(Proveedor proveedor) {
        this(proveedor.getIdProveedor(), 
                proveedor.getRazonSocial(), 
                proveedor.getDireccion(), 
                proveedor.getNumeroContacto(), 
                proveedor.getEmail());
    }

    /**
     * Obtiene el identificador único del proveedor.
     * 
     * @return idProveedor Identificador único.
     */
    public int getIdProveedor() {
        return idProveedor;
    }

    /**
     * Establece el identificador único del proveedor.
     * 
     * @param idProveedor Nuevo identificador del proveedor.
     */
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * Obtiene la razón social del proveedor.
     * 
     * @return razonSocial Razón social del proveedor.
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Establece la razón social del proveedor.
     * 
     * @param razonSocial Nueva razón social del proveedor.
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * Obtiene la dirección del proveedor.
     * 
     * @return direccion Dirección del proveedor.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del proveedor.
     * 
     * @param direccion Nueva dirección del proveedor.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el número de contacto del proveedor.
     * 
     * @return numeroContacto Número de contacto.
     */
    public String getNumeroContacto() {
        return numeroContacto;
    }

    /**
     * Establece el número de contacto del proveedor.
     * 
     * @param numeroContacto Nuevo número de contacto.
     */
    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    /**
     * Obtiene el correo electrónico del proveedor.
     * 
     * @return email Correo electrónico.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del proveedor.
     * 
     * @param email Nuevo correo electrónico.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
