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
import org.slf4j.Logger;
import service.ServiceProductoDaoImpl;
import service.interfaz.ServiceProducto;
import utils.UtilsLoggerManager;
import utils.UtilsProducto;
import view.ViewRegistroProductos;
/**
 *
 * @author Elvis
 */
public class ControllerProducto implements ActionListener{//implements controla las acciones de la vista
    private final Logger LOGGER = UtilsLoggerManager.getLogger(ControllerProducto.class);
    
    //variables globales para metodo cargar datos
    private int codigoProd=0;//=0
    private String nombreProd;
    private int idCategoria;
    private String undMedidaProd;
    private int stockProd;

    //instanciacion de objetos
    ServiceProducto serviceProducto = new ServiceProductoDaoImpl();
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
                    List<DTOProducto> listDtoFiltrado = serviceProducto.buscarProductosPorNombreOIdConPrefijo(filtro);
                    if (listDtoFiltrado == null) {
                        throw new NullPointerException("El producto no se encontró.");
                    }
                    UtilsProducto.actualizarTablaCompleta(modeloTabla, listDtoFiltrado);

                    view.getTblTablaProductos().setModel(modeloTabla);
                } catch (Exception e) {
                    LOGGER.error("No encontrado. " + e.getMessage());
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

        List<DTOProducto> listaDtoProductos = serviceProducto.obtenerTodosLosProductos();

        // Corrección en la creación de las filas
        modeloTabla = UtilsProducto.crearTablaCompleta(titulocolumnastbl, listaDtoProductos);

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
        return UtilsProducto.validarCamposVistaParaDTOProducto(
                view.getTxtNombreProducto().getText(), 
                view.getTxtCodCategoria().getText(), 
                view.getTxtUndMedida().getText(), 
                view.getTxtStockProducto().getText());
    }
    
    private boolean cargarDatos(){// metodo carga a las variablesGlob con los valores txt, validacion de datos correctos en integer parseint
        DTOProducto dtoProducto = UtilsProducto.cargarDatosDeLabelsADTOProducto(
            view.getTxtNombreProducto(),
            view.getTxtCodCategoria(),
            view.getTxtUndMedida(),
            view.getTxtStockProducto()
        );

        if (dtoProducto != null) {
            this.nombreProd = dtoProducto.getNombre();
            this.idCategoria = dtoProducto.getIdCategoria();
            this.undMedidaProd = dtoProducto.getUndMedida();
            this.stockProd = dtoProducto.getStock();
            return true;
        } else {
            return false;
        }
    }
    //***************FIN Validacion de formularios tipo booleanoo si esta vacio o no**********************
    private void agregarProducto(){//77777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
        System.out.println("Llegó");
        try {
            if (validarDatos()) { // Validación de datos
                        System.out.println("Llegó2");
                if (cargarDatos()) { // Cargar datos
                            System.out.println("Llegó3");
                    //nombreProd, idCategoria, undMedidaProd, stockProd
                    DTOProducto dtoProducto = new DTOProducto()
                            .setNombre(nombreProd)
                            .setIdCategoria(idCategoria)
                            .setUndMedida(undMedidaProd)
                            .setStock(stockProd);

                    // Intentar agregar el dtoProducto a la base de datos
                    serviceProducto.agregarProducto(dtoProducto);

                    // Solo se muestra el mensaje de éxito si no ocurre ninguna excepción
                    UtilsProducto.mostrarMensajeExitoso("Se ha registrado con éxito");

                    // Limpiar campos después de agregar el dtoProducto
                    LimpiarCampos();
                } else {
                    LOGGER.error("Error en cargar datos.");
                }
            }  
        } catch (Exception e) { // Manejo específico para SQLException
            // Este bloque catch solo se ejecuta si ocurre un error de SQL (como el UNIQUE constraint)
            UtilsProducto.mostrarMensajeError("YA EXISTE EL PRODUCTO");
            LOGGER.error("Error en el método agregarProducto - ControladorProducto: " + e.getMessage());
            // Actualizar la tabla sin importar si ocurrió una excepción o no
            listarTabla();
        }        
        /*try {
            if(validarDatos()){//validacion de metodo validar datos TRUE
                if(cargarDatosDeLabelsADTOProducto()){//validacion de cargar datos si es TRUE, si todo ok sigue bajando
                    Producto dtoProducto = new Producto(nombreProd, idCategoria, undMedidaProd, stockProd);//Aqui se usa el contructor sin codigo en dtoProducto, para usar el metodo de cargarDatosDeLabelsADTOProducto()
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
                            .setIdCategoria(idCategoria)
                            .setUndMedida(undMedidaProd)
                            .setStock(stockProd);
                    serviceProducto.actualizarProducto(dtoProducto);// Se hace uso del metodo actualizar que se creo en productoDAO
                    LimpiarCampos();//despues de ACTUALIZAR se limpia campos
                
                }
            }
        } catch (HeadlessException e) {
            LOGGER.error("Error en actualizar - ControladorProducto: " + e.getMessage());
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
                UtilsProducto.mostrarMensajeError("Tiene que seleccionar un producto mostrado en la tabla");
            }
        } catch (Exception e) {
            LOGGER.error("Error eliminar - Controlador Producto: " + e.getMessage());
        }finally{
            listarTabla();
        }
    }
    
    //metodo filtrado
    private void actualizarTablaFiltrada() {
        String filtro = view.getTxtIdBuscar().getText();
        DTOProducto productoFiltrado = serviceProducto.obtenerProductoPorParametro(filtro);
        try {
            UtilsProducto.actualizarTablaCompleta(modeloTabla, productoFiltrado);
        } catch (NullPointerException e){
            UtilsProducto.mostrarMensajeError("Debe llenar elementos en buscar");
            LOGGER.error("Elementos en buscar incompletos. ", e.getMessage());
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
        if (ae.getSource() ==view.getBtnBuscarProducto()) {
            actualizarTablaFiltrada();
        }
        
        
        //+++++++++++++++++++++++++++++++++++++++
        
        //+++++++++++++++++++++++++++
    
    }
 
    
    
}
