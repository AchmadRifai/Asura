/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Keluar;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOKeluar implements DAO<achmad.rifai.erp1.entity.Keluar>{
    private com.mongodb.DB d;

    public DAOKeluar(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Keluar v) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(v.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(v.toString()));
        d.getCollection("keluar").insert(o);
    }

    @Override
    public void delete(Keluar w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        o.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(w.getKode()));
        d.getCollection("keluar").remove(o);
    }

    @Override
    public void update(Keluar a, Keluar b) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject(),o=new com.mongodb.BasicDBObject();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        w.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(a.getKode()));
        o.put(achmad.rifai.erp1.util.Work.MD5("datane"), r.encrypt(b.toString()));
        d.getCollection("keluar").update(w, o);
    }

    @Override
    public List<Keluar> all() throws Exception {
        List<Keluar>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getCollection("keluar").find();
        for(com.mongodb.DBObject o:c.toArray()){
            l.add(new Keluar(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("datane")))));
        }l.sort(sortir());
        return l;
    }

    private Comparator<? super Keluar> sortir() {
        return new Comparator<Keluar>() {
            @Override
            public int compare(Keluar o1, Keluar o2) {
                int x;
                if(o1.getTgl().isAfter(o2.getTgl()))x=-1;
                else if(o1.getTgl().isBefore(o2.getTgl()))x=1;
                else x=0;
                return x;
            }
        };
    }
}