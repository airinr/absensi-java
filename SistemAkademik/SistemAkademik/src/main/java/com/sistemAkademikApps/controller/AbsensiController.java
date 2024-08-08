/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.java.com.sistemAkademikApps.model.AbsensiModel;
import main.java.com.sistemAkademikApps.model.SiswaModel;
import main.java.com.sistemAkademikApps.view.AbsensiView;
import main.java.com.sistemAkademikApps.view.FormDataSiswaView;

/**
 *
 * @author HP
 */
public class AbsensiController {
    private AbsensiModel absensiModel;
    
    public AbsensiModel getAbsensiModel(){
        return absensiModel;
    }

    public void setAbsensiModel(AbsensiModel absensiModel) {
        this.absensiModel = absensiModel;
    }
     
    Connection con;
    
    public void tampilData(AbsensiView formAbsen) throws SQLException{
        absensiModel = new AbsensiModel();
        ResultSet rs =  absensiModel.getAll();
        while(rs.next()){
                String nama = String.valueOf(rs.getString("nama"));
                String id_pertemuan = String.valueOf(rs.getString("id_pertemuan"));
                String keterangan = String.valueOf(rs.getString("keterangan"));
                
                String tabelData[] = {nama,id_pertemuan,keterangan};
                DefaultTableModel tblModel = (DefaultTableModel)formAbsen.getjTblAbsen().getModel();
                
                tblModel.addRow(tabelData);
            }
    }
    
    public void tambahPertemuan(AbsensiView formAbsen) throws SQLException, ParseException{
        String idPertemuan = formAbsen.getjTxtPertemuan().getText();
        Date tanggal = formAbsen.getjTgl().getDate();
        
        
        boolean validasi = absensiModel.validasiId(idPertemuan);
        
        //validasi data
        if(idPertemuan.trim().equals("")){
            JOptionPane.showMessageDialog(formAbsen, "ID Pertemuan Tidak Boleh Kosong");
        }else if(validasi){
            JOptionPane.showMessageDialog(formAbsen, "ID Pertemuan Sudah Digunakan");
        }else if(tanggal == null){
            JOptionPane.showMessageDialog(formAbsen, "Tanggal Tidak Boleh Kosong");
        }else{
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(tanggal);

            absensiModel = new AbsensiModel();

            absensiModel.insertPertemuan(idPertemuan, formattedDate);
            
            JComboBox<String> pertemuan = formAbsen.getjComboPertemuan();
            pertemuan.addItem(idPertemuan);
            

            formAbsen.getjHasilPertemuan().setText(idPertemuan);
            JOptionPane.showMessageDialog(null, "BERHASIL");
        }
        
    }
    
    public void tambahAbsen(AbsensiView formAbsen) throws SQLException{
        String nama = (String) formAbsen.getJcomboNama().getSelectedItem();
        String idPertemuan = formAbsen.getjHasilPertemuan().getText();
        String keterangan = (String) formAbsen.getJcomboKet().getSelectedItem();
        
        absensiModel = new AbsensiModel();
        
        absensiModel.insertAbsen(nama, idPertemuan, keterangan);
        JOptionPane.showMessageDialog(null, "BERHASIL");

        String tabelData[] = {nama, idPertemuan,keterangan};
        DefaultTableModel tblModel = (DefaultTableModel) formAbsen.getjTblAbsen().getModel();
        tblModel.addRow(tabelData);
    }
    
    public void tampilNama(AbsensiView formAbsen) throws SQLException{
        JComboBox<String> Nama = formAbsen.getJcomboNama();
        Nama.removeAllItems();
        String kelasPilih = (String) formAbsen.getjComboKelasPilih().getSelectedItem();
        ResultSet rs = absensiModel.nama(kelasPilih);
        System.out.println(kelasPilih);
            while(rs.next()){
                String nama = rs.getString("nama");
                Nama.addItem(nama);
            }
    }
    
    public void tampilKelas(AbsensiView formAbsen) throws SQLException{
        JComboBox<String> kelasCombo = formAbsen.getjComboKelasPilih();
        ResultSet rs = absensiModel.getClass(formAbsen);
        while(rs.next()){
            String kelas = rs.getString("kelas");
            kelasCombo.addItem(kelas);   
        }
    }
    
    public void tampilKelasWakel(AbsensiView formAbsen) throws SQLException{
        JComboBox<String> kelasCombo = formAbsen.getjComboKelas();
        ResultSet rs = absensiModel.getClass(formAbsen);
        while(rs.next()){
            String kelas = rs.getString("kelas");
            kelasCombo.addItem(kelas);   
        }
    }
    
    public void tampilWakel(AbsensiView formAbsen) throws SQLException{
        String kelasPilih = (String) formAbsen.getjComboKelas().getSelectedItem();
        ResultSet rsk = absensiModel.getWakel(formAbsen,kelasPilih);
        if(rsk.next()){
            formAbsen.getjHasilWakel().setText(rsk.getString("wali_kelas"));
        }
    }
    
    public void tampilPertemuan(AbsensiView formAbsen) throws SQLException{
        JComboBox<String> pertemuan = formAbsen.getjComboPertemuan();
        ResultSet rsk = absensiModel.getPertemuan(formAbsen);
        while(rsk.next()){
            pertemuan.addItem(rsk.getString("id_pertemuan"));
        }
    }
    
    public void tampilDataPerKelas(AbsensiView formAbsen) throws SQLException{
        formAbsen.getjTblAbsen().setModel(new DefaultTableModel(null, new String[]{"NAMA","ID PERTEMUAN","KETERANGAN"}));
        String kelasCari = (String) formAbsen.getjComboKelas().getSelectedItem();
        String pertemuanCari = (String)formAbsen.getjComboPertemuan().getSelectedItem();
        absensiModel = new AbsensiModel();
        ResultSet rs =  absensiModel.getTabelClass(formAbsen,kelasCari,pertemuanCari);
        while(rs.next()){
                String nama = String.valueOf(rs.getString("nama"));
                String idPertemuan = String.valueOf(rs.getString("id_pertemuan"));
                String keterangan = String.valueOf(rs.getString("keterangan"));
                
                
                String tabelData[] = {nama,idPertemuan,keterangan};
                DefaultTableModel tblModel = (DefaultTableModel)formAbsen.getjTblAbsen().getModel();
                
                tblModel.addRow(tabelData);
            }
    }
    
    public boolean validasiNamaSiswa(AbsensiView formAbsen) throws SQLException{
        String nama;
        String pertemuan = formAbsen.getjTxtPertemuan().getText();
        String namaSiswa = (String) formAbsen.getJcomboNama().getSelectedItem();
        ResultSet rs = absensiModel.validasiNama(pertemuan);
        boolean ketemu = false;
        while(rs.next()){
            nama = rs.getString("nama");
            if(nama.equals(namaSiswa)){
                ketemu = true;
            }
        }
        return ketemu;
    }
    
    public void tambahDataAbsen(AbsensiView formAbsen) throws SQLException, ParseException{
        boolean ketemuValidasi;
        ketemuValidasi = this.validasiNamaSiswa(formAbsen);
        if(ketemuValidasi){
            JOptionPane.showMessageDialog(formAbsen, "Nama tersebut sudah di absen");
        }else{
            this.tambahAbsen(formAbsen);
        }
    }
    
    
}
