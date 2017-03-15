/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Pesan;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPesan implements DAO<Pesan>{
    private com.mongodb.DB d;

    public DAOPesan(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Pesan v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("penerima"), r.encrypt(s2(v)));
        d.getCollection("pesan").insert(o);
    }

    @Override
    public void delete(Pesan w) throws Exception {
        Pesan p=new Pesan(s1(w),s2(w));
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Pesan a, Pesan b) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject(),o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("penerima"), r.encrypt(s2(b)));
        d.getCollection("pesan").update(w, o);
    }

    @Override
    public List<Pesan> all() throws Exception {
        List<Pesan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("pesan").find();
        for(com.mongodb.DBObject o:c.toArray()){
            Pesan p=new Pesan(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))),
                    r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("penerima"))));
            if(!p.isDeleted())l.add(p);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Pesan> sorter() {
        return new Comparator<Pesan>() {
            @Override
            public int compare(Pesan o1, Pesan o2) {
                int x;
                if(o1.getWaktu().isAfter(o2.getWaktu()))x=1;
                else if(o1.getWaktu().isBefore(o2.getWaktu()))x=-1;
                else x=0;
                return x;
            }
        };
    }

    private String s1(Pesan v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("deleted", v.isDeleted());
        o.put("waktu", v.getWaktu());
        o.put("kode", v.getKode());
        o.put("pengirim", v.getPengirim());
        o.put("pesan", v.getPesan());
        return o.toJSONString();
    }

    private String s2(Pesan v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(achmad.rifai.erp1.entity.Penerima p:v.getKe()){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("akun", p.getAkun());
            o.put("terbaca", p.isTerbaca());
            a.add(o);
        }return a.toJSONString();
    }

    public void trueDelete(Pesan v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        d.getCollection("pesan").remove(o);
    }
}