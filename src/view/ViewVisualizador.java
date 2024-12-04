package view;

import config.Conexion;
import graphics.chart.ModelChart;
import graphics.modelGraphics.ModelData;
import graphics.progress.Progress;
import style.RoundedPanel;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * Clase vistaPrincipal que representa el panel principal de la interfaz
 * gráfica. Establece la configuración inicial del fondo y sus componentes.
 *
 * @author Ralfph
 */
public class ViewVisualizador extends javax.swing.JPanel {

    private Conexion conexion;
    private Connection connection;

    /**
     * Constructor de la clase `viewVisualizador`. Este constructor inicializa
     * el panel con un color de fondo específico y carga los componentes
     * necesarios. También llama a los métodos esenciales para inicializar los
     * conteos de categorías, entradas, salidas y proveedores. Además, se
     * definen los valores necesarios para configurar el gráfico.
     */
    public ViewVisualizador() {
        this.setBackground(Color.decode("#000511"));
        initComponents();
        actualizarBarraDeProgresoCategorias(progress_categoria);
        actualizarBarraDeProgresoEntradas(progress_entrada);
        actualizarBarraDeProgresoSalidas(progress_salidas);
        actualizarBarraDeProgresoProveedores(progress_proveedores);
        curveLine.setTitle("Grafico Stock por Categoria");
        curveLine.addLegend("Cantidad", Color.decode("#7b4397"), Color.decode("#dc2430"));
        curveLine.addLegend("Costo", Color.decode("#e65c00"), Color.decode("#F9D423"));
        curveLine.addLegend("Beneficio", Color.decode("#0099F7"), Color.decode("#F11712"));
        setData();
    }

