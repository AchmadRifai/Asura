/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Pesan;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPesan implements DAO<Pesan>{
    private achmad.rifai.erp1.util.Db d;

    public DAOPesan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Pesan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getS().execute(QueryBuilder.insertInto("pesan").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Pesan w) throws Exception {
        Pesan p=Pesan.of(d,w.getKode());
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Pesan a, Pesan b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Pesan> all() throws Exception {
        List<Pesan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("pesan"));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Pesan v=new Pesan(json);
            if(!v.isDeleted())l.add(v);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Pesan> sorter() {
        return (Pesan o1, Pesan o2) -> {
            int x;
            if(o1.getWaktu().isAfter(o2.getWaktu()))x=1;
            else if(o1.getWaktu().isBefore(o2.getWaktu()))x=-1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Pesan v)throws Exception{
        d.getS().execute(QueryBuilder.delete().from("pesan").where(QueryBuilder.eq("berkas", v.getKode())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists pesan(berkas text primary key,bin list<text>);");
    }
}