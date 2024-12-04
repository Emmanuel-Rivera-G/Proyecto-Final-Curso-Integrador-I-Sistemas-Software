package controller;

import dao.implemetacion.DAOCategoriaImpl;
import dao.interfaz.DAOCategoria;
import dto.DTOCategoria;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Categoria;
import utils.MessageUtils;
import view.ViewRegistroCategorias;

/**
 * Controlador para gestionar las acciones relacionadas con la vista de registro de categorías.
 * Maneja la interacción entre la vista, el modelo y los servicios de datos.
 */
public class ControllerCategoria {
    
    private final ViewRegistroCategorias vista;
    private final DAOCategoria serviceCategoria;
    private final String[] titles = {"ID", "Nombre", "Detalles"};
    private DefaultTableModel defaultTableModel;
    
    private JTextField idCategoria;
    private JTextField nombre;
    private JTextField detalles;
    
    private JLabel buscar;
    private JLabel delete;
    private JLabel editar;
    private JLabel saved;
    
    private JTable tabla;

    /**
     * Constructor principal para inicializar el controlador con la vista proporcionada.
     *
     * @param vista instancia de la vista ViewRegistroCategorias
     */
    public ControllerCategoria(ViewRegistroCategorias vista) {
        this.vista = vista;
        this.serviceCategoria = new DAOCategoriaImpl();
        this.setTextFields();
        this.setLabels();
        this.setTabla();
        this.setMouseListenerBtns();
        this.setTableClickListener();
        this.actualizarTotalmenteTabla();
    }

    /**
     * Configura los campos de texto de la vista.
     */
    private void setTextFields() {
        this.idCategoria = vista.getTxt_fld_id_cat();
        this.nombre = vista.getTxt_fld_nombre();
        this.detalles = vista.getTxt_fld_detalles();
    }

    /**
     * Configura las etiquetas de la vista.
     */
    private void setLabels() {
        this.buscar = vista.getLbl_buscar();
        this.delete = vista.getLbl_delete();
        this.editar = vista.getLbl_editar();
        this.saved = vista.getLbl_saved();
    }

    /**
     * Configura la tabla de categorías en la vista.
     */
    private void setTabla() {
        this.tabla = vista.getTblcategorias();
        this.defaultTableModel = new DefaultTableModel(titles, 0);
        this.setModelToTabla();
    }

    /**
     * Asigna el modelo a la tabla.
     */
    private void setModelToTabla() {
        this.tabla.setModel(defaultTableModel);
    }

