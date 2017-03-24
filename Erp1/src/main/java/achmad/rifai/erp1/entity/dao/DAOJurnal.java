/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Jurnal;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOJurnal implements DAO<Jurnal>{
    private achmad.rifai.erp1.util.Db d;

    public DAOJurnal(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Jurnal v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getS().execute(QueryBuilder.insertInto("jurnal").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Jurnal w) throws Exception {
        Jurnal t=Jurnal.of(d,w.getKode());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Jurnal a, Jurnal b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Jurnal> all() throws Exception {
        List<Jurnal>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("jurnal"));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Jurnal v=new Jurnal(json);
            if(!v.isDeleted())l.add(v);
        }l.sort(sorter());
        return l;
    }

    public void trueDelete(Jurnal v)throws Exception{
        d.getS().execute(QueryBuilder.delete().from("jurnal").where(QueryBuilder.eq("berkas", v.getKode())));
    }

    private Comparator<? super Jurnal> sorter() {
        return (Jurnal o1, Jurnal o2) -> {
            int x;
            if(o1.getTgl().after(o2.getTgl()))x=1;
            else if(o1.getTgl().before(o2.getTgl()))x=-1;
            else x=0;
            return x;
        };
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists jurnal(berkas text primary key,bin list<text>);");
    }
}