/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;//------throw
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;//pruebaaaaaa filtrar
import javax.swing.event.DocumentListener;//pruebaaaaaa filtrar
import javax.swing.table.DefaultTableModel;
import model.Producto;
import model.ProductoDAO;
import view.ViewMenuPrincipal;
import view.viewRegistroProductos;// interfaz grafica Registro producto
//import view.ViewMenuPrincipal;

//import java.util.ArrayList;
/**
 *
 * @author Elvis
 */
public class ControladorProducto implements ActionListener{//implements controla las acciones de la vista
    //variables globales para metodo cargar datos
    private int codigoProd=0;//=0
    private String nombreProd;
    private int idCategoria;///############################
    private String undMedidaProd;
    private int stockProd;
    


    //instanciacion de objetos
    Producto producto = new Producto();//hara uso de constructor vacio
    ProductoDAO productodao = new ProductoDAO();
    viewRegistroProductos view = new viewRegistroProductos();
    DefaultTableModel modeloTabla = new DefaultTableModel();// carga los nombres de la base de datos para interactuar con el
    
    
    
    public ControladorProducto(viewRegistroProductos view) {
        this.view = view;// recibe los valores o dato
        view.setVisible(true);
        
        agregarEventos();//trayendo los metodos
        listarTabla();
        
        //-------------------------------para FILTRAR
        view.getTxtIdBuscar().getDocument().addDocumentListener(new DocumentListener() {
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
                String filtro = view.getTxtIdBuscar().getText();
                List<Producto> listaFiltrada = productodao.filtrarProducto(filtro);

                // Limpiar la tabla antes de mostrar los resultados filtrados
                modeloTabla.setRowCount(0);

                // Agregar los productos filtrados a la tabla
                for (Producto producto : listaFiltrada) {
                    modeloTabla.addRow(new Object[] {
                        producto.getId(),
                        producto.getNombre(),
                        producto.getIdcategoria(),
                        producto.getUndmedida(),
                        producto.getStock()
                    });
                }

                view.getTblTablaProductos().setModel(modeloTabla);

                //String filtro = view.getTxtIdBuscar().getText();
                //actualizarTablaFiltrada(filtro);
            }
        });
        
