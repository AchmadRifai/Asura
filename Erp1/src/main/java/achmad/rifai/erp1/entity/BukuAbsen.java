/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class BukuAbsen {
    public static BukuAbsen of(Db d, String tgl)throws Exception{
        BukuAbsen v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(QueryBuilder.select("bin").from("bukuabsen").where(QueryBuilder.eq("berkas", tgl)));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            v=new BukuAbsen(json);
        }return v;
    }

    private String tgl;
    private java.util.List<Absen>l;
    private boolean deleted;

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("tgl", tgl);
        o.put("deleted", ""+deleted);
        o.put("l", kontenJSON());
        return o.toJSONString();
    }

    public BukuAbsen(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        tgl=""+o.get("tgl");
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        kontenObject(o.get("l"));
    }

    private void kontenObject(Object get) {
        l=new java.util.LinkedList<>();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Absen v=new Absen();
            v.setL(Absen.Jenise.valueOf(""+o.get("l")));
            v.setS(""+o.get("s"));
            l.add(v);
        }
    }

    private Object kontenJSON() {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        l.forEach((v)->{
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("l", ""+v.getL());
            o.put("s", v.getS());
            a.add(o);
        });return a;
    }

    public BukuAbsen() {
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public java.util.List<Absen> getL() {
        return l;
    }

    public void setL(java.util.List<Absen> l) {
        this.l = l;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}