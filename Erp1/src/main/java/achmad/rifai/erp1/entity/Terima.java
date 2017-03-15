/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

/**
 *
 * @author ai
 */
public class Terima {
    private String kode,jurnal;
    private org.joda.time.DateTime tgl;
    private org.joda.money.Money uang;

    public Terima(String jurnal,org.joda.money.Money uang) {
        tgl=org.joda.time.DateTime.now();
        this.uang=uang;
        this.jurnal=jurnal;
        kode="terima"+jurnal+tgl;
    }

    public Terima(String k,com.mongodb.DB d) throws Exception{
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(k));
        com.mongodb.DBCursor c=d.getCollection("terima").find(w);
        for(com.mongodb.DBObject o:c.toArray(1))
            parsing(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("datane"))));
    }

    public Terima(String json) throws Exception {
        parsing(json);
    }

    private void parsing(String json) throws Exception {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        kode=""+o.get("kode");
        jurnal=""+o.get("jurnal");
        tgl=org.joda.time.DateTime.parse(""+o.get("tgl"));
        uang=org.joda.money.Money.parse(""+o.get("uang"));
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

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("kode", kode);
        o.put("jurnal", jurnal);
        o.put("tgl", tgl);
        o.put("uang", uang);
        return o.toJSONString();
    }
}