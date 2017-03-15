/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Suplier {
    private String kode,nama;
    private List<String>alamat,telp;
    private boolean deleted;

    public Suplier() {
    }

    public Suplier(String kode,String nama,List<String>alamat,List<String>telp) {
        this.kode=kode;
        this.alamat=alamat;
        this.telp=telp;
        this.nama=nama;
        deleted=false;
    }

    public Suplier(String j1, String j2, String j3) throws ParseException {
        parsing1(j1);
        parsing2(j2);
        parsing3(j3);
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<String> getAlamat() {
        return alamat;
    }

    public void setAlamat(List<String> alamat) {
        this.alamat = alamat;
    }

    public List<String> getTelp() {
        return telp;
    }

    public void setTelp(List<String> telp) {
        this.telp = telp;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Suplier(String k,com.mongodb.DB d)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(k));
        com.mongodb.DBCursor c=d.getCollection("suplier").find(o);
        for(com.mongodb.DBObject o1:c.toArray(1)){
            parsing1(r.decrypt(""+o1.get(achmad.rifai.erp1.util.Work.MD5("data"))));
            parsing2(r.decrypt(""+o1.get(achmad.rifai.erp1.util.Work.MD5("item"))));
            parsing3(r.decrypt(""+o1.get(achmad.rifai.erp1.util.Work.MD5("telp"))));
        }
    }

    private void parsing1(String j1) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(j1);
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        kode=""+o.get("kode");
        nama=""+o.get("nama");
    }

    private void parsing2(String j2) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(j2);
        alamat=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++)
            alamat.add(""+a.get(x));
    }

    private void parsing3(String j3) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(j3);
        telp=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++)
            telp.add(""+a.get(x));
    }
}