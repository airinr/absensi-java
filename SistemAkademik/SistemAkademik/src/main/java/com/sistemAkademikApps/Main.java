/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.sistemAkademikApps.model.Koneksi;
import main.java.com.sistemAkademikApps.view.AbsensiView;
import main.java.com.sistemAkademikApps.view.FormDataSiswaView;
import main.java.com.sistemAkademikApps.view.LoginView;
import main.java.com.sistemAkademikApps.view.MenuView;

/**
 *
 * @author HP
 */
public class Main {
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 new LoginView().setVisible(true);
//                 new MenuView().setVisible(true);
            }
        });
        Koneksi.getConnection();
    }
}
