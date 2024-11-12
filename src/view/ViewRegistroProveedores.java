package view;

import style.RoundedPanel;
import java.awt.Color;

/**
 * La clase ViewRegistroProveedores representa el panel de registro de
 * proveedores. Esta clase extiende JPanel y configura el fondo del panel.
 *
 * @author Ralfph
 */
public class ViewRegistroProveedores extends javax.swing.JPanel {

    /**
     * * Constructor de la clase ViewRegistroProveedores. * Este método es
     * llamado desde el constructor para inicializar el formulario. * Configura
     * el color de fondo del panel y llama al método initComponents.
     */
    public ViewRegistroProveedores() {
        this.setBackground(Color.decode("#000511"));
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_cont_icon_user1 =  new RoundedPanel(60, new Color(19, 22, 27));
        lbl_registroUsuarios = new javax.swing.JLabel();
        panel_cont_icon_user9 =  new RoundedPanel(60, new Color(19, 22, 27));
        panel_cont_icon_user3 =  new RoundedPanel(60, Color.WHITE);
        lbl_nombre_proveedor = new javax.swing.JLabel();
        txtField_nombre_proveedor = new javax.swing.JTextField();
        lbl_apellido_proveedor = new javax.swing.JLabel();
        txtField_apellido = new javax.swing.JTextField();
        lbl_documento = new javax.swing.JLabel();
        txtField_documento = new javax.swing.JTextField();
        lbl_RazonSocial = new javax.swing.JLabel();
        txtField_razon_social = new javax.swing.JTextField();
        lbl_telefono = new javax.swing.JLabel();
        txtField_telefono = new javax.swing.JTextField();
        lbl_correo = new javax.swing.JLabel();
        txtField_correo = new javax.swing.JTextField();
        lbl_id_proveedor = new javax.swing.JLabel();
        txt_field_id_proveedor = new javax.swing.JTextField();
        lbl_direccion = new javax.swing.JLabel();
        txtField_direccion = new javax.swing.JTextField();
        panel_cont_icon_user4 =  new RoundedPanel(60, new Color(19, 22, 27));
        panel_cont_icon_user5 =  new RoundedPanel(60, Color.WHITE);
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        panel_cont_icon_user8 =  new RoundedPanel(60, new Color(0,5,17));
        jLabel12 = new javax.swing.JLabel();
        panel_cont_icon_user7 =  new RoundedPanel(60,  new Color(0,5,17));
        lbl_btn_saved = new javax.swing.JLabel();
        panel_cont_icon_user6 =  new RoundedPanel(60,  new Color(0,5,17));
        lbl_eliminar = new javax.swing.JLabel();
        lbl_buscar = new javax.swing.JLabel();
        lbl_editar1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(839, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_cont_icon_user1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_cont_icon_user1.setForeground(new java.awt.Color(19, 22, 27));

        lbl_registroUsuarios.setFont(new java.awt.Font("Gill Sans MT", 0, 21)); // NOI18N
        lbl_registroUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        lbl_registroUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_registroUsuarios.setText("Registro de Proveedores");

        javax.swing.GroupLayout panel_cont_icon_user1Layout = new javax.swing.GroupLayout(panel_cont_icon_user1);
        panel_cont_icon_user1.setLayout(panel_cont_icon_user1Layout);
        panel_cont_icon_user1Layout.setHorizontalGroup(
            panel_cont_icon_user1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_registroUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_cont_icon_user1Layout.setVerticalGroup(
            panel_cont_icon_user1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_registroUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(panel_cont_icon_user1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1100, 60));

        panel_cont_icon_user9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_cont_icon_user9.setForeground(new java.awt.Color(19, 22, 27));

        panel_cont_icon_user3.setForeground(new java.awt.Color(19, 22, 27));

        lbl_nombre_proveedor.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lbl_nombre_proveedor.setText("Nombre");

        lbl_apellido_proveedor.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lbl_apellido_proveedor.setText("Apellido");

        lbl_documento.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lbl_documento.setText("Documento");

        lbl_RazonSocial.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lbl_RazonSocial.setText("Razon Social");

        lbl_telefono.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lbl_telefono.setText("Telefono");

        lbl_correo.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lbl_correo.setText("Correo");

        lbl_id_proveedor.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lbl_id_proveedor.setText("ID");

        txt_field_id_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_field_id_proveedorActionPerformed(evt);
            }
        });

