/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Penjualan;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPenjualan implements DAO<Penjualan>{
    private achmad.rifai.erp1.util.Db d;

    public DAOPenjualan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Penjualan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getNota(), v);
        d.getD().getCollectionFromString("penjualan").insert(f.genComparasion());
    }

    @Override
    public void delete(Penjualan w) throws Exception {
        Penjualan p=Penjualan.of(d,w.getNota());
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Penjualan a, Penjualan b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getNota(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getNota());
        d.getD().getCollectionFromString("penjualan").update(w, f.genComparasion());
    }

    @Override
    public List<Penjualan> all() throws Exception {
        List<Penjualan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("penjualan").find();
        while(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) o.get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Penjualan p=new Penjualan(json);
            if(!p.isDeleted())l.add(p);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Penjualan> sorter() {
        return (Penjualan o1, Penjualan o2) -> {
            int x;
            if(o1.getTgl().after(o2.getTgl()))x=-1;
            else if(o1.getTgl().before(o2.getTgl()))x=1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Penjualan v)throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", v.getNota());
        d.getD().getCollectionFromString("penjualan").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists penjualan(berkas text primary key,bin list<text>);");
    }
}