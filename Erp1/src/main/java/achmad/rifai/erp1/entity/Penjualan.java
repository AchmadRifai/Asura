/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import java.time.LocalDate;
import org.joda.money.CurrencyUnit;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Penjualan {
    public static Penjualan of(Db d, String nota)throws Exception{
        Penjualan v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject p=new com.mongodb.BasicDBObject();
        p.put("berkas", nota);
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("penjualan").find(p);
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList l=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<l.size();x++)json+=r.decrypt(""+l.get(x));
            v=new Penjualan(json);
            break;
        }return v;
    }

    private String pelanggan,nota,ket;
    private org.joda.money.Money total,terbayar;
    private java.sql.Date tgl;
    private java.util.List<ItemJual>items;
    private boolean deleted;

    public Penjualan(String pelanggan,String ket) {
        tgl=java.sql.Date.valueOf(LocalDate.now());
        total=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
        terbayar=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
        this.pelanggan=pelanggan;
        this.ket=ket;
        items=new java.util.LinkedList<>();
        deleted=false;
        nota=pelanggan+org.joda.time.DateTime.now();
    }

    public Penjualan() {
    }

    public Penjualan(String s) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(s);
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        tgl=java.sql.Date.valueOf(""+o.get("tgl"));
        total=org.joda.money.Money.parse(""+o.get("total"));
        pelanggan=""+o.get("pelanggan");
        ket=""+o.get("ket");
        nota=""+o.get("nota");
        terbayar=org.joda.money.Money.parse(""+o.get("terbayar"));
        itemsObject(o.get("items"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("deleted", ""+deleted);
        o.put("tgl", ""+tgl);
        o.put("total", ""+total);
        o.put("pelanggan", pelanggan);
        o.put("ket", ket);
        o.put("nota", nota);
        o.put("terbayar", ""+terbayar);
        o.put("items", itemsJSON());
        return o.toJSONString();
    }

    private void itemsObject(Object get){
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        items=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++){
            ItemJual i=new ItemJual();
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            i.setBarang(""+o.get("barang"));
            i.setJumlah(Integer.parseInt(""+o.get("jumlah")));
            i.setUang(org.joda.money.Money.parse(""+o.get("uang")));
            items.add(i);
        }
    }

    private Object itemsJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        items.stream().map((i) -> {
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("barang", i.getBarang());
            o.put("jumlah", ""+i.getJumlah());
            o.put("uang", ""+i.getUang());
            return o;
        }).forEachOrdered((o) -> {
            a.add(o);
        });return a;
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
        total=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
        items.forEach((i) -> {
            total=total.plus(i.getUang().multipliedBy(i.getJumlah()));
        });
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public org.joda.money.Money getTerbayar() {
        return terbayar;
    }

    public void setTerbayar(org.joda.money.Money terbayar) {
        this.terbayar = terbayar;
    }
}