/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.beans;

import achmad.rifai.erp1.util.Work;
import com.mongodb.DBObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ai
 */
public class Form1 {
    private String kode;
    private java.util.List<String>data;

    public Form1(String k,Object o) throws Exception{
        achmad.rifai.erp1.util.RSA r=Work.loadRSA();
        data=new java.util.LinkedList<>();
        kode=k;
        for(String s:achmad.rifai.erp1.Main.spliting(o.toString()))data.add(r.encrypt(s));
    }

    private void parsing(String json) throws Exception {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        achmad.rifai.erp1.util.RSA r=Work.loadRSA();
        kode=r.decrypt(""+o.get("berkas"));
        data=new java.util.LinkedList<>();
        for(int x=0;x<o.size()-1;x++)
            data.add(r.decrypt(""+o.get("bin"+x)));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();try {
            achmad.rifai.erp1.util.RSA r=Work.loadRSA();
            o.put("berkas", r.encrypt(kode));
            for(int x=0;x<data.size();x++)
                o.put("bin"+x, r.encrypt(data.get(x)));
        } catch (Exception ex) {
            Logger.getLogger(Form1.class.getName()).log(Level.SEVERE, null, ex);
        }return o.toJSONString();
    }

    public Form1(String json) throws Exception {
        parsing(json);
    }

    public Form1() {
    }

    public achmad.rifai.erp1.entity.Karyawan toKaryawan()throws Exception{
        String s="";
        achmad.rifai.erp1.util.RSA r=Work.loadRSA();
        for(String st:data)s+=r.decrypt(st);
        return new achmad.rifai.erp1.entity.Karyawan(s);
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public java.util.List<String> getData() {
        return data;
    }

    public void setData(java.util.List<String> data) {
        this.data = data;
    }

    public DBObject genComparasion() {
        DBObject o=new com.mongodb.BasicDBObject();
        com.mongodb.BasicDBList l=new com.mongodb.BasicDBList();
        data.forEach((s)->{l.add(s);});
        o.put("berkas", kode);
        o.put("bin", l);
        return o;
    }
}