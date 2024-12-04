package controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import dao.implemetacion.DAOSalidaImpl;
import dao.interfaz.DAOSalida;
import dto.DTOProducto;
import dto.DTOSalida;
import dto.DTOUsuario;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiFunction;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import org.slf4j.Logger;
import utils.MessageUtils;
import utils.UtilsLoggerManager;
import view.ViewListadoSalidas;
import view.ViewRegistroSalidas;

/**
 *
 * @author Emmanuel
 */
public class ControllerSalida {

    private final static Logger LOGGER = UtilsLoggerManager.getLogger(ControllerSalida.class);

    private final DAOSalida serviceSalida;

    private final String[] titulosTablaSalida = {"ID", "Producto", "Cantidad", "Valor Unitario", "Valor Total", "Fecha Salida", "Usuario", "Destino"};

    private ViewListadoSalidas vistaListado;

    private JTable tablaListadoSalidas;
    private JTable tablaListadoSalidasProductos;

    private DefaultTableModel defaultTablaSalidaModelListado;

    private JLabel btnAnularListado;
    private JLabel btnBuscarListado;
    private JLabel btnPdfListado;

    private JTextField txtSalidaListado;
    private JTextField txtSalidaIdListado;
    private JTextField txtFechaInicialListado;
    private JTextField txtFechaFinalListado;

    private void iniciarControladorVistaListado() {
        iniciarTablasListado();
        iniciarBotonesListado();
        iniciarTextFielListado();

        inicarListenerBtnVistaListado();
        actualizarTotalmenteTablaSalidaListado();
    }

    private void iniciarTablasListado() {
        this.tablaListadoSalidas = vistaListado.getTbl_listadoSalidas();
        this.tablaListadoSalidasProductos = vistaListado.getLbl_salidas_p();
        iniciarDefaultModelTableListado();
    }

    private void iniciarDefaultModelTableListado() {
        this.defaultTablaSalidaModelListado = new DefaultTableModel(titulosTablaSalida, 0);
        this.tablaListadoSalidas.setModel(defaultTablaSalidaModelListado);
    }

    private void iniciarBotonesListado() {
        this.btnAnularListado = vistaListado.getLbl_anular();
        this.btnBuscarListado = vistaListado.getLbl_buscar4();
        this.btnPdfListado = vistaListado.getLbl_pdf();
    }

    private void iniciarTextFielListado() {
        this.txtSalidaListado = vistaListado.getTxtField_salida();
        this.txtSalidaIdListado = vistaListado.getTxtField_id_salida();
        this.txtFechaInicialListado = vistaListado.getTxtField_fechaInicial();
        this.txtFechaFinalListado = vistaListado.getLblFechaFinal();
    }

