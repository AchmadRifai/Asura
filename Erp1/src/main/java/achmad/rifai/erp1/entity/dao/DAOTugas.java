/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Tugas;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOTugas implements DAO<Tugas>{
    private com.mongodb.DB d;

    public DAOTugas(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Tugas v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("petugas"), r.encrypt(s2(v)));
        o.put(achmad.rifai.erp1.util.Work.MD5("pilihan"), r.encrypt(s3(v)));
        d.getCollection("tugas").insert(o);
    }

    @Override
    public void delete(Tugas w) throws Exception {
        Tugas t=new Tugas(s1(w),s2(w),s3(w));
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Tugas a, Tugas b) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject(),o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(s1(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("petugas"), r.encrypt(s2(b)));
        o.put(achmad.rifai.erp1.util.Work.MD5("pilihan"), r.encrypt(s3(b)));
        d.getCollection("tugas").update(w, o);
    }

    @Override
    public List<Tugas> all() throws Exception {
        List<Tugas>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("tugas").find();
        for(com.mongodb.DBObject o:c.toArray()){
            Tugas t=new Tugas(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data")))
            ,r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("petugas"))),
            r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("pilihan"))));
            if(!t.isDeleted())l.add(t);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Tugas> sorter() {
        return new Comparator<Tugas>() {
            @Override
            public int compare(Tugas o1, Tugas o2) {
                int x;
                if(o1.getTgl().equals(o2.getTgl())){
                    if(o1.getNo()>o2.getNo())x=1;
                    else if(o1.getNo()<o2.getNo())x=-1;
                    else x=0;
                }else if(o1.getTgl().after(o2.getTgl()))x=1;
                else x=-1;
                return x;
            }
        };
    }

    private String s1(Tugas v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("ket", v.getKet());
        o.put("kode", v.getKode());
        o.put("no", v.getNo());
        o.put("tgl", v.getTgl());
        return o.toJSONString();
    }

    private String s2(Tugas v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(achmad.rifai.erp1.entity.Petugas p:v.getL()){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("diambil", p.isDiambil());
            o.put("sedang", p.isSedang());
            o.put("terlaksana", p.isTerlaksana());
            o.put("karyawan", p.getKaryawan());
            a.add(o);
        }return a.toJSONString();
    }

    private String s3(Tugas v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("batal", v.isBatal());
        o.put("deleted", v.isDeleted());
        o.put("pending", v.isPending());
        return o.toJSONString();
    }

    public void trueDelete(Tugas v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        d.getCollection("tugas").remove(o);
    }
}