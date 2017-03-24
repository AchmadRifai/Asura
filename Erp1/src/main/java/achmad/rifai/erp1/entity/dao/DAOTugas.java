/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Tugas;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOTugas implements DAO<Tugas>{
    private achmad.rifai.erp1.util.Db d;

    public DAOTugas(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Tugas v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getS().execute(QueryBuilder.insertInto("tugas").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Tugas w) throws Exception {
        Tugas t=Tugas.of(d,w.getKode());
        t.setDeleted(true);
        update(w,t);
    }

    @Override
    public void update(Tugas a, Tugas b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Tugas> all() throws Exception {
        List<Tugas>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("tugas"));
        for(com.datastax.driver.core.Row ro:rs){
            List<String>ls=ro.getList("bin", String.class);
            String s="";
            for(String st:ls)s+=r.decrypt(st);
            Tugas t=new Tugas(s);
            if(!t.isDeleted())l.add(t);
        }l.sort(sorter());
        return l;
    }

    public Comparator<? super Tugas> sorter() {
        return new Comparator<Tugas>() {
            @Override
            public int compare(Tugas o1, Tugas o2) {
                int x;
                if(o1.getTgl().equals(o2.getTgl())){
                    if(o1.getNo()>o2.getNo())x=1;
                    else if(o1.getNo()<o2.getNo())x=-1;
                    else x=0;
                }else if(o1.getTgl().after(o2.getTgl()))x=1;
                else x=-1;
                return x;
            }
        };
    }

    public void trueDelete(Tugas v)throws Exception{
        d.getS().execute(QueryBuilder.delete().from("tugas").where(QueryBuilder.eq("berkas", v.getKode())));
    }

    @Override
    public void createTable() throws Exception {
        d.getS().execute("create table if not exists tugas(berkas text primary key,bin list<text>);");
    }
}