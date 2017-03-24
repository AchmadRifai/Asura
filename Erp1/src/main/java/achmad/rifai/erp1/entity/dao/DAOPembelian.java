/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Pembelian;
import com.datastax.driver.core.querybuilder.QueryBuilder;
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
        d.getS().execute(QueryBuilder.insertInto("pembelian").value("berkas1", f.getKode1()).value("berkas2", f.getKode2())
        .value("berkas3", f.getKode3()).value("bin", f.getData()));
    }

    @Override
    public void delete(Pembelian w) throws Exception {
        Pembelian p=Pembelian.of(d,w.getStruk(),w.getSuplier(),w.getTgl());
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Pembelian a, Pembelian b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Pembelian> all() throws Exception {
        List<Pembelian>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(QueryBuilder.select("bin").from("pembelian"))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Pembelian v=new Pembelian(json);
            if(!v.isDeleted())l.add(v);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Pembelian> sorter() {
        return (Pembelian o1, Pembelian o2) -> {
            int x;
            if(o1.getTgl().after(o2.getTgl()))x=1;
            else if(o1.getTgl().before(o2.getTgl()))x=-1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Pembelian v)throws Exception{
        d.getS().execute(QueryBuilder.delete().from("pembelian").where(QueryBuilder.eq("berkas1", v.getStruk()))
        .and(QueryBuilder.eq("berkas2", v.getSuplier())).and(QueryBuilder.eq("berkas3", ""+v.getTgl())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists pembelian(berkas1 text,berkas2 text,berkas3 text,bin list<text>,primary key(berkas1,berkas2,berkas3));");
    }
}