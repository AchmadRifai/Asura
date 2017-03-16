/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin;

import achmad.rifai.erp1.entity.Karyawan;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ai
 */
public class Main {
    public static void main(String[]args){
        /*try {
            achmad.rifai.erp1.util.Work.styling();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                jalan();
            }
        });*/
        jajal();
    }

    private static void jalan() {
        new achmad.rifai.erp1.ui.Splash(new achmad.rifai.admin.ui.Login()).setVisible(true);
    }

    private static void jajal() {
        try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.util.Work.initDb(d.getHost(), d.getName(), d.getPort());
            Karyawan k=new Karyawan(d.getD(), "asura");
            String s1=s1(k),s2=s2(k),s3=s3(k),s4=s4(k);
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s3);
            System.out.println(s4);
            d.close();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String s1(Karyawan v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("id", v.getId());
        o.put("nama", v.getNama());
        o.put("jab", v.getJabatan());
        o.put("mail", v.getEmail());
        o.put("pass", v.getPass());
        return o.toJSONString();
    }

    private static String s2(Karyawan v) {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("block", v.isBlocked());
        o.put("del", v.isDeleted());
        o.put("masuk", v.isMasuk());
        o.put("hire", v.getHiredate());
        o.put("telp", v.getTelp());
        return o.toJSONString();
    }

    private static String s3(Karyawan v) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        if(null!=v.getAlamat())for(String s:v.getAlamat())a.add(s);
        return a.toJSONString();
    }

    private static String s4(Karyawan k) {
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        if(null!=k.getBonus())for(achmad.rifai.erp1.entity.Bonusan b:k.getBonus()){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
            o.put("bulan", b.getBulan());
            o.put("jumlah", b.getJumlah());
            o.put("nomer", b.getNomer());
            o.put("tahun", b.getTahun());
        }return a.toJSONString();
    }
}