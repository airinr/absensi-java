/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import main.java.com.sistemAkademikApps.model.Koneksi;
import main.java.com.sistemAkademikApps.model.SiswaModel;
import main.java.com.sistemAkademikApps.view.FormDataSiswaView;
import main.java.com.sistemAkademikApps.view.SiswaView;

/**
 *
 * @author HP
 */
public class SiswaController {
    private SiswaModel siswaModel;

    public SiswaModel getSiswaModel() {
        return siswaModel;
    }
    public void setSiswaModel(SiswaModel siswaModel) {
        this.siswaModel = siswaModel;
    }
    
    Connection con;
    
    public void tampilData(FormDataSiswaView formSiswa) throws SQLException{
        SiswaModel siswaModel = new SiswaModel();
        ResultSet rs =  siswaModel.getAll();
        while(rs.next()){
                String nisn = String.valueOf(rs.getString("nisn"));
                String nama = String.valueOf(rs.getString("nama"));
                String tempat = String.valueOf(rs.getString("tempat_lahir"));
                String ttl = String.valueOf(rs.getString("ttl"));
                String jk = String.valueOf(rs.getString("jenis_kelamin"));
                String alamat = String.valueOf(rs.getString("alamat"));
                String kelas = String.valueOf(rs.getString("kelas"));
                
                String tabelData[] = {nisn,nama,tempat,ttl,jk,alamat,kelas};
                DefaultTableModel tblModel = (DefaultTableModel)formSiswa.getjTableSiswa().getModel();
                
                tblModel.addRow(tabelData);
                
            }
    }
    
    public void tampilDataSiswa(SiswaView siswa) throws SQLException{
        SiswaModel siswaModel = new SiswaModel();
        ResultSet rs =  siswaModel.getAll();
        while(rs.next()){
                String nisn = String.valueOf(rs.getString("nisn"));
                String nama = String.valueOf(rs.getString("nama"));
                String tempat = String.valueOf(rs.getString("tempat_lahir"));
                String ttl = String.valueOf(rs.getString("ttl"));
                String jk = String.valueOf(rs.getString("jenis_kelamin"));
                String alamat = String.valueOf(rs.getString("alamat"));
                String kelas = String.valueOf(rs.getString("kelas"));
                
                String tabelData[] = {nisn,nama,tempat,ttl,jk,alamat,kelas};
                DefaultTableModel tblModel = (DefaultTableModel)siswa.getjTableSiswa().getModel();
                
                tblModel.addRow(tabelData);
                
            }
    }
    
    
    
    public void simpanData(FormDataSiswaView siswa) throws SQLException, ParseException{
        String nisn = siswa.getTxtNisn().getText();
        String nama = siswa.getTxtNama().getText();
        String tempat = siswa.getTxtTtl().getText();
        String alamat = siswa.getTxtAlamat().getText();
        Date ttl = siswa.getTglLahir().getDate();
        String jk;

        siswaModel = new SiswaModel();
        ResultSet rs = siswaModel.kls();
        boolean validasi = siswaModel.validasiNisn(nisn);

        boolean isCheck = siswa.getRadioL().isSelected();
        if (isCheck == true) {
            jk = "L";
        } else{
            jk = "P";
        }

        //kelas
        String kelas = (String) siswa.getComboKelas().getSelectedItem();
        
         //Validasi Data
        if(nisn.trim().equals("")){
            JOptionPane.showMessageDialog(siswa, "NISN Tidak Boleh Kosong");
        }else if(validasi){
            JOptionPane.showMessageDialog(siswa, "Data nisn sudah digunakan");
        }
        else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(siswa, "Nama Siswa Tidak Boleh Kosong");
        }
        else if(ttl == null){
            JOptionPane.showMessageDialog(siswa, "Tempat Tanggal Lahir Tidak Boleh Kosong");
        }
        else if(alamat.trim().equals("")){
            JOptionPane.showMessageDialog(siswa, "Alamat TIdak Boleh Kosong");
        }
        else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM--dd");
            String formattedDate = sdf.format(ttl);
            
            siswaModel.insert(nisn, nama,tempat, formattedDate, jk, alamat, kelas);
            JOptionPane.showMessageDialog(null, "BERHASIL");
            
