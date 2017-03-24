/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import com.datastax.driver.core.querybuilder.QueryBuilder;

/**
 *
 * @author ai
 */
public class Jabatan {
    public static Jabatan of(Db d, String nama) throws Exception{
        Jabatan v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("jabatan")
        .where(QueryBuilder.eq("berkas", nama)));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            v=new Jabatan(json);
        }return v;
    }

    private String nama;
    private org.joda.money.Money gaji;
    private int kapasitas;
    private boolean deleted;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public org.joda.money.Money getGaji() {
        return gaji;
    }

    public void setGaji(org.joda.money.Money gaji) {
        this.gaji = gaji;
    }

    public Jabatan(String json) throws Exception{
        parsing(json);
    }

    public Jabatan(){}

    private void parsing(String json) throws Exception {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        nama=""+o.get("nama");
        gaji=org.joda.money.Money.parse(""+o.get("gaji"));
        kapasitas=Integer.parseInt(""+o.get("kap"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("nama", nama);
        o.put("gaji", ""+gaji);
        o.put("kap", ""+kapasitas);
        o.put("deleted", ""+deleted);
        return o.toJSONString();
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}