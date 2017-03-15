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
public class Pelanggan {
    private String kode,nama;
    private java.util.List<String>alamat,telp;
    private boolean deleted,blocked;

    public Pelanggan(String s1, String s2, String s3) throws ParseException {
        parsing1(s1);
        parsing2(s2);
        parsing3(s3);
    }

    public Pelanggan(String nama,String kode,java.util.List<String>alamat) {
        telp=new java.util.LinkedList<>();
        this.alamat=alamat;
        this.kode=kode;
        this.nama=nama;
        deleted=false;
        blocked=false;
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

    public java.util.List<String> getAlamat() {
        return alamat;
    }

    public void setAlamat(java.util.List<String> alamat) {
        this.alamat = alamat;
    }

    public java.util.List<String> getTelp() {
        return telp;
    }

    public void setTelp(java.util.List<String> telp) {
        this.telp = telp;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Pelanggan(String k,com.mongodb.DB d)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(k));
        com.mongodb.DBCursor c=d.getCollection("pelanggan").find(o);
        for(com.mongodb.DBObject o1:c.toArray(1)){
            parsing1(r.decrypt(""+o1.get(achmad.rifai.erp1.util.Work.MD5("data"))));
            parsing2(r.decrypt(""+o1.get(achmad.rifai.erp1.util.Work.MD5("alamat"))));
            parsing3(r.decrypt(""+o1.get(achmad.rifai.erp1.util.Work.MD5("telp"))));
        }
    }

    private void parsing1(String s1) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(s1);
        blocked=Boolean.parseBoolean(""+o.get("blocked"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        kode=""+o.get("kode");
        nama=""+o.get("nama");
    }

    private void parsing2(String s2) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s2);
        alamat=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++)
            alamat.add(""+a.get(x));
    }

    private void parsing3(String s3) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s3);
        telp=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++)
            telp.add(""+a.get(x));
    }
}