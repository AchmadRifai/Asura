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
public class Pesan {
    private String pengirim,pesan,kode;
    private org.joda.time.DateTime waktu;
    private boolean deleted;
    private java.util.List<Penerima>ke;

    public Pesan(String k,com.mongodb.DB d) throws Exception{
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(k));
        com.mongodb.DBCursor c=d.getCollection("pesan").find(w);
        for(com.mongodb.DBObject o:c.toArray(1)){
            parsing1(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))));
            parsing2(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("penerima"))));
        }
    }

    public Pesan(String j1, String j2) throws ParseException {
        parsing1(j1);
        parsing2(j2);
    }

    public Pesan(String pesan,String pengirim,java.util.List<Penerima>l) {
        waktu=org.joda.time.DateTime.now();
        ke=l;
        this.pengirim=pengirim;
        this.pesan=pesan;
        deleted=false;
        kode=pengirim+waktu;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public org.joda.time.DateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(org.joda.time.DateTime waktu) {
        this.waktu = waktu;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public java.util.List<Penerima> getKe() {
        return ke;
    }

    public void setKe(java.util.List<Penerima> ke) {
        this.ke = ke;
    }

    private void parsing1(String j1) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(j1);
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        waktu=org.joda.time.DateTime.parse(""+o.get("waktu"));
        kode=""+o.get("kode");
        pengirim=""+o.get("pengirim");
        pesan=""+o.get("pesan");
    }

    private void parsing2(String j2) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(j2);
        ke=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Penerima pe=new Penerima();
            pe.setAkun(""+o.get("akun"));
            pe.setTerbaca(Boolean.parseBoolean(""+o.get("terbaca")));
            ke.add(pe);
        }
    }
}