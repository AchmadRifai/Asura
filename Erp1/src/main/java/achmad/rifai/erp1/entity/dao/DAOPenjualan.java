/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Penjualan;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPenjualan implements DAO<Penjualan>{
    private com.mongodb.DB d;

    public DAOPenjualan(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Penjualan v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("nota"), r.encrypt(v.getNota()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("next"), r.encrypt(s2(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("item"), r.encrypt(s3(v)));
        d.getCollection("penjualan").insert(o);
    }

    @Override
    public void delete(Penjualan w) throws Exception {
        Penjualan p=new Penjualan(s1(w),s2(w),s3(w));
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Penjualan a, Penjualan b) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject(),o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("nota"), r.encrypt(a.getNota()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("next"), r.encrypt(s2(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("item"), r.encrypt(s3(b)));
        d.getCollection("penjualan").update(w, o);
    }

    @Override
    public List<Penjualan> all() throws Exception {
        List<Penjualan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("penjualan").find();
        for(com.mongodb.DBObject o:c.toArray()){
            Penjualan p=new Penjualan(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))),
            r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("next"))),
            r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("item"))));
            if(!p.isDeleted())l.add(p);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Penjualan> sorter() {
        return new Comparator<Penjualan>() {
            @Override
            public int compare(Penjualan o1, Penjualan o2) {
                int x;
                if(o1.getTgl().after(o2.getTgl()))x=1;
                else if(o1.getTgl().before(o2.getTgl()))x=-1;
                else x=0;
                return x;
            }
        };
    }

    private String s1(Penjualan v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("ket", v.getKet());
        o.put("nota", v.getNota());
        o.put("pelanggan", v.getPelanggan());
        return o.toJSONString();
    }

    private String s2(Penjualan v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("deleted", v.isDeleted());
        o.put("tgl", v.getTgl());
        o.put("total", v.getTotal());
        return o.toJSONString();
    }

    private String s3(Penjualan v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(achmad.rifai.erp1.entity.ItemJual i:v.getItems()){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("barang", i.getBarang());
            o.put("jumlah", i.getJumlah());
            o.put("uang", i.getUang());
            a.add(o);
        }return a.toJSONString();
    }

    public void trueDelete(Penjualan v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("nota"), r.encrypt(v.getNota()));
        d.getCollection("penjualan").remove(o);
    }
}