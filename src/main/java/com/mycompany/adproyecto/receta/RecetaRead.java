/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.adproyecto.receta;

import com.mycompany.adproyecto.Main;
import com.mycompany.adproyecto.receta.IDAO.BinaryDAOReceta;
import com.mycompany.adproyecto.receta.IDAO.BufferedDAOReceta;
import com.mycompany.adproyecto.receta.IDAO.RADAOReceta;
import com.mycompany.adproyecto.receta.IDAO.object.ObjectDAOReceta;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author mrpox
 */
public class RecetaRead extends javax.swing.JFrame {
    
    private BufferedDAOReceta bR;
    private BinaryDAOReceta dR;
    private ObjectDAOReceta oR;
    private char typeData;
    private File file;
    private RADAOReceta rR;

    /**
     * Creates new form LibroRecetasRead
     */
    public RecetaRead() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public void setTypeData(char typeData) {
        this.typeData = typeData;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscarButtonID = new javax.swing.JButton();
        buscarButtonAll = new javax.swing.JButton();
        textConsultaAll = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        textResultados = new javax.swing.JLabel();
        textBusqueda = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buscarButtonID.setText("BUSCAR");
        buscarButtonID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonIDActionPerformed(evt);
            }
        });

        buscarButtonAll.setText("BUSCAR");
        buscarButtonAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonAllActionPerformed(evt);
            }
        });

        textConsultaAll.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        textConsultaAll.setText("Consulta All:");

        textResultados.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        textResultados.setText("RESULTADOS:");

        textBusqueda.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        textBusqueda.setText("Busqueda por ID:");

        jScrollPane2.setViewportView(list);

        backButton.setText("VOLVER");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(71, 71, 71)
                            .addComponent(buscarButtonID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(backButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(buscarButtonAll, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(textConsultaAll))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textResultados)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(textBusqueda)
                    .addContainerGap(625, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(buscarButtonID, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(textConsultaAll, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscarButtonAll, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(textBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(407, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarButtonIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonIDActionPerformed
        // TODO add your handling code here:
        DefaultListModel<String> nuevoModelo = new DefaultListModel<>();
        if (file.length() < 1) {
            nuevoModelo.addElement("ARCHIVO VACIO");
            list.setModel(nuevoModelo);
            list.repaint();
            return;
        } else {
            list.setModel(nuevoModelo);
            list.repaint();
        }
        String date = "null";
        Receta out = null;
        int idIntroducido;
        if (this.id.getText().equals("")) {
            idIntroducido = 0;
        } else {
            idIntroducido = Integer.parseInt(this.id.getText());
        }
        switch (typeData) {
            case 'B' -> {
                bR = new BufferedDAOReceta(file.getAbsolutePath());
                out = bR.consultaId(idIntroducido);
            }
            case 'D' -> {
                dR = new BinaryDAOReceta(file.getAbsolutePath());
                out = dR.consultaId(idIntroducido);
            }
            case 'O' -> {
                oR = new ObjectDAOReceta(file.getAbsolutePath());
                out = oR.consultaId(idIntroducido);
            }
            case 'R' -> {
                rR = new RADAOReceta(file.getAbsolutePath());
                out = rR.consultaId(idIntroducido);
            }
            case 'X' -> {
            }
        }
        nuevoModelo = new DefaultListModel<>();
        if (out == null) {
            nuevoModelo.addElement("Resultado no encontrado");
        } else {
            nuevoModelo.addElement("Libro Recetas:");
            nuevoModelo.addElement("ID: " + out.getIdReceta());
            nuevoModelo.addElement("Nombre: " + out.getNombre());
            if(out.getIdLibro()==0){
                    nuevoModelo.addElement("ID Libro de Recetas: " + null);
                }else{
                    nuevoModelo.addElement("ID Libro de Recetas: " + out.getIdLibro());
                }
            if (out.getFechInvención() != null) {
                date = out.getFechInvención().toString();
            }
            nuevoModelo.addElement("Fecha de creación: " + date);
            nuevoModelo.addElement("Vegana: " + out.getVegana());
        }
        list.setModel(nuevoModelo);
        list.repaint();
    }//GEN-LAST:event_buscarButtonIDActionPerformed

    private void buscarButtonAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonAllActionPerformed
        // TODO add your handling code here:
        ArrayList<Receta> listaR = null;
        String date;
        DefaultListModel<String> nuevoModelo = new DefaultListModel<>();
        if (file.length() < 1) {
            nuevoModelo.addElement("ARCHIVO VACIO");
            list.setModel(nuevoModelo);
            list.repaint();
            return;
        } else {
            list.setModel(nuevoModelo);
            list.repaint();
        }
        switch (typeData) {
            case 'B' -> {
                bR = new BufferedDAOReceta(file.getAbsolutePath());
                listaR = bR.consultaAll();
            }
            case 'D' -> {
                dR = new BinaryDAOReceta(file.getAbsolutePath());
                listaR = dR.consultaAll();
            }
            case 'O' -> {
                oR = new ObjectDAOReceta(file.getAbsolutePath());
                listaR = oR.consultaAll();
            }
            case 'R' -> {
                rR = new RADAOReceta(file.getAbsolutePath());
                listaR = rR.consultaAll();
            }
            case 'X' -> {
            }
        }
        nuevoModelo.clear();
        if (listaR == null) {
            nuevoModelo.addElement("No hay elementos o hubo un error");
        } else {
            for (Receta out : listaR) {
                date="null";
                nuevoModelo.addElement("Libro Recetas:");
                nuevoModelo.addElement("ID: " + out.getIdReceta());
                nuevoModelo.addElement("Nombre: " + out.getNombre());
                if(out.getIdLibro()==0){
                    nuevoModelo.addElement("ID Libro de Recetas: " + null);
                }else{
                    nuevoModelo.addElement("ID Libro de Recetas: " + out.getIdLibro());
                }
                if (out.getFechInvención() != null) {
                    date = out.getFechInvención().toString();
                }
                nuevoModelo.addElement("Fecha de creación: " + date);
                nuevoModelo.addElement("Vegana: " + out.getVegana());
                nuevoModelo.addElement("\n");
            }
        }
        list.setModel(nuevoModelo);
        list.repaint();
    }//GEN-LAST:event_buscarButtonAllActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        Main m = new Main();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecetaRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RecetaRead().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton buscarButtonAll;
    private javax.swing.JButton buscarButtonID;
    private javax.swing.JTextField id;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> list;
    private javax.swing.JLabel textBusqueda;
    private javax.swing.JLabel textConsultaAll;
    private javax.swing.JLabel textResultados;
    // End of variables declaration//GEN-END:variables
}
