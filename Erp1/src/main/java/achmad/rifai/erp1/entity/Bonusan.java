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
    private org.joda.money.Money jumlah;
    private int nomer;

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