/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps.model;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javafx.css.StyleOrigin.USER;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import main.java.com.sistemAkademikApps.view.FormDataSiswaView;


/**
 *
 * @author HP
 */
public class SiswaModel {
    private String nisn;
    private String nama;
    private String ttl;
    private String alamat;
    private String jk;
    private String kelas;

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
    
    Connection con;
    
     public ResultSet getAll(){
        try{
            con = Koneksi.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from data_siswa ORDER BY kelas ASC;";
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
            
        }catch(SQLException e){
            System.out.println("error"+e);
            return null;
        }   
    }
     
    public ResultSet kls(){
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
    
    
    public void insert(String nisn, String nama,String tmpt, String ttl, String jk, String alamat, String kelas) throws SQLException, ParseException{
        con = Koneksi.getConnection();
        String sql = "INSERT INTO data_siswa (nisn, nama,tempat_lahir, ttl, jenis_kelamin, alamat, kelas) VALUES (?, ?,?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(ttl);
        String formattedDate = sdf.format(date);

        pstmt.setString(1, nisn);
        pstmt.setString(2, nama);
        pstmt.setString(3, tmpt);
        pstmt.setString(4, formattedDate);
        pstmt.setString(5, jk);
        pstmt.setString(6, alamat);
        pstmt.setString(7, kelas);

        pstmt.executeUpdate();
    }
    
    public boolean validasiNisn(String nisn) throws SQLException{
        String query = "SELECT COUNT(*) FROM data_siswa WHERE nisn = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, nisn);
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
    
     public void update(String nisn, String nama,String tempat, String ttl, String jk, String alamat, String kelas) throws SQLException{
        con = Koneksi.getConnection();
        String sql = "UPDATE data_siswa SET nisn=?, nama=?,tempat_lahir=?, ttl=?, jenis_kelamin=?, alamat=?, kelas=? WHERE nisn=?";
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, nisn);
        pstmt.setString(2, nama);
        pstmt.setString(3, tempat);
        pstmt.setString(4, ttl);
        pstmt.setString(5, jk);
        pstmt.setString(6, alamat);
        pstmt.setString(7, kelas);
        pstmt.setString(8, nisn);

        pstmt.executeUpdate();
    }
     
    public ResultSet getClass(String kelas){
        try{
            con = Koneksi.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from data_siswa where kelas='"+kelas+"'";
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
            
        }catch(SQLException e){
            System.out.println("error"+e);
            return null;
        }   
    }
    
     public void delete(FormDataSiswaView siswa, String nisn) throws SQLException{
        con = Koneksi.getConnection();
        String sql = "DELETE FROM data_siswa where nisn='"+nisn+"'";
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.executeUpdate();
    }
    
    
   
}
