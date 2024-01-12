
package com.mycompany.biblioteca.musical.igu;

//import com.mycompany.biblioteca.musical.logica.Controladora;


public class Menu extends javax.swing.JFrame {

    //Controladora control = new Controladora();
    
    public Menu() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAddDisco = new javax.swing.JButton();
        btnGeneros = new javax.swing.JButton();
        btnAddBanda = new javax.swing.JButton();
        btnVerDiscos1 = new javax.swing.JButton();
        btnGenero = new javax.swing.JButton();
        btnPais = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel1.setText("BIBLIOTECA MUSICAL");

        btnAddDisco.setText("AGREGAR DISCO");
        btnAddDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDiscoActionPerformed(evt);
            }
        });

        btnGeneros.setText("FILTRAR GENEROS");
        btnGeneros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerosActionPerformed(evt);
            }
        });

        btnAddBanda.setText("AGREGAR BANDA");
        btnAddBanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBandaActionPerformed(evt);
            }
        });

        btnVerDiscos1.setText("VER BANDAS");
        btnVerDiscos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDiscos1ActionPerformed(evt);
            }
        });

        btnGenero.setText("AGREGAR GENERO");
        btnGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneroActionPerformed(evt);
            }
        });

        btnPais.setText("AGREGAR PAIS");
        btnPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnPais, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnVerDiscos1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAddBanda, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddBanda, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerDiscos1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPais, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddBandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBandaActionPerformed
        AgregarBanda pantallaNueva = new AgregarBanda();
        pantallaNueva.setVisible(true);
        pantallaNueva.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnAddBandaActionPerformed

    private void btnAddDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDiscoActionPerformed
        AgregarDisco pantallaNueva = new AgregarDisco();
        pantallaNueva.setVisible(true);
        pantallaNueva.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnAddDiscoActionPerformed

    private void btnGenerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerosActionPerformed
        PantallaGeneros pantallaNueva = new PantallaGeneros();
        pantallaNueva.setVisible(true);
        pantallaNueva.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGenerosActionPerformed

    private void btnVerDiscos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDiscos1ActionPerformed
        PantallaBandas pantallaNueva = new PantallaBandas();
        pantallaNueva.setVisible(true);
        pantallaNueva.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnVerDiscos1ActionPerformed

    private void btnPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaisActionPerformed
        AgregarPais pantallaNueva = new AgregarPais();
        pantallaNueva.setVisible(true);
        pantallaNueva.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnPaisActionPerformed

    private void btnGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneroActionPerformed
        AgregarGenero pantallaNueva = new AgregarGenero();
        pantallaNueva.setVisible(true);
        pantallaNueva.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGeneroActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBanda;
    private javax.swing.JButton btnAddDisco;
    private javax.swing.JButton btnGenero;
    private javax.swing.JButton btnGeneros;
    private javax.swing.JButton btnPais;
    private javax.swing.JButton btnVerDiscos1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
