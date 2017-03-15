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
public class ItemJual {
    private String barang;
    private int jumlah;
    private org.joda.money.Money uang;

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public org.joda.money.Money getUang() {
        return uang;
    }

    public void setUang(org.joda.money.Money uang) {
        this.uang = uang;
    }
}