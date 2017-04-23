/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Ledger;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOLedger implements DAO<Ledger>{
    private achmad.rifai.erp1.util.Db d;

    public DAOLedger(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Ledger v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("ledger").insert(f.genComparasion());
    }

    @Override
    public void delete(Ledger w) throws Exception {
        Ledger l=Ledger.of(d,w.getKode());
        l.setDeleted(true);
        update(w,l);
    }

    @Override
    public void update(Ledger a, Ledger b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("ledger").update(w, f.genComparasion());
    }

    @Override
    public List<Ledger> all() throws Exception {
        List<Ledger>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("ledger").find();
        while(c.hasNext()){
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            Ledger le=new Ledger(json);
            if(!le.isDeleted())l.add(le);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Ledger> sorter() {
        return (Ledger o1, Ledger o2) -> {
            int x;
            if(o1.getTgl().after(o2.getTgl()))x=-1;
            else if(o1.getTgl().before(o2.getTgl()))x=1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Ledger a) throws Exception{
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("berkas", a.getKode());
        d.getD().getCollectionFromString("ledger").remove(o);
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists ledger(berkas text primary key,bin list<text>);");
    }
}