        //-------------------------------para FILTRAR
        
        
    
    }

    /*public ControladorProducto(ViewMenuPrincipal vista) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        this.view = view;// recibe los valores o dato
        view.setVisible(true);
        
        agregarEventos();//trayendo los metodos
        listarTabla();
    
    }*/

    // aqui lee si se ha apretado al boton o no mediante LISTENER
    private void agregarEventos(){       
        
        
        view.getBtnRegistrarProducto().addActionListener(this);
        
        view.getBtnEditarProducto().addActionListener(this);
        view.getBtnEliminarProducto().addActionListener(this);
        view.getBtnLimpiarProducto().addActionListener(this);
        
        view.getTblTablaProductos().addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e){
                llenarCampos(e);
            }
            
        });
    }
    
    
    private void listarTabla() {// TRAE LOS REGISTROS A LA TABLA
        // Correccion del array de nombres de columnas
        String[] titulocolumnastbl = new String[] {"Codigo", "Nombre", "IdCategoria", "UndMedida", "Stock"};

        modeloTabla = new DefaultTableModel(titulocolumnastbl, 0);
        List<Producto> listaProductos = productodao.listar();

        // Corrección en la creación de las filas
        for (Producto producto : listaProductos) {
            modeloTabla.addRow(new Object[] {
                producto.getId(),
                producto.getNombre(),
                producto.getIdcategoria(),
                producto.getUndmedida(),
                producto.getStock()
            });
        }

        view.getTblTablaProductos().setModel(modeloTabla);
        view.getTblTablaProductos().setPreferredSize(new Dimension(350, modeloTabla.getRowCount() * 16));
    }
    /*private void listarTabla(){
        String[] titulocolumnastbl = new String[]("Codigo","Nombre","IdCategoria","UndMedida","Stock");
        //String[] titulos = new String[]("Codigo","Nombre","IdCategoria","UndMedida","Stock");
        //String[] titulocolumnastbl = new String[]("CODIGO","NOMBRE","IDCATEGORIA","UND MEDIDA","STOCK");
        modeloTabla = new DefaultTableModel(titulocolumnastbl,0);
        List<Producto>listaProductos = productodao.listar();
        for (Producto producto : listaProductos){
            
            modeloTabla.addRow(new Object[](producto.getId(),producto.getNombre(),producto.getIdcategoria(),producto.getUndmedida(),producto.getStock()));
            
        }
        view.getTblTablaProductos().setModel(modeloTabla);
        view.getTblTablaProductos().setPreferredSize(new Dimension(350,modeloTabla.getRowCount()*16));
        
    }*/
    
    private void llenarCampos(MouseEvent e){// DESDE LA TABLA A LOS TXT
       JTable target = (JTable) e.getSource();
       
       codigoProd = (int) view.getTblTablaProductos().getModel().getValueAt(target.getSelectedRow(),0);//OJO PARA ACTUALIZAR almacena un valor entero en codigoProd
       view.getTxtCodProducto().setText(view.getTblTablaProductos().getModel().getValueAt(target.getSelectedRow(),0).toString());//PRUEBAAAAAAAAAAAAAAAAAA convierte a STRINGy muestra ese valor como texto en el campo de entrada txtfield
       
       view.getTxtNombreProducto().setText(view.getTblTablaProductos().getModel().getValueAt(target.getSelectedRow(),1).toString());//posicion1 de la tabla ...vector
       view.getTxtCodCategoria().setText(view.getTblTablaProductos().getModel().getValueAt(target.getSelectedRow(),2).toString());
       view.getTxtUndMedida().setText(view.getTblTablaProductos().getModel().getValueAt(target.getSelectedRow(),3).toString());
       view.getTxtStockProducto().setText(view.getTblTablaProductos().getModel().getValueAt(target.getSelectedRow(),4).toString());
       
    }
    
    //***************INICIO Validacion de formularios tipo booleanoo si esta vacio o no**********************
    private boolean validarDatos(){
        //if ("".equals(viewRegistroProductos.getTxtNombreProducto().getText())) {}
        if("".equals(view.getTxtNombreProducto().getText()) ||"".equals(view.getTxtCodCategoria().getText()) || "".equals(view.getTxtUndMedida().getText())||"".equals(view.getTxtStockProducto().getText())){
            JOptionPane.showInputDialog(null,"Debe llenar los campos","ERROR", JOptionPane.ERROR_MESSAGE);
            return false;//retornara falso porque algun campo esta vacio
        }
        return true;//todo esta bien
   
    }
    
    private boolean cargarDatos(){// metodo carga a las variablesGlob con los valores txt, validacion de datos correctos en integer parseint
        try {
            nombreProd = view.getTxtNombreProducto().getText();//lee el valor del txt y lo guarda en la variable nombreProd
            idCategoria = Integer.parseInt(view.getTxtCodCategoria().getText());///#############################
            undMedidaProd = view.getTxtUndMedida().getText();
            stockProd = Integer.parseInt(view.getTxtStockProducto().getText()) ;
            return true;
        } catch (Exception e) {
            JOptionPane.showInputDialog(null,"El campo ID CATEGORIA y STOCK deben ser numerico","ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en la carga de datos: "+e);
            return false;
        }
    }
    //***************FIN Validacion de formularios tipo booleanoo si esta vacio o no**********************
    private void agregarProducto(){//77777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
        try {
            if (validarDatos()) { // Validación de datos
                if (cargarDatos()) { // Cargar datos
                    Producto producto = new Producto(nombreProd, idCategoria, undMedidaProd, stockProd);

                    // Intentar agregar el producto a la base de datos
                    productodao.agregar(producto);

                    // Solo se muestra el mensaje de éxito si no ocurre ninguna excepción
                    JOptionPane.showMessageDialog(null, "Se ha registrado con éxito");

                    // Limpiar campos después de agregar el producto
                    LimpiarCampos();
                }
            }
        } catch (SQLException e) { // Manejo específico para SQLException
            // Este bloque catch solo se ejecuta si ocurre un error de SQL (como el UNIQUE constraint)
            JOptionPane.showMessageDialog(null, "YA EXISTE EL PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en el método agregarProducto - ControladorProducto: " + e);
        } catch (HeadlessException e) { // Otras excepciones
            System.out.println("Error en el método agregarProducto - ControladorProducto: " + e);
        } finally {
            // Actualizar la tabla sin importar si ocurrió una excepción o no
            listarTabla();
        }        
        /*try {
            if(validarDatos()){//validacion de metodo validar datos TRUE
                if(cargarDatos()){//validacion de cargar datos si es TRUE, si todo ok sigue bajando
                    Producto producto = new Producto(nombreProd, idCategoria, undMedidaProd, stockProd);//Aqui se usa el contructor sin codigo en producto, para usar el metodo de cargarDatos()
                    productodao.agregar(producto);//desde aqui llama al metodo agregar (interactua con la BD sql)de productoDAO ya instanciado
                    
                    JOptionPane.showMessageDialog(null, "Se ha registrado con exito");
                    LimpiarCampos();//despues de agregar se limpia campos
                }
            }
            
        } catch (HeadlessException e) {//HeadlessException e
            
            System.out.println("Error en el metodo agregarProducto - Controladorproducto:  "+e);
        }finally{//Para que despues cargue autamaticamente lo que se cargo a la BD se actualice a la tabla con listarTabla()
            listarTabla();
        }*/
        
    }
    
    //metodo de limpiar campos de txtfields
    private void LimpiarCampos(){
        view.getTxtCodProducto().setText("");
        view.getTxtNombreProducto().setText("");// campos vacios
        view.getTxtCodCategoria().setText("");
        view.getTxtUndMedida().setText("");
        view.getTxtStockProducto().setText("");
        
        codigoProd = 0;
        nombreProd = "";//campos vacios
        idCategoria = 0;
        undMedidaProd = "";
        stockProd = 0;
        
    }
    
    
    //Metodo de actualizar produco
    private void actualizarProducto(){
        try {
            if(validarDatos()){
                if(cargarDatos()){
                    Producto producto = new Producto(codigoProd, nombreProd, idCategoria, undMedidaProd, stockProd);
                    productodao.actualizar(producto);// Se hace uso del metodo actualizar que se creo en productoDAO
                    LimpiarCampos();//despues de ACTUALIZAR se limpia campos
                
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error en actualizar - ControladorProducto: "+e);
        }finally{
            listarTabla();
        }
    }
    
    //METODO BORRAR - ELIMINAR
    private void eliminarProducto(){
        try {
            if (codigoProd != 0){//negacion si no esigual a cero sera TRUE
                productodao.eliminar(codigoProd);
                JOptionPane.showMessageDialog(null,"Registro borrado");
                LimpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null,"Tiene que seleccionar un producto mostrado en la tabla","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            
            System.out.println("Error eliminar - Controlador Producto: "+e);
        }finally{
        listarTabla();
        }
    }
    
    //metodo filtrado
    private void actualizarTablaFiltrada(String filtro) {
        List<Producto> productosFiltrados = productodao.filtrarProducto(filtro);

        // Obtiene el modelo de la tabla y la limpia
        modeloTabla.setRowCount(0);

        // Agrega las filas filtradas
        for (Producto producto : productosFiltrados) {
            modeloTabla.addRow(new Object[]{
                producto.getId(),
                producto.getNombre(),
                producto.getIdcategoria(),
                producto.getUndmedida(),
                producto.getStock()
            });
        }

        view.getTblTablaProductos().setModel(modeloTabla);
    }
    
    
    
    
    
    
    //metodo abstracto secreo desde listar ACTION LISTENER para dar action a los buttons
    //IMPLEMENTACION DE ACCIONES
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==view.getBtnRegistrarProducto()){//Con el evento agregar LISTENER confirma el clic
            agregarProducto();
        }
        if (ae.getSource()==view.getBtnLimpiarProducto()){//Con el evento limpiar LISTENER confirma el clic
            LimpiarCampos();
        }
        if (ae.getSource()==view.getBtnEditarProducto()){
            actualizarProducto();
        }
        if (ae.getSource() ==view.getBtnEliminarProducto()){
            eliminarProducto();
        }
        
        
        //+++++++++++++++++++++++++++++++++++++++
        
        //+++++++++++++++++++++++++++
    
    }
 
    
    
}
