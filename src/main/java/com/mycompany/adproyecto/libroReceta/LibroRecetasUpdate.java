/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.adproyecto.libroReceta;

import com.mycompany.adproyecto.Main;
import com.mycompany.adproyecto.libroReceta.IDAO.BinaryDAOLibroRecetas;
import com.mycompany.adproyecto.libroReceta.IDAO.BufferedDAOLibroRecetas;
import com.mycompany.adproyecto.libroReceta.IDAO.RADAOLibroRecetas;
import com.mycompany.adproyecto.libroReceta.IDAO.object.ObjectDAOLibroRecetas;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JOptionPane;

/**
 *
 * @author mrpox
 */
public class LibroRecetasUpdate extends javax.swing.JFrame {

    private BufferedDAOLibroRecetas bLR;
    private BinaryDAOLibroRecetas dLR;
    private ObjectDAOLibroRecetas oLR;
    private char typeData;
    private File file;
    private RADAOLibroRecetas rLR;
    private final SimpleDateFormat sdf;

    /**
     * Creates new form LibroRecetaUpdateç
     */
    public LibroRecetasUpdate() {
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
        textBusqueda = new javax.swing.JLabel();
        isbn = new javax.swing.JTextField();
        textName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textNumPags = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        isDigitalorNot = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backButton.setText("VOLVER");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        textBusqueda.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        textBusqueda.setText("ISBN del Libro a  Editar:");

        textName.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setText("NOMBRE");

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel3.setText("NÚMERO DE PÁGINAS");

        textNumPags.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel4.setText("FECHA DE PUBLICACIÓN");

        textDate.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel5.setText("DIGITAL");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textNumPags, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textBusqueda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(207, 207, 207))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textDate)
                                    .addComponent(isDigitalorNot, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textNumPags, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(isDigitalorNot, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        Main m = new Main();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        LibroRecetas libroRIn = null;
        LibroRecetas libroRAux = null;
        int isbnNum;
        if (isbn.getText().isEmpty() || textName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ALGUN CAMPO REQUERIDO ES INCORRECTO",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        isbnNum = Integer.parseInt(isbn.getText());
        libroRIn = putData(libroRIn);
        if (libroRIn == null) {
            return;
        }
        switch (typeData) {
            case 'B' -> {
                bLR = new BufferedDAOLibroRecetas(file.getAbsolutePath());
                libroRAux = bLR.consultaId(isbnNum);
                if (testLibroIfExists(libroRAux)) {
                    if (bLR.modificar(libroRAux, libroRIn)) {
                        JOptionPane.showMessageDialog(null, "ACTUALIZACIÓN DEL LIBRO DE RECETAS CORRECTA",
                                "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR EN LA ACTUALIZACIÓN",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ISBN DEL LIBRO A ACTUALIZAR NO SE HA ENCONTRADO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            case 'D' -> {
                dLR = new BinaryDAOLibroRecetas(file.getAbsolutePath());
                libroRAux = dLR.consultaId(isbnNum);
                if (testLibroIfExists(libroRAux)) {
                    if (dLR.modificar(libroRAux, libroRIn)) {
                        JOptionPane.showMessageDialog(null, "ACTUALIZACIÓN DEL LIBRO DE RECETAS CORRECTA",
                                "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR EN LA ACTUALIZACIÓN",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ISBN DEL LIBRO A ACTUALIZACIAR NO SE HA ENCONTRADO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            case 'O' -> {
                oLR = new ObjectDAOLibroRecetas(file.getAbsolutePath());
                libroRAux = oLR.consultaId(isbnNum);
                if (testLibroIfExists(libroRAux)) {
                    if (oLR.modificar(libroRAux, libroRIn)) {
                        JOptionPane.showMessageDialog(null, "ACTUALIZACIÓN DEL LIBRO DE RECETAS CORRECTA",
                                "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR EN LA ACTUALIZACIÓN",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ISBN DEL LIBRO A ACTUALIZACIAR NO SE HA ENCONTRADO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            case 'R' -> {
                rLR = new RADAOLibroRecetas(file.getAbsolutePath());
                libroRAux = rLR.consultaId(isbnNum);
                if (testLibroIfExists(libroRAux)) {
                    if (rLR.modificar(libroRAux, libroRIn)) {
                        JOptionPane.showMessageDialog(null, "ACTUALIZACIÓN DEL LIBRO DE RECETAS CORRECTA",
                                "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR EN LA ACTUALIZACIÓN",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ISBN DEL LIBRO A ACTUALIZACIAR NO SE HA ENCONTRADO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            case 'X' -> {
            }
        }
    }//GEN-LAST:event_updateButtonActionPerformed

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
            java.util.logging.Logger.getLogger(LibroRecetasUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibroRecetasUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibroRecetasUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibroRecetasUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibroRecetasUpdate().setVisible(true);
            }
        });
    }

    private boolean testLibroIfExists(LibroRecetas libroRAux) {
        return libroRAux != null;
    }

    private LibroRecetas putData(LibroRecetas libroR) {
        libroR = new LibroRecetas(Integer.parseInt(isbn.getText()), textName.getText());
        libroR.setNombre(textName.getText());
        libroR.setDigital(isDigitalorNot.isSelected());
        if (textNumPags.getText().length() >= 1) {
            libroR.setNumPags(Integer.parseInt(textNumPags.getText()));
        }
        if (!textDate.getText().isEmpty()) {
            try {
                Date d = sdf.parse(textDate.getText());
                libroR.setFechaPublicacion(d);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "FECHA MAL PUESTA",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return libroR;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JToggleButton isDigitalorNot;
    private javax.swing.JTextField isbn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel textBusqueda;
    private javax.swing.JTextField textDate;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textNumPags;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
