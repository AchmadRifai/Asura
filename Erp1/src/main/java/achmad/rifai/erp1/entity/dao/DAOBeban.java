/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Beban;
import java.util.List;

/**
 *
 * @author janoko
 */
public class DAOBeban implements DAO<Beban>{
    private com.mongodb.DB d;

    public DAOBeban(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void insert(Beban v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getCollectionFromString("beban").insert(f.genComparasion());
    }

    @Override
    public void createTable() throws Exception {
        //
    }

    @Override
    public void delete(Beban w) throws Exception {
        Beban b=Beban.of(d,w.getKode());
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void update(Beban a, Beban b) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(a.getKode(), b);
        d.getCollectionFromString("beban").update(w, f.genComparasion());
    }

    @Override
    public List<Beban> all() throws Exception {
        List<Beban>l=new java.util.LinkedList<>();
        com.mongodb.DBCursor c=d.getCollectionFromString("beban").find();
        while(c.hasNext()){
            
        }
        return l;
    }

    
}