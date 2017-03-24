/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Jabatan;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOJabatan implements DAO<Jabatan>{
    private achmad.rifai.erp1.util.Db d;

    public DAOJabatan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Jabatan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 fo=new achmad.rifai.erp1.beans.Form1(v.getNama(), v);
        d.getS().execute(QueryBuilder.insertInto("jabatan").value("berkas", fo.getKode()).value("bin", fo.getData()));
    }

    @Override
    public void delete(Jabatan w) throws Exception {
        Jabatan j=Jabatan.of(d,w.getNama());
        j.setDeleted(true);
        update(w,j);
    }

    @Override
    public void update(Jabatan a, Jabatan b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Jabatan> all() throws Exception {
        List<Jabatan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("jabatan"));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Jabatan v=new Jabatan(json);
            if(!v.isDeleted())l.add(v);
        }return l;
    }

    public void trueDelete(Jabatan a)throws Exception{
        d.getS().execute(QueryBuilder.delete().from("jabatan").where(QueryBuilder.eq("berkas", a.getNama())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists jabatan(berkas text primary key,bin list<text>);");
    }
}