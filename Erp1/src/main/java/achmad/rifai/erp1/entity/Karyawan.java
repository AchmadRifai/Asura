/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

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
    private String id,nama,pass,telp,email,jabatan;
    private boolean masuk,deleted,blocked;
    private List<String> alamat;
    private DateTime hiredate;
    private List<Bonusan>bonus;

    public Karyawan(String s1, String s2, String s3, String s4) throws ParseException {
        parsing1(s1);
        parsing2(s2);
        parsing3(s3);
        parsing4(s4);
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

    public Karyawan(com.mongodb.DB d,String id)throws Exception{
        this.id=id;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject o1=new com.mongodb.BasicDBObject();
        o1.put(achmad.rifai.erp1.util.Work.MD5("kode"), r.encrypt(id));
        com.mongodb.DBCursor c=d.getCollection("karyawan").find(o1);
        for(com.mongodb.DBObject o:c.toArray(1)){
            parsing1(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))));
            parsing2(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("next"))));
            parsing3(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("alamat"))));
            parsing4(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("bonus"))));
        }
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

    private void parsing1(String s1) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(s1);
        id=""+o.get("id");
        nama=""+o.get("nama");
        jabatan=""+o.get("jab");
        email=""+o.get("mail");
        pass=""+o.get("pass");
    }

    private void parsing2(String s2) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(s2);
        blocked=Boolean.parseBoolean(""+o.get("block"));
        deleted=Boolean.parseBoolean(""+o.get("del"));
        masuk=Boolean.parseBoolean(""+o.get("masuk"));
        hiredate=org.joda.time.DateTime.parse(""+o.get("hire"));
        telp=""+o.get("telp");
    }

    private void parsing3(String s3) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s3);
        alamat=new java.util.LinkedList<>();
        for(int x=0;x<a.size();x++)alamat.add(""+a.get(x));
    }

    private void parsing4(String s4) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s4);
        bonus=new java.util.LinkedList<>();
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
}