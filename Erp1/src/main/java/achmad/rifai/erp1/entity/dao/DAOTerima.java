/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Terima;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOTerima implements DAO<Terima>{
    private com.mongodb.DB d;

    public DAOTerima(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Terima v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(v.toString()));
        d.getCollection("terima").insert(o);
    }

    @Override
    public void delete(Terima w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(w.getKode()));
        d.getCollection("terima").remove(o);
    }

    @Override
    public void update(Terima a, Terima b) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject(),w=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(b.toString()));
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        d.getCollection("terima").update(w, o);
    }

    @Override
    public List<Terima> all() throws Exception {
        List<Terima>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("terima").find();
        for(com.mongodb.DBObject o:c.toArray())l.add(new Terima(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("datane")))));
        return l;
    }
}