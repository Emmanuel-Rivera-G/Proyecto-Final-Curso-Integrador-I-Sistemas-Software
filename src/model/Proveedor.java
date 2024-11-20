package model;

/**
 * La clase {@code Proveedor} representa a un proveedor dentro del sistema.
 * Contiene información básica como el identificador, la razón social, la dirección,
 * el número de contacto y el correo electrónico.
 * 
 * Esta clase permite gestionar y acceder a los datos relacionados con los proveedores
 * que suministran productos o servicios.
 * 
 * @author Yinyer
 */
public class Proveedor {

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
     * Constructor vacío de la clase {@code Proveedor}.
     * Permite crear una instancia de un proveedor sin inicializar sus atributos.
     */
    public Proveedor() {
    }

    /**
     * Constructor que inicializa todos los atributos del proveedor.
     * 
     * @param idProveedor   Identificador único del proveedor.
     * @param razonSocial   Razón social del proveedor.
     * @param direccion     Dirección del proveedor.
     * @param numeroContacto Número de contacto del proveedor.
     * @param email         Correo electrónico del proveedor.
     */
    public Proveedor(int idProveedor, String razonSocial, String direccion, String numeroContacto, String email) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.numeroContacto = numeroContacto;
        this.email = email;
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
