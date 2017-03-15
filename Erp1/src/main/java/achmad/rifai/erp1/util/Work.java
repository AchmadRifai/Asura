/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in 

the editor.
 */
package achmad.rifai.erp1.util;

import achmad.rifai.erp1.beans.DBSetting;
import achmad.rifai.erp1.entity.dao.DAOJabatan;
import java.io.IOException;
import java.security.GeneralSecurityException;
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
        return bi.toString(36);
    }

    public static void saveDbs(DBSetting dbs) throws GeneralSecurityException, IOException, ClassNotFoundException {
        RSA r=loadRSA();
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put(Work.MD5("host"), r.encrypt(dbs.getHost()));
        o.put(Work.MD5("port"), r.encrypt(""+dbs.getPort()));
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
        return new Db(dbs.getHost(),dbs.getName(),dbs.getPort());
    }

    private static DBSetting loadDBS() throws GeneralSecurityException, IOException, ParseException, ClassNotFoundException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        java.io.File f=new java.io.File(System.getProperty("user.home")+"/.asura/work/dbs");
        RSA r=loadRSA();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(new java.io.FileReader(f));
        DBSetting dbs=new DBSetting();
        dbs.setHost(r.decrypt(""+o.get(Work.MD5("host"))));
        dbs.setName(r.decrypt(""+o.get(Work.MD5("name"))));
        dbs.setPort(Integer.parseInt(r.decrypt(""+o.get(Work.MD5("port")))));
        return dbs;
    }

    public static void initDb(String host, String name, int port) throws Exception {
        Db d=new Db(host,name,port);
        java.io.File f1=new java.io.File(System.getProperty("user.home")+"/.asura/work/pub");
        while(!f1.exists()){}
        insertJabatAdmin(d);
        insertKaryaAdmin(d);
        d.close();
    }

    private static void insertJabatAdmin(Db d) throws Exception {
        DAOJabatan dao=new DAOJabatan(d.getD());
        java.util.List<achmad.rifai.erp1.entity.Jabatan>l=dao.all();
        if(l.isEmpty()){
            achmad.rifai.erp1.entity.Jabatan j=new achmad.rifai.erp1.entity.Jabatan();
            j.setGaji(org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
            j.setKapasitas(3);
            j.setNama("admin");
            dao.insert(j);
        }
    }

    private static void insertKaryaAdmin(Db d) throws Exception {
        achmad.rifai.erp1.entity.dao.DAOKaryawan dao=new achmad.rifai.erp1.entity.dao.DAOKaryawan(d.getD());
        java.util.List<achmad.rifai.erp1.entity.Karyawan>l=dao.all();
        if(l.isEmpty()){
            achmad.rifai.erp1.entity.Karyawan k=new achmad.rifai.erp1.entity.Karyawan();
            k.setAlamat(new java.util.LinkedList<>());
            k.setBlocked(false);
            k.setBonus(new java.util.LinkedList<achmad.rifai.erp1.entity.Bonusan>());
            k.setDeleted(false);
            k.setEmail("achmad.rifai.jowo.asli@gmail.com");
            k.setHiredate(org.joda.time.DateTime.now());
            k.setId("asura");
            k.setJabatan("admin");
            k.setMasuk(false);
            k.setNama("asura");
            k.setPass("adminerp");
            k.setTelp("0");
            dao.insert(k);
            jejakadmin(d);
        }
    }

    private static void jejakadmin(Db d) throws Exception {
        achmad.rifai.erp1.entity.Jejak j=new achmad.rifai.erp1.entity.Jejak("mengaktifkan sistem", "bathara");
        new achmad.rifai.erp1.entity.dao.DAOJejak(d.getD()).insert(j);
    }

    private static void style(String s) throws ClassNotFoundException, InstantiationException, IllegalAccessException, 
            UnsupportedLookAndFeelException {
        for(javax.swing.UIManager.LookAndFeelInfo l:javax.swing.UIManager.getInstalledLookAndFeels())
            if(s == null ? l.getName() == null : s.equals(l.getName())){
            javax.swing.UIManager.setLookAndFeel(l.getClassName());
            break;
        }
    }
}