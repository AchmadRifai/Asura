/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Pelanggan;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPelanggan implements DAO<Pelanggan>{
    private com.mongodb.DB d;

    public DAOPelanggan(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Pelanggan v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("alamat"), r.encrypt(s2(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("telp"), r.encrypt(s3(v)));
        d.getCollection("pelanggan").insert(o);
    }

    @Override
    public void delete(Pelanggan w) throws Exception {
        Pelanggan p=new Pelanggan(s1(w),s2(w),s3(w));
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Pelanggan a, Pelanggan b) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject(),o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("alamat"), r.encrypt(s2(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("telp"), r.encrypt(s3(b)));
        d.getCollection("pelanggan").update(w, o);
    }

    @Override
    public List<Pelanggan> all() throws Exception {
        List<Pelanggan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("pelanggan").find();
        for(com.mongodb.DBObject o:c.toArray()){
            Pelanggan p=new Pelanggan(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))),
            r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("alamat"))),
            r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("telp"))));
            if(!p.isDeleted())l.add(p);
        }return l;
    }

    private String s1(Pelanggan v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("blocked", v.isBlocked());
        o.put("deleted", v.isDeleted());
        o.put("kode", v.getKode());
        o.put("nama", v.getNama());
        return o.toJSONString();
    }

    private String s2(Pelanggan v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(String s:v.getAlamat())a.add(s);
        return a.toJSONString();
    }

    private String s3(Pelanggan v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(String s:v.getTelp())a.add(s);
        return a.toJSONString();
    }
}