/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps.model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Koneksi {
    static Connection con;
    
    public static Connection getConnection(){
        if(con == null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("db_data");
            data.setUser("root");
            data.setPassword("");
            
            try{
                con = data.getConnection();
                System.out.println("koneksi berhasil");
            }catch(SQLException e){
                System.out.println("tidak konek");
            }
        }
        return con;
    }
}
