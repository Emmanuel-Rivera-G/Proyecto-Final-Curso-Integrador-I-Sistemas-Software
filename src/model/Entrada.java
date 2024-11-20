/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class Entrada {
    private int identrada;
    private int idproducto;
    private String nombreproducto;
    private String descoperacion;
    private String fecha;
    private int cantidad;
    private double preciounitario;
    private double total;
    
    //polimorfismo diferente parametros
    //contructor vacio

    public Entrada() {
    }
    //contructor considerando el ID autoincrementable al REGISTRAR
    public Entrada(int idproducto, String nombreproducto, String descoperacion, String fecha, int cantidad, double preciounitario, double total) {
        this.idproducto = idproducto;
        this.nombreproducto = nombreproducto;
        this.descoperacion = descoperacion;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.preciounitario = preciounitario;
        this.total = total;
    }
    //constructor para EDITAR entrada con ID al buscar
    public Entrada(int identrada, int idproducto, String nombreproducto, String descoperacion, String fecha, int cantidad, double preciounitario, double total) {
        this.identrada = identrada;
        this.idproducto = idproducto;
        this.nombreproducto = nombreproducto;
        this.descoperacion = descoperacion;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.preciounitario = preciounitario;
        this.total = total;
    }
    
    //get y set

    public int getIdentrada() {
        return identrada;
    }

    public void setIdentrada(int identrada) {
        this.identrada = identrada;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getDescoperacion() {
        return descoperacion;
    }

    public void setDescoperacion(String descoperacion) {
        this.descoperacion = descoperacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

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

    public double getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(double preciounitario) {
        this.preciounitario = preciounitario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
    
    
}
