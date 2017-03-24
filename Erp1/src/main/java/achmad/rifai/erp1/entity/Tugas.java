/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.time.LocalDate;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Tugas {
    public static Tugas of(Db d, String kode) throws Exception {
        Tugas t=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("tugas").
                where(QueryBuilder.eq("berkas", kode)));
        for(com.datastax.driver.core.Row ro:rs){
            String enc="";
            java.util.List<String>ls=ro.getList("bin", String.class);
            for(String s:ls)enc+=r.decrypt(s);
            t=new Tugas(enc);
        }return t;
    }

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

    public Tugas(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        petugasObject(o.get("l"));
        this.tgl=java.sql.Date.valueOf(""+o.get("tgl"));
        this.no=Integer.parseInt(""+o.get("no"));
        this.pending=Boolean.parseBoolean(""+o.get("pending"));
        this.batal=Boolean.parseBoolean(""+o.get("batal"));
        this.deleted=Boolean.parseBoolean("deleted");
        this.kode=""+o.get("kode");
        this.ket=""+o.get("ket");
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("l", petugasJSON());
        o.put("tgl", ""+tgl);
        o.put("no", ""+no);
        o.put("pending", ""+pending);
        o.put("batal", ""+batal);
        o.put("deleted", ""+deleted);
        o.put("kode", kode);
        o.put("ket", ket);
        return o.toJSONString();
    }

    private void petugasObject(Object get) {
        l=new java.util.LinkedList<>();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Petugas p=new Petugas();
            p.setDiambil(Boolean.parseBoolean("diambil"));
            p.setKaryawan(""+o.get("karyawan"));
            p.setSedang(Boolean.parseBoolean(""+o.get("sedang")));
            p.setTerlaksana(Boolean.parseBoolean(""+o.get("terlaksana")));
            l.add(p);
        }
    }

    private Object petugasJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        l.stream().map((p) -> {
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("diambil", ""+p.isDiambil());
            o.put("karyawan", p.getKaryawan());
            o.put("sedang", ""+p.isSedang());
            o.put("terlaksana", ""+p.isTerlaksana());
            return o;
        }).forEachOrdered((o) -> {
            a.add(o);
        });return a;
    }
}