            String tabelData[] = {nisn, nama, tempat, formattedDate,jk, alamat, kelas};
            DefaultTableModel tblModel = (DefaultTableModel) siswa.getjTableSiswa().getModel();
            tblModel.addRow(tabelData);
            
            siswa.getTxtNisn().setText("");
            siswa.getTxtNama().setText("");
            siswa.getTxtTtl().setText("");
            siswa.getTglLahir().setDate(null);
            siswa.getRadioL().setSelected(false);
            siswa.getRadioP().setSelected(false);
            siswa.getTxtAlamat().setText("");
            siswa.getRadioL().setSelected(false);
            siswa.getComboKelas().setSelectedIndex(0);
        }
        
        
    }
   
    public void kelas(FormDataSiswaView siswa) throws SQLException{
        ResultSet rs = siswaModel.kls();
        while(rs.next()){
            String kelas = rs.getString("kelas");
            siswa.getComboKelas().addItem(kelas);
            siswa.getComboCariKelas().addItem(kelas);
        }
    }
    
    public void kelasSiswa(SiswaView siswaView) throws SQLException{
        ResultSet rs = siswaModel.kls();
        while(rs.next()){
            String kelas = rs.getString("kelas");
            siswaView.getComboCariKelas().addItem(kelas);
        }
    }
    
    public void tampilPerSiswa(FormDataSiswaView siswa) throws ParseException{
        DefaultTableModel tblSiswa = (DefaultTableModel)siswa.getjTableSiswa().getModel();
        
        
        //set data
        String tblNisn = tblSiswa.getValueAt(siswa.getjTableSiswa().getSelectedRow(), 0).toString();
        String tblNama = tblSiswa.getValueAt(siswa.getjTableSiswa().getSelectedRow(), 1).toString();
        String tblTempat = tblSiswa.getValueAt(siswa.getjTableSiswa().getSelectedRow(), 2).toString();
        String tblTtl = tblSiswa.getValueAt(siswa.getjTableSiswa().getSelectedRow(), 3).toString();
        String tblJk = tblSiswa.getValueAt(siswa.getjTableSiswa().getSelectedRow(), 4).toString();
        String tblAlamat = tblSiswa.getValueAt(siswa.getjTableSiswa().getSelectedRow(), 5).toString();
        String tblKelas = tblSiswa.getValueAt(siswa.getjTableSiswa().getSelectedRow(), 6).toString();
        
        siswa.getTxtNisn().setText(tblNisn);
        siswa.getTxtNama().setText(tblNama);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(tblTtl);
        siswa.getTglLahir().setDate(date);
            
        siswa.getTxtTtl().setText(tblTtl);
        if(tblJk.trim().equals("L")){
            siswa.getRadioL().setSelected(true);
        }else{
            siswa.getRadioP().setSelected(true);
        }
        siswa.getTxtAlamat().setText(tblAlamat);
        siswa.getComboKelas().setSelectedItem(tblKelas); 
        siswa.getTxtTtl().setText(tblTempat);
 
    }
    
    public void editSiswa(FormDataSiswaView siswa) throws SQLException{
        
        String nisn = siswa.getTxtNisn().getText();
        String nama = siswa.getTxtNama().getText();
        String tempat = siswa.getTxtTtl().getText();
        
        
        DefaultTableModel tblSiswa = (DefaultTableModel)siswa.getjTableSiswa().getModel();
        
        if(nisn.trim().equals("")){
            JOptionPane.showMessageDialog(siswa, "Pilih data siswa terlebih dahulu");
        }else{
            if(siswa.getjTableSiswa().getSelectedRowCount() == 1){
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ttl = siswa.getTglLahir().getDate();
            String formattedDate = sdf.format(ttl);
            
            String alamat = siswa.getTxtAlamat().getText();
            String jk;
            
            boolean isCheck = siswa.getRadioL().isSelected();
            if (isCheck == true) {
                jk = "L";
            } else{
                jk = "P";
            }
            
            String kelas = (String) siswa.getComboKelas().getSelectedItem();
            
            tblSiswa.setValueAt(nisn, siswa.getjTableSiswa().getSelectedRow(), 0);
            tblSiswa.setValueAt(nama, siswa.getjTableSiswa().getSelectedRow(), 1);
            tblSiswa.setValueAt(tempat, siswa.getjTableSiswa().getSelectedRow(), 2);
            tblSiswa.setValueAt(formattedDate, siswa.getjTableSiswa().getSelectedRow(), 3);
            tblSiswa.setValueAt(jk, siswa.getjTableSiswa().getSelectedRow(), 4);
            tblSiswa.setValueAt(alamat, siswa.getjTableSiswa().getSelectedRow(), 5);
            tblSiswa.setValueAt(kelas, siswa.getjTableSiswa().getSelectedRow(), 6);
            
            siswaModel.update(nisn, nama,tempat, formattedDate, jk, alamat, kelas);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        }
        }
        
        
        
        
    }
    
    public void tampilDataPerKelas(FormDataSiswaView formSiswa) throws SQLException{
        formSiswa.getjTableSiswa().setModel(new DefaultTableModel(null, new String[]{"NISN","NAMA","TEMPAT LAHIR","TANGGAL LAHIR","JENIS KELAMIN","ALAMAT","KELAS"}));
        String kelasCari = (String) formSiswa.getComboCariKelas().getSelectedItem();
        SiswaModel siswaModel = new SiswaModel();
        ResultSet rs =  siswaModel.getClass(kelasCari);
        while(rs.next()){
                String nisn = String.valueOf(rs.getString("nisn"));
                String nama = String.valueOf(rs.getString("nama"));
                String tempat = String.valueOf(rs.getString("tempat_lahir"));
                String ttl = String.valueOf(rs.getString("ttl"));
                String jk = String.valueOf(rs.getString("jenis_kelamin"));
                String alamat = String.valueOf(rs.getString("alamat"));
                String kelas = String.valueOf(rs.getString("kelas"));
                
                String tabelData[] = {nisn,nama,tempat,ttl,jk,alamat,kelas};
                DefaultTableModel tblModel = (DefaultTableModel)formSiswa.getjTableSiswa().getModel();
                
                tblModel.addRow(tabelData);
            }
    }
    
    public void tampilDataPerKelasSiswa(SiswaView siswaView) throws SQLException{
        siswaView.getjTableSiswa().setModel(new DefaultTableModel(null, new String[]{"NISN","NAMA","TEMPAT LAHIR","TANGGAL LAHIR","JENIS KELAMIN","ALAMAT","KELAS"}));
        String kelasCari = (String) siswaView.getComboCariKelas().getSelectedItem();
        SiswaModel siswaModel = new SiswaModel();
        ResultSet rs =  siswaModel.getClass(kelasCari);
        while(rs.next()){
                String nisn = String.valueOf(rs.getString("nisn"));
                String nama = String.valueOf(rs.getString("nama"));
                String tempat = String.valueOf(rs.getString("tempat_lahir"));
                String ttl = String.valueOf(rs.getString("ttl"));
                String jk = String.valueOf(rs.getString("jenis_kelamin"));
                String alamat = String.valueOf(rs.getString("alamat"));
                String kelas = String.valueOf(rs.getString("kelas"));
                
                String tabelData[] = {nisn,nama,tempat,ttl,jk,alamat,kelas};
                DefaultTableModel tblModel = (DefaultTableModel)siswaView.getjTableSiswa().getModel();
                
                tblModel.addRow(tabelData);
            }
    }
    
    public void hapusData(FormDataSiswaView formSiswa) throws SQLException{
        String nisn = formSiswa.getTxtNisn().getText();
        SiswaModel siswa = new SiswaModel();
        siswa.delete(formSiswa, nisn);
        DefaultTableModel tblModel = (DefaultTableModel)formSiswa.getjTableSiswa().getModel();
        tblModel.setRowCount(0);
        this.tampilData(formSiswa);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        
        formSiswa.getTxtNisn().setText("");
        formSiswa.getTxtNama().setText("");
        formSiswa.getTxtTtl().setText("");
        formSiswa.getTglLahir().setDate(null);
        formSiswa.getRadioL().setSelected(false);
        formSiswa.getRadioP().setSelected(false);
        formSiswa.getTxtAlamat().setText("");
        formSiswa.getRadioL().setSelected(false);
        formSiswa.getComboKelas().setSelectedIndex(0);
    }
    
    
 
}
