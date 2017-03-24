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
        String o=System.getProperty("os.name");
        if("Linux".equals(o))style("GTK+");
        else if("Windows".equals(o))style("Windows");
        else style("Nimbus");
    }

    public static String MD5(String toString) throws GeneralSecurityException{
        java.security.MessageDigest md=java.security.MessageDigest.getInstance("MD5");
        java.math.BigInteger bi=new java.math.BigInteger(md.digest(toString.getBytes()));
        return bi.toString(46);
    }

    public static void saveDbs(DBSetting dbs) throws GeneralSecurityException, IOException, ClassNotFoundException {
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
        Db d=new Db(host,"system");
        d.getS().execute("create keyspace if not exists asura with replication={'class':'SimpleStrategy','replication_factor':3};");
        d.setName(name);
        createDB(d);
        createData(d);
        d.close();
    }

    private static void style(String s) throws ClassNotFoundException, InstantiationException, IllegalAccessException, 
            UnsupportedLookAndFeelException {
        for(javax.swing.UIManager.LookAndFeelInfo l:javax.swing.UIManager.getInstalledLookAndFeels())
            if(s == null ? l.getName() == null : s.equals(l.getName())){
            javax.swing.UIManager.setLookAndFeel(l.getClassName());
            break;
        }
    }

    private static void createDB(Db d) throws Exception {
        new achmad.rifai.erp1.entity.dao.DAOBarang(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOJabatan(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOJurnal(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOKaryawan(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOKeluar(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOLedger(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOPelanggan(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOPembelian(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOPenjualan(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOPesan(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAORekening(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOSuplier(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOTerima(d).createTable();
        new achmad.rifai.erp1.entity.dao.DAOTugas(d).createTable();
    }

    private static void createData(Db d) throws Exception {
        java.io.File f1=new java.io.File(System.getProperty("user.home")+"/.asura/work/pub");
        while(!f1.exists()){}
        jabatanData(d);
        karyawanData(d);
    }

    private static void jabatanData(Db d) throws Exception {
        achmad.rifai.erp1.entity.dao.DAOJabatan dao=new achmad.rifai.erp1.entity.dao.DAOJabatan(d);
        if(0==dao.all().size()){
            List<Jabatan>l=listJabatan();
            for(Jabatan j:l)dao.insert(j);
        }
    }

    private static List<Jabatan> listJabatan() {
        List<Jabatan>l=new java.util.LinkedList<>();
        Jabatan j1=new Jabatan();
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
        k1.setBonus(new java.util.LinkedList<>());
        k1.setDeleted(false);
        k1.setEmail("achmad.rifai.jowo.asli@gmail.com");
        k1.setHiredate(org.joda.time.DateTime.now());
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
    }

    private static void jejakbiasa(Karyawan k, Db d) throws Exception {
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks t=dao.current(k.getId()),t2=dao.current(k.getId());
        List<Jejak>l=new java.util.LinkedList<>();
        l.add(new Jejak("Terdaftar",k.getId()));
        t2.setL(l);
        dao.update(t, t2);
    }
}