    private void inicarListenerBtnVistaListado() {
        this.btnAnularListado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionBtnAnularListado(e);
            }
        });
        this.btnBuscarListado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionBtnBuscarListado(e);
            }
        });
        this.btnPdfListado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Hola");
                actionBtnPdfListado(e);
            }
        });
    }

    private void actionBtnAnularListado(MouseEvent event) {
        for (JTextField txt : new JTextField[]{txtSalidaListado, txtSalidaIdListado, txtFechaInicialListado, txtFechaFinalListado}) {
            txt.setText("");
        }
    }

    private void actionBtnBuscarListado(MouseEvent event) {
        int idSalida = parseStringToInt.apply(this.txtSalidaIdListado.getText(), "El id tiene que ser un número.");
        DTOSalida dtoSalida = serviceSalida.obtenerSalidaPorId(idSalida);
        if (dtoSalida == null) {
            MessageUtils.mostrarMensajeError("La salida buscada no exite de ID: " + idSalida);
            return;
        }
        this.actualizarTablaSalidaListado(List.of(dtoSalida));
    }

    private void actionBtnPdfListado(MouseEvent event) {
        JFileChooser selecArchivo = new JFileChooser();
        File archivo;

        try {
            if (selecArchivo.showDialog(null, "Exportar") == JFileChooser.APPROVE_OPTION) {
                archivo = selecArchivo.getSelectedFile();
                String fileName = archivo.getName().toLowerCase();

                if (!fileName.endsWith(".pdf")) {
                    archivo = new File(archivo.getAbsolutePath() + ".pdf");
                }

                String resultado = exportarPdf(archivo, tablaListadoSalidas);
                JOptionPane.showMessageDialog(null, resultado + "\nArchivo exportado: " + archivo.getAbsolutePath());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error durante la exportación: " + e.getMessage());
        }
    }

    private String exportarPdf(File archivo, JTable tablaListadoSalidas) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();

            int numColumns = tablaListadoSalidas.getColumnCount();
            PdfPTable pdfTable = new PdfPTable(numColumns);

            JTableHeader header = tablaListadoSalidas.getTableHeader();
            for (int i = 0; i < numColumns; i++) {
                pdfTable.addCell(header.getColumnModel().getColumn(i).getHeaderValue().toString());
            }

            TableModel model = tablaListadoSalidas.getModel();
            int numRows = tablaListadoSalidas.getRowCount();
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    Object value = model.getValueAt(i, j);
                    pdfTable.addCell(value != null ? value.toString() : "");
                }
            }

            pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            document.add(pdfTable);

            JOptionPane.showMessageDialog(null, "Exportación a PDF exitosa en: " + archivo.getAbsolutePath());
            return "Exitoso.";
        } catch (DocumentException | HeadlessException | FileNotFoundException e) {
            e.printStackTrace();
            return "Hubo un problema";
        } finally {
            document.close();
        }
    }

    private void actualizarTotalmenteTablaSalidaListado() {
        actualizarTablaSalidaListado(serviceSalida.obtenerTodasLasSalidas());
    }

    private void actualizarTablaSalidaListado(List<DTOSalida> list) {
        this.defaultTablaSalidaModelListado.setRowCount(0);
        for (DTOSalida dtos : list) {
            this.defaultTablaSalidaModelListado.addRow(toArray(dtos));
        }
    }

    public static Object[] toArray(DTOSalida dtoSalida) throws NullPointerException {
        return new Object[]{
            dtoSalida.getIdSalida(),
            dtoSalida.getDtoProducto().getNombre(),
            dtoSalida.getCantidad(),
            dtoSalida.getValorUnitario(),
            dtoSalida.getValorTotal(),
            dtoSalida.getFechaSalida(),
            dtoSalida.getDtoUsuario().getNombre(),
            dtoSalida.getDestino()
        };
    }
    
    
    

    private ViewRegistroSalidas vistaRegistro;
    
    private final String[] titulosTablaSalidaRegistro = {"ID Salida", "ID Entrada", "Producto", "Precio U.", "Cantidad", "Importe"};
    
    private JTable tablaSalidaRegistro;
    private DefaultTableModel defaultTableModelSalidaRegistro;
    
    private JTextField txtIdSalidaRegistro;
    private JTextField txtNombreSalidaRegistro;
    private JTextField txtStockDisponibleSalidaRegistro;
    private JTextField txtCantidadSalidaRegistro;
    private JTextField txtPrecioSalidaRegistro;
    private JTextField txtFechaSalidaRegistro;
    
    private JTextField txtSubTotalSalidaRegistro;
    private JTextField txtIGVSalidaRegistro;
    private JTextField txtTotalSalidaRegistro;
    
    private JLabel btnBuscarSalidaRegistro;
    private JLabel btnGenerarSalidaRegistro;
    private JLabel btnAgregarSalidaRegistro;
    
    private JLabel btnClearSalidaRegistro;
    private JLabel btnRefreshSalidaRegistro;

    private void iniciarControladorVistaRegistro() {
        this.iniciarTablaSalidaRegistro();
        this.iniciarBotonesRegistro();
        this.iniciarTxtFieldRegistro();
        
        this.iniciarListenerBtnRegistro();
        this.actulaizarTablaTotalmenteRegistro();
    }
    
    private void iniciarTablaSalidaRegistro() {
        this.tablaSalidaRegistro = this.vistaRegistro.getTbl_salidas();
        this.defaultTableModelSalidaRegistro = new DefaultTableModel(titulosTablaSalida, 0);
        this.tablaSalidaRegistro.setModel(defaultTableModelSalidaRegistro);
    }
    
    private void iniciarBotonesRegistro() {
        this.btnAgregarSalidaRegistro = this.vistaRegistro.getLbl_agregar();
        this.btnBuscarSalidaRegistro = this.vistaRegistro.getLbl_buscar4();
        this.btnGenerarSalidaRegistro = this.vistaRegistro.getLbl_generar();
        this.btnClearSalidaRegistro = this.vistaRegistro.getLbl_delete();
        this.btnRefreshSalidaRegistro = this.vistaRegistro.getLbl_restar();
    }
    
    private void iniciarTxtFieldRegistro() {
        this.txtIdSalidaRegistro = this.vistaRegistro.getTxtField_id_salida();
        this.txtNombreSalidaRegistro = this.vistaRegistro.getTxtField_nombre();
        this.txtStockDisponibleSalidaRegistro = this.vistaRegistro.getTxtField_stockDisponible();
        this.txtCantidadSalidaRegistro = this.vistaRegistro.getTxtField_cantidad();
        this.txtPrecioSalidaRegistro = this.vistaRegistro.getTxtField_precio();
        this.txtFechaSalidaRegistro = this.vistaRegistro.getTxtField_fecha_salida();
        this.txtIGVSalidaRegistro = this.vistaRegistro.getTxtField_igv();
        this.txtSubTotalSalidaRegistro = this.vistaRegistro.getTxtField_subtotal();
        this.txtTotalSalidaRegistro = this.vistaRegistro.getTxtField_total();
    }
    
    private void iniciarListenerBtnRegistro() {
        this.btnAgregarSalidaRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionBtnAgregarRegistro(e);
            }
        });
        this.btnBuscarSalidaRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionBtnBuscarRegistro(e);
            }
        });
        this.btnGenerarSalidaRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionBtnGenerarRegistro(e);
            }
        });
        this.btnClearSalidaRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionBtnClearRegistro(e);
            }
        });
        this.btnRefreshSalidaRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionBtnRefreshRegistro(e);
            }
        });
    }
    
    private void actionBtnAgregarRegistro(MouseEvent event) {
        DTOSalida dtoSalida = new DTOSalida();
        dtoSalida.setCantidad(Integer.parseInt(this.txtCantidadSalidaRegistro.getText()));
        dtoSalida.setDestino("");
        dtoSalida.setDtoProducto(new DTOProducto());
        dtoSalida.setDtoUsuario(new DTOUsuario());
        dtoSalida.setFechaSalida(LocalDateTime.parse(this.txtFechaSalidaRegistro.getText()));
        dtoSalida.setIdSalida(parseStringToInt.apply(this.txtIdSalidaRegistro.getText(), "El id tiene que ser un número."));
        dtoSalida.setValorTotal(Double.parseDouble(this.txtTotalSalidaRegistro.getText()));
        dtoSalida.setValorUnitario(Double.parseDouble(this.txtTotalSalidaRegistro.getText()) / Double.parseDouble(this.txtCantidadSalidaRegistro.getText()));
        MessageUtils.mostrarMensajeExitoso(String.valueOf(serviceSalida.guardarSalida(dtoSalida)));
    }
    
    private void actionBtnBuscarRegistro(MouseEvent event) {
        int idSalida = parseStringToInt.apply(this.txtIdSalidaRegistro.getText(), "El id tiene que ser un número.");
        if (idSalida == -1) {
            actulaizarTablaTotalmenteRegistro();
            return;
        }
        DTOSalida dtoSalida = serviceSalida.obtenerSalidaPorId(idSalida);
        if (dtoSalida == null) {
            MessageUtils.mostrarMensajeError("La salida buscada no exite de ID: " + idSalida);
            return;
        }
        this.actualizarTablaSalidaRegistro(List.of(dtoSalida));
    }
    
    private void actionBtnGenerarRegistro(MouseEvent event) {
        
    }
    
    private void actionBtnClearRegistro(MouseEvent event) {
        for (JTextField txt : new JTextField[]{
            txtCantidadSalidaRegistro,
            txtFechaSalidaRegistro,
            txtIGVSalidaRegistro, 
            txtIdSalidaRegistro,
            txtNombreSalidaRegistro,
            txtPrecioSalidaRegistro,
            txtStockDisponibleSalidaRegistro,
            txtSubTotalSalidaRegistro,
            txtTotalSalidaRegistro
        }) {
            txt.setText("");
        }
    }
    
    private void actionBtnRefreshRegistro(MouseEvent event) {
        actulaizarTablaTotalmenteRegistro();
    }
    
    private void actulaizarTablaTotalmenteRegistro() {
        this.actualizarTablaSalidaRegistro(serviceSalida.obtenerTodasLasSalidas());
    }
    
    private void actualizarTablaSalidaRegistro(List<DTOSalida> list) {
        this.defaultTableModelSalidaRegistro.setRowCount(0);
        for (DTOSalida dtos : list) {
            this.defaultTableModelSalidaRegistro.addRow(toArray(dtos));
        }
    }
    
    
    

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

    private ControllerSalida() {
        this.serviceSalida = new DAOSalidaImpl();
    }
    
    public ControllerSalida(ViewListadoSalidas vistaListado) {
        this();
        this.vistaListado = vistaListado;
        iniciarControladorVistaListado();
    }
    
    public ControllerSalida(ViewRegistroSalidas vistaRegistro) {
        this();
        this.vistaRegistro = vistaRegistro;
        iniciarControladorVistaRegistro();
    }

}
