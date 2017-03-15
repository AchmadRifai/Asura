/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Barang;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOBarang implements DAO<Barang>{
    private com.mongodb.DB d;

    public DAOBarang(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Barang v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(v.toString()));
        d.getCollection("barang").insert(o);
    }

    @Override
    public void delete(Barang w) throws Exception {
        Barang t=new Barang(w.toString());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Barang a, Barang b) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject(),o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(b.toString()));
        d.getCollection("barang").update(w, o);
    }

    @Override
    public List<Barang> all() throws Exception {
        List<Barang>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("barang").find();
        for(com.mongodb.DBObject o:c.toArray()){
            Barang b=new Barang(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("datane"))));
            if(!b.isDeleted())l.add(b);
        }return l;
    }
}