    /**
     * Configura los listeners de eventos para los botones de la vista.
     */
    private void setMouseListenerBtns() {
        this.buscar.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { actionLblBuscar(e); }
        });
        this.delete.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { actionLblDelete(e); }
        });
        this.editar.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { actionLblEditar(e); }
        });
        this.saved.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { actionLblSaved(e); }
        });
    }

    /**
     * Configura el listener para seleccionar filas en la tabla.
     */
    private void setTableClickListener() {
        this.tabla.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { llenarFormularioDesdeTabla(e); }
        });
    }

    /**
     * Llena los campos de texto con los datos de la fila seleccionada en la tabla.
     *
     * @param event evento de clic en la tabla
     */
    private void llenarFormularioDesdeTabla(MouseEvent event) {
        int selectedRow = tabla.getSelectedRow();
        if (selectedRow == -1) return;

        try {
            String id = tabla.getValueAt(selectedRow, 0).toString();
            String nombreValue = tabla.getValueAt(selectedRow, 1).toString();
            String detallesValue = tabla.getValueAt(selectedRow, 2).toString();
            
            this.idCategoria.setText(id);
            this.nombre.setText(nombreValue);
            this.detalles.setText(detallesValue);
        } catch (Exception e) {
            MessageUtils.mostrarMensajeError("Error al llenar el formulario desde la tabla: " + e.getMessage());
        }
    }

    /**
     * Acción ejecutada al hacer clic en la etiqueta "Buscar".
     *
     * @param event evento de clic
     */
    private void actionLblBuscar(MouseEvent event) {
        actualizarTotalmenteTabla();
        int id = parseStringToInt.apply(this.idCategoria.getText(), "Por favor ingrese un número válido en el campo ID.");
        if (id == -1) return;

        DTOCategoria categoria = serviceCategoria.obtenerCategoriaPorId(id);
        if (categoria == null || categoria.getIdCategoria() == -1) {
            MessageUtils.mostrarMensajeError("Categoría no encontrada.");
        } else {
            String[] NAndD = categoria.getNombre().split("\\|");
            this.nombre.setText(NAndD[0]);
            if (NAndD.length > 1) this.detalles.setText(NAndD[1]);
            actualizarTabla(List.of(categoria));
            MessageUtils.mostrarMensajeExitoso("Categoría encontrada.");
        }
    }

    /**
     * Acción ejecutada al hacer clic en la etiqueta "Eliminar".
     *
     * @param event evento de clic
     */
    private void actionLblDelete(MouseEvent event) {
        int id = parseStringToInt.apply(this.idCategoria.getText(), "Por favor ingrese un número válido en el campo ID.");
        if (id == -1) return;

        serviceCategoria.eliminarCategoria(id);
        MessageUtils.mostrarMensajeExitoso("Categoría eliminada correctamente.");
        actualizarTotalmenteTabla();
    }

    /**
     * Acción ejecutada al hacer clic en la etiqueta "Editar".
     *
     * @param event evento de clic
     */
    private void actionLblEditar(MouseEvent event) {
        int id = parseStringToInt.apply(this.idCategoria.getText(), "Por favor ingrese un número válido en el campo ID.");
        if (id == -1) return;

        String nuevoNombre = this.nombre.getText();
        String nuevosDetalles = this.detalles.getText();

        Categoria categoria = new Categoria();
        categoria.setIdcat(id);
        categoria.setNombre(nuevoNombre + " | " + nuevosDetalles);

        serviceCategoria.actualizarCategoria(new DTOCategoria(categoria));
        MessageUtils.mostrarMensajeExitoso("Categoría editada correctamente.");
        actualizarTotalmenteTabla();
    }

    /**
     * Acción ejecutada al hacer clic en la etiqueta "Guardar".
     *
     * @param event evento de clic
     */
    private void actionLblSaved(MouseEvent event) {
        int id = parseStringToInt.apply(this.idCategoria.getText(), "Ingresar en el id un número.");
        if (id == -1) return;
        
        String nombreCat = this.nombre.getText();
        String detallesCat = this.detalles.getText();
        
        Categoria c = new Categoria();
        c.setIdcat(id);
        c.setNombre(nombreCat + " | " + detallesCat);
        
        serviceCategoria.agregarCategoria(new DTOCategoria(c));
        MessageUtils.mostrarMensajeExitoso("Subido Correctamente.");
        actualizarTotalmenteTabla();
    }

    /**
     * Actualiza completamente la tabla de categorías con los datos más recientes.
     */
    private void actualizarTotalmenteTabla() {
        List<DTOCategoria> listaCategoriasActualizada = serviceCategoria.obtenerTodosLasCategorias();
        actualizarTabla(listaCategoriasActualizada);
    }

    /**
     * Actualiza el modelo de la tabla con una lista de categorías.
     *
     * @param list lista de DTOCategoria
     */
    private void actualizarTabla(List<DTOCategoria> list) {
        defaultTableModel.setRowCount(0);
        for (DTOCategoria dtoCategoria : list) {
            agregarFilaDTOCategoriaATabla(defaultTableModel, dtoCategoria);
        }
    }

    /**
     * Agrega una fila de datos a la tabla a partir de un DTOCategoria.
     *
     * @param tableModel modelo de la tabla
     * @param dtoCategoria categoría en formato DTO
     */
    private void agregarFilaDTOCategoriaATabla(TableModel tableModel, DTOCategoria dtoCategoria) throws NullPointerException {
        defaultTableModel.addRow(toArray(dtoCategoria));
    }

    /**
     * Convierte un DTOCategoria en un arreglo de objetos para agregarlo a la tabla.
     *
     * @param dtoCategoria categoría en formato DTO
     * @return arreglo de datos de la categoría
     */
    public static Object[] toArray(DTOCategoria dtoCategoria) throws NullPointerException {
        String[] nombreAndDetalles = dtoCategoria.getNombre().split("\\|");
        String nombre = nombreAndDetalles[0];
        String detalles = "";
        if (nombreAndDetalles.length > 1) detalles = nombreAndDetalles[1];
        return new Object[]{
            dtoCategoria.getIdCategoria(),
            nombre,
            detalles
        };
    }

    /**
     * Convierte un texto a un número entero, mostrando un mensaje de error si no es válido.
     */
    private final BiFunction<String, String, Integer> parseStringToInt = (String text, String mensajeError) -> {
        int num;
        try {
            num = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            MessageUtils.mostrarMensajeError(mensajeError);
            System.err.println(e.getMessage());
            return -1;
        }
        return num;
    };
}
