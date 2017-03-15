/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

/**
 *
 * @author ai
 */
public class Petugas {
    private String karyawan;
    private boolean terlaksana,diambil,sedang;

    public String getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(String karyawan) {
        this.karyawan = karyawan;
    }

    public boolean isTerlaksana() {
        return terlaksana;
    }

    public void setTerlaksana(boolean terlaksana) {
        this.terlaksana = terlaksana;
    }

    public boolean isDiambil() {
        return diambil;
    }

    public void setDiambil(boolean diambil) {
        this.diambil = diambil;
    }

    public boolean isSedang() {
        return sedang;
    }

    public void setSedang(boolean sedang) {
        this.sedang = sedang;
    }
}