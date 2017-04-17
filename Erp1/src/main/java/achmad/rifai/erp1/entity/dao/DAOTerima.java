/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Terima;
import com.datastax.driver.core.querybuilder.QueryBuilder;
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
        d.getS().execute(QueryBuilder.insertInto("terima").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Terima w) throws Exception {
        Terima t=Terima.of(d,w.getKode());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Terima a, Terima b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Terima> all() throws Exception {
        List<Terima>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("terima"));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Terima v=new Terima(json);
            if(!v.isDeleted())l.add(v);
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
        d.getS().execute(QueryBuilder.delete().from("terima").where(QueryBuilder.eq("berkas", v.getKode())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists terima(berkas text primary key,bin list<text>);");
    }
}