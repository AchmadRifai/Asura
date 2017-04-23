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
public class Ledger {
    public static Ledger of(Db d, String kode)throws Exception{
        Ledger v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject p=new com.mongodb.BasicDBObject();
        p.put("berkas", kode);
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("ledger").find(p);
        while(c.hasNext()){
            com.mongodb.BasicDBList l=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<l.size();x++)json+=r.decrypt(""+l.get(x));
            v=new Ledger(json);
            break;
        }return v;
    }

    private String kode,ket;
    private int no;
    private java.sql.Date tgl;
    private org.joda.money.Money debit,kredit;
    private boolean deleted;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }

    public org.joda.money.Money getDebit() {
        return debit;
    }

    public void setDebit(org.joda.money.Money debit) {
        this.debit = debit;
    }

    public org.joda.money.Money getKredit() {
        return kredit;
    }

    public void setKredit(org.joda.money.Money kredit) {
        this.kredit = kredit;
    }

    public Ledger(String json) throws ParseException {
        parsing(json);
    }

    private void parsing(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(json);
        kode=""+o.get("kode");
        ket=""+o.get("ket");
        no=Integer.parseInt(""+o.get("no"));
        tgl=java.sql.Date.valueOf(""+o.get("tgl"));
        debit=org.joda.money.Money.parse(""+o.get("debit"));
        kredit=org.joda.money.Money.parse(""+o.get("kredit"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("kode", kode);
        o.put("ket", ket);
        o.put("no", ""+no);
        o.put("tgl", ""+tgl);
        o.put("debit", ""+debit);
        o.put("kredit", ""+kredit);
        o.put("deleted", ""+deleted);
        return o.toJSONString();
    }

    public Ledger() {
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}