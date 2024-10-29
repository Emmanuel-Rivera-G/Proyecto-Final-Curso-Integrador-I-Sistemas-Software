package controller;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import dto.DTOProducto;
import service.ServiceProducto;
import utils.UtilsProducto;
import view.ViewRegistroProductos;
/**
 *
 * @author Elvis
 */
public class ControllerProducto implements ActionListener{//implements controla las acciones de la vista
    //variables globales para metodo cargar datos
    private int codigoProd=0;//=0
    private String nombreProd;
    private int idCategoria;
    private String undMedidaProd;
    private int stockProd;
    


    //instanciacion de objetos
    ServiceProducto serviceProducto = new ServiceProducto();
    ViewRegistroProductos view = new ViewRegistroProductos();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    
    public ControllerProducto(ViewRegistroProductos view) {
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
                try {
                    List<DTOProducto> ListDtoFiltrado = serviceProducto.buscarProductosPorNombreOIdConPrefijo(filtro);
                    if (ListDtoFiltrado == null) {
                        throw new NullPointerException("El producto no se encontró.");
                    }
                    modeloTabla.setRowCount(0);
                    for (DTOProducto dtoFiltrado : ListDtoFiltrado) {
                        modeloTabla.addRow(new Object[] {
                            dtoFiltrado.getIdProducto(),
                            dtoFiltrado.getNombre(),
                            dtoFiltrado.getIdCategoría(),
                            dtoFiltrado.getUndMedida(),
                            dtoFiltrado.getStock()
                        });
                    }

                    view.getTblTablaProductos().setModel(modeloTabla);
                } catch (Exception e) {
                    modeloTabla.setRowCount(0);
                    List<DTOProducto> listaDtoProductos = serviceProducto.obtenerTodosLosProductos();
                    for (DTOProducto producto : listaDtoProductos) {
                        modeloTabla.addRow(new Object[] {
                            producto.getIdProducto(),
                            producto.getNombre(),
                            producto.getIdCategoría(),
                            producto.getUndMedida(),
                            producto.getStock()
                        });
                    }

                    view.getTblTablaProductos().setModel(modeloTabla);
                }
            }
        });
        
        //-------------------------------para FILTRAR
        
        
    
    }

    /*public ControllerProducto(ViewMenuPrincipal vista) {
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
        view.getBtnBuscarProducto().addActionListener(this);
        
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
        List<DTOProducto> listaDtoProductos = serviceProducto.obtenerTodosLosProductos();

        // Corrección en la creación de las filas
        for (DTOProducto dtoProducto : listaDtoProductos) {
            modeloTabla.addRow(UtilsProducto.convertirAArray(dtoProducto));
            dtoProducto = null;
        }

        view.getTblTablaProductos().setModel(modeloTabla);
        view.getTblTablaProductos().setPreferredSize(new Dimension(350, modeloTabla.getRowCount() * 16));
    }
    /*private void listarTabla(){
        String[] titulocolumnastbl = new String[]("Codigo","Nombre","IdCategoria","UndMedida","Stock");
        //String[] titulos = new String[]("Codigo","Nombre","IdCategoria","UndMedida","Stock");
        //String[] titulocolumnastbl = new String[]("CODIGO","NOMBRE","IDCATEGORIA","UND MEDIDA","STOCK");
        modeloTabla = new DefaultTableModel(titulocolumnastbl,0);
        List<Producto>listaProductos = productodao.obtenerTodosLosProductos();
        for (Producto dtoProducto : listaProductos){
            
            modeloTabla.addRow(new Object[](dtoProducto.getId(),dtoProducto.getNombre(),dtoProducto.getIdcategoria(),dtoProducto.getUndmedida(),dtoProducto.getStock()));
            
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
        //if ("".equals(ViewRegistroProductos.getTxtNombreProducto().getText())) {}
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
                    //nombreProd, idCategoria, undMedidaProd, stockProd
                    DTOProducto dtoProducto = new DTOProducto()
                            .setNombre(nombreProd)
                            .setIdCategoría(idCategoria)
                            .setUndMedida(undMedidaProd)
                            .setStock(stockProd);

                    // Intentar agregar el dtoProducto a la base de datos
                    serviceProducto.agregarProducto(dtoProducto);

                    // Solo se muestra el mensaje de éxito si no ocurre ninguna excepción
                    JOptionPane.showMessageDialog(null, "Se ha registrado con éxito");

                    // Limpiar campos después de agregar el dtoProducto
                    LimpiarCampos();
                }
            }
        } catch (Exception e) { // Manejo específico para SQLException
            // Este bloque catch solo se ejecuta si ocurre un error de SQL (como el UNIQUE constraint)
            JOptionPane.showMessageDialog(null, "YA EXISTE EL PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en el método agregarProducto - ControladorProducto: " + e);
        } finally {
            // Actualizar la tabla sin importar si ocurrió una excepción o no
            listarTabla();
        }        
        /*try {
            if(validarDatos()){//validacion de metodo validar datos TRUE
                if(cargarDatos()){//validacion de cargar datos si es TRUE, si todo ok sigue bajando
                    Producto dtoProducto = new Producto(nombreProd, idCategoria, undMedidaProd, stockProd);//Aqui se usa el contructor sin codigo en dtoProducto, para usar el metodo de cargarDatos()
                    serviceProducto.agregarProducto(dtoProducto);//desde aqui llama al metodo agregar (interactua con la BD sql)de productoDAO ya instanciado
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
                    DTOProducto dtoProducto = new DTOProducto()
                            .setIdProducto(codigoProd)
                            .setNombre(nombreProd)
                            .setIdCategoría(idCategoria)
                            .setUndMedida(undMedidaProd)
                            .setStock(stockProd);
                    serviceProducto.actualizarProducto(dtoProducto);// Se hace uso del metodo actualizar que se creo en productoDAO
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
                serviceProducto.eliminarProducto(codigoProd);
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
    private void actualizarTablaFiltrada() {
        String filtro = view.getTxtIdBuscar().getText();
        DTOProducto productoFiltrado = serviceProducto.obtenerProductoPorParametro(filtro);

        // Obtiene el modelo de la tabla y la limpia
        modeloTabla.setRowCount(0);

        // Agrega las filas filtradas
        modeloTabla.addRow(UtilsProducto.convertirAArray(productoFiltrado));

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
        if (ae.getSource() ==view.getBtnBuscarProducto()) {
            actualizarTablaFiltrada();
        }
        
        
        //+++++++++++++++++++++++++++++++++++++++
        
        //+++++++++++++++++++++++++++
    
    }
 
    
    
}
