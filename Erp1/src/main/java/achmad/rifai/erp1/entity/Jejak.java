/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import java.time.LocalDate;
import org.joda.time.DateTime;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Jejak {
    private String aksi,pelaku;
    private org.joda.time.DateTime waktu;
    private java.sql.Date tgl;

    public Jejak(String aksi, String pelaku) {
        this.aksi = aksi;
        this.pelaku = pelaku;
        waktu=org.joda.time.DateTime.now();
        tgl=java.sql.Date.valueOf(LocalDate.now());
    }

    public Jejak() {}

    public Jejak(String json) throws ParseException{
        parsing(json);
    }

    public String getAksi() {
        return aksi;
    }

    public void setAksi(String aksi) {
        this.aksi = aksi;
    }

    public String getPelaku() {
        return pelaku;
    }

    public void setPelaku(String pelaku) {
        this.pelaku = pelaku;
    }

    public DateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(DateTime waktu) {
        this.waktu = waktu;
    }

    private void parsing(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(json);
        pelaku=""+o.get("pelaku");
        aksi=""+o.get("aksi");
        waktu=org.joda.time.DateTime.parse(""+o.get("waktu"));
        tgl=java.sql.Date.valueOf(""+o.get("tgl"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("pelaku", pelaku);
        o.put("aksi", aksi);
        o.put("waktu", waktu.toString());
        o.put("tgl", tgl);
        return o.toJSONString();
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }
}