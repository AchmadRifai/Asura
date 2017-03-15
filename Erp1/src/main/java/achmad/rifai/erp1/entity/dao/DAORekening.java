/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Rekening;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAORekening implements DAO<Rekening>{
    private com.mongodb.DB d;

    public DAORekening(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Rekening v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(v.toString()));
        d.getCollection("rekening").insert(o);
    }

    @Override
    public void delete(Rekening w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(w.getKode()));
        d.getCollection("rekening").remove(o);
    }

    @Override
    public void update(Rekening a, Rekening b) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject(),w=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(b.toString()));
        d.getCollection("rekening").update(w, o);
    }

    @Override
    public List<Rekening> all() throws Exception {
        List<Rekening>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("rekening").find();
        for(com.mongodb.DBObject o:c.toArray())
            l.add(new Rekening(r.encrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("datane")))));
        return l;
    }
}