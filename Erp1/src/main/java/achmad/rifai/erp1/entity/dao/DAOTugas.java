/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Tugas;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOTugas implements DAO<Tugas>{
    private achmad.rifai.erp1.util.Db d;

    public DAOTugas(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Tugas v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("tugas").insert(f.genComparasion());
    }

    @Override
    public void delete(Tugas w) throws Exception {
        Tugas t=Tugas.of(d,w.getKode());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Tugas a, Tugas b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("tugas").update(w, f.genComparasion());
    }

    @Override
    public List<Tugas> all() throws Exception {
        List<Tugas>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("tugas").find();
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Tugas t=new Tugas(json);
            if(!t.isDeleted())l.add(t);
        }l.sort(sorter());
        return l;
    }

    public Comparator<? super Tugas> sorter() {
        return (Tugas o1, Tugas o2) -> {
            int x;
            if(o1.getTgl().equals(o2.getTgl())){
                if(o1.getNo()>o2.getNo())x=-1;
                else if(o1.getNo()<o2.getNo())x=1;
                else x=0;
            }else if(o1.getTgl().after(o2.getTgl()))x=-1;
            else x=1;
            return x;
        };
    }

    public void trueDelete(Tugas v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", v.getKode());
        d.getD().getCollectionFromString("tugas").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getS().execute("create table if not exists tugas(berkas text primary key,bin list<text>);");
    }
}