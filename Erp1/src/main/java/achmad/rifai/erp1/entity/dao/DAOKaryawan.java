/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Karyawan;
import achmad.rifai.erp1.util.RSA;
import achmad.rifai.erp1.util.Work;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOKaryawan implements DAO<Karyawan>{
    private achmad.rifai.erp1.util.Db d;

    public DAOKaryawan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Karyawan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getId(), v);
        d.getD().getCollectionFromString("karyawan").insert(f.genComparasion());
    }

    public void truedelete(Karyawan w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", w.getId());
        d.getD().getCollectionFromString("karyawan").remove(o);
    }

    @Override
    public void update(Karyawan a, Karyawan b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getId(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getId());
        d.getD().getCollectionFromString("karyawan").update(w, f.genComparasion());
    }

    @Override
    public List<Karyawan> all() throws Exception {
        List<Karyawan>l=new java.util.LinkedList<>();
        RSA r=Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("karyawan").find();
        while(c.hasNext()){
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Karyawan k=new Karyawan(json);
            if(!k.isDeleted())l.add(k);
        }l.sort(sorter());
        return l;
    }

    @Override
    public void delete(Karyawan w) throws Exception {
        Karyawan o=Karyawan.of(d,w.getId());
        o.setDeleted(true);
        update(w,o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists karyawan(berkas text primary key,bin list<text>);");
    }

    private Comparator<? super Karyawan> sorter() {
        return (Karyawan o1, Karyawan o2) -> {
            int x;
            if(o1.getHiredate().after(o2.getHiredate()))x=-1;
            else if(o1.getHiredate().before(o2.getHiredate()))x=1;
            else x=0;
            return x;
        };
    }
}