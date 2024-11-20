package dto;

import model.Almacen;

/**
 * La clase {@code DTOAlmacen} es un Data Transfer Object que encapsula 
 * la información del almacén, permitiendo transferir datos entre capas
 * del sistema sin exponer directamente el modelo.
 * 
 * Esta clase se utiliza para representar los datos de un almacén y 
 * convertir objetos {@code Almacen} en objetos DTO para su transporte.
 * 
 * @author Yinyer
 */
public class DTOAlmacen {
    
    /**
     * Identificador único del almacén.
     */
    private int idAlmacen;

    /**
     * Capacidad máxima del almacén en términos de productos.
     */
    private int capacidad;

    /**
     * Ubicación física del almacén.
     */
    private String ubicacion;

    /**
     * Descripción adicional del almacén.
     */
    private String descripcion;

    /**
     * Constructor que inicializa todos los atributos de la clase {@code DTOAlmacen}.
     * 
     * @param idAlmacen    Identificador único del almacén.
     * @param capacidad    Capacidad máxima del almacén.
     * @param ubicacion    Ubicación física del almacén.
     * @param descripcion  Descripción adicional del almacén.
     */
    public DTOAlmacen(int idAlmacen, int capacidad, String ubicacion, String descripcion) {
        this.idAlmacen = idAlmacen;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    /**
     * Constructor que crea un objeto {@code DTOAlmacen} a partir de un objeto {@code Almacen}.
     * 
     * @param almacen Objeto {@code Almacen} del cual se extraen los datos.
     */
    public DTOAlmacen(Almacen almacen) {
        this(almacen.getIdAlmacen(), 
             almacen.getCapacidad(), 
             almacen.getUbicacion(), 
             almacen.getDescripcion());
    }

    /**
     * Obtiene el identificador único del almacén.
     * 
     * @return idAlmacen Identificador del almacén.
     */
    public int getIdAlmacen() {
        return idAlmacen;
    }

    /**
     * Establece el identificador único del almacén.
     * 
     * @param idAlmacen Nuevo identificador del almacén.
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
     * @param capacidad Nueva capacidad del almacén.
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
     * Obtiene la descripción adicional del almacén.
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
