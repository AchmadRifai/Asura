/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.BukuAbsen;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOBukuAbsen implements DAO<BukuAbsen>{
    private achmad.rifai.erp1.util.Db d;

    public DAOBukuAbsen(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(BukuAbsen v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getTgl(), v);
        d.getD().getCollectionFromString("bukuabsen").insert(f.genComparasion());
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists bukuabsen(berkas text primary key,bin list<text>);");
    }

    @Override
    public void delete(BukuAbsen w) throws Exception {
        BukuAbsen b=BukuAbsen.of(d,w.getTgl());
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void update(BukuAbsen a, BukuAbsen b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getTgl(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getTgl());
        d.getD().getCollectionFromString("bukuabsen").update(w, f.genComparasion());
    }

    @Override
    public List<BukuAbsen> all() throws Exception {
        List<BukuAbsen>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("bukuabsen").find();
        while(c.hasNext()){
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            BukuAbsen b=new BukuAbsen(json);
            if(!b.isDeleted())l.add(b);
        }l.sort(sorter());
        return l;
    }

    public BukuAbsen current()throws Exception{
        java.sql.Date tgl=java.sql.Date.valueOf(LocalDate.now());
        BukuAbsen v=BukuAbsen.of(d, ""+tgl);
        if(v==null){
            v=new BukuAbsen();
            v.setDeleted(false);
            v.setL(new java.util.LinkedList<>());
            v.setTgl(""+tgl);
            insert(v);
        }return v;
    }

    public void trueDelete(BukuAbsen a) {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", a.getTgl());
        d.getD().getCollectionFromString("bukuabsen").remove(o);
    }

    private Comparator<? super BukuAbsen> sorter() {
        return (BukuAbsen o1, BukuAbsen o2) -> {
            int x;
            java.sql.Date d1=java.sql.Date.valueOf(o1.getTgl()),d2=java.sql.Date.valueOf(o2.getTgl());
            if(d1.after(d2))x=-1;
            else if(d1.before(d2))x=1;
            else x=0;
            return x;
        };
    }
}