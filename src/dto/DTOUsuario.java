/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import model.Usuario;

/**
 * La clase {@code DTOUsuario} actúa como un Data Transfer Object (DTO) para
 * transferir información de usuarios entre capas del sistema.
 *
 * @author Ralfph
 */
public class DTOUsuario {

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

    /**
     * Constructor vacío para crear un objeto {@code DTOUsuario} sin valores
     * iniciales.
     */
    public DTOUsuario() {
    }

    /**
     * Constructor que inicializa un {@code DTOUsuario} a partir de un objeto
     * {@code Usuario}.
     *
     * @param usuario El objeto {@code Usuario} con los datos a transferir.
     */
    public DTOUsuario(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.documento = usuario.getDocumento();
        this.direccion = usuario.getDireccion();
        this.telefono = usuario.getTelefono();
        this.correo = usuario.getCorreo();
        this.idTipoUsuario = usuario.getIdTipoUsuario();
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
    }

    public DTOUsuario(int idUsuario, String nombre, String apellido, String documento, String direccion, String telefono, String correo, int idTipoUsuario, String username, String password) {
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
