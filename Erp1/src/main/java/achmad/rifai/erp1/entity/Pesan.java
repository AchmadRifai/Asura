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
public class Pesan {
    public static Pesan of(Db d, String kode)throws Exception{
        Pesan v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(
                QueryBuilder.select("bin").from("pesan").where(QueryBuilder.eq("berkas", kode)))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            v=new Pesan(json);
        }return v;
    }

    private String pengirim,pesan,kode;
    private org.joda.time.DateTime waktu;
    private boolean deleted;
    private java.util.List<Penerima>ke;

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("pengirim", pengirim);
        o.put("pesan", pesan);
        o.put("kode", kode);
        o.put("waktu", ""+waktu);
        o.put("deleted", ""+deleted);
        o.put("ke", keJSON());
        return o.toJSONString();
    }

    public Pesan(String json) throws ParseException{
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        pengirim=""+o.get("pengirim");
        pesan=""+o.get("pesan");
        kode=""+o.get("kode");
        waktu=org.joda.time.DateTime.parse(""+o.get("waktu"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        keObject(o.get("ke"));
    }

    private Object keJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        ke.stream().map((p) -> {
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("terbaca", ""+p.isTerbaca());
            o.put("akun", p.getAkun());
            return o;
        }).forEachOrdered((o) -> {
            a.add(o);
        });return a;
    }

    private void keObject(Object get){
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) get;
        ke=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Penerima p=new Penerima();
            p.setAkun(""+o.get("akun"));
            p.setTerbaca(Boolean.parseBoolean(""+o.get("terbaca")));
            ke.add(p);
        }
    }

    public Pesan(String j1, String j2) throws ParseException {
        parsing1(j1);
        parsing2(j2);
    }

    public Pesan(String pesan,String pengirim,java.util.List<Penerima>l) {
        waktu=org.joda.time.DateTime.now();
        ke=l;
        this.pengirim=pengirim;
        this.pesan=pesan;
        deleted=false;
        kode=pengirim+waktu.getDayOfMonth()+waktu.getMonthOfYear()+waktu.getYear()+waktu.getHourOfDay()+waktu.getMinuteOfHour()+
                waktu.getSecondOfMinute()+waktu.getZone()+waktu.getMillis();
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public org.joda.time.DateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(org.joda.time.DateTime waktu) {
        this.waktu = waktu;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public java.util.List<Penerima> getKe() {
        return ke;
    }

    public void setKe(java.util.List<Penerima> ke) {
        this.ke = ke;
    }

    private void parsing1(String j1) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(j1);
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        waktu=org.joda.time.DateTime.parse(""+o.get("waktu"));
        kode=""+o.get("kode");
        pengirim=""+o.get("pengirim");
        pesan=""+o.get("pesan");
    }

    private void parsing2(String j2) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(j2);
        ke=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Penerima pe=new Penerima();
            pe.setAkun(""+o.get("akun"));
            pe.setTerbaca(Boolean.parseBoolean(""+o.get("terbaca")));
            ke.add(pe);
        }
    }
}