    /**
     * Obtiene la cantidad total de categorías registradas en la base de datos.
     * Este método realiza una consulta SQL para contar el número total de
     * categorías en la tabla "categorias" y devuelve el resultado.
     *
     * @return la cantidad total de categorías registradas en la base de datos.
     * Si ocurre algún error en la consulta, retorna 0.
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
     * Obtiene el total acumulado de entradas registradas en la base de datos.
     * Este método ejecuta una consulta SQL para calcular la suma de las
     * cantidades de todas las entradas en la tabla "entradas".
     *
     * @return el total acumulado de entradas. Si ocurre un error en la
     * consulta, retorna 0.
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
     * Obtiene el total acumulado de salidas registradas en el sistema.
     * Actualmente, este método devuelve un valor fijo como ejemplo. Sin
     * embargo, está preparado para integrar una consulta SQL que calcule el
     * total de salidas en la tabla "salidas".
     *
     * @return el total acumulado de salidas. Por ahora, devuelve 50 como valor
     * fijo.
     */
    private int obtenerTotalSalidas() {
        //int totalSalidas = 10;
        /*
        String sql = "SELECT SUM(cantidad) AS total_salidas FROM salidas"; 

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
     * Obtiene el total de proveedores registrados en el sistema. Este método
     * actualmente retorna un valor fijo como ejemplo, pero puede ser modificado
     * para realizar una consulta SQL que recupere el total real desde la base
     * de datos.
     *
     * @return el total de proveedores registrados. Por ahora, devuelve 15 como
     * valor fijo.
     */
    private int obtenerTotalProveedores() {
        return 15;
    }

    /**
     * Actualiza la barra de progreso que representa la cantidad de categorías
     * registradas. Este método obtiene la cantidad de categorías mediante el
     * método {@link  #obtenerCantidadCategorias()} y ajusta el valor de la barra
     * de progreso.
     *
     * @param progressBar la barra de progreso que se actualizará.
     */
    private void actualizarBarraDeProgresoCategorias(Progress progressBar) {
        int cantidadCategorias = obtenerCantidadCategorias();
        progressBar.setValue(cantidadCategorias);
        progressBar.start();
    }

    /**
     * Actualiza la barra de progreso que representa el porcentaje de entradas
     * registradas. Este método calcula el progreso en base a un valor máximo de
     * entradas permitido y ajusta la barra en consecuencia.
     *
     * @param progressBar la barra de progreso que se actualizará.
     */
    private void actualizarBarraDeProgresoEntradas(Progress progressBar) {
        int totalEntradas = obtenerTotalEntradas();
        int maxEntradas = 1000;
        int progreso = (int) ((totalEntradas / (double) maxEntradas) * 100);
        progressBar.setValue(progreso);
        progressBar.start();
    }

    /**
     * Actualiza la barra de progreso que representa el porcentaje de salidas
     * registradas. Este método calcula el progreso en base a un valor máximo de
     * salidas permitido y ajusta la barra en consecuencia.
     *
     * @param progressBar la barra de progreso que se actualizará.
     */
    private void actualizarBarraDeProgresoSalidas(Progress progressBar) {
        int totalSalidas = obtenerTotalSalidas();
        int maxSalidas = 100;
        int progreso = (int) ((totalSalidas / (double) maxSalidas) * 100);
        progressBar.setValue(progreso);
        progressBar.start();
    }

    /**
     * Actualiza la barra de progreso que representa el número de proveedores
     * registrados. Este método obtiene el total de proveedores usando el método
     * {@link #obtenerTotalProveedores()} y ajusta el valor de la barra de
     * progreso.
     *
     * @param progressBar la barra de progreso que se actualizará.
     */
    private void actualizarBarraDeProgresoProveedores(Progress progressBar) {
        int totalProveedores = obtenerTotalProveedores();
        progressBar.setValue(totalProveedores);
        progressBar.start();
    }

    /**
     * Configura y establece los datos necesarios para el gráfico de línea
     * curva. Este método realiza una consulta SQL para obtener datos
     * relacionados con categorías, cantidades, costos totales y beneficios. Los
     * datos obtenidos se utilizan para el gráfico visual.
     */
    private void setData() {
        List<ModelData> list = new ArrayList<>();
        conexion = new Conexion();
        connection = conexion.getConnection();

        String sql = "SELECT c.nombre AS categoria, "
                + "SUM(p.stock) AS total_stock, "
                + "SUM(e.cantidad * e.precioUnitario) AS costo_total, "
                + "SUM(e.cantidad * e.precioUnitario) * 0.3 AS beneficio_total "
                + "FROM categorias c "
                + "JOIN productos p ON c.id_categoria = p.idCategoria "
                + "JOIN entradas e ON p.id = e.idProductos "
                + "GROUP BY c.nombre";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            // Almacenar los datos en la lista
            while (rs.next()) {
                String categoria = rs.getString("categoria");
                int cantidadStock = rs.getInt("total_stock");
                double costoTotal = rs.getDouble("costo_total");
                double beneficioTotal = rs.getDouble("beneficio_total");

                list.add(new ModelData(categoria, cantidadStock, costoTotal, beneficioTotal));
            }

            for (ModelData md : list) {
                curveLine.addData(new ModelChart(md.getParametroIdentificador(), new double[]{
                    md.getParametroCantidad(), // Cantidad
                    md.getCosto(), // Costo
                    md.getBeneficio() // Beneficio
                }));
            }

            curveLine.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el monto total acumulado de las entradas en soles. Este método
     * ejecuta una consulta SQL que calcula la suma del campo "total" en la
     * tabla "entradas".
     *
     * @return el monto total de entradas en soles. Si ocurre un error en la
     * consulta, retorna 0.
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
     * Obtiene el monto total acumulado de las salidas en soles. Este método
     * actualmente devuelve un valor fijo como ejemplo, pero puede ser extendido
     * para realizar una consulta SQL en la tabla "salidas".
     *
     * @return el monto total de salidas en soles. Por ahora, devuelve 1500.00
     * como valor fijo.
     */
    private double obtenerTotalSalidasSoles() {
        return 1500.00;
    }

    /**
     * Actualiza el valor mostrado en una etiqueta para representar el saldo
     * total de entradas. Este método utiliza
     * {@link #obtenerTotalEntradasSoles()} para recuperar el total de entradas
     * en soles y lo muestra en la etiqueta proporcionada.
     *
     * @param lblEntradas la etiqueta que mostrará el saldo total de entradas.
     */
    private void actualizarEntradasSaldo(JLabel lblEntradas) {
        double totalEntradas = obtenerTotalEntradasSoles();
        lblEntradas.setText("" + totalEntradas);
    }

    /**
     * Actualiza el valor mostrado en una etiqueta para representar el saldo
     * total de salidas. Este método utiliza {@link #obtenerTotalSalidasSoles()}
     * para recuperar el total de salidas en soles y lo muestra en la etiqueta
     * proporcionada.
     *
     * @param lblSalidas la etiqueta que mostrará el saldo total de salidas.
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

        progressCircleUI1 = new graphics.progress.ProgressCircleUI();
        jPanel3 =  new RoundedPanel(30, new Color(240, 191, 38));
        jLabel1 = new javax.swing.JLabel();
        progress_categoria = new graphics.progress.Progress();
        jPanel4 = new RoundedPanel(30, new Color(49, 215, 1));
        jLabel4 = new javax.swing.JLabel();
        progress_entrada = new graphics.progress.Progress();
        jPanel5 = new RoundedPanel(30, new Color(31, 169, 221));
        jLabel3 = new javax.swing.JLabel();
        progress_proveedores = new graphics.progress.Progress();
        jPanel6 = new RoundedPanel(30, new Color(200, 25, 88));
        jLabel2 = new javax.swing.JLabel();
        progress_salidas = new graphics.progress.Progress();
        jPanel7 = new RoundedPanel(30, Color.BLACK);
        jPanel8 = new RoundedPanel(30, new Color(19, 22, 27));
        jSpinner1 = new javax.swing.JSpinner();
        jPanel9 = new RoundedPanel(30, new Color(19, 22, 27));
        jSpinner2 = new javax.swing.JSpinner();
        jPanel10 = new RoundedPanel(30, new Color(19, 22, 27));
        lbl_salidassole = new javax.swing.JLabel();
        lbl_calc_salidas = new javax.swing.JLabel();
        jPanel11 = new RoundedPanel(30, new Color(19, 22, 27));
        jLabel5 = new javax.swing.JLabel();
        lbl_calc_entrada = new javax.swing.JLabel();
        lbl_calcular = new javax.swing.JLabel();
        jPanel15 = new RoundedPanel(30, Color.WHITE);
        fld_busqueda_general = new javax.swing.JTextField();
        lbl_search = new javax.swing.JLabel();
        jPanel12 = new RoundedPanel(30, Color.BLACK)
        ;
        chartGraphic = new graphics.panel.PanelShadow();
        curveLine = new graphics.chart.CurveLineChart();

        setPreferredSize(new java.awt.Dimension(839, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel1.setText("Categorias");

        progress_categoria.setBackground(new java.awt.Color(39, 44, 54));
        progress_categoria.setBorder(null);
        progress_categoria.setForeground(new java.awt.Color(39, 44, 54));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(progress_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 210, 110));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel4.setText("Entradas");

        progress_entrada.setBackground(new java.awt.Color(39, 44, 54));
        progress_entrada.setForeground(new java.awt.Color(39, 44, 54));

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
                        .addGap(65, 65, 65)
                        .addComponent(progress_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progress_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 210, 110));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel3.setText("Proveedores");

        progress_proveedores.setBackground(new java.awt.Color(39, 44, 54));
        progress_proveedores.setForeground(new java.awt.Color(39, 44, 54));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(progress_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(58, 58, 58))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progress_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 110, 210, 110));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel2.setText("Salidas");

        progress_salidas.setBackground(new java.awt.Color(39, 44, 54));
        progress_salidas.setForeground(new java.awt.Color(39, 44, 54));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel2))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(progress_salidas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress_salidas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 210, 110));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSpinner1.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerListModel(new String[] {"2024", "2025", "2026", "2027"}));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbl_salidassole.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        lbl_salidassole.setForeground(new java.awt.Color(255, 255, 255));
        lbl_salidassole.setText("Salidas S/.");

        lbl_calc_salidas.setBackground(new java.awt.Color(255, 255, 255));
        lbl_calc_salidas.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        lbl_calc_salidas.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_salidassole)
                .addGap(18, 18, 18)
                .addComponent(lbl_calc_salidas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_calc_salidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_salidassole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Entradas S/.");

        lbl_calc_entrada.setBackground(new java.awt.Color(255, 255, 255));
        lbl_calc_entrada.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        lbl_calc_entrada.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lbl_calc_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_calc_entrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(57, 57, 57)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(lbl_calcular)
                .addGap(24, 24, 24))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_calcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 1040, 80));

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        fld_busqueda_general.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        fld_busqueda_general.setBorder(null);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(fld_busqueda_general, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(fld_busqueda_general, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 290, 50));

        lbl_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/style/icons_menu_principal/icon-search.png"))); // NOI18N
        add(lbl_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, -1, -1));

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        chartGraphic.setBackground(new java.awt.Color(0, 0, 0));
        chartGraphic.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartGraphic.setColorGradient(new java.awt.Color(0, 0, 0));

        curveLine.setBackground(new java.awt.Color(255, 255, 255));
        curveLine.setForeground(new java.awt.Color(255, 255, 255));
        curveLine.setTitleFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        javax.swing.GroupLayout chartGraphicLayout = new javax.swing.GroupLayout(chartGraphic);
        chartGraphic.setLayout(chartGraphicLayout);
        chartGraphicLayout.setHorizontalGroup(
            chartGraphicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chartGraphicLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(curveLine, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
                .addContainerGap())
        );
        chartGraphicLayout.setVerticalGroup(
            chartGraphicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chartGraphicLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(curveLine, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chartGraphic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chartGraphic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 1040, 330));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento que se ejecuta cuando se hace clic en el JLabel `lbl_calcular`.
     * Este método actualiza los saldos de entradas y salidas llamando a los
     * métodos * {@link #actualizarEntradasSaldo(JLabel)} y
     * {@link #actualizarSalidasSaldo(JLabel)}.
     *
     * @param evt el evento de clic del ratón
     */
    private void lbl_calcularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_calcularMouseClicked
        actualizarEntradasSaldo(lbl_calc_entrada);
        actualizarSalidasSaldo(lbl_calc_salidas);    }//GEN-LAST:event_lbl_calcularMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private graphics.panel.PanelShadow chartGraphic;
    private graphics.chart.CurveLineChart curveLine;
    private javax.swing.JTextField fld_busqueda_general;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JLabel lbl_calc_entrada;
    private javax.swing.JLabel lbl_calc_salidas;
    private javax.swing.JLabel lbl_calcular;
    private javax.swing.JLabel lbl_salidassole;
    private javax.swing.JLabel lbl_search;
    private graphics.progress.ProgressCircleUI progressCircleUI1;
    private graphics.progress.Progress progress_categoria;
    private graphics.progress.Progress progress_entrada;
    private graphics.progress.Progress progress_proveedores;
    private graphics.progress.Progress progress_salidas;
    // End of variables declaration//GEN-END:variables
}
