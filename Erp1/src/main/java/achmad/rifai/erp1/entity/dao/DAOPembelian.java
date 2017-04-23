/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Pembelian;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPembelian implements DAO<Pembelian>{
    private achmad.rifai.erp1.util.Db d;

    public DAOPembelian(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Pembelian v) throws Exception {
        achmad.rifai.erp1.beans.Form3 f=new achmad.rifai.erp1.beans.Form3(v.getStruk(), v.getSuplier(), ""+v.getTgl(), v);
        d.getD().getCollectionFromString("pembelian").insert(f.genComparator());
    }

    @Override
    public void delete(Pembelian w) throws Exception {
        Pembelian p=Pembelian.of(d,w.getStruk(),w.getSuplier(),w.getTgl());
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Pembelian a, Pembelian b) throws Exception {
        achmad.rifai.erp1.beans.Form3 f=new achmad.rifai.erp1.beans.Form3(b.getStruk(), b.getSuplier(), ""+b.getTgl(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas1", a.getStruk());
        w.put("berkas2", a.getSuplier());
        w.put("berkas3", ""+a.getTgl());
        d.getD().getCollectionFromString("pembelian").update(w, f.genComparator());
    }

    @Override
    public List<Pembelian> all() throws Exception {
        List<Pembelian>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("pembelian").find();
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Pembelian p=new Pembelian(json);
            if(!p.isDeleted())l.add(p);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Pembelian> sorter() {
        return (Pembelian o1, Pembelian o2) -> {
            int x;
            if(o1.getTgl().after(o2.getTgl()))x=-1;
            else if(o1.getTgl().before(o2.getTgl()))x=1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Pembelian v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas1", v.getStruk());
        o.put("berkas2", v.getSuplier());
        o.put("berkas3", ""+v.getTgl());
        d.getD().getCollectionFromString("pembelian").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists pembelian(berkas1 text,berkas2 text,berkas3 text,bin list<text>,primary key(berkas1,berkas2,berkas3));");
    }
}