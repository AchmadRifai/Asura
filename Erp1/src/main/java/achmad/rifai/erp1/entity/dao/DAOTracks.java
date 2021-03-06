/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Tracks;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOTracks implements DAO<Tracks>{
    private achmad.rifai.erp1.util.Db d;

    public DAOTracks(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Tracks v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("tracks").insert(f.genComparasion());
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists tracks(berkas text primary key,bin list<text>);");
    }

    @Override
    public void delete(Tracks w) throws Exception {
        Tracks b=Tracks.of(d,w.getKode());
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void update(Tracks a, Tracks b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("tracks").update(w, f.genComparasion());
    }

    @Override
    public List<Tracks> all() throws Exception {
        List<Tracks>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("tracks").find();
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Tracks t=new Tracks(json);
            if(!t.isDeleted())l.add(t);
        }l.sort(sorter());
        return l;
    }

    public void trueDelete(Tracks a) throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", a.getKode());
        d.getD().getCollectionFromString("tugas").remove(o);
    }

    private Comparator<? super Tracks> sorter() {
        return (Tracks o1, Tracks o2) -> {
            int x;
            if(o1.getTahun()==o2.getTahun()){
                if(o1.getBln().getValue()>o2.getBln().getValue())x=1;
                else if(o1.getBln().getValue()<o2.getBln().getValue())x=-1;
                else x=0;
            }else if(o1.getTahun()>o2.getTahun())x=-1;
            else x=1;
            return x;
        };
    }

    public Tracks current(String id)throws Exception{
        java.sql.Date tgl=java.sql.Date.valueOf(LocalDate.now());
        String kode=id+tgl.toLocalDate().getMonth()+tgl.getYear();
        Tracks v=Tracks.of(d, kode);
        if(v==null){
            v=new Tracks();
            v.setBln(tgl.toLocalDate().getMonth());
            v.setDeleted(false);
            v.setId(id);
            v.setKode(kode);
            v.setTahun(tgl.getYear());
            v.setL(new java.util.LinkedList<>());
            insert(v);
        }return v;
    }
}