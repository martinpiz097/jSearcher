/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.buscadorFrases.gui;

import com.sun.javafx.iio.ImageStorage;
import com.sun.javafx.iio.ImageStorage.ImageType;
import org.martin.buscadorFrases.data.Buscador;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableColumn;

/**
 *
 * @author martin
 */
public class WinSearcher extends javax.swing.JFrame {

    public static final String STR_RUTA_NO_ESPECIFICADA = "[No se ha ingresado ninguna ruta]";
    JFileChooser fileChoos;
    Buscador searcher;
    
    public WinSearcher() {
        initComponents();
        setTitle("jSearcher V0.4a");
        opFile.setSelected(true);
        txtFiltro.setEditable(false);
        fileChoos = new JFileChooser();
        fileChoos.setMultiSelectionEnabled(false);
        fileChoos.setDialogTitle("Directorio a buscar");
        fileChoos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChoos.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {

                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                
                return "Solo directorios";
            }
        });
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        opFile = new javax.swing.JRadioButton();
        opTexto = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        btnExplorar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        chkOcultos = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelResultados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de busqueda"));

        jLabel1.setText("¿Que desea buscar?");

        buttonGroup1.add(opFile);
        opFile.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        opFile.setText("Un archivo");

        buttonGroup1.add(opTexto);
        opTexto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        opTexto.setText("Texto en especifico");

        jLabel2.setText("¿Donde?");

        txtRuta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRutaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRutaKeyReleased(evt);
            }
        });

        btnExplorar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnExplorar.setText("...");
        btnExplorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExplorarActionPerformed(evt);
            }
        });

        jLabel3.setText("Filtro:");

        txtFiltro.setToolTipText("Escriba aqui lo que desea buscar. Al finalizar solo presione ENTER");
        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        chkOcultos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkOcultos.setText("Incluir ocultos");
        chkOcultos.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFiltro, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRuta, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExplorar)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(opFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(opTexto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(chkOcultos)
                        .addGap(23, 23, 23))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(opFile)
                    .addComponent(opTexto)
                    .addComponent(chkOcultos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExplorar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));
        panelResultados.setLayout(new java.awt.GridLayout(0, 1));

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblResultados);

        panelResultados.add(jScrollPane1);

        jScrollPane2.setViewportView(panelResultados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExplorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExplorarActionPerformed

        fileChoos.showOpenDialog(this);
        File directory = fileChoos.getSelectedFile();
        
        txtFiltro.setEditable(directory != null);
        
        if (directory != null){ 
            txtRuta.setText(directory.getAbsolutePath());
            txtRuta.setBackground(Color.GREEN);
        }
        else{
            
            if (txtRuta.getText().isEmpty()) {
                txtRuta.setText(STR_RUTA_NO_ESPECIFICADA);
                txtRuta.setBackground(Color.RED);
            }
        }
    }//GEN-LAST:event_btnExplorarActionPerformed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String ruta = txtRuta.getText().trim();
            String filtro = txtFiltro.getText().trim();
            
            Thread hSearcher = new Thread(() -> {
                try {
                    // panelResultados.removeAll();
                    searcher = new Buscador(ruta, filtro, tblResultados);
                    System.out.println("Entro al try");
                    
                    txtFiltro.setEnabled(false);
                    if (opFile.isSelected()) searcher.searchFile();
                    
                    else searcher.searchText();
                    
                    if (searcher.isTerminado()) {
                        
                        txtFiltro.setEnabled(true);
                        if (searcher.getContadorResultados() > 0) {
                        
                            JOptionPane.showMessageDialog(
                                this,
                                "Busqueda Finalizada",
                                "Mensaje",
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(
                                this,
                                "Busqueda Finalizada\nNo se encontraron resultados",
                                "Mensaje",
                                JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(WinSearcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            hSearcher.start();
            
            
//            DialogoEspera dialog = null;
//            dialog = new DialogoEspera(this, true);
//            
//            System.out.println("Antes del while");
//            System.out.println(hSearcher.isAlive());
//            while (hSearcher.isAlive()) {
//                System.out.println(hSearcher.isAlive() + " - " + searcher.isTerminado());
//            }
//            System.out.println(hSearcher.isAlive());
//            dialog.setVisible(!isVisible());

        
            
        }
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
    }//GEN-LAST:event_formWindowClosing

    private void txtRutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaKeyPressed

       
    }//GEN-LAST:event_txtRutaKeyPressed

    private void txtRutaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaKeyReleased

        boolean isVacio = txtRuta.getText().isEmpty();
        txtFiltro.setEditable(!isVacio);

        if (isVacio) txtRuta.setBackground(Color.WHITE);
        
    }//GEN-LAST:event_txtRutaKeyReleased

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
            java.util.logging.Logger.getLogger(WinSearcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WinSearcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WinSearcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WinSearcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WinSearcher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExplorar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkOcultos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton opFile;
    private javax.swing.JRadioButton opTexto;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
