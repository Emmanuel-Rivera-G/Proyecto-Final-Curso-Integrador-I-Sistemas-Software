package service;

import dao.interfaz.DAOEntrada;
import dto.DTOEntrada;
import java.util.List;
import service.interfaz.ServiceEntrada;

/**
 *
 * Clase de servicio para manejar las operaciones de negocio de las entradas de productos.
 * 
 * @author Yinyer
 */
public class ServiceEntradaImpl implements ServiceEntrada {

    private final DAOEntrada daoEntrada;

    public ServiceEntradaImpl(DAOEntrada daoEntrada) {
        this.daoEntrada = daoEntrada;
    }

    /**
     * Agrega una nueva entrada, calculando el valor total automáticamente.
     */
    @Override
    public boolean agregarEntrada(DTOEntrada entrada) {
        calcularValorTotal(entrada);
        return daoEntrada.agregarEntrada(entrada);
    }

    /**
     * Actualiza una entrada existente.
     */
    @Override
    public boolean actualizarEntrada(DTOEntrada entrada) {
        calcularValorTotal(entrada);
        return daoEntrada.actualizarEntrada(entrada);
    }

    /**
     * Elimina una entrada por su ID.
     */
    @Override
    public boolean eliminarEntrada(int idEntrada) {
        return daoEntrada.eliminarEntrada(idEntrada);
    }

    /**
     * Obtiene una entrada específica por su ID.
     */
    @Override
    public DTOEntrada obtenerEntradaPorId(int idEntrada) {
        return daoEntrada.obtenerEntradaPorId(idEntrada);
    }

    /**
     * Obtiene todas las entradas.
     */
    @Override
    public List<DTOEntrada> obtenerTodasLasEntradas() {
        return daoEntrada.obtenerTodasLasEntradas();
    }

    /**
     * Calcula y establece el valor total de la entrada.
     */
    private void calcularValorTotal(DTOEntrada entrada) {
        entrada.setValorTotal(entrada.getCantidad() * entrada.getValorUnitario());
    }

    /**
     * Busca entradas cuyo ID comience con un prefijo específico.
     */
    @Override
    public List<DTOEntrada> buscarEntradasIdPorPrefijo(String prefijo) {
        if (prefijo == null || prefijo.isEmpty()) {
            throw new IllegalArgumentException("El prefijo no puede ser nulo o vacío");
        }
        //return daoEntrada.buscarEntradasIdPorPrefijo(prefijo);
        throw new UnsupportedOperationException("No soportado todavía."); 
    }

    /**
     * Busca entradas por nombre o descripción que empiecen con un prefijo específico.
     */
    @Override
    public List<DTOEntrada> buscarEntradasPorNombreODescripcionConPrefijo(String prefijo) {
        if (prefijo == null || prefijo.isEmpty()) {
            throw new IllegalArgumentException("El prefijo no puede ser nulo o vacío");
        }
        //return daoEntrada.buscarEntradasPorNombreODescripcionConPrefijo(prefijo);
        throw new UnsupportedOperationException("No soportado todavía."); 
    }
    
}
