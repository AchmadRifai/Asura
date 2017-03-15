/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import java.time.LocalDate;
import org.joda.money.CurrencyUnit;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Penjualan {
    private String pelanggan,nota,ket;
    private org.joda.money.Money total;
    private java.sql.Date tgl;
    private java.util.List<ItemJual>items;
    private boolean deleted;

    public Penjualan(String k,com.mongodb.DB d) throws Exception{
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("nota"), r.encrypt(k));
        com.mongodb.DBCursor c=d.getCollection("penjualan").find(w);
        for(com.mongodb.DBObject o:c.toArray(1)){
            parsing1(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))));
            parsing2(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("next"))));
            parsing3(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("item"))));
        }
    }

    public Penjualan(String j1, String j2, String j3) throws ParseException {
        parsing1(j1);
        parsing2(j2);
        parsing3(j3);
    }

    public Penjualan(String pelanggan,String ket) {
        tgl=java.sql.Date.valueOf(LocalDate.now());
        total=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
        this.pelanggan=pelanggan;
        this.ket=ket;
        items=new java.util.LinkedList<>();
        deleted=false;
    }

    public Penjualan() {
    }

    public String getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(String pelanggan) {
        this.pelanggan = pelanggan;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public org.joda.money.Money getTotal() {
        return total;
    }

    public void setTotal(org.joda.money.Money total) {
        this.total = total;
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }

    public java.util.List<ItemJual> getItems() {
        return items;
    }

    public void setItems(java.util.List<ItemJual> items) {
        this.items = items;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private void parsing1(String j1) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(j1);
        ket=""+o.get("ket");
        nota=""+o.get("nota");
        pelanggan=""+o.get("pelanggan");
    }

    private void parsing2(String j2) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(j2);
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        tgl=java.sql.Date.valueOf(""+o.get("tgl"));
        total=org.joda.money.Money.parse(""+o.get("total"));
    }

    private void parsing3(String j3) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(j3);
        items=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            ItemJual i=new ItemJual();
            i.setBarang(""+o.get("barang"));
            i.setJumlah(Integer.parseInt(""+o.get("jumlah")));
            i.setUang(org.joda.money.Money.parse(""+o.get("uang")));
            items.add(i);
        }
    }
}