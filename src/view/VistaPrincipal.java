package view;

import config.Conexion;
import style.RoundedPanel;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase `VistaPrincipal` que representa el panel principal de la interfaz
 * gráfica destinada a empleados o usuarios normales. Esta clase establece la
 * configuración inicial del fondo y de sus componentes. Además, proporciona una
 * interfaz para visualizar parámetros clave como la cantidad de entradas,
 * salidas, categorías y proveedores.
 *
 * La clase incluye métodos para inicializar y actualizar etiquetas con las
 * estadísticas relevantes, ofreciendo una vista resumida. .
 *
 * @autor Ralfph
 */
public class VistaPrincipal extends javax.swing.JPanel {

    /**
     * Constructor de la clase `VistaPrincipal`. Inicializa el panel con un
     * color de fondo específico y carga los componentes. * También llama a los
     * métodos necesarios para la inicialización de las cantidades de
     * categorías, entradas, salidas y proveedores.
     */
    public VistaPrincipal() {
        this.setBackground(Color.decode("#000511"));
        initComponents();
        actualizarLabelCategorias(lbl_categorias);
        actualizarLabelEntradas(lbl_entradas);
        actualizarLabelSalidas(lbl_salidas);
        actualizarLabelProveedores(lbl_proveedores);

    }

    /**
     * Obtiene la cantidad total de categorías.
     *
     * @return la cantidad total de categorías
     */
    private int obtenerCantidadCategorias() {
        int cantidad = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion conexion = new Conexion();
            connection = conexion.getConnection();
            String sql = "SELECT COUNT(*) AS total_categorias FROM categorias";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                cantidad = rs.getInt("total_categorias");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return cantidad;
    }

