/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import java.sql.Date;
import org.joda.money.CurrencyUnit;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Pembelian {
    public static Pembelian of(Db d, String struk, String suplier, Date tgl)throws Exception{
        Pembelian v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject p=new com.mongodb.BasicDBObject();
        p.put("berkas1", struk);
        p.put("berkas2", suplier);
        p.put("berkas3", ""+tgl);
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("pembelian").find(p);
        while(c.hasNext()){
            com.mongodb.BasicDBList l=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<l.size();x++)json+=r.decrypt(""+l.get(x));
            v=new Pembelian(json);
            break;
        }return v;
    }

    private String struk,suplier;
    private org.joda.money.Money harga;
    private java.sql.Date tgl;
    private java.util.List<ItemBeli>items;
    private boolean deleted;

    public Pembelian() {
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("struk", struk);
        o.put("suplier", suplier);
        o.put("harga", ""+harga);
        o.put("tgl", ""+tgl);
        o.put("deleted", ""+deleted);
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        items.stream().map((i) -> {
            org.json.simple.JSONObject o1=new org.json.simple.JSONObject();
            o1.put("barang", i.getBarang());
            o1.put("harga", ""+i.getHarga());
            o1.put("jumlah", ""+i.getJumlah());
            o1.put("satuan", i.getSatuan());
            return o1;
        }).forEachOrdered((o1) -> {
            a.add(o1);
        });o.put("items", a);
        return o.toJSONString();
    }

    public Pembelian(String s) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(s);
        struk=""+o.get("struk");
        suplier=""+o.get("suplier");
        harga=org.joda.money.Money.parse(""+o.get("harga"));
        tgl=java.sql.Date.valueOf(""+o.get("tgl"));
        items=new java.util.LinkedList<>();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) o.get("items");
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o1=(org.json.simple.JSONObject) a.get(x);
            ItemBeli i=new ItemBeli();
            i.setBarang(""+o1.get("barang"));
            i.setHarga(org.joda.money.Money.parse(""+o1.get("harga")));
            i.setJumlah(Integer.parseInt(""+o1.get("jumlah")));
            i.setSatuan(""+o1.get("satuan"));
            items.add(i);
        }
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
        harga=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
        items.forEach((i) -> {harga=harga.plus(i.getHarga().multipliedBy(i.getJumlah()));});
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}