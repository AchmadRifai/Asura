/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Suplier {
    public static Suplier of(Db d, String kode) throws Exception{
        Suplier v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject p=new com.mongodb.BasicDBObject();
        p.put("berkas", kode);
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("suplier").find(p);
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList l=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<l.size();x++)json+=r.decrypt(""+l.get(x));
            v=new Suplier(json);
            break;
        }return v;
    }

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

    public Suplier(String st) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(st);
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        kode=""+o.get("kode");
        nama=""+o.get("nama");
        alamatObject(o.get("alamat"));
        telpObject(o.get("telp"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("deleted", ""+deleted);
        o.put("kode", kode);
        o.put("nama", nama);
        o.put("alamat", alamatJSON());
        o.put("telp", telpJSON());
        return o.toJSONString();
    }

    private Object alamatJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(String s:alamat)a.add(s);
        return a;
    }

    private Object telpJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(String s:telp)a.add(s);
        return a;
    }

    private void telpObject(Object get){
        telp=new java.util.LinkedList<>();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        for(int x=0;x<a.size();x++)telp.add(""+a.get(x));
    }

    private void alamatObject(Object get){
        alamat=new java.util.LinkedList<>();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        for(int x=0;x<a.size();x++)alamat.add(""+a.get(x));
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
}