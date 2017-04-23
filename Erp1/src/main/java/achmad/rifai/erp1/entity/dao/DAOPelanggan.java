/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Pelanggan;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPelanggan implements DAO<Pelanggan>{
    private achmad.rifai.erp1.util.Db d;

    public DAOPelanggan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Pelanggan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("pelanggan").insert(f.genComparasion());
    }

    @Override
    public void delete(Pelanggan w) throws Exception {
        Pelanggan p=Pelanggan.of(d,w.getKode());
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Pelanggan a, Pelanggan b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("pelanggan").update(w, f.genComparasion());
    }

    @Override
    public List<Pelanggan> all() throws Exception {
        List<Pelanggan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("pelanggan").find();
        while(c.hasNext()){
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Pelanggan p=new Pelanggan(json);
            if(!p.isDeleted())l.add(p);
        }return l;
    }

    public void trueDelete(Pelanggan a) throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", a.getKode());
        d.getD().getCollectionFromString("pelanggan").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists pelanggan(berkas text primary key,bin list<text>);");
    }
}