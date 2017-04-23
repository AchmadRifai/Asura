/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Jurnal;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOJurnal implements DAO<Jurnal>{
    private achmad.rifai.erp1.util.Db d;

    public DAOJurnal(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Jurnal v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("jurnal").insert(f.genComparasion());
    }

    @Override
    public void delete(Jurnal w) throws Exception {
        Jurnal t=Jurnal.of(d,w.getKode());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Jurnal a, Jurnal b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("jurnal").update(w, f.genComparasion());
    }

    @Override
    public List<Jurnal> all() throws Exception {
        List<Jurnal>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("jurnal").find();
        while(c.hasNext()){
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Jurnal j=new Jurnal(json);
            if(!j.isDeleted())l.add(j);
        }l.sort(sorter());
        return l;
    }

    public void trueDelete(Jurnal v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", v.getKode());
        d.getD().getCollectionFromString("jurnal").remove(o);
    }

    private Comparator<? super Jurnal> sorter() {
        return (Jurnal o1, Jurnal o2) -> {
            int x;
            if(o1.getTgl().after(o2.getTgl()))x=-1;
            else if(o1.getTgl().before(o2.getTgl()))x=1;
            else x=0;
            return x;
        };
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists jurnal(berkas text primary key,bin list<text>);");
    }
}