/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import java.time.LocalDate;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Tugas {
    private String ket,kode;
    private java.sql.Date tgl;
    private int no;
    private java.util.List<Petugas>l;
    private boolean batal,deleted,pending;

    public Tugas(String ket,int no,java.util.List<Petugas>l) {
        this.l=l;
        this.ket=ket;
        this.no=no;
        tgl=java.sql.Date.valueOf(LocalDate.now());
        batal=false;
        deleted=false;
        pending=false;
        kode=ket+tgl+no;
    }

    public Tugas(String j1, String j2, String j3) throws ParseException {
        parsing1(j1);
        parsing2(j2);
        parsing3(j3);
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public java.util.List<Petugas> getL() {
        return l;
    }

    public void setL(java.util.List<Petugas> l) {
        this.l = l;
    }

    public boolean isBatal() {
        return batal;
    }

    public void setBatal(boolean batal) {
        this.batal = batal;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public Tugas(String k,com.mongodb.DB d) throws Exception{
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(k));
        com.mongodb.DBCursor c=d.getCollection("tugas").find(w);
        for(com.mongodb.DBObject o:c.toArray(1)){
            parsing1(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))));
            parsing2(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("petugas"))));
            parsing3(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("pilihan"))));
        }
    }

    private void parsing1(String j1) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(j1);
        kode=""+o.get("kode");
        ket=""+o.get("ket");
        no=Integer.parseInt(""+o.get("no"));
        tgl=java.sql.Date.valueOf(""+o.get("tgl"));
    }

    private void parsing2(String j2) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(j2);
        l=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++){
            Petugas pe=new Petugas();
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            pe.setDiambil(Boolean.parseBoolean(""+o.get("diambil")));
            pe.setKaryawan(""+o.get("karyawan"));
            pe.setSedang(Boolean.parseBoolean(""+o.get("sedang")));
            pe.setTerlaksana(Boolean.parseBoolean(""+o.get("terlaksana")));
            l.add(pe);
        }
    }

    private void parsing3(String j3) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(j3);
        batal=Boolean.parseBoolean(""+o.get("batal"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        pending=Boolean.parseBoolean(""+o.get("pending"));
    }
}