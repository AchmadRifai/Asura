/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Suplier;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOSuplier implements DAO<Suplier>{
    private achmad.rifai.erp1.util.Db d;

    public DAOSuplier(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Suplier v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getS().execute(QueryBuilder.insertInto("suplier").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Suplier w) throws Exception {
        Suplier s=Suplier.of(d,w.getKode());
        s.setDeleted(true);
        update(w,s);
    }

    @Override
    public void update(Suplier a, Suplier b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Suplier> all() throws Exception {
        List<Suplier>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("suplier"));
        for(com.datastax.driver.core.Row ro:rs){
            String st="";
            for(String s:ro.getList("bin", String.class))st+=r.decrypt(s);
            Suplier su=new Suplier(st);
            if(!su.isDeleted())l.add(su);
        }return l;
    }

    public void trueDelete(Suplier v)throws Exception{
        d.getS().execute(QueryBuilder.delete().from("suplier").where(QueryBuilder.eq("berkas", v.getKode())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists suplier(berkas text primary key,bin list<text>);");
    }
}