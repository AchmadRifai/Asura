/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Terima;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOTerima implements DAO<Terima>{
    private achmad.rifai.erp1.util.Db d;

    public DAOTerima(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Terima v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("terima").insert(f.genComparasion());
    }

    @Override
    public void delete(Terima w) throws Exception {
        Terima t=Terima.of(d,w.getKode());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Terima a, Terima b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", b.getKode());
        d.getD().getCollectionFromString("terima").update(w, f.genComparasion());
    }

    @Override
    public List<Terima> all() throws Exception {
        List<Terima>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("terima").find();
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Terima t=new Terima(json);
            if(!t.isDeleted())l.add(t);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Terima> sorter() {
        return (Terima o1, Terima o2) -> {
            int x;
            if(o1.getTgl().isAfter(o2.getTgl()))x=-1;
            else if(o1.getTgl().isBefore(o2.getTgl()))x=1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Terima v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", v.getKode());
        d.getD().getCollectionFromString("terima").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists terima(berkas text primary key,bin list<text>);");
    }
}