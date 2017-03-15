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
public class Bonusan {
    private java.time.Month bulan;
    private java.time.Year tahun;
    private org.joda.money.Money jumlah;
    private int nomer;

    public java.time.Month getBulan() {
        return bulan;
    }

    public void setBulan(java.time.Month bulan) {
        this.bulan = bulan;
    }

    public java.time.Year getTahun() {
        return tahun;
    }

    public void setTahun(java.time.Year tahun) {
        this.tahun = tahun;
    }

    public org.joda.money.Money getJumlah() {
        return jumlah;
    }

    public void setJumlah(org.joda.money.Money jumlah) {
        this.jumlah = jumlah;
    }

    public int getNomer() {
        return nomer;
    }

    public void setNomer(int nomer) {
        this.nomer = nomer;
    }
}