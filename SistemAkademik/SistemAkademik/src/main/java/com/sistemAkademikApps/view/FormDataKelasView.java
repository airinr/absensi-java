/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps.view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.LayoutManager;
import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import main.java.com.sistemAkademikApps.controller.KelasController;
import main.java.com.sistemAkademikApps.model.KelasModel;

/**
 *
 * @author HP
 */
public class FormDataKelasView extends javax.swing.JFrame {
    KelasModel kelasModel;
    KelasController kelasController;
    
    public FormDataKelasView() throws SQLException {
        initComponents();
        setLocationRelativeTo(this);
        
        kelasModel = new KelasModel();
        kelasController = new KelasController();
        kelasController.setKelasModel(kelasModel);
        kelasController.tampilData(this);
        kelasModel.getAll(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtKelas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtNip = new javax.swing.JTextField();
        jTabelKelas = new javax.swing.JScrollPane();
        jTableKelas = new javax.swing.JTable();
        jTxtWakel = new javax.swing.JTextField();
        jBtnBack = new rojerusan.RSMaterialButtonRectangle();
        jBtnEdit = new rojerusan.RSMaterialButtonRectangle();
        jBtnHapus1 = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tabel Kelas");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1220, 710));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA KELAS");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 31, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 120));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("KELAS :");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, 20));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("WALI KELAS :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, -1, -1));

        jTxtKelas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTxtKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtKelasActionPerformed(evt);
            }
        });
        jPanel3.add(jTxtKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 275, 53));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("NIP WALI KELAS :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, -1, -1));

        jTxtNip.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTxtNip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNipActionPerformed(evt);
            }
        });
        jPanel3.add(jTxtNip, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 263, 275, 51));

        jTabelKelas.setToolTipText("");
        jTabelKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelKelasMouseClicked(evt);
            }
        });
        jTabelKelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabelKelasKeyPressed(evt);
            }
        });

        jTableKelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KELAS", "WALI KELAS", "NIP WALI KELAS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKelasMouseClicked(evt);
            }
        });
        jTabelKelas.setViewportView(jTableKelas);
        jTableKelas.getAccessibleContext().setAccessibleName("");

        jPanel3.add(jTabelKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 550, 360));

        jTxtWakel.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTxtWakel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtWakelActionPerformed(evt);
            }
        });
        jPanel3.add(jTxtWakel, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 168, 275, 53));

        jBtnBack.setBackground(new java.awt.Color(153, 153, 153));
        jBtnBack.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jBtnBack.setText("BACK");
        jBtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBackMouseClicked(evt);
            }
        });
        jPanel3.add(jBtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 510, 140, 40));

        jBtnEdit.setBackground(new java.awt.Color(0, 153, 51));
        jBtnEdit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jBtnEdit.setText("EDIT");
        jBtnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnEditMouseClicked(evt);
            }
        });
        jPanel3.add(jBtnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 140, 40));

        jBtnHapus1.setBackground(new java.awt.Color(204, 0, 0));
        jBtnHapus1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jBtnHapus1.setText("HAPUS");
        jBtnHapus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnHapus1MouseClicked(evt);
            }
        });
        jBtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHapus1ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnHapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 140, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-80, 120, 1360, 580));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtKelasActionPerformed

    private void jTxtNipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtNipActionPerformed

    private void jTabelKelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabelKelasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabelKelasKeyPressed

    private void jTabelKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelKelasMouseClicked
        
        try {
            // TODO add your handling code here:
            kelasController.tampilPerWakel(this);
        } catch (ParseException ex) {
            Logger.getLogger(FormDataKelasView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTabelKelasMouseClicked

    private void jTableKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKelasMouseClicked
        try {
            // TODO add your handling code here:
            kelasController.tampilPerWakel(this);
        } catch (ParseException ex) {
            Logger.getLogger(FormDataKelasView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableKelasMouseClicked

    private void jTxtWakelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtWakelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtWakelActionPerformed

    private void jBtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBackMouseClicked
        // TODO add your handling code here:
        MenuView menu = new MenuView();
        menu.setVisible(true);
        this.setVisible(false);
       
    }//GEN-LAST:event_jBtnBackMouseClicked

    private void jBtnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnEditMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            kelasController.editWakel(this);
        } catch (SQLException ex) {
            Logger.getLogger(FormDataKelasView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnEditMouseClicked

    private void jBtnHapus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHapus1MouseClicked
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            kelasController.hapusData(this);
        } catch (SQLException ex) {
            Logger.getLogger(FormDataKelasView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnHapus1MouseClicked

    private void jBtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHapus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnHapus1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormDataKelasView().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormDataKelasView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    

//    public JComboBox<String> getjComboKelas() {
//        return jComboKelas;
//    }

    public JTextField getjTxtNip() {
        return jTxtNip;
    }

    public JTextField getjTxtWakel() {
        return jTxtWakel;
    }

    public Object getjTxtId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JTable getjTableKelas() {
        return jTableKelas;
    }

    public JTextField getjTxtKelas() {
        return jTxtKelas;
    }

    
    /***
     * //simpan
     * try {
            // TODO add your handling code here:
            kelasController.simpanData(this);
        } catch (SQLException ex) {
            Logger.getLogger(FormDataKelasView.class.getName()).log(Level.SEVERE, null, ex);
        }
        * 
        * //edit
        * try {
            // TODO add your handling code here:
            kelasController.editWakel(this);
        } catch (SQLException ex) {
            Logger.getLogger(FormDataKelasView.class.getName()).log(Level.SEVERE, null, ex);
        }
        * 
        * //hapus
        * 
        *try {
            // TODO add your handling code here:
            kelasController.hapusData(this);
        } catch (SQLException ex) {
            Logger.getLogger(FormDataKelasView.class.getName()).log(Level.SEVERE, null, ex);
        }
     */
    
    

  
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle jBtnBack;
    private rojerusan.RSMaterialButtonRectangle jBtnEdit;
    private rojerusan.RSMaterialButtonRectangle jBtnHapus1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jTabelKelas;
    private javax.swing.JTable jTableKelas;
    private javax.swing.JTextField jTxtKelas;
    private javax.swing.JTextField jTxtNip;
    private javax.swing.JTextField jTxtWakel;
    // End of variables declaration//GEN-END:variables

   


}
