/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Suplier;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOSuplier implements DAO<Suplier>{
    private com.mongodb.DB d;

    public DAOSuplier(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Suplier v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("item"), r.encrypt(s2(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("telp"), r.encrypt(s3(v)));
        d.getCollection("suplier").insert(o);
    }

    @Override
    public void delete(Suplier w) throws Exception {
        Suplier s=new Suplier(s1(w),s2(w),s3(w));
        s.setDeleted(true);
        update(w,s);
    }

    @Override
    public void update(Suplier a, Suplier b) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject(),w=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("item"), r.encrypt(s2(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("telp"), r.encrypt(s3(b)));
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        d.getCollection("suplier").update(w, o);
    }

    @Override
    public List<Suplier> all() throws Exception {
        List<Suplier>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("suplier").find();
        for(com.mongodb.DBObject o:c.toArray()){
            Suplier s=new Suplier(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))),
            r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("item"))),
            r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("telp"))));
            if(!s.isDeleted())l.add(s);
        }return l;
    }

    private String s1(Suplier v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("deleted", v.isDeleted());
        o.put("kode", v.getKode());
        o.put("nama", v.getNama());
        return o.toJSONString();
    }

    private String s2(Suplier v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(String s:v.getAlamat())a.add(s);
        return a.toJSONString();
    }

    private String s3(Suplier v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(String s:v.getTelp())a.add(s);
        return a.toJSONString();
    }
}