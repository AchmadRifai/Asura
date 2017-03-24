/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.BukuAbsen;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOBukuAbsen implements DAO<BukuAbsen>{
    private achmad.rifai.erp1.util.Db d;

    public DAOBukuAbsen(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(BukuAbsen v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getTgl(), v);
        d.getS().execute(QueryBuilder.insertInto("bukuabsen").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists bukuabsen(berkas text primary key,bin list<text>);");
    }

    @Override
    public void delete(BukuAbsen w) throws Exception {
        BukuAbsen b=BukuAbsen.of(d,w.getTgl());
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void update(BukuAbsen a, BukuAbsen b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<BukuAbsen> all() throws Exception {
        List<BukuAbsen>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(QueryBuilder.select("bin").from("bukuabsen"))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            BukuAbsen v=new BukuAbsen(json);
            if(!v.isDeleted())l.add(v);
        }l.sort(sorter());
        return l;
    }

    public BukuAbsen current()throws Exception{
        java.sql.Date tgl=java.sql.Date.valueOf(LocalDate.now());
        BukuAbsen v=BukuAbsen.of(d, ""+tgl);
        if(v==null){
            v=new BukuAbsen();
            v.setDeleted(false);
            v.setL(new java.util.LinkedList<>());
            v.setTgl(""+tgl);
            insert(v);
        }return v;
    }

    private void trueDelete(BukuAbsen a) {
        d.getS().execute(QueryBuilder.delete().from("bukuabsen").where(QueryBuilder.eq("berkas", a.getTgl())));
    }

    private Comparator<? super BukuAbsen> sorter() {
        return (BukuAbsen o1, BukuAbsen o2) -> {
            int x;
            java.sql.Date d1=java.sql.Date.valueOf(o1.getTgl()),d2=java.sql.Date.valueOf(o2.getTgl());
            if(d1.after(d2))x=1;
            else if(d1.before(d2))x=-1;
            else x=0;
            return x;
        };
    }
}