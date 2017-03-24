/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Tracks {
    public static Tracks of(Db d, String kode)throws Exception{
        Tracks v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(QueryBuilder.select("bin").from("tracks").
                where(QueryBuilder.eq("berkas", kode)))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            v=new Tracks(json);
        }return v;
    }

    private String kode,id;
    private java.time.Month bln;
    private int tahun;
    private java.util.List<Jejak>l;
    private boolean deleted;

    public Tracks(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        kode=""+o.get("kode");
        id=""+o.get("id");
        bln=java.time.Month.valueOf(""+o.get("bln"));
        tahun=Integer.parseInt(""+o.get("tahun"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        jejakObject(o.get("l"));
    }

    public Tracks() {
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("kode", kode);
        o.put("id", id);
        o.put("bln", ""+bln);
        o.put("tahun", ""+tahun);
        o.put("deleted", ""+deleted);
        o.put("l", jejakJSON());
        return o.toJSONString();
    }

    private void jejakObject(Object get) {
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        l=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Jejak j=new Jejak();
            j.setAksi(""+o.get("aksi"));
            j.setDeleted(Boolean.parseBoolean(""+o.get("deleted")));
            j.setPelaku(""+o.get("pelaku"));
            j.setWaktu(org.joda.time.DateTime.parse(""+o.get("waktu")));
            j.setTgl(java.sql.Date.valueOf(""+o.get("tgl")));
            l.add(j);
        }
    }

    private Object jejakJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        l.forEach((j)->{
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("aksi", j.getAksi());
            o.put("pelaku", j.getPelaku());
            o.put("tgl", ""+j.getTgl());
            o.put("waktu", ""+j.getWaktu());
            o.put("deleted", ""+j.isDeleted());
            a.add(o);
        });return a;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.time.Month getBln() {
        return bln;
    }

    public void setBln(java.time.Month bln) {
        this.bln = bln;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public java.util.List<Jejak> getL() {
        return l;
    }

    public void setL(java.util.List<Jejak> l) {
        this.l = l;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}