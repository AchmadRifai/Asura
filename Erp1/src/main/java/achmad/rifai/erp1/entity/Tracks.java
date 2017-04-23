/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import java.util.Comparator;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Tracks {
    public static Tracks of(Db d, String kode)throws Exception{
        Tracks v=null;
            achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject p=new com.mongodb.BasicDBObject();
        p.put("berkas", kode);
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("tracks").find(p);
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList l=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<l.size();x++)json+=r.decrypt(""+l.get(x));
            v=new Tracks(json);
            break;
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
        tahun=Integer.parseInt(""+o.get("thn"));
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
        o.put("thn", ""+tahun);
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
            j.setWaktu(org.joda.time.DateTime.parse(""+o.get("waktu")));
            l.add(j);
        }l.sort(sorter());
    }

    private Object jejakJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        l.forEach((j)->{
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("aksi", j.getAksi());
            o.put("waktu", ""+j.getWaktu());
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

    private Comparator<? super Jejak> sorter() {
        return (Jejak o1, Jejak o2) -> {
            int x;
            if(o1.getWaktu().isAfter(o2.getWaktu()))x=-1;
            else if(o1.getWaktu().isBefore(o2.getWaktu()))x=1;
            else x=0;
            return x;
        };
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
}