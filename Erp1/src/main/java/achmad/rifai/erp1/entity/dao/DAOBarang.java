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
    private achmad.rifai.erp1.util.Db d;

    public DAOBarang(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Barang v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("barang").insert(f.genComparasion());
    }

    @Override
    public void delete(Barang w) throws Exception {
        Barang t=Barang.of(d,w.getKode());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Barang a, Barang b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("barang").update(w, f.genComparasion());
    }

    @Override
    public List<Barang> all() throws Exception {
        List<Barang>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("barang").find();
        while(c.hasNext()){
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Barang b=new Barang(json);
            if(!b.isDeleted())l.add(b);
        }return l;
    }

    public void trueDelete(Barang v) throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", v.getKode());
        d.getD().getCollectionFromString("barang").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists barang(berkas text primary key,bin list<text>);");
    }
}