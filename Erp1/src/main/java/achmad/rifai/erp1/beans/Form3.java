/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.beans;

import com.mongodb.DBObject;

/**
 *
 * @author ai
 */
public class Form3 {
    private String kode1,kode2,kode3;
    private java.util.List<String>data;

    public Form3(String k1,String k2,String k3,Object o) throws Exception{
        kode1=k1;
        kode2=k2;
        kode3=k3;
        data=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(String s:achmad.rifai.erp1.Main.spliting(o.toString()))
            data.add(r.encrypt(s));
    }

    public Form3() {
    }

    public achmad.rifai.erp1.entity.Pembelian toPembelian()throws Exception{
        String json="";
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(String s:data)json+=r.decrypt(s);
        return new achmad.rifai.erp1.entity.Pembelian(json);
    }

    public String getKode1() {
        return kode1;
    }

    public void setKode1(String kode1) {
        this.kode1 = kode1;
    }

    public String getKode2() {
        return kode2;
    }

    public void setKode2(String kode2) {
        this.kode2 = kode2;
    }

    public String getKode3() {
        return kode3;
    }

    public void setKode3(String kode3) {
        this.kode3 = kode3;
    }

    public java.util.List<String> getData() {
        return data;
    }

    public void setData(java.util.List<String> data) {
        this.data = data;
    }

    public DBObject genComparator() {
        DBObject o=new com.mongodb.BasicDBObject();
        com.mongodb.BasicDBList l=new com.mongodb.BasicDBList();
        data.forEach((s)->{l.add(s);});
        o.put("bin", l);
        o.put("berkas1", kode1);
        o.put("berkas2", kode2);
        o.put("berkas3", kode3);
        return o;
    }
}