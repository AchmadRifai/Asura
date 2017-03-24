/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Keluar;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOKeluar implements DAO<Keluar>{
    private achmad.rifai.erp1.util.Db d;

    public DAOKeluar(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Keluar v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getS().execute(QueryBuilder.insertInto("keluar").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Keluar w) throws Exception {
        Keluar k=Keluar.of(d,w.getKode());
        k.setDeleted(true);
        update(w,k);
    }

    @Override
    public void update(Keluar a, Keluar b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Keluar> all() throws Exception {
        List<Keluar>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(QueryBuilder.select("bin").from("keluar"))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Keluar v=new Keluar(json);
            if(!v.isDeleted())l.add(v);
        }l.sort(sortir());
        return l;
    }

    private Comparator<? super Keluar> sortir() {
        return (Keluar o1, Keluar o2) -> {
            int x;
            if(o1.getTgl().isAfter(o2.getTgl()))x=-1;
            else if(o1.getTgl().isBefore(o2.getTgl()))x=1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Keluar a) throws Exception{
        d.getS().execute(QueryBuilder.delete().from("keluar").where(QueryBuilder.eq("berkas", a.getKode())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists keluar(berkas text primary key,bin list<text>);");
    }
}