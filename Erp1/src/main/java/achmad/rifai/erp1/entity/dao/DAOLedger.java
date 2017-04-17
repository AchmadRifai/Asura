/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Ledger;
import com.datastax.driver.core.querybuilder.QueryBuilder;
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
        d.getS().execute(QueryBuilder.insertInto("ledger").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Ledger w) throws Exception {
        Ledger l=Ledger.of(d,w.getKode());
        l.setDeleted(true);
        update(w,l);
    }

    @Override
    public void update(Ledger a, Ledger b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Ledger> all() throws Exception {
        List<Ledger>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("ledger"));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Ledger v=new Ledger(json);
            if(!v.isDeleted())l.add(v);
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
        d.getS().execute(QueryBuilder.delete().from("ledger").where(QueryBuilder.eq("berkas", a.getKode())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists ledger(berkas text primary key,bin list<text>);");
    }
}