    /**
     * Obtiene el total de entradas registradas en la base de datos. Este método
     * realiza una consulta SQL para sumar la cantidad total de entradas.
     *
     * @return el total de entradas
     */
    private int obtenerTotalEntradas() {
        int totalEntradas = 0;
        String sql = "SELECT SUM(cantidad) AS total_entradas FROM entradas";

        try (Connection connection = new Conexion().getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                totalEntradas = rs.getInt("total_entradas");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalEntradas;
    }

    /**
     * Obtiene el total de salidas registradas en la base de datos. Este método
     * actualmente devuelve un valor fijo de 50, pero se puede actualizar para
     * realizar una consulta SQL que sume la cantidad total de salidas.
     *
     *
     * @return el total de salidas
     */
    private int obtenerTotalSalidas() {
        //int totalSalidas = 10;
        /*
    String sql = "SELECT SUM(cantidad) AS total_salidas FROM salidas"; // Asumiendo tabla "salidas"

    try (Connection connection = new Conexion().getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            totalSalidas = rs.getInt("total_salidas");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
         */
        return 50;
    }

    /**
     * * Obtiene el total de proveedores registrados en la base de datos.
     *
     * Actualmente, este método devuelve un valor fijo de 15.
     *
     * @return el total de proveedores
     */
    private int obtenerTotalProveedores() {
        return 15;
    }

    /**
     * Actualiza el JLabel de categorías con la cantidad total de categorías
     * obtenidas de la base de datos.
     *
     * @param lblCategoria el JLabel que muestra la cantidad de categorías
     */
    private void actualizarLabelCategorias(JLabel lblCategoria) {
        int cantidadCategorias = obtenerCantidadCategorias();
        lblCategoria.setText("" + cantidadCategorias);
    }

    /**
     * Actualiza el JLabel de entradas con el total de entradas obtenidas de la
     * base de datos.
     *
     * @param lblEntradas el JLabel que muestra el total de entradas
     */
    private void actualizarLabelEntradas(JLabel lblEntradas) {
        int totalEntradas = obtenerTotalEntradas();
        lblEntradas.setText("" + totalEntradas);
    }

    /**
     * Actualiza el JLabel de salidas con el total de salidas obtenidas de la
     * base de datos.
     *
     * @param lblSalidas el JLabel que muestra el total de salidas
     */
    private void actualizarLabelSalidas(JLabel lblSalidas) {
        int totalSalidas = obtenerTotalSalidas();
        lblSalidas.setText("" + totalSalidas);
    }

    /**
     * Actualiza el JLabel de proveedores con la cantidad total de proveedores.
     *
     * @param lblProveedores el JLabel que muestra el total de proveedores
     */
    private void actualizarLabelProveedores(JLabel lblProveedores) {
        int totalProveedores = obtenerTotalProveedores();
        lblProveedores.setText("" + totalProveedores);
    }

    /**
     * Obtiene el total de entradas en términos monetarios (soles) registradas
     * en la base de datos. Este método realiza una consulta SQL para sumar el
     * total de las entradas en soles.
     *
     * @return el total de entradas en soles
     */
    private double obtenerTotalEntradasSoles() {
        double totalEntradas = 0;
        String sql = "SELECT SUM(total) AS total_entradas FROM entradas";

        try (Connection connection = new Conexion().getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalEntradas = rs.getDouble("total_entradas");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalEntradas;
    }

    /**
     * Obtiene el total de salidas en términos monetarios (soles). Actualmente,
     * este método devuelve un valor fijo de 1500.00, pero se puede actualizar
     * para realizar una consulta SQL que sume el total de las salidas en soles.
     *
     * @return el total de salidas en soles
     */
    private double obtenerTotalSalidasSoles() {
        return 1500.00;
    }

    /**
     * Actualiza el JLabel de entradas con el total de entradas en soles
     * obtenidas de la base de datos.
     *
     * @param lblEntradas el JLabel que muestra el total de entradas en soles
     */
    private void actualizarEntradasSaldo(JLabel lblEntradas) {
        double totalEntradas = obtenerTotalEntradasSoles();
        lblEntradas.setText("" + totalEntradas);
    }

    /**
     * Actualiza el JLabel de salidas con el total de salidas en soles obtenidas
     * de la base de datos. @param lblSalidas el JLabel que muestra el total de
     * salidas en soles
     */
    private void actualizarSalidasSaldo(JLabel lblSalidas) {
        double totalSalidas = obtenerTotalSalidasSoles();
        lblSalidas.setText("" + totalSalidas);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 =  new RoundedPanel(30, new Color(5,63,255));
        jLabel1 = new javax.swing.JLabel();
        lbl_categorias = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new RoundedPanel(30, new Color(38,43,56));
        jLabel4 = new javax.swing.JLabel();
        lbl_entradas = new javax.swing.JLabel();
        jPanel5 = new RoundedPanel(30, new Color(38,43,56));
        jLabel3 = new javax.swing.JLabel();
        lbl_proveedores = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel(30, Color.WHITE);
        jLabel2 = new javax.swing.JLabel();
        lbl_salidas = new javax.swing.JLabel();
        jPanel7 = new RoundedPanel(30, new Color(19,22,27));
        jPanel10 = new RoundedPanel(30, new Color(38,43,56));
        jLabel6 = new javax.swing.JLabel();
        lbl_cal_salida = new javax.swing.JLabel();
        jPanel8 = new RoundedPanel(30, new Color(38,43,56))
        ;
        jSpinner1 = new javax.swing.JSpinner();
        jPanel9 = new RoundedPanel(30, new Color(38,43,56));
        jSpinner2 = new javax.swing.JSpinner();
        jPanel11 = new RoundedPanel(30, new Color(38,43,56));
        jLabel5 = new javax.swing.JLabel();
        lbl_calc_entrada = new javax.swing.JLabel();
        lbl_calcular = new javax.swing.JLabel();
        jPanel12 = new RoundedPanel(30, new Color(19,22,27));
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_cant_baja_stock = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btn_actualizarCant = new javax.swing.JButton();
        jPanel16 = new RoundedPanel(30, new Color(19,22,27));
        jPanel14 = new RoundedPanel(30, Color.WHITE);
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_producto = new javax.swing.JTable();
        jPanel15 = new RoundedPanel(30, Color.WHITE);
        fld_busqueda_producto = new javax.swing.JTextField();
        lbl_busqueda_producto_cant = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel17 = new RoundedPanel(30, new Color(19,22,27));
        jPanel13 = new RoundedPanel(30, Color.WHITE);
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_categoria = new javax.swing.JTable();
        lbl_search_categoria = new javax.swing.JLabel();
        jPanel18 = new RoundedPanel(30, Color.WHITE);
        lbl_busquedaCategoria = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(839, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Categorias");

        lbl_categorias.setBackground(new java.awt.Color(255, 255, 255));
        lbl_categorias.setFont(new java.awt.Font("Gill Sans MT", 0, 24)); // NOI18N
        lbl_categorias.setForeground(new java.awt.Color(255, 255, 255));
        lbl_categorias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Gill Sans MT", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_categorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(86, 86, 86)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(47, 47, 47)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_categorias, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(48, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 210, 110));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Entradas");

        lbl_entradas.setBackground(new java.awt.Color(255, 255, 255));
        lbl_entradas.setFont(new java.awt.Font("Gill Sans MT", 0, 24)); // NOI18N
        lbl_entradas.setForeground(new java.awt.Color(255, 255, 255));
        lbl_entradas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(lbl_entradas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_entradas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 210, 110));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Proveedores");

        lbl_proveedores.setBackground(new java.awt.Color(255, 255, 255));
        lbl_proveedores.setFont(new java.awt.Font("Gill Sans MT", 0, 24)); // NOI18N
        lbl_proveedores.setForeground(new java.awt.Color(255, 255, 255));
        lbl_proveedores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbl_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 210, 110));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel2.setText("Salidas");

