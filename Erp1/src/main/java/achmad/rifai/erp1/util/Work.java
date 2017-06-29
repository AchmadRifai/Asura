/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in 

the editor.
 */
package achmad.rifai.erp1.util;

import achmad.rifai.erp1.beans.DBSetting;
import achmad.rifai.erp1.entity.Jabatan;
import achmad.rifai.erp1.entity.Jejak;
import achmad.rifai.erp1.entity.Karyawan;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.UnsupportedLookAndFeelException;
import org.joda.money.CurrencyUnit;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Work {
    public static RSA loadRSA() throws GeneralSecurityException, IOException {
        return new RSA(new java.io.File(System.getProperty("user.home")+"/.asura/work/pri"),
                new java.io.File(System.getProperty("user.home")+"/.asura/work/pub"));
    }

    public static void styling() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        javax.swing.UIManager.setLookAndFeel(new com.seaglasslookandfeel.SeaGlassLookAndFeel());
    }

    public static String MD5(String toString) throws GeneralSecurityException{
        java.security.MessageDigest md=java.security.MessageDigest.getInstance("MD5");
        java.math.BigInteger bi=new java.math.BigInteger(md.digest(toString.getBytes()));
        return bi.toString(36);
    }

    public static void saveDbs(DBSetting dbs) throws Exception {
        RSA r=loadRSA();
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put(Work.MD5("host"), r.encrypt(dbs.getHost()));
        o.put(Work.MD5("name"), r.encrypt(dbs.getName()));
        java.io.File f=new java.io.File(System.getProperty("user.home")+"/.asura/work/dbs");
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        java.io.FileWriter w=new java.io.FileWriter(f);
        o.writeJSONString(w);
        w.close();
        hiding();
    }

    public static Db loadDB() throws Exception {
        DBSetting dbs=loadDBS();
        return new Db(dbs.getHost(),dbs.getName());
    }

    private static DBSetting loadDBS() throws GeneralSecurityException, IOException, ParseException, ClassNotFoundException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        java.io.File f=new java.io.File(System.getProperty("user.home")+"/.asura/work/dbs");
        RSA r=loadRSA();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(new java.io.FileReader(f));
        DBSetting dbs=new DBSetting();
        dbs.setHost(r.decrypt(""+o.get(Work.MD5("host"))));
        dbs.setName(r.decrypt(""+o.get(Work.MD5("name"))));
        return dbs;
    }

    public static void initDb(String host, String name) throws Exception {
        Db d=new Db(host,name);
        java.io.File f1=new java.io.File(System.getProperty("user.home")+"/.asura/work/pri"),
                f2=new java.io.File(System.getProperty("user.home")+"/.asura/work/pub");
        while(!f1.exists()||!f2.exists()){}
        createData(d);
        d.close();
    }

    private static void createData(Db d) throws Exception {
        jabatanData(d);
        karyawanData(d);
    }

    private static void jabatanData(Db d) throws Exception {
        achmad.rifai.erp1.entity.dao.DAOJabatan dao=new achmad.rifai.erp1.entity.dao.DAOJabatan(d);
        if(0==dao.all().size()){
            List<Jabatan>l=listJabatan();
            for(Jabatan j:l){
                Jabatan b=Jabatan.of(d, j.getNama());
                if(b==null)dao.insert(j);
                else dao.update(b, j);
            }
        }
    }

    private static List<Jabatan> listJabatan() {
        List<Jabatan>l=new java.util.LinkedList<>();
        Jabatan j1=new Jabatan(),j2=new Jabatan(),j3=new Jabatan(),j4=new Jabatan();
        j4.setDeleted(false);
        j4.setGaji(org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
        j4.setKapasitas(1);
        j4.setNama("pemilik");
        j4.setIsi(0);
        j3.setIsi(0);
        j2.setIsi(0);
        j1.setIsi(0);
        l.add(j4);
        j3.setDeleted(false);
        j3.setGaji(org.joda.money.Money.of(CurrencyUnit.of("IDR"), 7500000));
        j3.setKapasitas(5);
        j3.setNama("Keuangan");
        l.add(j3);
        j2.setDeleted(false);
        j2.setGaji(org.joda.money.Money.of(CurrencyUnit.of("IDR"), 2750000));
        j2.setKapasitas(30);
        j2.setNama("pergudangan");
        l.add(j2);
        j1.setKapasitas(3);
        j1.setNama("admin");
        j1.setGaji(org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
        j1.setDeleted(false);
        l.add(j1);
        return l;
    }

    private static void karyawanData(Db d) throws Exception {
        achmad.rifai.erp1.entity.dao.DAOKaryawan dao=new achmad.rifai.erp1.entity.dao.DAOKaryawan(d);
        if(0==dao.all().size()){
            List<Karyawan>l=listKaryawan();
            for(Karyawan k:l){
                dao.insert(k);
                if("admin".equals(k.getJabatan()))jejake(k,d);
                else jejakbiasa(k,d);
            }
        }
    }

    private static List<Karyawan> listKaryawan() {
        List<Karyawan>l=new java.util.LinkedList<>();
        Karyawan k1=new Karyawan();
        k1.setAlamat(new java.util.LinkedList<>());
        k1.setBlocked(false);
        k1.setDeleted(false);
        k1.setEmail("achmad.rifai.jowo.asli@gmail.com");
        k1.setHiredate(java.sql.Date.valueOf(LocalDate.now()));
        k1.setId("asura");
        k1.setJabatan("admin");
        k1.setMasuk(false);
        k1.setNama("asura");
        k1.setPass("god's");
        k1.setTelp("0");
        l.add(k1);
        return l;
    }

    private static List<Jejak> jejakPertama(String id) {
        List<Jejak>l=new java.util.LinkedList<>();
        l.add(new Jejak("Memulai System",id));
        l.add(new Jejak("Terdaftar",id));
        return l;
    }

    private static void jejake(Karyawan k,Db d) throws Exception{
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks t=dao.current(k.getId()),t2=dao.current(k.getId());
        t2.setL(jejakPertama(k.getId()));
        dao.update(t, t2);
        ralatJabatane(k,d);
    }

    private static void jejakbiasa(Karyawan k, Db d) throws Exception {
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks t=dao.current(k.getId()),t2=dao.current(k.getId());
        List<Jejak>l=new java.util.LinkedList<>();
        l.add(new Jejak("Terdaftar",k.getId()));
        t2.setL(l);
        dao.update(t, t2);
        ralatJabatane(k,d);
    }

    public static void saveSession(String s)throws Exception{
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        RSA r=loadRSA();
        o.put(Work.MD5("achmad"), r.encrypt(s));
        o.put(Work.MD5("rifa'i"), r.encrypt(""+org.joda.time.DateTime.now()));
        java.io.File f=new java.io.File(System.getProperty("user.home")+"/.asura/work/jejak");
        java.io.FileWriter w=new java.io.FileWriter(f);
        o.writeJSONString(w);
        w.close();
    }

    private static void hiding() throws Exception{
        java.io.File f=new java.io.File(System.getProperty("user.home")+"/.asura");
        if(System.getProperty("os.name").contains("Windows")){
            Process p=Runtime.getRuntime().exec("attrib +h "+f.getAbsolutePath());
            p.waitFor();
        }
    }

    private static void ralatJabatane(Karyawan k, Db d) throws Exception {
        Jabatan j=Jabatan.of(d, k.getJabatan()),b=Jabatan.of(d, k.getJabatan());
        achmad.rifai.erp1.entity.dao.DAOJabatan dao=new achmad.rifai.erp1.entity.dao.DAOJabatan(d);
        b.setIsi(b.getIsi()+1);
        dao.update(j, b);
    }
}