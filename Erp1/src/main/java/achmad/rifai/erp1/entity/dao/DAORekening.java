/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Rekening;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAORekening implements DAO<Rekening>{
    private achmad.rifai.erp1.util.Db d;

    public DAORekening(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Rekening v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("rekening").insert(f.genComparasion());
    }

    @Override
    public void delete(Rekening w) throws Exception {
        Rekening r=Rekening.of(d,w.getKode());
        r.setDeleted(true);
        update(w,r);
    }

    @Override
    public void update(Rekening a, Rekening b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("rekening").update(w, f.genComparasion());
    }

    @Override
    public List<Rekening> all() throws Exception {
        List<Rekening>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("rekening").find();
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Rekening re=new Rekening(json);
            if(!re.isDeleted())l.add(re);
        }return l;
    }

    public void trueDelete(Rekening v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", v.getKode());
        d.getD().getCollectionFromString("rekening").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists rekening(berkas text primary key,bin list<text>);");
    }
}