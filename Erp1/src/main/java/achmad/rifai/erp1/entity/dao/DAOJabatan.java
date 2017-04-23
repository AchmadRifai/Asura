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
    private achmad.rifai.erp1.util.Db d;

    public DAOJabatan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Jabatan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 fo=new achmad.rifai.erp1.beans.Form1(v.getNama(), v);
        d.getD().getCollectionFromString("jabatan").insert(fo.genComparasion());
    }

    @Override
    public void delete(Jabatan w) throws Exception {
        Jabatan j=Jabatan.of(d,w.getNama());
        j.setDeleted(true);
        update(w,j);
    }

    @Override
    public void update(Jabatan a, Jabatan b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getNama(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getNama());
        d.getD().getCollectionFromString("jabatan").update(w, f.genComparasion());
    }

    @Override
    public List<Jabatan> all() throws Exception {
        List<Jabatan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("jabatan").find();
        while(c.hasNext()){
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Jabatan j=new Jabatan(json);
            if(!j.isDeleted())l.add(j);
        }return l;
    }

    public void trueDelete(Jabatan a)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", a.getNama());
        d.getD().getCollectionFromString("jabatan").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists jabatan(berkas text primary key,bin list<text>);");
    }
}