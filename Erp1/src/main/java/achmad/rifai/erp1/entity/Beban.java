/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import com.mongodb.DB;
import java.util.Comparator;
import org.json.simple.parser.ParseException;

/**
 *
 * @author janoko
 */
public class Beban {
    private static Comparator<? super Cicilan> sorter() {
        return (Cicilan o1, Cicilan o2) -> {
            int x;
            if(o1.getTgl().after(o2.getTgl()))x=1;
            else if(o1.getTgl().before(o2.getTgl()))x=-1;
            else x=0;
            return x;
        };
    }

    public static Beban of(DB d, String kode) throws Exception{
        Beban b=null;
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", kode);
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollectionFromString("beban").find(w);
        if(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList l=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<l.size();x++)json+=r.decrypt(""+l.get(x));
            b=new Beban(json);
        }return b;
    }

    private String bukti,kode,kepada;
    private java.util.List<Cicilan>cicil;
    private org.joda.money.Money jumlah;
    private float bunga;
    private org.joda.time.DateTime tgl;
    private boolean lunas,deleted;

    public Beban(){}

    public Beban(String json) throws ParseException{
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        bukti=""+o.get("bukti");
        kode=""+o.get("kode");
        kepada=""+o.get("kepada");
        jumlah=org.joda.money.Money.parse(""+o.get("jumlah"));
        bunga=Float.parseFloat(""+o.get("bunga"));
        tgl=org.joda.time.DateTime.parse(""+o.get("tgl"));
        lunas=Boolean.parseBoolean(""+o.get("lunas"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        nextParsing(o.get("cicil"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("bukti", bukti);
        o.put("kode", kode);
        o.put("kepada", kepada);
        o.put("jumlah", ""+jumlah);
        o.put("bunga", ""+bunga);
        o.put("tgl", ""+tgl);
        o.put("lunas", ""+lunas);
        o.put("deleted", ""+deleted);
        o.put("cicil", genCicil());
        return o.toJSONString();
    }

    private void nextParsing(Object get) {
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        cicil=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++){
            Cicilan c=new Cicilan();
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            c.setBukti(""+o.get("bukti"));
            c.setByr(org.joda.money.Money.parse(""+o.get("byr")));
            c.setTgl(java.sql.Date.valueOf(""+o.get("tgl")));
            cicil.add(c);
        }cicil.sort(Beban.sorter());
    }

    private Object genCicil() {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        cicil.forEach((c)->{
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("bukti", c.getBukti());
            o.put("byr", ""+c.getByr());
            o.put("tgl", ""+c.getTgl());
            a.add(o);
        });return a;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKepada() {
        return kepada;
    }

    public void setKepada(String kepada) {
        this.kepada = kepada;
    }

    public java.util.List<Cicilan> getCicil() {
        return cicil;
    }

    public void setCicil(java.util.List<Cicilan> cicil) {
        this.cicil = cicil;
    }

    public org.joda.money.Money getJumlah() {
        return jumlah;
    }

    public void setJumlah(org.joda.money.Money jumlah) {
        this.jumlah = jumlah;
    }

    public float getBunga() {
        return bunga;
    }

    public void setBunga(float bunga) {
        this.bunga = bunga;
    }

    public org.joda.time.DateTime getTgl() {
        return tgl;
    }

    public void setTgl(org.joda.time.DateTime tgl) {
        this.tgl = tgl;
    }

    public boolean isLunas() {
        return lunas;
    }

    public void setLunas(boolean lunas) {
        this.lunas = lunas;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}