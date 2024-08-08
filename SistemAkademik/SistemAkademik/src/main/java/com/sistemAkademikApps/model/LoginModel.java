/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.java.com.sistemAkademikApps.view.LoginView;

/**
 *
 * @author ASUS
 */
public class LoginModel {
    private String username;
    private String password;
    private String nisn="";
    private String user="";

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getNisn() {
        return nisn;
    }
    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    Connection con;
    
    public ResultSet login(LoginView login) throws SQLException{
        //String username,password,nisn=null,user=null;
        try{
            username = login.getT_username().getText();
            password = login.getT_password().getText();
            con = Koneksi.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from data_kelas where wali_kelas='"+username+"' and nip_wali_kelas='"+password+"';";
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
            
        }catch(SQLException e){
            System.out.println("error"+e);
        }
        return null;
        
    }
}
