/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Pesan;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPesan implements DAO<Pesan>{
    private achmad.rifai.erp1.util.Db d;

    public DAOPesan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Pesan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("pesan").insert(f.genComparasion());
    }

    @Override
    public void delete(Pesan w) throws Exception {
        Pesan p=Pesan.of(d,w.getKode());
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Pesan a, Pesan b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("pesan").update(w, f.genComparasion());
    }

    @Override
    public List<Pesan> all() throws Exception {
        List<Pesan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("pesan").find();
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Pesan p=new Pesan(json);
            if(!p.isDeleted())l.add(p);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Pesan> sorter() {
        return (Pesan o1, Pesan o2) -> {
            int x;
            if(o1.getWaktu().isAfter(o2.getWaktu()))x=-1;
            else if(o1.getWaktu().isBefore(o2.getWaktu()))x=1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Pesan v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", v.getKode());
        d.getD().getCollectionFromString("pesan").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists pesan(berkas text primary key,bin list<text>);");
    }
}