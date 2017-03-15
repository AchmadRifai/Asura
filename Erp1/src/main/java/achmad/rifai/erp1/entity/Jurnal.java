/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import java.sql.Date;
import org.joda.money.Money;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Jurnal {
    private String kode,ket;
    private int no;
    private Date tgl;
    private Money debit,kredit;
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

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public Money getDebit() {
        return debit;
    }

    public void setDebit(Money debit) {
        this.debit = debit;
    }

    public Money getKredit() {
        return kredit;
    }

    public void setKredit(Money kredit) {
        this.kredit = kredit;
    }

    public Jurnal(String k,com.mongodb.DB d) throws Exception{
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(k));
        com.mongodb.DBCursor c=d.getCollection("jurnal").find(w);
        for(com.mongodb.DBObject o:c.toArray(1))
            parsing(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("datane"))));
    }

    public Jurnal(String json) throws ParseException {
        parsing(json);
    }

    private void parsing(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(json);
        kode=""+o.get("kode");
        ket=""+o.get("ket");
        no=Integer.parseInt(""+o.get("no"));
        tgl=Date.valueOf(""+o.get("tgl"));
        debit=Money.parse(""+o.get("debit"));
        kredit=Money.parse(""+o.get("kredit"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("kode", kode);
        o.put("ket", ket);
        o.put("no", no);
        o.put("tgl", tgl);
        o.put("debit", debit);
        o.put("kredit", kredit);
        o.put("deleted", deleted);
        return o.toJSONString();
    }

    public Jurnal() {
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}