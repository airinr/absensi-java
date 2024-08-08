/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import main.java.com.sistemAkademikApps.view.FormDataKelasView;

/**
 *
 * @author PC-LENOVO
 */
public class KelasModel {
    private String kelas;
    private String wali_kelas;
    private String nip_Wali_kelas;

    public String getkelas() {
        return kelas;
    }

    public void setkelas(String kelas) {
        this.kelas = kelas;
    }

    public String getWali_kelas() {
        return wali_kelas;
    }

    public void setWali_kelas(String walikelas) {
        this.wali_kelas = walikelas;
    }

    public String getNip_Wali_kelas() {
        return nip_Wali_kelas;
    }

    public void setNipWalikelas(String nipWalikelas) {
        this.nip_Wali_kelas = nip_Wali_kelas;
    }
    
    
    
    Connection con;
    public ResultSet getAll(FormDataKelasView formkelas){
        try{
            con = Koneksi.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from data_kelas;";
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
           
        }catch(SQLException e){
            System.out.println("error"+e);
        }   
        return null;
    }
        
    public void update(String kelas, String nama, String nip) throws SQLException{
        con = Koneksi.getConnection();
        String sql = "UPDATE data_kelas SET kelas=?, wali_kelas=?, nip_wali_kelas=? WHERE nip_wali_kelas=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
       
        pstmt.setString(1, kelas);
        pstmt.setString(2, nama);
        pstmt.setString(3, nip);
        pstmt.setString(4, nip);
        pstmt.executeUpdate();
    }
    
    public void delete(FormDataKelasView formKelas, String nip) throws SQLException{
        con = Koneksi.getConnection();
        String sql = "DELETE FROM data_kelas where nip_wali_kelas='"+nip+"'";
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        pstmt.executeUpdate();
    }
    
    public ResultSet validasiNip(String nip){
        try{
            con = Koneksi.getConnection();
            Statement st = con.createStatement();
            String sql = "select nip_wali_kelas from data_kelas where nip_wali_kelas='"+nip+"'";
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
            
        }catch(SQLException e){
            System.out.println("error"+e);
            return null;
        }   
    }
    
    public ResultSet validasiKelas(String kelas){
        try{
            con = Koneksi.getConnection();
            Statement st = con.createStatement();
            String sql = "select kelas from data_kelas where kelas='"+kelas+"'";
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
            
        }catch(SQLException e){
            System.out.println("error"+e);
            return null;
        }   
    }
}
