/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.adproyecto.receta;

import com.mycompany.adproyecto.Main;
import com.mycompany.adproyecto.receta.IDAO.BinaryDAOReceta;
import com.mycompany.adproyecto.receta.IDAO.BufferedDAOReceta;
import com.mycompany.adproyecto.receta.IDAO.DOMDAOReceta;
import com.mycompany.adproyecto.receta.IDAO.RADAOReceta;
import com.mycompany.adproyecto.receta.IDAO.object.ObjectDAOReceta;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JOptionPane;

/**
 *
 * @author mrpox
 */
public class RecetaDelete extends javax.swing.JFrame {

    private BufferedDAOReceta bR;
    private BinaryDAOReceta dR;
    private ObjectDAOReceta oR;
    private char typeData;
    private File file;
    private RADAOReceta rR;
    private final SimpleDateFormat sdf;
    private DOMDAOReceta domR;

    /**
     * Creates new form LibroRecetasDelete
     */
    public RecetaDelete() {
        super("RECETA DELETE");
        initComponents();
        this.setLocationRelativeTo(null);
        this.sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        this.sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        this.setResizable(false);
    }

    public void setTypeData(char typeData) {
        this.typeData = typeData;
    }

    public void setFile(File file) {
        this.file = file;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        textId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buttonBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backButton.setText("VOLVER");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        textId.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel1.setText("ID de la Receta a borrar:");

        buttonBorrar.setText("BORRAR");
        buttonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(81, 81, 81)
                                .addComponent(textId, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(331, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addGap(115, 115, 115)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109)
                .addComponent(buttonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        Main m = new Main();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void buttonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBorrarActionPerformed
        // TODO add your handling code here:
        Receta recetaIn = null;
        int id;
        if (textId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ALGUN CAMPO REQUERIDO ES INCORRECTO",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!textId.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "SOLO SE ACEPTA VALOR NUMÉRICO EN EL CAMPO ID",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        id = Integer.parseInt(textId.getText());
        switch (typeData) {
            case 'B' -> {
                bR = new BufferedDAOReceta(file.getAbsolutePath());
                recetaIn = bR.consultaId(id);
                if (recetaIn != null) {
                    if (bR.baja(recetaIn) != null) {
                        JOptionPane.showMessageDialog(null, "BORRADO DE LA RECETA CORRECTO",
                                "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR EN EL BORRADO",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ID DE LA RECETA A BORRAR NO SE HA ENCONTRADO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
            case 'D' -> {
                dR = new BinaryDAOReceta(file.getAbsolutePath());
                recetaIn = dR.consultaId(id);
                if (recetaIn != null) {
                    if (dR.baja(recetaIn) != null) {
                        JOptionPane.showMessageDialog(null, "BORRADO DE LA RECETA CORRECTO",
                                "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR EN EL BORRADO",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ID DE LA RECETA A BORRAR NO SE HA ENCONTRADO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            case 'O' -> {
                oR = new ObjectDAOReceta(file.getAbsolutePath());
                recetaIn = oR.consultaId(id);
                if (recetaIn != null) {
                    if (oR.baja(recetaIn) != null) {
                        JOptionPane.showMessageDialog(null, "BORRADO DE LA RECETA CORRECTO",
                                "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR EN EL BORRADO",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ID DE LA RECETA A BORRAR NO SE HA ENCONTRADO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            case 'R' -> {
                rR = new RADAOReceta(file.getAbsolutePath());
                recetaIn = rR.consultaId(id);
                if (recetaIn != null) {
                    if (rR.baja(recetaIn) != null) {
                        JOptionPane.showMessageDialog(null, "BORRADO DE LA RECETA CORRECTO",
                                "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR EN EL BORRADO",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ID DE LA RECETA A BORRAR NO SE HA ENCONTRADO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            case 'X' -> {
                domR = new DOMDAOReceta(file.getAbsolutePath());
                recetaIn = domR.consultaId(id);
                if (recetaIn != null) {
                    if (domR.baja(recetaIn) != null) {
                        JOptionPane.showMessageDialog(null, "BORRADO DE LA RECETA CORRECTO",
                                "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR EN EL BORRADO",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ID DE LA RECETA A BORRAR NO SE HA ENCONTRADO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_buttonBorrarActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RecetaDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecetaDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecetaDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecetaDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RecetaDelete().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton buttonBorrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField textId;
    // End of variables declaration//GEN-END:variables
}
