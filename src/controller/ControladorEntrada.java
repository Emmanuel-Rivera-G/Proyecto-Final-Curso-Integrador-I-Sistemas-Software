/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ViewRegistroEntradas;
import dao.implemetacion.DAOEntradaImpl;

import java.sql.SQLException;//------throw
import java.awt.Dimension;
import java.awt.HeadlessException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;//pruebaaaaaa filtrar
import javax.swing.event.DocumentListener;//pruebaaaaaa filtrar
import javax.swing.table.DefaultTableModel;
import model.Entrada;
import model.Producto;



/**
 *
 * @author Lenovo
 */
public class ControladorEntrada implements ActionListener{
    
    //instanciacion de objetos
    ViewRegistroEntradas view = new ViewRegistroEntradas();//PARA llamarlo desde menu principal
    DAOEntradaImpl entradadao = new DAOEntradaImpl();
    DefaultTableModel modeloTablaBuscarProdEntrada = new DefaultTableModel();// carga los nombres de la base de datos para interactuar con el
    
    DefaultTableModel modeloTablaEntrada = new DefaultTableModel();//SOLO PARA tabla entradas
    
    //Var glob cargadatosEntrada
    private int codeEntrada=0;//=0 SOLO PARA LLENAR CAMPOS desde tabla a txtfield ACTUALIZAR
    private int codeProducto;
    private String nombProducto;
    private String operacion;
    private String fecha;
    private int cantidad;
    private double precioUnit;
    private double totalEntrada;
   
    
    public ControladorEntrada(ViewRegistroEntradas view) {
        this.view = view;// recibe los valores o dato
        view.setVisible(true);
        
        agregarEventos();//trayendo los metodos
        listarTablaBuscarProdEntradas();
        listarTablaENTRADAS();

        //-------------------------------para FILTRAR con DOCUMENT LISTENER UPDATE automatico
        view.getTxtBuscarNameProduct().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            private void filtrarTabla() {
                String filtro = view.getTxtBuscarNameProduct().getText();
                List<Producto> listaFiltrada = entradadao.filtrarProductoABuscar(filtro);

                // Limpiar la tabla antes de mostrar los resultados filtrados
                modeloTablaBuscarProdEntrada.setRowCount(0);

                // Agregar los productos filtrados a la tabla
                for (Producto producto : listaFiltrada) {
                    modeloTablaBuscarProdEntrada.addRow(new Object[] {
                        producto.getId(),
                        producto.getNombre(),
                        producto.getCategoria().getIdcat(),
                        producto.getUndmedida(),
                        producto.getStock()
                    });
                }

                view.getTblTablaProductosABuscarEnt().setModel(modeloTablaBuscarProdEntrada);

                //String filtro = view.getTxtIdBuscar().getText();
                //actualizarTablaFiltrada(filtro);
            }
        });
        
        // -------------------------------Para calcular la suma automáticamente
        view.getTxtCantidadP().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarMultTOTAL();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarMultTOTAL();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarMultTOTAL();
            }
        });

        view.getTxtPrecioUnitario().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarMultTOTAL();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarMultTOTAL();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarMultTOTAL();
            }
            
            
        });
 
    }
    
    //--------
    private void actualizarMultTOTAL() {
        try {
            int cantidad = Integer.parseInt(view.getTxtCantidadP().getText());
            double precioUnit = Double.parseDouble(view.getTxtPrecioUnitario().getText());
            double total = cantidad * precioUnit;
            view.getTxtTotalE().setText(String.format("%.2f", total));//2 decimales
        } catch (NumberFormatException e) {
            view.getTxtTotalE().setText("");
        }     
    }
    //--------
    
    private void agregarEventos(){       
        
        view.getBtnRegistrarEntrada().addActionListener(this);
        view.getBtnEditarEntrada().addActionListener(this);
        view.getBtnEliminarEntrada().addActionListener(this);
        
        view.getTblTablaProductosABuscarEnt().addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e){
                llenarCampos(e);
            }
            
        });
        
        view.getTblEntradas().addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e){
                llenarCamposTblEntrada(e);
            }
            
        });
    }
    
    private void listarTablaBuscarProdEntradas() {// TRAE LOS REGISTROS A LA TABLA
        // Correccion del array de nombres de columnas
        String[] titulocolumnastbl = new String[] {"Codigo", "Nombre", "IdCategoria", "UndMedida", "Stock"};

        modeloTablaBuscarProdEntrada = new DefaultTableModel(titulocolumnastbl, 0);
        List<Producto> listaProductos = entradadao.listar();

        // Corrección en la creación de las filas
        for (Producto producto : listaProductos) {
            modeloTablaBuscarProdEntrada.addRow(new Object[] {
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria().getIdcat(),
                producto.getUndmedida(),
                producto.getStock()
            });
        }

        view.getTblTablaProductosABuscarEnt().setModel(modeloTablaBuscarProdEntrada);
        view.getTblTablaProductosABuscarEnt().setPreferredSize(new Dimension(350, modeloTablaBuscarProdEntrada.getRowCount() * 16));
    }
    
    private void llenarCampos(MouseEvent e){// DESDE LA TABLA A LOS TXT
       JTable target = (JTable) e.getSource();
       
       view.getTxtIdProducto().setText(view.getTblTablaProductosABuscarEnt().getModel().getValueAt(target.getSelectedRow(),0).toString());//posicion1 de la tabla producto ID producto...vector
       view.getTxtNombreProducto().setText(view.getTblTablaProductosABuscarEnt().getModel().getValueAt(target.getSelectedRow(),1).toString());//posicion2 de la tabla nombre producto...vector
     

    }
    //---------FIN arriba para 1ro tabla prod ID y nombre****************************************************************************************************************************************************************************
    
    private void listarTablaENTRADAS() {// TRAE LOS REGISTROS DE LA BD A LA TABLA
        // Correccion del array de nombres de columnas
        String[] titulocolumnastbl = new String[] {"Cod Entrada", "Cod Producto", "Producto", "Operacion", "Fecha", "Cantidad", "P/U", "Total"};

        modeloTablaEntrada = new DefaultTableModel(titulocolumnastbl, 0);
        List<Entrada> listaEntradas = entradadao.listarTablaEntrada();//listarTablaEntrada DESDE DAO

        // Corrección en la creación de las filas
        for (Entrada entrada : listaEntradas) {
            modeloTablaEntrada.addRow(new Object[] {
                //getter de entrada
                entrada.getIdentrada(),
                entrada.getIdproducto(),
                entrada.getNombreproducto(),
                entrada.getDescoperacion(),
                entrada.getFecha(),
                entrada.getCantidad(),
                entrada.getPreciounitario(),
                entrada.getTotal()

            });
        }

        view.getTblEntradas().setModel(modeloTablaEntrada);
        view.getTblEntradas().setPreferredSize(new Dimension(350, modeloTablaEntrada.getRowCount() * 16));
    }
    
    // Para registro----------------------------------##########----------
    private boolean validarDatosEntradas(){
        
        if("".equals(view.getTxtIdProducto().getText()) ||
                "".equals(view.getTxtDescOperacion().getText()) ||
                "".equals(view.getTxtFechaEntrada().getText()) || 
                "".equals(view.getTxtCantidadP().getText())||
                "".equals(view.getTxtPrecioUnitario().getText())){
            
            JOptionPane.showInputDialog(null,"Debe llenar los campos","ERROR", JOptionPane.ERROR_MESSAGE);
            return false;//retornara falso porque algun campo esta vacio
        }
        return true;//todo esta bien
   
    }
    private boolean cargarDatosEntradas() {
        try {
            codeProducto = Integer.parseInt(view.getTxtIdProducto().getText().trim()); // Eliminar espacios extra
            nombProducto = view.getTxtNombreProducto().getText().trim();
            operacion = view.getTxtDescOperacion().getText().trim();
            fecha = view.getTxtFechaEntrada().getText().trim();
            cantidad = Integer.parseInt(view.getTxtCantidadP().getText().trim());

            // Reemplazar coma por punto para valores numéricos
            precioUnit = Double.parseDouble(view.getTxtPrecioUnitario().getText().trim().replace(",", "."));
            totalEntrada = Double.parseDouble(view.getTxtTotalE().getText().trim().replace(",", "."));

            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, 
                "Verificar decimal de numeros\n" +
                "Ejemplo: '1750.00'",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en cargarDatosEntradas - controlador: " + e);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Ha ocurrido un error inesperado.",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en cargarDatosEntradas - controlador: " + e);
            return false;
        }
    }
    /*private boolean cargarDatosEntradas(){// carga a las variablesGlobls con los valores txt
        try {

            codeProducto = Integer.parseInt(view.getTxtIdProducto().getText()); //lee el valor del txt y lo guarda en la variable CODEPRODUCTO
            nombProducto = view.getTxtNombreProducto().getText();
            operacion = view.getTxtDescOperacion().getText();
            fecha = view.getTxtFechaEntrada().getText();
            cantidad = Integer.parseInt(view.getTxtCantidadP().getText());
            precioUnit = Double.parseDouble(view.getTxtPrecioUnitario().getText());
            totalEntrada = Double.parseDouble(view.getTxtTotalE().getText());

            return true;
            
        } catch (Exception e) {
            JOptionPane.showInputDialog(null,"El campo CANTIDAD deben ser ENTERO","ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en cargarDatosEntradas - controlador: " + e);
            return false;
        }
    }*/
    
    private void LimpiarCamposEntrada(){
                
        view.getTxtIdEntrada().setText("");
        view.getTxtIdProducto().setText("");
        view.getTxtNombreProducto().setText("");
        view.getTxtDescOperacion().setText("");
        view.getTxtFechaEntrada().setText("");
        view.getTxtCantidadP().setText("");
        view.getTxtPrecioUnitario().setText("");
        view.getTxtTotalE().setText("");

        codeEntrada=0;
        codeProducto=0;
        nombProducto="";
        operacion="";
        fecha="";
        cantidad=0;
        precioUnit=0.00;
        totalEntrada=0.00;
  
    }
    
    
    //METODO FINAL AGREGAR PRODUCTO
    private void agregarEntradaDesdeController(){
        try {
            if (validarDatosEntradas()) { // Validación de datos
                if (cargarDatosEntradas()) { // Cargar datos
                    Entrada entrada = new Entrada(codeProducto, nombProducto, operacion, fecha, cantidad, precioUnit,totalEntrada);

                    // Agregar el producto a la base de datos
                    entradadao.agregar(entrada);//agregar proviene de DAO

                    //El mensaje emerge solo si no hay ninguna excepción
                    JOptionPane.showMessageDialog(null, "ENTRADA registrado con EXITO");

                    // Limpiar campos después de agregar el producto
                    LimpiarCamposEntrada();
                }
            }
        } catch (SQLException e) {
            // Este bloque catch solo se ejecuta si ocurre un error de SQL (como el UNIQUE constraint)
            JOptionPane.showMessageDialog(null, "YA EXISTE EL PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en el método agregarEntradaDesdeController - ControladorENTRADA: " + e);
        } catch (HeadlessException e) { // Otras excepciones
            System.out.println("Error en el método agregarEntradaDesdeController - ControladorENTRADA: " + e);
        } finally {
            // Actualizar la tabla sin importar si ocurrió una excepción o no
            listarTablaENTRADAS();
        }        
    }
    // EDITAR REGISTRO DE ENTRADA
    private void editarEntrada(){
        try {
            if(validarDatosEntradas()){
                if(cargarDatosEntradas()){
                    Entrada entrada = new Entrada(codeEntrada, codeProducto, nombProducto, operacion, fecha, cantidad, precioUnit,totalEntrada);
                    entradadao.editarEntrd(entrada);// Se hace uso del metodo actualizar que se creo en productoDAO
                    LimpiarCamposEntrada();//despues de ACTUALIZAR se limpia campos
                    JOptionPane.showMessageDialog(null, "Se ha editado con EXITO");
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error en actualizar - ControladorProducto: "+e);
        }finally{
            listarTablaENTRADAS();
        }
    }
    
    private void llenarCamposTblEntrada(MouseEvent e){// DE LA TABLA A LOS TXT
       JTable target = (JTable) e.getSource();
       
       codeEntrada = (int) view.getTblEntradas().getModel().getValueAt(target.getSelectedRow(),0);//OJO PARA ACTUALIZAR almacena un valor entero en codigoEnt
       view.getTxtIdEntrada().setText(view.getTblEntradas().getModel().getValueAt(target.getSelectedRow(),0).toString());
       view.getTxtIdProducto().setText(view.getTblEntradas().getModel().getValueAt(target.getSelectedRow(),1).toString());
       view.getTxtNombreProducto().setText(view.getTblEntradas().getModel().getValueAt(target.getSelectedRow(),2).toString());
       view.getTxtDescOperacion().setText(view.getTblEntradas().getModel().getValueAt(target.getSelectedRow(),3).toString());
       view.getTxtFechaEntrada().setText(view.getTblEntradas().getModel().getValueAt(target.getSelectedRow(),4).toString());
       view.getTxtCantidadP().setText(view.getTblEntradas().getModel().getValueAt(target.getSelectedRow(),5).toString());
       view.getTxtPrecioUnitario().setText(view.getTblEntradas().getModel().getValueAt(target.getSelectedRow(),6).toString());
       view.getTxtTotalE().setText(view.getTblEntradas().getModel().getValueAt(target.getSelectedRow(),7).toString());
       
       
    }
    
    //metodo ELIMINAR ENTRADA
    private void eliminarEntrada(){
        try {
            if (codeEntrada != 0){//negacion si no es igual a cero sera TRUE
                entradadao.eliminarEntrd(codeEntrada);
                JOptionPane.showMessageDialog(null,"Registro eliminado");
                LimpiarCamposEntrada();
            }else{
                JOptionPane.showMessageDialog(null,"Tiene que seleccionar una ENTRADA mostrado en la tabla","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            
            System.out.println("Error eliminarEntrada - ControladorEntrada: "+ e);
        }finally{
            listarTablaENTRADAS();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (ae.getSource()==view.getBtnRegistrarEntrada()){//Con el evento agregar LISTENER confirma el clic
            agregarEntradaDesdeController();
        }
        
        if (ae.getSource()==view.getBtnEditarEntrada()){
            editarEntrada();
        }
        if (ae.getSource() ==view.getBtnEliminarEntrada()){
            eliminarEntrada();
        }
    
    }
    
}
