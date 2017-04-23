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
public class DAOKeluar implements DAO<Keluar>{
    private achmad.rifai.erp1.util.Db d;

    public DAOKeluar(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Keluar v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("keluar").insert(f.genComparasion());
    }

    @Override
    public void delete(Keluar w) throws Exception {
        Keluar k=Keluar.of(d,w.getKode());
        k.setDeleted(true);
        update(w,k);
    }

    @Override
    public void update(Keluar a, Keluar b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("keluar").update(w, f.genComparasion());
    }

    @Override
    public List<Keluar> all() throws Exception {
        List<Keluar>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("keluar").find();
        while(c.hasNext()){
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Keluar k=new Keluar(json);
            if(!k.isDeleted())l.add(k);
        }l.sort(sortir());
        return l;
    }

    private Comparator<? super Keluar> sortir() {
        return (Keluar o1, Keluar o2) -> {
            int x;
            if(o1.getTgl().isAfter(o2.getTgl()))x=1;
            else if(o1.getTgl().isBefore(o2.getTgl()))x=-1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Keluar a) throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", a.getKode());
        d.getD().getCollectionFromString("keluar").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists keluar(berkas text primary key,bin list<text>);");
    }
}