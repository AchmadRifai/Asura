/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.BulanBonus;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOBulanBonus implements DAO<BulanBonus>{
    private achmad.rifai.erp1.util.Db d;

    public DAOBulanBonus(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(BulanBonus v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getD().getCollectionFromString("bulanbonus").insert(f.genComparasion());
    }

    @Override
    public void createTable() throws Exception {
        //d.getRS("create table if not exists bonusKaryawan(berkas text primary key,bin list<text>);");
    }

    @Override
    public void delete(BulanBonus w) throws Exception {
        BulanBonus b=BulanBonus.of(w.getKode(),d);
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void update(BulanBonus a, BulanBonus b) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(b.getKode(), b);
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("berkas", a.getKode());
        d.getD().getCollectionFromString("bulanbonus").update(w, f.genComparasion());
    }

    @Override
    public List<BulanBonus> all() throws Exception {
        List<BulanBonus>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBCursor c=d.getD().getCollectionFromString("bulanbonus").find();
        while(c.hasNext()){
            com.mongodb.BasicDBList li=(com.mongodb.BasicDBList) c.next().get("bin");
            String json="";
            for(int x=0;x<li.size();x++)json+=r.decrypt(""+li.get(x));
            BulanBonus b=new BulanBonus(json);
            if(!b.isDeleted())l.add(b);
        }l.sort(sorter());
        return l;
    }

    public void trueDelete(BulanBonus a)throws Exception{
        com.mongodb.DBObject p=new com.mongodb.BasicDBObject();
        p.put("berkas", a.getKode());
        d.getD().getCollectionFromString("bulanbonus").remove(p);
    }

    private Comparator<? super BulanBonus> sorter() {
        return (BulanBonus o1, BulanBonus o2) -> {
            int x;
            if(o1.getThn().equals(o2.getThn())){
                x=o1.getBln().compareTo(o2.getBln());
            }else if(o1.getThn().isAfter(o2.getThn()))x=-1;
            else x=1;
            return x;
        };
    }
}