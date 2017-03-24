/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Pelanggan;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPelanggan implements DAO<Pelanggan>{
    private achmad.rifai.erp1.util.Db d;

    public DAOPelanggan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Pelanggan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getS().execute(QueryBuilder.insertInto("pelanggan").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Pelanggan w) throws Exception {
        Pelanggan p=Pelanggan.of(d,w.getKode());
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Pelanggan a, Pelanggan b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Pelanggan> all() throws Exception {
        List<Pelanggan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(QueryBuilder.select("bin").from("pelanggan"))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Pelanggan v=new Pelanggan(json);
            if(!v.isDeleted())l.add(v);
        }return l;
    }

    public void trueDelete(Pelanggan a) throws Exception{
        d.getS().execute(QueryBuilder.delete().from("pelanggan").where(QueryBuilder.eq("berkas", a.getKode())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists pelanggan(berkas text primary key,bin list<text>);");
    }
}