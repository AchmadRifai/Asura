/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Rekening;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAORekening implements DAO<Rekening>{
    private achmad.rifai.erp1.util.Db d;

    public DAORekening(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Rekening v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getS().execute(QueryBuilder.insertInto("rekening").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Rekening w) throws Exception {
        Rekening r=Rekening.of(d,w.getKode());
        r.setDeleted(true);
        update(w,r);
    }

    @Override
    public void update(Rekening a, Rekening b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Rekening> all() throws Exception {
        List<Rekening>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("rekening"));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Rekening v=new Rekening(json);
            if(!v.isDeleted())l.add(v);
        }return l;
    }

    public void trueDelete(Rekening v)throws Exception{
        d.getS().execute(QueryBuilder.delete().from("rekening").where(QueryBuilder.eq("berkas", v.getKode())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists rekening(berkas text primary key,bin list<text>);");
    }
}