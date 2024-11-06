/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics.modelGraphics;

/**
 *
 * @author rasmx
 */
public class ModelData {

    /**
     * @return the parametroIdentificador
     */
    public String getParametroIdentificador() {
        return parametroIdentificador;
    }

    /**
     * @param parametroIdentificador the parametroIdentificador to set
     */
    public void setParametroIdentificador(String parametroIdentificador) {
        this.parametroIdentificador = parametroIdentificador;
    }

    /**
     * @return the parametroCantidad
     */
    public int getParametroCantidad() {
        return parametroCantidad;
    }

    /**
     * @param parametroCantidad the parametroCantidad to set
     */
    public void setParametroCantidad(int parametroCantidad) {
        this.parametroCantidad = parametroCantidad;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * @return the beneficio
     */
    public double getBeneficio() {
        return beneficio;
    }

    /**
     * @param beneficio the beneficio to set
     */
    public void setBeneficio(double beneficio) {
        this.beneficio = beneficio;
    }

    public ModelData() {
    }

    public ModelData(String parametroIdentificador, int parametroCantidad, double costo, double beneficio) {
        this.parametroIdentificador = parametroIdentificador;
        this.parametroCantidad = parametroCantidad;
        this.costo = costo;
        this.beneficio = beneficio;
    }

    private String parametroIdentificador;
    private int parametroCantidad;
    private double costo;
    private double beneficio;

}
