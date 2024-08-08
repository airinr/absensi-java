/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.sistemAkademikApps.controller;

import java.sql.Connection;
import java.sql.SQLException;
import main.java.com.sistemAkademikApps.model.SiswaModel;
import main.java.com.sistemAkademikApps.view.AboutUs;
import main.java.com.sistemAkademikApps.view.AbsensiView;
import main.java.com.sistemAkademikApps.view.FormDataKelasView;
import main.java.com.sistemAkademikApps.view.FormDataSiswaView;
import main.java.com.sistemAkademikApps.view.KelasView;
import main.java.com.sistemAkademikApps.view.SiswaView;

/**
 *
 * @author HP
 */
public class MenuController {
    public SiswaModel siswaModel;
    
    public SiswaModel getLoginModel() {
        return siswaModel;
    }
    public void setSiswaModel(SiswaModel siswaModel) {
        this.siswaModel = siswaModel;
    }
    Connection con;
    
    public void tampilMenuForm() throws SQLException{
        FormDataSiswaView formSiswa = new FormDataSiswaView();
        formSiswa.setVisible(true);
    }
    public void tampilMenuAbsen() throws SQLException{
        AbsensiView absen = new AbsensiView();
        absen.setVisible(true);
    }
    
    public void tampilMenuSiswa() throws SQLException{
         SiswaView siswa = new SiswaView();
         siswa.setVisible(true);
    }
     
    public void tampilMenuKelas() throws SQLException{
         KelasView kelas = new KelasView();
         kelas.setVisible(true);
    }
     
    public void tampilFormSiswa(){
        
    }
    
    public void tampilFormKelas() throws SQLException{
        FormDataKelasView formKelas = new FormDataKelasView();
        formKelas.setVisible(true);
    }
    
     public void tampilMenuAbout() throws SQLException{
         AboutUs tentang = new AboutUs();
         tentang.setVisible(true);
    }
    
    
}
