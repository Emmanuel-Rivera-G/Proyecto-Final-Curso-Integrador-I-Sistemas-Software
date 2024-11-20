package model;

/**
 * La clase {@code Almacen} representa un almacén donde se almacenan productos. 
 * Cada almacén tiene una capacidad, ubicación y descripción, que ayudan a identificar 
 * sus características y el propósito de su uso.
 * 
 * @author Yinyer
 */
public class Almacen {

    /**
     * Identificador único del almacén.
     */
    private int idAlmacen;

    /**
     * Capacidad máxima del almacén en términos de volumen o cantidad de productos.
     */
    private int capacidad;

    /**
     * Ubicación física del almacén.
     */
    private String ubicacion;

    /**
     * Descripción general del almacén, incluyendo detalles adicionales.
     */
    private String descripcion;

    /**
     * Constructor por defecto que crea un objeto {@code Almacen} sin inicializar atributos.
     */
    public Almacen() {
    }

    /**
     * Constructor que inicializa todos los atributos del {@code Almacen}.
     * 
     * @param idAlmacen    Identificador único del almacén.
     * @param capacidad    Capacidad máxima del almacén.
     * @param ubicacion    Ubicación física del almacén.
     * @param descripcion  Descripción del almacén.
     */
    public Almacen(int idAlmacen, int capacidad, String ubicacion, String descripcion) {
        this.idAlmacen = idAlmacen;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador único del almacén.
     * 
     * @return idAlmacen Identificador único del almacén.
     */
    public int getIdAlmacen() {
        return idAlmacen;
    }

    /**
     * Establece el identificador único del almacén.
     * 
     * @param idAlmacen Nuevo identificador único del almacén.
     */
    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    /**
     * Obtiene la capacidad máxima del almacén.
     * 
     * @return capacidad Capacidad máxima del almacén.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad máxima del almacén.
     * 
     * @param capacidad Nueva capacidad máxima del almacén.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene la ubicación física del almacén.
     * 
     * @return ubicacion Ubicación física del almacén.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación física del almacén.
     * 
     * @param ubicacion Nueva ubicación física del almacén.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene la descripción general del almacén.
     * 
     * @return descripcion Descripción del almacén.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece una nueva descripción para el almacén.
     * 
     * @param descripcion Nueva descripción del almacén.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
