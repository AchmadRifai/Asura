/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

import achmad.rifai.erp1.entity.Penjualan;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ai
 */
public class DAOPenjualan implements DAO<Penjualan>{
    private achmad.rifai.erp1.util.Db d;

    public DAOPenjualan(achmad.rifai.erp1.util.Db db){
        d=db;
    }

    @Override
    public void insert(Penjualan v) throws Exception {
        achmad.rifai.erp1.beans.Form1 f=new achmad.rifai.erp1.beans.Form1(v.getNota(), v);
        d.getS().execute(QueryBuilder.insertInto("penjualan").value("berkas", f.getKode()).value("bin", f.getData()));
    }

    @Override
    public void delete(Penjualan w) throws Exception {
        Penjualan p=Penjualan.of(d,w.getNota());
        p.setDeleted(true);
        update(w,p);
    }

    @Override
    public void update(Penjualan a, Penjualan b) throws Exception {
        trueDelete(a);
        insert(b);
    }

    @Override
    public List<Penjualan> all() throws Exception {
        List<Penjualan>l=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(QueryBuilder.select("bin").from("penjualan"))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            Penjualan v=new Penjualan(json);
            if(!v.isDeleted())l.add(v);
        }l.sort(sorter());
        return l;
    }

    private Comparator<? super Penjualan> sorter() {
        return (Penjualan o1, Penjualan o2) -> {
            int x;
            if(o1.getTgl().after(o2.getTgl()))x=-1;
            else if(o1.getTgl().before(o2.getTgl()))x=1;
            else x=0;
            return x;
        };
    }

    public void trueDelete(Penjualan v)throws Exception{
        d.getS().execute(QueryBuilder.delete().from("penjualan").where(QueryBuilder.eq("berkas", v.getNota())));
    }

    @Override
    public void createTable() throws Exception {
        d.getRS("create table if not exists penjualan(berkas text primary key,bin list<text>);");
    }
}