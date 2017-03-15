/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Pembelian;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPembelian implements DAO<Pembelian>{
    private com.mongodb.DB d;

    public DAOPembelian(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Pembelian v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("struk"), r.encrypt(v.getStruk()));
        o.put(achmad.rifai.erp1.util.Work.MD5("suplier"), r.encrypt(v.getSuplier()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("item"), r.encrypt(s2(v)));
        d.getCollection("pembelian").insert(o);
    }

    @Override
    public void delete(Pembelian w) throws Exception {
        Pembelian p=new Pembelian(s1(w),s2(w));
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Pembelian a, Pembelian b) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject(),w=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("struk"), r.encrypt(a.getStruk()));
        w.put(achmad.rifai.erp1.util.Work.MD5("suplier"), r.encrypt(a.getSuplier()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("item"), r.encrypt(s2(b)));
        d.getCollection("pembelian").update(w, o);
    }

    @Override
    public List<Pembelian> all() throws Exception {
        List<Pembelian>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("pembelian").find();
        for(com.mongodb.DBObject o:c.toArray()){
            Pembelian p=new Pembelian(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))),
            r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("item"))));
            if(!p.isDeleted())l.add(p);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Pembelian> sorter() {
        return new Comparator<Pembelian>() {
            @Override
            public int compare(Pembelian o1, Pembelian o2) {
                int x;
                if(o1.getTgl().after(o2.getTgl()))x=1;
                else if(o1.getTgl().before(o2.getTgl()))x=-1;
                else x=0;
                return x;
            }
        };
    }

    private String s1(Pembelian v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("deleted", v.isDeleted());
        o.put("tgl", v.getTgl());
        o.put("harga", v.getHarga());
        o.put("suplier", v.getSuplier());
        o.put("struk", v.getStruk());
        return o.toJSONString();
    }

    private String s2(Pembelian v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(achmad.rifai.erp1.entity.ItemBeli i:v.getItems()){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("barang", i.getBarang());
            o.put("harga", i.getHarga());
            o.put("jumlah", i.getJumlah());
            o.put("satuan", i.getSatuan());
            a.add(o);
        }return a.toJSONString();
    }

    public void trueDelete(Pembelian v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("struk"), r.encrypt(v.getStruk()));
        o.put(achmad.rifai.erp1.util.Work.MD5("suplier"), r.encrypt(v.getSuplier()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(v)));
        d.getCollection("pembelian").remove(o);
    }
}