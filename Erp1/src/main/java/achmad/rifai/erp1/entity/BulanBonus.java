/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Comparator;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class BulanBonus {
    public static BulanBonus of(String kode, Db d)throws Exception{
        BulanBonus b=null;
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("bonusKaryawan").
                where(QueryBuilder.eq("berkas", kode)));
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            b=new BulanBonus(json);
        }return b;
    }

    private String peg,kode;
    private java.time.Month bln;
    private java.time.Year thn;
    private java.util.List<Bonusan>l;
    private boolean deleted;

    public BulanBonus(String json) throws ParseException{
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        kode=""+o.get("kode");
        peg=""+o.get("peg");
        bln=java.time.Month.valueOf(""+o.get("bln"));
        thn=java.time.Year.parse(""+o.get("thn"));
        lst(o.get("l"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
    }

    public BulanBonus() {
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("kode", kode);
        o.put("peg", peg);
        o.put("bln", ""+bln);
        o.put("thn", ""+thn);
        o.put("l", bnse());
        o.put("deleted", ""+deleted);
        return o.toJSONString();
    }

    private void lst(Object get) {
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        l=new java.util.LinkedList<>();
        a.forEach((x)->{
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) x;
            Bonusan b=new Bonusan();
            b.setJumlah(org.joda.money.Money.parse(""+o.get("jumlah")));
            b.setNomer(Integer.parseInt(""+o.get("nomer")));
            l.add(b);
        });l.sort(sorter());
    }

    private Object bnse() {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        l.forEach((b)->{
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("jumlah", ""+b.getJumlah());
            o.put("nomer", ""+b.getNomer());
            a.add(o);
        });return a;
    }

    public String getPeg() {
        return peg;
    }

    public void setPeg(String peg) {
        this.peg = peg;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public java.time.Month getBln() {
        return bln;
    }

    public void setBln(java.time.Month bln) {
        this.bln = bln;
    }

    public java.time.Year getThn() {
        return thn;
    }

    public void setThn(java.time.Year thn) {
        this.thn = thn;
    }

    public java.util.List<Bonusan> getL() {
        return l;
    }

    public void setL(java.util.List<Bonusan> l) {
        this.l = l;
        l.sort(sorter());
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private Comparator<? super Bonusan> sorter() {
        return (Bonusan o1, Bonusan o2) -> {
            int x;
            if(o1.getNomer()>o2.getNomer())x=-1;
            else if(o1.getNomer()<o2.getNomer())x=1;
            else x=0;
            return x;
        };
    }
}