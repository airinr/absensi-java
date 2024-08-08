package main.java.com.sistemAkademikApps.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import main.java.com.sistemAkademikApps.model.Koneksi;
import main.java.com.sistemAkademikApps.model.KelasModel;
import main.java.com.sistemAkademikApps.model.SiswaModel;
import main.java.com.sistemAkademikApps.view.AbsensiView;
import main.java.com.sistemAkademikApps.view.FormDataSiswaView;
import main.java.com.sistemAkademikApps.view.FormDataKelasView;
import main.java.com.sistemAkademikApps.view.LoginView;
import main.java.com.sistemAkademikApps.view.MenuView;
import main.java.com.sistemAkademikApps.view.RegisterView;

public class KelasController {
    private KelasModel kelasModel;

    public KelasModel getKelasModel() {
        return kelasModel;
    }

    public void setKelasModel(KelasModel kelasModel) {
        this.kelasModel = kelasModel;
    }
    
    Connection con;
    public void simpanData(RegisterView register) throws SQLException{
        String kelas = register.getjTxtKelas().getText();
        String wali_kelas = register.getjTxtNama().getText();
        String nip_Wali_kelas = register.getjTxtNip().getText();
        
        String nip;
        kelasModel = new KelasModel();
        ResultSet rs = kelasModel.validasiNip(nip_Wali_kelas);
        boolean ketemuNip = false;
        if(rs.next()){
            nip = rs.getString("nip_wali_kelas");
            if(nip_Wali_kelas.equals(nip)){
                ketemuNip = true;
            }
        }
        
        String kelasK;
        ResultSet rsk = kelasModel.validasiKelas(kelas);
        boolean ketemuKelas = false;
        if(rsk.next()){
            kelasK = rsk.getString("kelas");
            if(kelas.equals(kelasK)){
                ketemuKelas = true;
            }
        }
       
        
        if(kelas.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Kelas tidak boleh kosong");
        }else if(wali_kelas.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
        }else if(nip_Wali_kelas.trim().equals("")){
            JOptionPane.showMessageDialog(null, "NIP tidak boleh kosong");
        }else if(ketemuNip){
            JOptionPane.showMessageDialog(null, "NIP sudah digunakan");
        }else if(ketemuKelas){
            JOptionPane.showMessageDialog(null, "Kelas tersebut sudah memiliki wali kelas");
        }
        else{
            try {
                con = Koneksi.getConnection();
                String sql = "INSERT INTO data_kelas (kelas, wali_kelas, nip_Wali_kelas) VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, kelas);
                pstmt.setString(2, wali_kelas);
                pstmt.setString(3, nip_Wali_kelas);


                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Silahkan Login Menggunakan Akun Anda");
                LoginView login = new LoginView();
                login.setVisible(true);
                register.setVisible(false);

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "GAGAL: " + e.getMessage());
                    }
        }
    }
    
    public void tampilData(FormDataKelasView formkelas)throws SQLException{
        KelasModel kelasModel = new KelasModel();
        ResultSet rs = kelasModel.getAll(formkelas);

       while(rs.next()){
            String kelas = String.valueOf(rs.getString("kelas"));
            String waliKelas = String.valueOf(rs.getString("wali_kelas"));
            String nip = String.valueOf(rs.getString("nip_Wali_Kelas"));

            String tabelData[] = {kelas, waliKelas, nip};
            DefaultTableModel tblModel = (DefaultTableModel)formkelas.getjTableKelas().getModel();

            tblModel.addRow(tabelData);
    
        }    
    }
    
    public void tampilPerWakel(FormDataKelasView formkelas) throws ParseException{
        DefaultTableModel tblModel = (DefaultTableModel)formkelas.getjTableKelas().getModel();

        //set data    
        String tblKls = tblModel.getValueAt(formkelas.getjTableKelas().getSelectedRow(), 0).toString();
        String tblWakel = tblModel.getValueAt(formkelas.getjTableKelas().getSelectedRow(), 1).toString();
        String tblNip = tblModel.getValueAt(formkelas.getjTableKelas().getSelectedRow(), 2).toString();

        formkelas.getjTxtKelas().setText(tblKls);
        formkelas.getjTxtWakel().setText(tblWakel);
        formkelas.getjTxtNip().setText(tblNip);
       
    }
    
    public void editWakel(FormDataKelasView formkelas) throws SQLException{
        DefaultTableModel tblModel = (DefaultTableModel)formkelas.getjTableKelas().getModel();
        
        if(formkelas.getjTableKelas().getSelectedRowCount() == 1){
            String kelas = formkelas.getjTxtKelas().getText();
            String wakel = formkelas.getjTxtWakel().getText();
            String nip = formkelas.getjTxtNip().getText();
            
            tblModel.setValueAt(kelas, formkelas.getjTableKelas().getSelectedRow(), 0);
            tblModel.setValueAt(wakel, formkelas.getjTableKelas().getSelectedRow(), 1);
            tblModel.setValueAt(nip, formkelas.getjTableKelas().getSelectedRow(), 2);
 
            kelasModel.update(kelas, wakel, nip);
        }
    }
    
    public void hapusData(FormDataKelasView formKelas) throws SQLException{
        String nip = formKelas.getjTxtNip().getText();
        KelasModel kelasModel = new KelasModel();
        kelasModel.delete(formKelas, nip);
        DefaultTableModel tblModel = (DefaultTableModel)formKelas.getjTableKelas().getModel();
        tblModel.setRowCount(0); 
        this.tampilData(formKelas);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        
        
    }

    
}
