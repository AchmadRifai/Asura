/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Jejak;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOJejak implements DAO<Jejak>{
    private com.mongodb.DB d;

    public DAOJejak(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Jejak v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("user"), r.encrypt(v.getPelaku()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(v.toString()));
        d.getCollection("jejak").insert(o);
    }

    @Override
    public void delete(Jejak w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(w.toString()));
        d.getCollection("jejak").remove(o);
    }

    @Override
    public void update(Jejak a, Jejak b) throws Exception {
        com.mongodb.DBObject o1=new com.mongodb.BasicDBObject(),o2=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o1.put(achmad.rifai.erp1.util.Work.MD5("user"), r.encrypt(a.getPelaku()));
        o1.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(a.toString()));
        o2.put(achmad.rifai.erp1.util.Work.MD5("user"), r.encrypt(b.getPelaku()));
        o2.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(b.toString()));
        d.getCollection("jejak").update(o1, o2);
    }

    @Override
    public List<Jejak> all() throws Exception {
        List<Jejak>l=new java.util.LinkedList<>();
        com.mongodb.DBCursor c=d.getCollection("jejak").find();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.mongodb.DBObject o:c.toArray()){
            l.add(new Jejak(r.decrypt("" + o.get(achmad.rifai.erp1.util.Work.MD5("datane")))));
        }return l;
    }
}