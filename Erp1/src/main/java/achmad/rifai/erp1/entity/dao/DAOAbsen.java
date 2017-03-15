/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Absen;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOAbsen implements DAO<Absen>{
    private com.mongodb.DB d;

    public DAOAbsen(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Absen v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(v.toString()));
        d.getCollection("absen").insert(o);
    }

    @Override
    public void delete(Absen w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(w.toString()));
        d.getCollection("absen").remove(o);
    }

    @Override
    public void update(Absen a, Absen b) throws Exception {
        com.mongodb.DBObject o1=new com.mongodb.BasicDBObject(),o2=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o1.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(a.toString()));
        o2.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(b.toString()));
        d.getCollection("absen").update(o1, o2);
    }

    @Override
    public List<Absen> all() throws Exception {
        List<Absen>l=new java.util.LinkedList<>();
        com.mongodb.DBCursor c=d.getCollection("absen").find();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.mongodb.DBObject o:c.toArray()){
            l.add(new Absen(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("datane")))));
        }return l;
    }
}