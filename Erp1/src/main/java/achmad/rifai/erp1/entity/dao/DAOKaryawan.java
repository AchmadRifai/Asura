/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Karyawan;
import achmad.rifai.erp1.util.RSA;
import achmad.rifai.erp1.util.Work;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOKaryawan implements DAO<Karyawan>{
    private com.mongodb.DB d;

    public DAOKaryawan(com.mongodb.DB d){
        this.d=d;
    }

    @Override
    public void insert(Karyawan v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        RSA r=Work.loadRSA();
        o.put(Work.MD5("kode"), r.encrypt(v.getId()));
        o.put(Work.MD5("data"), r.encrypt(s1(v)));
        o.put(Work.MD5("next"), r.encrypt(s2(v)));
        o.put(Work.MD5("alamat"), r.encrypt(s3(v)));
        o.put(Work.MD5("bonus"), r.encrypt(s4(v)));
        d.getCollection("karyawan").insert(o);
    }

    public void truedelete(Karyawan w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        RSA r=Work.loadRSA();
        o.put(Work.MD5("kode"), r.encrypt(w.getId()));
        d.getCollection("karyawan").remove(o);
    }

    @Override
    public void update(Karyawan a, Karyawan b) throws Exception {
        com.mongodb.DBObject o1=new com.mongodb.BasicDBObject(),o2=new com.mongodb.BasicDBObject();
        RSA r=Work.loadRSA();
        o1.put(Work.MD5("kode"), r.encrypt(a.getId()));
        o2.put(Work.MD5("data"), r.encrypt(s1(b)));
        o2.put(Work.MD5("next"), r.encrypt(s2(b)));
        o2.put(Work.MD5("alamat"), r.encrypt(s3(b)));
        o2.put(Work.MD5("bonus"), r.encrypt(s4(b)));
        d.getCollection("karyawan").update(o1, o2);
    }

    @Override
    public List<Karyawan> all() throws Exception {
        List<Karyawan>l=new java.util.LinkedList<>();
        RSA r=Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("karyawan").find();
        for(com.mongodb.DBObject o:c.toArray()){
            Karyawan k=new Karyawan(r.decrypt(""+o.get(Work.MD5("data"))),
            r.decrypt(""+o.get(Work.MD5("next"))),
            r.decrypt(""+o.get(Work.MD5("alamat"))),
            r.decrypt(""+o.get(Work.MD5("bonus"))));
            if(!k.isDeleted())l.add(k);
        }return l;
    }

    private String s1(Karyawan v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("id", v.getId());
        o.put("nama", v.getNama());
        o.put("jabatan", v.getJabatan());
        o.put("email", v.getEmail());
        o.put("pass", v.getPass());
        return o.toJSONString();
    }

    private String s2(Karyawan v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("blocked", v.isBlocked());
        o.put("deleted", v.isDeleted());
        o.put("masuk", v.isMasuk());
        o.put("hiredate", v.getHiredate());
        o.put("telp", v.getTelp());
        return o.toJSONString();
    }

    private String s3(Karyawan v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(String s:v.getAlamat())a.add(s);
        return a.toJSONString();
    }

    private String s4(Karyawan v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(achmad.rifai.erp1.entity.Bonusan b:v.getBonus()){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("bulan", b.getBulan());
            o.put("jumlah", b.getJumlah());
            o.put("nomer", b.getNomer());
            o.put("tahun", b.getTahun());
        }return a.toJSONString();
    }

    @Override
    public void delete(Karyawan w) throws Exception {
        Karyawan o=new Karyawan(s1(w),s2(w),s3(w),s4(w));
        o.setDeleted(true);
        update(w,o);
    }
}