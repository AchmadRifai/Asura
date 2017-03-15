/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Absen {
    private String s;
    private java.sql.Date tgl;
    private Jenise l;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }

    public Jenise getL() {
        return l;
    }

    public void setL(Jenise l) {
        this.l = l;
    }

    private void parsing(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(json);
        s=""+o.get("s");
        tgl=java.sql.Date.valueOf(""+o.get("tgl"));
        l=Jenise.valueOf(""+o.get("l"));
    }

    public enum Jenise {
        MASUK,ALPHA,SAKIT,IZIN
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("s", s);
        o.put("tgl", tgl);
        o.put("jenis", l);
        return o.toJSONString();
    }

    public Absen(String json) throws ParseException {
        parsing(json);
    }

    public Absen() {
    }
}