/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Pelanggan {
    public static Pelanggan of(Db d, String kode)throws Exception{
        Pelanggan v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject p=new com.mongodb.BasicDBObject();
        p.put("berkas", kode);
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("pelanggan").find(p);
        while(c.hasNext()){
            com.mongodb.BasicDBList l=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<l.size();x++)json+=r.decrypt(""+l.get(x));
            v=new Pelanggan(json);
            break;
        }return v;
    }

    private String kode,nama;
    private java.util.List<String>alamat,telp;
    private boolean deleted,blocked;

    public Pelanggan(String nama,String kode,java.util.List<String>alamat) {
        telp=new java.util.LinkedList<>();
        this.alamat=alamat;
        this.kode=kode;
        this.nama=nama;
        deleted=false;
        blocked=false;
    }

    public Pelanggan(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        kode=""+o.get("kode");
        nama=""+o.get("nama");
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        blocked=Boolean.parseBoolean(""+o.get("blocked"));
        alamatObject(o.get("alamat"));
        telpObject(o.get("telp"));
    }

    public Pelanggan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    private void alamatObject(Object get) {
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        alamat=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++)alamat.add(""+a.get(x));
    }

    private void telpObject(Object get) {
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        telp=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++)telp.add(""+a.get(x));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("kode", kode);
        o.put("nama", nama);
        o.put("deleted", ""+deleted);
        o.put("blocked", ""+blocked);
        o.put("alamat", alamatJSON());
        o.put("telp", telpJSON());
        return o.toJSONString();
    }

    private Object alamatJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        alamat.forEach((s) -> {
            a.add(s);
        });
        return a;
    }

    private Object telpJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        telp.forEach((s) -> {
            a.add(s);
        });
        return a;
    }
}