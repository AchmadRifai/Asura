/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.time.Month;
import java.time.Year;
import java.util.List;
import org.joda.time.DateTime;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Karyawan{
    public static Karyawan of(Db d, String id)throws Exception{
        Karyawan v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(com.datastax.driver.core.Row ro:d.getS().execute(
        QueryBuilder.select("bin").from("karyawan").where(QueryBuilder.eq("berkas", id)))){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            v=new Karyawan(json);
        }return v;
    }

    private String id,nama,pass,telp,email,jabatan;
    private boolean masuk,deleted,blocked;
    private List<String> alamat;
    private DateTime hiredate;
    private List<Bonusan>bonus;

    public Karyawan(String s) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(s);
        id=""+o.get("id");
        nama=""+o.get("nama");
        pass=""+o.get("pass");
        telp=""+o.get("telp");
        email=""+o.get("email");
        jabatan=""+o.get("jabatan");
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        blocked=Boolean.parseBoolean(""+o.get("blocked"));
        masuk=Boolean.parseBoolean(""+o.get("masuk"));
        hiredate=org.joda.time.DateTime.parse(""+o.get("hiredate"));
        alamatObject(o.get("alamat"));
        bonusObject(o.get("bonus"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("id", id);
        o.put("nama", nama);
        o.put("pass", pass);
        o.put("telp", telp);
        o.put("email", email);
        o.put("jabatan", jabatan);
        o.put("masuk", ""+masuk);
        o.put("deleted", ""+deleted);
        o.put("blocked", ""+blocked);
        o.put("hiredate", ""+hiredate);
        o.put("alamat", alamatJSON());
        o.put("bonus", bonusJSON());
        return o.toJSONString();
    }

    private void alamatObject(Object g){
        alamat=new java.util.LinkedList<>();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) g;
        for(int x=0;x<a.size();x++)alamat.add(""+a.get(x));
    }

    private void bonusObject(Object g){
        bonus=new java.util.LinkedList<>();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) g;
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Bonusan b=new Bonusan();
            b.setBulan(Month.valueOf(""+o.get("bulan")));
            b.setJumlah(org.joda.money.Money.parse(""+o.get("jumlah")));
            b.setNomer(Integer.parseInt(""+o.get("nomer")));
            b.setTahun(Year.parse(""+o.get("tahun")));
            bonus.add(b);
        }
    }

    private Object alamatJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(String s:alamat)a.add(s);
        return a;
    }

    private Object bonusJSON(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        for(Bonusan b:bonus){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("bulan", ""+b.getBulan());
            o.put("jumlah", ""+b.getJumlah());
            o.put("nomer", ""+b.getNomer());
            o.put("tahun", ""+b.getTahun());
            a.add(o);
        }return a;
    }

    public List<Bonusan> getBonus() {
        return bonus;
    }

    public void setBonus(List<Bonusan> bonus) {
        this.bonus = bonus;
    }

    public DateTime getHiredate() {
        return hiredate;
    }

    public void setHiredate(DateTime hiredate) {
        this.hiredate = hiredate;
    }

    public Karyawan(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public boolean isMasuk() {
        return masuk;
    }

    public void setMasuk(boolean masuk) {
        this.masuk = masuk;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public java.util.List<String> getAlamat() {
        return alamat;
    }

    public void setAlamat(java.util.List<String> alamat) {
        this.alamat = alamat;
    }
}