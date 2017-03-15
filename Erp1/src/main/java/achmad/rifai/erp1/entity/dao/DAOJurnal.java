/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Jurnal;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOJurnal implements DAO<Jurnal>{
    private com.mongodb.DB d;

    public DAOJurnal(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Jurnal v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(v.toString()));
        d.getCollection("jurnal").insert(o);
    }

    @Override
    public void delete(Jurnal w) throws Exception {
        Jurnal t=new Jurnal(w.toString());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Jurnal a, Jurnal b) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject(),v=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        v.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(b.toString()));
        d.getCollection("jurnal").update(w, v);
    }

    @Override
    public List<Jurnal> all() throws Exception {
        List<Jurnal>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("jurnal").find();
        for(com.mongodb.DBObject o:c.toArray())l.add(new Jurnal(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("datane")))));
        l.sort(new Comparator<Jurnal>() {
            @Override
            public int compare(Jurnal o1, Jurnal o2) {
                int x;
                if(o1.getTgl().after(o2.getTgl()))x=1;
                else if(o1.getTgl().before(o2.getTgl()))x=-1;
                else x=0;
                return x;
            }
        });return l;
    }
}