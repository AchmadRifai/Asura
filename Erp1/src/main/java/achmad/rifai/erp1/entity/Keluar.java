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
public class Keluar {
    public static Keluar of(Db d, String kode)throws Exception{
        Keluar v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject p=new com.mongodb.BasicDBObject();
        p.put("berkas", kode);
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("keluar").find(p);
        while(c.hasNext()){
            com.mongodb.BasicDBList l=(com.mongodb.BasicDBList) c.next().get("keluar");
            String json="";
            for(int x=0;x<l.size();x++)json+=r.decrypt(""+l.get(x));
            v=new Keluar(json);
            break;
        }return v;
    }

    private String kode,jurnal;
    private org.joda.time.DateTime tgl;
    private org.joda.money.Money uang;
    private boolean deleted;

    public Keluar(String json) throws ParseException{
        parsing(json);
    }

    public Keluar() {
    }

    private void parsing(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(json);
        kode=""+o.get("kode");
        jurnal=""+o.get("jurnal");
        tgl=org.joda.time.DateTime.parse(""+o.get("tgl"));
        uang=org.joda.money.Money.parse(""+o.get("uang"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("kode", kode);
        o.put("jurnal", jurnal);
        o.put("tgl", ""+tgl);
        o.put("uang", ""+uang);
        o.put("deleted", ""+deleted);
        return o.toJSONString();
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJurnal() {
        return jurnal;
    }

    public void setJurnal(String jurnal) {
        this.jurnal = jurnal;
    }

    public org.joda.time.DateTime getTgl() {
        return tgl;
    }

    public void setTgl(org.joda.time.DateTime tgl) {
        this.tgl = tgl;
    }

    public org.joda.money.Money getUang() {
        return uang;
    }

    public void setUang(org.joda.money.Money uang) {
        this.uang = uang;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}