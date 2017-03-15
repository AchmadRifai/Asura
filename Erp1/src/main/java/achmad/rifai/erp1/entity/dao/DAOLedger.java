/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Ledger;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOLedger implements DAO<Ledger>{
    private com.mongodb.DB d;

    public DAOLedger(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Ledger v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(v.toString()));
        d.getCollection("ledger").insert(o);
    }

    @Override
    public void delete(Ledger w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(w.getKode()));
        d.getCollection("ledger").remove(o);
    }

    @Override
    public void update(Ledger a, Ledger b) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject(),o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(b.toString()));
        d.getCollection("ledger").update(w, o);
    }

    @Override
    public List<Ledger> all() throws Exception {
        List<Ledger>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("ledger").find();
        for(com.mongodb.DBObject o:c.toArray())l.add(new Ledger(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("datane")))));
        l.sort(sorter());
        return l;
    }

    private Comparator<? super Ledger> sorter() {
        return new Comparator<Ledger>() {
            @Override
            public int compare(Ledger o1, Ledger o2) {
                int x;
                if(o1.getTgl().after(o2.getTgl()))x=1;
                else if(o1.getTgl().before(o2.getTgl()))x=-1;
                else x=0;
                return x;
            }
        };
    }
}