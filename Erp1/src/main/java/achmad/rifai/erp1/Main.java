/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1;

import achmad.rifai.erp1.entity.Bonusan;
import achmad.rifai.erp1.entity.ItemBeli;
import achmad.rifai.erp1.entity.ItemJual;
import achmad.rifai.erp1.entity.Penerima;
import achmad.rifai.erp1.entity.Petugas;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.money.CurrencyUnit;

/**
 *string = 525
 * 1000(duwek) = 525
 * saiki(wayah) = 525
 * saiki(tgl) = 525
 * float = 525
 * @author ai
 */
public class Main {
    public static void main(String[]args)throws Exception{
        /*try {
            achmad.rifai.erp1.util.Work.styling();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            mlaku();
            }
            });*/
        
        /*achmad.rifai.erp1.entity.Penjualan p=new achmad.rifai.erp1.entity.Penjualan("anonim", "biasane");
        p.setItems(Main.genJualan());
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        String s=p.toString();
        System.out.println(s);
        List<String>ls=Main.spliting(s),enc=new java.util.LinkedList<>();
        for(String st:ls)enc.add(r.encrypt(st));
        enc.forEach((st)->{System.out.println(st);});
        s="";
        for(String st:enc)s+=r.decrypt(st);
        System.out.println(s);
        p=new achmad.rifai.erp1.entity.Penjualan(s);
        System.out.println(p.getTgl());
        System.out.println(p.getTotal());
        System.out.println(p.isDeleted());
        System.out.println(p.getItems().get(0).getUang());*/
        jajal1();
        //jajal2();
        //jajal3();
    }

    private static void mlaku() {
        new achmad.rifai.erp1.ui.Splash().setVisible(true);
    }

    private static List<Penerima> genPenerima() {
        List<Penerima>l=new java.util.LinkedList<>();
        Penerima p1=new Penerima(),p2=new Penerima(),p3=new Penerima(),p4=new Penerima(),p5=new Penerima();
        p1.setAkun("puntadewa");
        p1.setTerbaca(false);
        p2.setAkun("werkudara");
        p2.setTerbaca(false);
        p3.setAkun("janoko");
        p3.setTerbaca(false);
        p4.setAkun("nakula");
        p4.setTerbaca(false);
        p5.setAkun("sadewa");
        p5.setTerbaca(false);
        l.add(p1);
        l.add(p2);
        l.add(p3);
        l.add(p4);
        l.add(p5);
        return l;
    }

    public static List<String> spliting(String s) {
        List<String>l=new java.util.ArrayList<>();
        int b=s.length()/250;
        for(int x=0;x<=b;x++){
            int c=x*250,p=c+250;
            if(p>=s.length())l.add(s.substring(c,s.length()));
            else l.add(s.substring(c, p));
        }return l;
    }

    private static List<ItemJual> genJualan() {
        List<ItemJual>l=new java.util.LinkedList<>();
        ItemJual i1=new ItemJual();
        i1.setBarang("sabun");
        i1.setJumlah(2);
        i1.setUang(org.joda.money.Money.of(CurrencyUnit.of("IDR"), 2000));
        l.add(i1);
        return l;
    }

    private static List<ItemBeli> genPembelian() {
        List<ItemBeli>l=new java.util.LinkedList<>();
        ItemBeli i=new ItemBeli();
        i.setBarang("sepatu");
        i.setSatuan("pasang");
        i.setHarga(org.joda.money.Money.of(CurrencyUnit.of("IDR"), 200000));
        i.setJumlah(10);
        l.add(i);
        return l;
    }

    private static List<String> genALamat() {
        List<String>l=new java.util.LinkedList<>();
        l.add("Hastina Pura");
        l.add("Indrakila");
        l.add("Madukara");
        return l;
    }

    private static List<Petugas> genPetugas() {
        List<Petugas>l=new java.util.LinkedList<>();
        Petugas p1=new Petugas(),p2=new Petugas(),p3=new Petugas(),p4=new Petugas(),p5=new Petugas();
        p1.setDiambil(true);
        p2.setDiambil(true);
        p3.setDiambil(true);
        p4.setDiambil(true);
        p5.setDiambil(true);
        p1.setTerlaksana(false);
        p2.setTerlaksana(false);
        p3.setTerlaksana(false);
        p4.setTerlaksana(false);
        p5.setTerlaksana(false);
        p1.setSedang(true);
        p2.setSedang(true);
        p3.setSedang(true);
        p4.setSedang(true);
        p5.setSedang(true);
        p1.setKaryawan("Puntadewa");
        p2.setKaryawan("Werkudara");
        p3.setKaryawan("Janoko");
        p4.setKaryawan("Nakulo");
        p5.setKaryawan("Sadewo");
        l.add(p1);
        l.add(p2);
        l.add(p3);
        l.add(p4);
        l.add(p5);
        return l;
    }

    private static void jajal1() {
        try {
            achmad.rifai.erp1.beans.DBSetting dbs=new achmad.rifai.erp1.beans.DBSetting();
            dbs.setHost("localhost");
            dbs.setName("asura");
            achmad.rifai.erp1.util.Work.saveDbs(dbs);
            achmad.rifai.erp1.util.Work.initDb(dbs.getHost(), dbs.getName());
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.entity.dao.DAOKaryawan dao=new achmad.rifai.erp1.entity.dao.DAOKaryawan(d);
            dao.all().forEach((k)->{
            System.out.println(k.getJabatan());
            System.out.println(k.getNama());
            System.out.println(k.getId());
            });
            d.close();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void jajal3() {
        try {
            achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
            String s=r.encrypt("aku cinta kamu");
            System.out.println(s);
            System.out.println(s.length());
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
