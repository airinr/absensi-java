/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.java.com.sistemAkademikApps.model.LoginModel;
import main.java.com.sistemAkademikApps.view.FormDataSiswaView;
import main.java.com.sistemAkademikApps.view.LoginView;
import main.java.com.sistemAkademikApps.view.MenuView;


/**
 *
 * @author ASUS
 */
public class LoginController {
    private LoginModel loginModel;

    public LoginModel getLoginModel() {
        return loginModel;
    }
    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }
    Connection con;
    public void login(LoginView login) throws SQLException {
        String username = login.getT_username().getText();
        String password = login.getT_password().getText();

        loginModel = new LoginModel();
        ResultSet rs = loginModel.login(login);
        boolean userFound = false;
        
       

        try {
            while (rs.next()) {
                userFound = true;
            }

            if (userFound) {
                MenuView homeFrame = new MenuView();
                homeFrame.setVisible(true);
            } else {
                  if (username.trim().equals("Username") || password.trim().equals("Password")) {
                        if (username.trim().equals("Username")) {
                            JOptionPane.showMessageDialog(null, "Username Tidak Boleh Kosong");
                        }
                        if (password.trim().equals("Password")) {
                            JOptionPane.showMessageDialog(null, "Password Tidak Boleh Kosong");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Username atau Password salah!");
                    }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
