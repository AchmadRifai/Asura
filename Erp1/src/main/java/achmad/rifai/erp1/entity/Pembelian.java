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
public class Pembelian {
    private String struk,suplier;
    private org.joda.money.Money harga;
    private java.sql.Date tgl;
    private java.util.List<ItemBeli>items;
    private boolean deleted;

    public Pembelian(String k,String j,com.mongodb.DB d) throws Exception{
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("struk"), r.encrypt(k));
        w.put(achmad.rifai.erp1.util.Work.MD5("suplier"), r.encrypt(j));
        com.mongodb.DBCursor c=d.getCollection("pembelian").find(w);
        for(com.mongodb.DBObject o:c.toArray(1)){
            parsing1(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))));
            parsing2(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("item"))));
        }
    }

    public Pembelian(String s1, String s2) throws ParseException {
        parsing1(s1);
        parsing2(s2);
    }

    public Pembelian() {
    }

    public String getStruk() {
        return struk;
    }

    public void setStruk(String struk) {
        this.struk = struk;
    }

    public String getSuplier() {
        return suplier;
    }

    public void setSuplier(String suplier) {
        this.suplier = suplier;
    }

    public org.joda.money.Money getHarga() {
        return harga;
    }

    public void setHarga(org.joda.money.Money harga) {
        this.harga = harga;
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }

    public java.util.List<ItemBeli> getItems() {
        return items;
    }

    public void setItems(java.util.List<ItemBeli> items) {
        this.items = items;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private void parsing1(String s1) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(s1);
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        tgl=java.sql.Date.valueOf(""+o.get("tgl"));
        harga=org.joda.money.Money.parse(""+o.get("harga"));
        suplier=""+o.get("suplier");
        struk=""+o.get("struk");
    }

    private void parsing2(String s2) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s2);
        items=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++){
            ItemBeli i=new ItemBeli();
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            i.setBarang(""+o.get("barang"));
            i.setHarga(org.joda.money.Money.parse(""+o.get("harga")));
            i.setJumlah(Integer.parseInt(""+o.get("jumlah")));
            i.setSatuan(""+o.get("satuan"));
            items.add(i);
        }
    }
}