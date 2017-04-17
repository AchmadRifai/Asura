/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Karyawan;
import achmad.rifai.erp1.util.RSA;
import achmad.rifai.erp1.util.Work;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOKaryawan implements DAO<Karyawan>{
    private achmad.rifai.erp1.util.Db d;

    public DAOKaryawan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Karyawan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getId(), v);
        d.getS().execute(QueryBuilder.insertInto("karyawan").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    public void truedelete(Karyawan w) throws Exception {
        d.getS().execute(QueryBuilder.delete().from("karyawan").where(QueryBuilder.eq("berkas", w.getId())));
    }

    @Override
    public void update(Karyawan a, Karyawan b) throws Exception {
        truedelete(a);
        insert(b);
    }

    @Override
    public List<Karyawan> all() throws Exception {
        List<Karyawan>l=new java.util.LinkedList<>();
        RSA r=Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(QueryBuilder.select("bin").from("karyawan"))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Karyawan v=new Karyawan(json);
            if(!v.isDeleted())l.add(v);
        }l.sort(sorter());
        return l;
    }

    @Override
    public void delete(Karyawan w) throws Exception {
        Karyawan o=Karyawan.of(d,w.getId());
        o.setDeleted(true);
        update(w,o);
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists karyawan(berkas text primary key,bin list<text>);");
    }

    private Comparator<? super Karyawan> sorter() {
        return (Karyawan o1, Karyawan o2) -> {
            int x;
            if(o1.getHiredate().after(o2.getHiredate()))x=-1;
            else if(o1.getHiredate().before(o2.getHiredate()))x=1;
            else x=0;
            return x;
        };
    }
}