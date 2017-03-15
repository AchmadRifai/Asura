/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Jabatan;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOJabatan implements DAO<Jabatan>{
    private com.mongodb.DB d;

    public DAOJabatan(com.mongodb.DB d){
        this.d=d;
    }

    @Override
    public void insert(Jabatan v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("nama"), r.encrypt(v.getNama()));
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(v.toString()));
        d.getCollection("jabatan").insert(o);
    }

    @Override
    public void delete(Jabatan w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(w.toString()));
        d.getCollection("jabatan").remove(o);
    }

    @Override
    public void update(Jabatan a, Jabatan b) throws Exception {
        com.mongodb.DBObject o1=new com.mongodb.BasicDBObject(),o2=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o1.put(achmad.rifai.erp1.util.Work.MD5("nama"), r.encrypt(a.getNama()));
        o2.put(achmad.rifai.erp1.util.Work.MD5("data"), r.encrypt(b.toString()));
        d.getCollection("jabatan").update(o1, o2);
    }

    @Override
    public List<Jabatan> all() throws Exception {
        List<Jabatan>l=new java.util.LinkedList<>();
        com.mongodb.DBCursor c=d.getCollection("jabatan").find();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.mongodb.DBObject o:c.toArray()){
            Jabatan j=new Jabatan(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))));
            l.add(j);
        }c.close();
        return l;
    }
    
}