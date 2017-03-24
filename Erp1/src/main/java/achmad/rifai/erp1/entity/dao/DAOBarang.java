/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Barang;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOBarang implements DAO<Barang>{
    private achmad.rifai.erp1.util.Db d;

    public DAOBarang(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Barang v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getS().execute(QueryBuilder.insertInto("barang").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Barang w) throws Exception {
        Barang t=Barang.of(d,w.getKode());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Barang a, Barang b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Barang> all() throws Exception {
        List<Barang>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("barang"));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Barang v=new Barang(json);
            if(!v.isDeleted())l.add(v);
        }return l;
    }

    public void trueDelete(Barang v) throws Exception{
        d.getS().execute(QueryBuilder.delete().from("barang").where(QueryBuilder.eq("berkas", v.getKode())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists barang(berkas text primary key,bin list<text>);");
    }
}