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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import main.java.com.sistemAkademikApps.view.AbsensiView;
import main.java.com.sistemAkademikApps.view.FormDataSiswaView;

/**
 *
 * @author HP
 */
public class AbsensiModel {
    private String id_absen;
    private String nisn;
    private String id_pertemuan;
    private String keterangan;

    public String getId_absen() {
        return id_absen;
    }

    public void setId_absen(String id_absen) {
        this.id_absen = id_absen;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getId_pertemuan() {
        return id_pertemuan;
    }

    public void setId_pertemuan(String id_pertemuan) {
        this.id_pertemuan = id_pertemuan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
   Connection con;
    
   public ResultSet getAll(){
        try{
            con = Koneksi.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from absen_siswa";
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
            
        }catch(SQLException e){
            System.out.println("error"+e);
            return null;
        }   
    }
   
   public boolean validasiId(String idPertemuan) throws SQLException{
        String query = "SELECT COUNT(*) FROM pertemuan WHERE id_pertemuan = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, idPertemuan);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
           
    }
   
   public ResultSet nama(String kelas){
        try{
            con = Koneksi.getConnection();
            String query = "select nama from data_siswa where kelas=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, kelas);
            ResultSet rs = pstmt.executeQuery();
  
            return rs;
        } catch (SQLException e) {
            System.out.println("error"+e);
        }
        return null;
    }
   
    public ResultSet kelas(AbsensiView absen){
        try{
            con = Koneksi.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select kelas from data_kelas");
            
            return rs;
        } catch (SQLException e) {
            System.out.println("error"+e);
        }
        return null;
    }
   
   public void insertPertemuan(String idPertemuan, String tanggal) throws SQLException, ParseException{
        con = Koneksi.getConnection();
        String sql = "INSERT INTO pertemuan (id_pertemuan,tanggal) VALUES (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(tanggal);
        String formattedDate = sdf.format(date);


        pstmt.setString(1, idPertemuan);
        pstmt.setString(2, formattedDate);
        
        pstmt.executeUpdate();
   }
   
   public void insertAbsen(String nama, String idPertemuan, String keterangan) throws SQLException{
        con = Koneksi.getConnection();
        String sql = "INSERT INTO absen_siswa (nama, id_pertemuan,keterangan) VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        pstmt.setString(1, nama);
        pstmt.setString(2, idPertemuan);
        pstmt.setString(3, keterangan);
        
        pstmt.executeUpdate();
   }
   
    public ResultSet getClass(AbsensiView formAbsen){
        try{
            con = Koneksi.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select kelas from data_kelas");
        
            return rs;
        } catch (SQLException e) {
            System.out.println("error"+e);
        }
        return null;
    }
    
    public ResultSet getPertemuan(AbsensiView formAbsen){
        try{
            con = Koneksi.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id_pertemuan from pertemuan");
        
            return rs;
        } catch (SQLException e) {
            System.out.println("error"+e);
        }
        return null;
    }
    
    public ResultSet getWakel(AbsensiView formAbsen, String kelas){
        ResultSet rs=null;
        try{
            con = Koneksi.getConnection();
            String query = "select wali_kelas from data_kelas where kelas=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, kelas);
            rs = pstmt.executeQuery();
            
            return rs;
        } catch (SQLException e) {
            System.out.println("error"+e);
        }
        return null;
    }
    
     public ResultSet getTabelClass(AbsensiView formAbsen, String kelas, String pertemuan){
        try{
            con = Koneksi.getConnection();
            Statement st = con.createStatement();
            String sql = "select absen_siswa.* from absen_siswa,data_siswa where"
                    + " data_siswa.nama = absen_siswa.nama and data_siswa.kelas='"+kelas+"' and absen_siswa.id_pertemuan='"+pertemuan+"'";
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
            
        }catch(SQLException e){
            System.out.println("error"+e);
            return null;
        }   
    }
    
    public ResultSet validasiNama(String pertemuan){
        try{
            con = Koneksi.getConnection();
            Statement st = con.createStatement();
            String sql = "select nama from absen_siswa where id_pertemuan='"+pertemuan+"'";
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
            
        }catch(SQLException e){
            System.out.println("error"+e);
            return null;
        }   
    }
    
    public void updateAbsen(String keterangan) throws SQLException{
        con = Koneksi.getConnection();
        String sql = "UPDATE absen_siswa SET keterangan=? WHERE id_absen=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
       
        pstmt.setString(1, keterangan);
 
        pstmt.executeUpdate();
    }
   
    
}