        lbl_salidas.setBackground(new java.awt.Color(255, 255, 255));
        lbl_salidas.setFont(new java.awt.Font("Gill Sans MT", 0, 24)); // NOI18N
        lbl_salidas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel2)
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_salidas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_salidas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 210, 110));

        jLabel6.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Salidas S/.");

        lbl_cal_salida.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        lbl_cal_salida.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_cal_salida, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_cal_salida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSpinner1.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerListModel(new String[] {"2024", "2025", "2026", "2027"}));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSpinner2.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        jSpinner2.setModel(new javax.swing.SpinnerListModel(new String[] {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"}));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jLabel5.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Entradas S/.");

        lbl_calc_entrada.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        lbl_calc_entrada.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_calc_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_calc_entrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addContainerGap())
        );

        lbl_calcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/style/icons_menu_principal/icon-calcular.png"))); // NOI18N
        lbl_calcular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_calcularMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(160, 160, 160))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(lbl_calcular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_calcular)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 370, 230));

        tbl_cant_baja_stock.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        tbl_cant_baja_stock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Producto", "Stock"
            }
        ));
        jScrollPane1.setViewportView(tbl_cant_baja_stock);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Listado de Productos con baja cantidad de stock :");

        btn_actualizarCant.setBackground(new java.awt.Color(0, 5, 17));
        btn_actualizarCant.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        btn_actualizarCant.setForeground(new java.awt.Color(255, 255, 255));
        btn_actualizarCant.setText("Actualizar");
        btn_actualizarCant.setBorder(null);
        btn_actualizarCant.setOpaque(true);
        btn_actualizarCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarCantActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(btn_actualizarCant, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(222, 222, 222))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_actualizarCant, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 650, 230));

        tbl_producto.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        tbl_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Producto", "Cantidad"
            }
        ));
        jScrollPane3.setViewportView(tbl_producto);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
        );

        fld_busqueda_producto.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        fld_busqueda_producto.setBorder(null);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fld_busqueda_producto, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fld_busqueda_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbl_busqueda_producto_cant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/style/icons_menu_principal/icon-search.png"))); // NOI18N
        lbl_busqueda_producto_cant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_busqueda_producto_cantMouseClicked(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Buscar producto :");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(lbl_busqueda_producto_cant)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel8)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_busqueda_producto_cant)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 510, 300));

        tbl_categoria.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        tbl_categoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Categoria", "Cantidad"
            }
        ));
        jScrollPane2.setViewportView(tbl_categoria);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbl_search_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/style/icons_menu_principal/icon-search.png"))); // NOI18N
        lbl_search_categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_search_categoriaMouseClicked(evt);
            }
        });

        lbl_busquedaCategoria.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lbl_busquedaCategoria.setBorder(null);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_busquedaCategoria)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_busquedaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Buscar categoria :");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(188, 188, 188))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_search_categoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_search_categoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, 510, 300));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento que se ejecuta cuando se hace clic en el JLabel `lbl_calcular`.
     * Este método actualiza los saldos de entradas y salidas llamando a los
     * métodos {@link #actualizarEntradasSaldo(JLabel)} y
     * {@link #actualizarSalidasSaldo(JLabel)}.
     *
     * @param evt el evento de clic del ratón
     */
    private void lbl_calcularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_calcularMouseClicked
        actualizarEntradasSaldo(lbl_calc_entrada);
        actualizarSalidasSaldo(lbl_cal_salida);
    }//GEN-LAST:event_lbl_calcularMouseClicked
    /**
     * Evento que se ejecuta cuando se hace clic en el botón
     * `btn_actualizarCant`. Este método llama al método
     * {@link #mostrarProductosBajoStock(JTable)} para mostrar los productos con
     * bajo stock en la tabla `tbl_cant_baja_stock`.
     *
     * @param evt el evento de acción del botón
     */
    private void btn_actualizarCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarCantActionPerformed
        mostrarProductosBajoStock(tbl_cant_baja_stock);
    }//GEN-LAST:event_btn_actualizarCantActionPerformed

    /**
     * Evento que se ejecuta cuando se hace clic en el JLabel
     * `lbl_busqueda_producto_cant`. Este método llama al método
     * {@link #buscarProductoMostrarStock(String, JTable)} para buscar y mostrar
     * el stock del producto ingresado en `fld_busqueda_producto`.
     *
     * @param evt el evento de clic del ratón
     */
    private void lbl_busqueda_producto_cantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_busqueda_producto_cantMouseClicked
        String nombreProducto = fld_busqueda_producto.getText();
        buscarProductoMostrarStock(nombreProducto, tbl_producto);    }//GEN-LAST:event_lbl_busqueda_producto_cantMouseClicked

    /**
     * Evento que se ejecuta cuando se hace clic en el JLabel
     * `lbl_search_categoria`. Este método llama al método
     * {@link #buscarCategoriaMostrarStock(String, JTable)} para buscar y
     * mostrar el stock de los productos de la categoría ingresada en
     * `lbl_busquedaCategoria`.
     *
     * @param evt el evento de clic del ratón
     */
    private void lbl_search_categoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_search_categoriaMouseClicked
        String nombreCategoria = lbl_busquedaCategoria.getText();
        buscarCategoriaMostrarStock(nombreCategoria, tbl_categoria);
    }//GEN-LAST:event_lbl_search_categoriaMouseClicked

    /**
     * Muestra los productos con bajo stock en la tabla proporcionada. Este
     * método realiza una consulta SQL para obtener los productos cuyo stock es
     * menor a 60 y actualiza la tabla proporcionada con los resultados.
     *
     * @param tablaBajoStock la tabla donde se mostrarán los productos con bajo
     * stock
     */
    public void mostrarProductosBajoStock(JTable tablaBajoStock) {
        String sql = "SELECT nombre, stock FROM productos WHERE stock < 60";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion conexion = new Conexion();
            connection = conexion.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tablaBajoStock.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nombre"),
                    rs.getInt("stock")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Busca un producto por su nombre y muestra su stock en la tabla
     * proporcionada. * Este método realiza una consulta SQL para obtener el
     * stock del producto cuyo nombre coincide con el proporcionado y actualiza
     * la tabla con los resultados.
     *
     * @param nombreProducto el nombre del producto a buscar @param tblProducto
     * la tabla donde se mostrará el stock del producto
     */
    public void buscarProductoMostrarStock(String nombreProducto, JTable tblProducto) {
        String sql = "SELECT nombre, stock FROM productos WHERE nombre LIKE ?";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion conexion = new Conexion();
            connection = conexion.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + nombreProducto + "%");
            rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tblProducto.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nombre"),
                    rs.getInt("stock")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Busca productos por categoría y muestra su stock en la tabla
     * proporcionada. Este método realiza una consulta SQL para obtener el stock
     * de los productos que pertenecen a la categoría proporcionada y actualiza
     * la tabla con los resultados.@param nombreCategoria el nombre de la
     * categoría a buscar
     *
     * @param tblCategoria la tabla donde se mostrará el stock de los productos
     */
    public void buscarCategoriaMostrarStock(String nombreCategoria, JTable tblCategoria) {
        String sql = "SELECT p.nombre, p.stock FROM productos p "
                + "JOIN categorias c ON p.idCategoria = c.id_categoria "
                + "WHERE c.nombre LIKE ?";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion conexion = new Conexion();
            connection = conexion.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + nombreCategoria + "%");
            rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tblCategoria.getModel();
            model.setRowCount(0); // Limpiar la tabla

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nombre"),
                    rs.getInt("stock")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizarCant;
    private javax.swing.JTextField fld_busqueda_producto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextField lbl_busquedaCategoria;
    private javax.swing.JLabel lbl_busqueda_producto_cant;
    private javax.swing.JLabel lbl_cal_salida;
    private javax.swing.JLabel lbl_calc_entrada;
    private javax.swing.JLabel lbl_calcular;
    private javax.swing.JLabel lbl_categorias;
    private javax.swing.JLabel lbl_entradas;
    private javax.swing.JLabel lbl_proveedores;
    private javax.swing.JLabel lbl_salidas;
    private javax.swing.JLabel lbl_search_categoria;
    private javax.swing.JTable tbl_cant_baja_stock;
    private javax.swing.JTable tbl_categoria;
    private javax.swing.JTable tbl_producto;
    // End of variables declaration//GEN-END:variables
}
