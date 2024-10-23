/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * La clase {@code Usuario} representa un usuario del sistema. Contiene
 * atributos básicos como nombre, apellido, documento de identificación,
 * dirección, teléfono, correo electrónico, tipo de usuario, y credenciales de
 * acceso (username y password).
 *
 * Esta clase tiene métodos getters y setters para acceder y modificar estos
 * atributos.
 *
 * @author Ralfph
 */
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String documento;
    private String direccion;
    private String telefono;
    private String correo;
    private int idTipoUsuario;
    private String username;
    private String password;

    public Usuario() {
    }

    /**
     * Constructor que inicializa un usuario con todos los atributos.
     *
     * @param idUsuario Identificador único del usuario.
     * @param nombre Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param documento Documento de identificación del usuario(DNI).
     * @param direccion Dirección física del usuario.
     * @param telefono Número de teléfono del usuario.
     * @param correo Correo electrónico del usuario.
     * @param idTipoUsuario Identificador del tipo de usuario
     * (1=Administrador,2=Empleado).
     * @param username Nombre de usuario (credencial para inicio de sesión).
     * @param password Contraseña del usuario.
     */
    public Usuario(int idUsuario, String nombre, String apellido, String documento, String direccion, String telefono, String correo, int idTipoUsuario, String username, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.idTipoUsuario = idTipoUsuario;
        this.username = username;
        this.password = password;
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El ID del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param idUsuario El ID del usuario.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del usuario.
     *
     * @return El apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param apellido El apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el documento de identificación del usuario.
     *
     * @return El documento de identificación del usuario.
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el documento de identificación del usuario.
     *
     * @param documento El documento de identificación del usuario.
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Obtiene la dirección física del usuario.
     *
     * @return La dirección del usuario.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección física del usuario.
     *
     * @param direccion La dirección del usuario.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el número de teléfono del usuario.
     *
     * @return El número de teléfono del usuario.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del usuario.
     *
     * @param telefono El número de teléfono del usuario.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo El correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el identificador del tipo de usuario.
     *
     * @return El ID del tipo de usuario.
     */
    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    /**
     * Establece el identificador del tipo de usuario.
     *
     * @param idTipoUsuario El ID del tipo de usuario.
     */
    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    /**
     * Obtiene el nombre de usuario utilizado para el inicio de sesión.
     *
     * @return El nombre de usuario (username).
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario utilizado para el inicio de sesión.
     *
     * @param username El nombre de usuario (username).
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
