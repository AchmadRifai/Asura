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
public class Barang {
    public static Barang of(Db d, String kode) throws Exception{
        Barang v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject p=new com.mongodb.BasicDBObject();
        p.put("berkas", kode);
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("barang").find(p);
        while(c.hasNext()){
            com.mongodb.BasicDBList l=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<l.size();x++)json+=r.decrypt(""+l.get(x));
            v=new Barang(json);
            break;
        }return v;
    }

    private String kode,nama,satuan;
    private org.joda.money.Money harga;
    private int stok;
    private boolean deleted;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public org.joda.money.Money getHarga() {
        return harga;
    }

    public void setHarga(org.joda.money.Money harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Barang(String json) throws ParseException {
        parsing(json);
    }

    public Barang() {
    }

    private void parsing(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(json);
        kode=""+o.get("kode");
        nama=""+o.get("nama");
        harga=org.joda.money.Money.parse(""+o.get("harga"));
        stok=Integer.parseInt(""+o.get("stok"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        satuan=""+o.get("satuan");
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("kode", kode);
        o.put("nama", nama);
        o.put("harga", ""+harga);
        o.put("stok", ""+stok);
        o.put("deleted", ""+deleted);
        o.put("satuan", satuan);
        return o.toJSONString();
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}