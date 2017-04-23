/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Suplier;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOSuplier implements DAO<Suplier>{
    private achmad.rifai.erp1.util.Db d;

    public DAOSuplier(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Suplier v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("suplier").insert(f.genComparasion());
    }

    @Override
    public void delete(Suplier w) throws Exception {
        Suplier s=Suplier.of(d,w.getKode());
        s.setDeleted(true);
        update(w,s);
    }

    @Override
    public void update(Suplier a, Suplier b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("suplier").update(w, f.genComparasion());
    }

    @Override
    public List<Suplier> all() throws Exception {
        List<Suplier>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("suplier").find();
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Suplier su=new Suplier(json);
            if(!su.isDeleted())l.add(su);
        }return l;
    }

    public void trueDelete(Suplier v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", v.getKode());
        d.getD().getCollectionFromString("suplier").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists suplier(berkas text primary key,bin list<text>);");
    }
}