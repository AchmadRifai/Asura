/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.BulanBonus;
import com.datastax.driver.core.querybuilder.QueryBuilder;
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
        d.getS().execute(QueryBuilder.insertInto("bonusKaryawan").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists bonusKaryawan(berkas text primary key,bin list<text>);");
    }

    @Override
    public void delete(BulanBonus w) throws Exception {
        BulanBonus b=BulanBonus.of(w.getKode(),d);
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void update(BulanBonus a, BulanBonus b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<BulanBonus> all() throws Exception {
        List<BulanBonus>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(QueryBuilder.select("bin").from("bonusKaryawan"))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            BulanBonus b=new BulanBonus(json);
            if(!b.isDeleted())l.add(b);
        }l.sort(sorter());
        return l;
    }

    public void trueDelete(BulanBonus a)throws Exception{
        d.getS().execute(QueryBuilder.delete().from("bonusKaryawan").where(QueryBuilder.eq("berkas", a.getKode())));
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