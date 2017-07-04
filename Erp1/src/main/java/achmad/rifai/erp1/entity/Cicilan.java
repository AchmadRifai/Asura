/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

/**
 *
 * @author janoko
 */
public class Cicilan {
    private java.sql.Date tgl;
    private org.joda.money.Money byr;
    private String bukti;

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }

    public org.joda.money.Money getByr() {
        return byr;
    }

    public void setByr(org.joda.money.Money byr) {
        this.byr = byr;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }
}