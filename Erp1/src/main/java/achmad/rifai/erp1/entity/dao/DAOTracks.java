/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Tracks;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOTracks implements DAO<Tracks>{
    private achmad.rifai.erp1.util.Db d;

    public DAOTracks(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Tracks v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getKode(), v);
        d.getS().execute(QueryBuilder.insertInto("tracks").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists tracks(berkas text primary key,bin list<text>);");
    }

    @Override
    public void delete(Tracks w) throws Exception {
        Tracks b=Tracks.of(d,w.getKode());
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void update(Tracks a, Tracks b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Tracks> all() throws Exception {
        List<Tracks>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(QueryBuilder.select("bin").from("tracks"))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Tracks v=new Tracks(json);
            if(!v.isDeleted())l.add(v);
        }l.sort(sorter());
        return l;
    }

    public void trueDelete(Tracks a) throws Exception{
        d.getS().execute(QueryBuilder.delete().from("tracks").where(QueryBuilder.eq("berkas", a.getKode())));
    }

    private Comparator<? super Tracks> sorter() {
        return (Tracks o1, Tracks o2) -> {
            int x;
            if(o1.getTahun()==o2.getTahun()){
                if(o1.getBln().getValue()>o2.getBln().getValue())x=1;
                else if(o1.getBln().getValue()<o2.getBln().getValue())x=-1;
                else x=0;
            }else if(o1.getTahun().isAfter(o2.getTahun()))x=-1;
            else x=1;
            return x;
        };
    }

    public Tracks current(String id)throws Exception{
        java.sql.Date tgl=java.sql.Date.valueOf(LocalDate.now());
        String kode=id+tgl.toLocalDate().getMonth()+tgl.getYear();
        Tracks v=Tracks.of(d, kode);
        if(v==null){
            v=new Tracks();
            v.setBln(tgl.toLocalDate().getMonth());
            v.setDeleted(false);
            v.setId(id);
            v.setKode(kode);
            v.setTahun(java.time.Year.of(tgl.getYear()));
            v.setL(new java.util.LinkedList<>());
            insert(v);
        }return v;
    }
}