        lbl_direccion.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lbl_direccion.setText("Direccion");

        javax.swing.GroupLayout panel_cont_icon_user3Layout = new javax.swing.GroupLayout(panel_cont_icon_user3);
        panel_cont_icon_user3.setLayout(panel_cont_icon_user3Layout);
        panel_cont_icon_user3Layout.setHorizontalGroup(
            panel_cont_icon_user3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panel_cont_icon_user3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtField_direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(lbl_direccion)
                    .addComponent(lbl_id_proveedor)
                    .addComponent(txtField_razon_social, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(txtField_telefono)
                    .addComponent(txtField_correo)
                    .addComponent(txtField_nombre_proveedor)
                    .addComponent(lbl_apellido_proveedor)
                    .addComponent(lbl_nombre_proveedor)
                    .addComponent(txtField_apellido)
                    .addComponent(lbl_documento)
                    .addComponent(txtField_documento)
                    .addComponent(lbl_RazonSocial)
                    .addComponent(lbl_telefono)
                    .addComponent(lbl_correo)
                    .addComponent(txt_field_id_proveedor))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panel_cont_icon_user3Layout.setVerticalGroup(
            panel_cont_icon_user3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_id_proveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_field_id_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_nombre_proveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtField_nombre_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_apellido_proveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtField_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_documento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtField_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_RazonSocial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtField_razon_social, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_direccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtField_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_telefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtField_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_correo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtField_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_cont_icon_user4.setForeground(new java.awt.Color(19, 22, 27));

        panel_cont_icon_user5.setForeground(new java.awt.Color(19, 22, 27));

        tablaProveedores.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Stock", "ID cantidad", "Precio Venta"
            }
        ));
        tablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProveedores);

        javax.swing.GroupLayout panel_cont_icon_user5Layout = new javax.swing.GroupLayout(panel_cont_icon_user5);
        panel_cont_icon_user5.setLayout(panel_cont_icon_user5Layout);
        panel_cont_icon_user5Layout.setHorizontalGroup(
            panel_cont_icon_user5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_cont_icon_user5Layout.setVerticalGroup(
            panel_cont_icon_user5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_cont_icon_user4Layout = new javax.swing.GroupLayout(panel_cont_icon_user4);
        panel_cont_icon_user4.setLayout(panel_cont_icon_user4Layout);
        panel_cont_icon_user4Layout.setHorizontalGroup(
            panel_cont_icon_user4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_cont_icon_user4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_cont_icon_user5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_cont_icon_user4Layout.setVerticalGroup(
            panel_cont_icon_user4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_cont_icon_user5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel_cont_icon_user8.setForeground(new java.awt.Color(19, 22, 27));

        jLabel12.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Complete la información del Proveedor");

        javax.swing.GroupLayout panel_cont_icon_user8Layout = new javax.swing.GroupLayout(panel_cont_icon_user8);
        panel_cont_icon_user8.setLayout(panel_cont_icon_user8Layout);
        panel_cont_icon_user8Layout.setHorizontalGroup(
            panel_cont_icon_user8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        panel_cont_icon_user8Layout.setVerticalGroup(
            panel_cont_icon_user8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_cont_icon_user7.setForeground(new java.awt.Color(19, 22, 27));

        lbl_btn_saved.setIcon(new javax.swing.ImageIcon(getClass().getResource("/style/icons-registro/icon-guardar.png"))); // NOI18N
        lbl_btn_saved.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_btn_savedMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_cont_icon_user7Layout = new javax.swing.GroupLayout(panel_cont_icon_user7);
        panel_cont_icon_user7.setLayout(panel_cont_icon_user7Layout);
        panel_cont_icon_user7Layout.setHorizontalGroup(
            panel_cont_icon_user7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_cont_icon_user7Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(lbl_btn_saved)
                .addGap(61, 61, 61))
        );
        panel_cont_icon_user7Layout.setVerticalGroup(
            panel_cont_icon_user7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user7Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lbl_btn_saved)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        panel_cont_icon_user6.setForeground(new java.awt.Color(19, 22, 27));

        lbl_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/style/icons-registro/icon-eliminar.png"))); // NOI18N
        lbl_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_eliminarMouseClicked(evt);
            }
        });

        lbl_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/style/icons-registro/icon-buscar.png"))); // NOI18N
        lbl_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_buscarMouseClicked(evt);
            }
        });

        lbl_editar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/style/icons-registro/icon-editar.png"))); // NOI18N
        lbl_editar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_editar1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_cont_icon_user6Layout = new javax.swing.GroupLayout(panel_cont_icon_user6);
        panel_cont_icon_user6.setLayout(panel_cont_icon_user6Layout);
        panel_cont_icon_user6Layout.setHorizontalGroup(
            panel_cont_icon_user6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user6Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(lbl_editar1)
                .addGap(118, 118, 118)
                .addComponent(lbl_eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(lbl_buscar)
                .addGap(99, 99, 99))
        );
        panel_cont_icon_user6Layout.setVerticalGroup(
            panel_cont_icon_user6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panel_cont_icon_user6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_editar1)
                    .addComponent(lbl_eliminar)
                    .addComponent(lbl_buscar))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_cont_icon_user9Layout = new javax.swing.GroupLayout(panel_cont_icon_user9);
        panel_cont_icon_user9.setLayout(panel_cont_icon_user9Layout);
        panel_cont_icon_user9Layout.setHorizontalGroup(
            panel_cont_icon_user9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user9Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panel_cont_icon_user9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_cont_icon_user9Layout.createSequentialGroup()
                        .addComponent(panel_cont_icon_user7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel_cont_icon_user6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_cont_icon_user8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_cont_icon_user9Layout.createSequentialGroup()
                        .addComponent(panel_cont_icon_user3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel_cont_icon_user4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        panel_cont_icon_user9Layout.setVerticalGroup(
            panel_cont_icon_user9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cont_icon_user9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_cont_icon_user8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_cont_icon_user9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_cont_icon_user3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_cont_icon_user4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_cont_icon_user9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_cont_icon_user7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_cont_icon_user6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 50, Short.MAX_VALUE))
        );

        add(panel_cont_icon_user9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1100, 590));
    }// </editor-fold>//GEN-END:initComponents

    private void tablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedoresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaProveedoresMouseClicked


    private void lbl_btn_savedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_btn_savedMouseClicked

    }//GEN-LAST:event_lbl_btn_savedMouseClicked

    private void lbl_buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_buscarMouseClicked

    }//GEN-LAST:event_lbl_buscarMouseClicked

    private void lbl_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_eliminarMouseClicked

    }//GEN-LAST:event_lbl_eliminarMouseClicked

    private void lbl_editar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_editar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_editar1MouseClicked

    private void txt_field_id_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_field_id_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_field_id_proveedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_RazonSocial;
    private javax.swing.JLabel lbl_apellido_proveedor;
    private javax.swing.JLabel lbl_btn_saved;
    private javax.swing.JLabel lbl_buscar;
    private javax.swing.JLabel lbl_correo;
    private javax.swing.JLabel lbl_direccion;
    private javax.swing.JLabel lbl_documento;
    private javax.swing.JLabel lbl_editar1;
    private javax.swing.JLabel lbl_eliminar;
    private javax.swing.JLabel lbl_id_proveedor;
    private javax.swing.JLabel lbl_nombre_proveedor;
    private javax.swing.JLabel lbl_registroUsuarios;
    private javax.swing.JLabel lbl_telefono;
    private javax.swing.JPanel panel_cont_icon_user1;
    private javax.swing.JPanel panel_cont_icon_user3;
    private javax.swing.JPanel panel_cont_icon_user4;
    private javax.swing.JPanel panel_cont_icon_user5;
    private javax.swing.JPanel panel_cont_icon_user6;
    private javax.swing.JPanel panel_cont_icon_user7;
    private javax.swing.JPanel panel_cont_icon_user8;
    private javax.swing.JPanel panel_cont_icon_user9;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTextField txtField_apellido;
    private javax.swing.JTextField txtField_correo;
    private javax.swing.JTextField txtField_direccion;
    private javax.swing.JTextField txtField_documento;
    private javax.swing.JTextField txtField_nombre_proveedor;
    private javax.swing.JTextField txtField_razon_social;
    private javax.swing.JTextField txtField_telefono;
    private javax.swing.JTextField txt_field_id_proveedor;
    // End of variables declaration//GEN-END:variables
}
