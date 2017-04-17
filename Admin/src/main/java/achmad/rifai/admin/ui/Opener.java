/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui;

import achmad.rifai.erp1.entity.Absen;
import achmad.rifai.erp1.entity.ItemBeli;
import achmad.rifai.erp1.entity.ItemJual;
import achmad.rifai.erp1.entity.Jejak;
import achmad.rifai.erp1.entity.Penerima;
import achmad.rifai.erp1.entity.Petugas;
import java.time.Month;
import java.time.Year;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Opener extends javax.swing.JDialog {
private String id;
private java.util.concurrent.AbstractExecutorService a;
private java.io.File f;
    /**
     * Creates new form Opener
     */
    public Opener(java.awt.Frame parent, boolean modal,String i) {
        super(parent, modal);
        id=i;
        a=new java.util.concurrent.ScheduledThreadPoolExecutor(15);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        progBarang = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        progAbsen = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        progJabatan = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        progJurnal = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        progKaryawan = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        progKeluar = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        progLedger = new javax.swing.JProgressBar();
        jLabel8 = new javax.swing.JLabel();
        progPelanggan = new javax.swing.JProgressBar();
        jLabel9 = new javax.swing.JLabel();
        progPembelian = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        progPenjualan = new javax.swing.JProgressBar();
        jLabel11 = new javax.swing.JLabel();
        progPesan = new javax.swing.JProgressBar();
        jLabel12 = new javax.swing.JLabel();
        progRekening = new javax.swing.JProgressBar();
        jLabel13 = new javax.swing.JLabel();
        progSuplier = new javax.swing.JProgressBar();
        jLabel14 = new javax.swing.JLabel();
        progIncome = new javax.swing.JProgressBar();
        jLabel15 = new javax.swing.JLabel();
        progTrack = new javax.swing.JProgressBar();
        jLabel16 = new javax.swing.JLabel();
        progTugas = new javax.swing.JProgressBar();
        jLabel17 = new javax.swing.JLabel();
        progBonus = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Opener File");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jLabel1.setText("Barang");
        getContentPane().add(jLabel1);
        getContentPane().add(progBarang);

        jLabel2.setText("Absen");
        getContentPane().add(jLabel2);
        getContentPane().add(progAbsen);

        jLabel3.setText("Jabatan");
        getContentPane().add(jLabel3);
        getContentPane().add(progJabatan);

        jLabel4.setText("Jurnal");
        getContentPane().add(jLabel4);
        getContentPane().add(progJurnal);

        jLabel5.setText("Karyawan");
        getContentPane().add(jLabel5);
        getContentPane().add(progKaryawan);

        jLabel6.setText("Expenses");
        getContentPane().add(jLabel6);
        getContentPane().add(progKeluar);

        jLabel7.setText("Ledger");
        getContentPane().add(jLabel7);
        getContentPane().add(progLedger);

        jLabel8.setText("Pelanggan");
        getContentPane().add(jLabel8);
        getContentPane().add(progPelanggan);

        jLabel9.setText("Pembelian");
        getContentPane().add(jLabel9);
        getContentPane().add(progPembelian);

        jLabel10.setText("Penjualan");
        getContentPane().add(jLabel10);
        getContentPane().add(progPenjualan);

        jLabel11.setText("Pesan");
        getContentPane().add(jLabel11);
        getContentPane().add(progPesan);

        jLabel12.setText("Rekening");
        getContentPane().add(jLabel12);
        getContentPane().add(progRekening);

        jLabel13.setText("Suplier");
        getContentPane().add(jLabel13);
        getContentPane().add(progSuplier);

        jLabel14.setText("Income");
        getContentPane().add(jLabel14);
        getContentPane().add(progIncome);

        jLabel15.setText("Buku Jejak");
        getContentPane().add(jLabel15);
        getContentPane().add(progTrack);

        jLabel16.setText("Tugas");
        getContentPane().add(jLabel16);
        getContentPane().add(progTugas);

        jLabel17.setText("Bonus");
        getContentPane().add(jLabel17);
        getContentPane().add(progBonus);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(() -> {
            jalan();
        }).start();
    try {
        achmad.rifai.admin.util.Work.jejak(id, "Menulis berkas back up ke pusat data");
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }//GEN-LAST:event_formWindowOpened
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar progAbsen;
    private javax.swing.JProgressBar progBarang;
    private javax.swing.JProgressBar progBonus;
    private javax.swing.JProgressBar progIncome;
    private javax.swing.JProgressBar progJabatan;
    private javax.swing.JProgressBar progJurnal;
    private javax.swing.JProgressBar progKaryawan;
    private javax.swing.JProgressBar progKeluar;
    private javax.swing.JProgressBar progLedger;
    private javax.swing.JProgressBar progPelanggan;
    private javax.swing.JProgressBar progPembelian;
    private javax.swing.JProgressBar progPenjualan;
    private javax.swing.JProgressBar progPesan;
    private javax.swing.JProgressBar progRekening;
    private javax.swing.JProgressBar progSuplier;
    private javax.swing.JProgressBar progTrack;
    private javax.swing.JProgressBar progTugas;
    // End of variables declaration//GEN-END:variables

    private void jalan() {
        javax.swing.JFileChooser fc=new javax.swing.JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel File(*.xlsx)", "xlsx"));
        fc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        fc.setFileHidingEnabled(true);
        fc.setMultiSelectionEnabled(false);
        while(javax.swing.JFileChooser.APPROVE_OPTION!=fc.showOpenDialog(rootPane)||null==fc.getSelectedFile()){}
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));f=fc.getSelectedFile();next();
    }

    private void next() {
        a.execute(this::barang);
        a.execute(this::absen);
        a.execute(this::jabatan);
        a.execute(this::jurnal);
        a.execute(this::karyawan);
        a.execute(this::keluar);
        a.execute(this::bonus);
        a.execute(this::ledger);
        a.execute(this::pelanggan);
        a.execute(this::pembelian);
        a.execute(this::penjualan);
        a.execute(this::pesan);
        a.execute(this::rekening);
        a.execute(this::suplier);
        a.execute(this::income);
        a.execute(this::tracks);
        a.execute(this::tugas);
        while(!a.isTerminated()){}
        this.setVisible(false);
    }

    private void barang() {
    try {
        java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("barang");
        Iterator<Row>i1=s.iterator();achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        java.util.List<achmad.rifai.erp1.entity.Barang>l=new java.util.LinkedList<>();
        boolean skip=true;while(i1.hasNext()){
            if(skip){
                skip=false;continue;
            }achmad.rifai.erp1.entity.Barang b=new achmad.rifai.erp1.entity.Barang();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            b.setDeleted(Boolean.parseBoolean(r.getCell(5).getStringCellValue()));
            b.setHarga(org.joda.money.Money.parse(r.getCell(2).getStringCellValue()));
            b.setKode(r.getCell(0).getStringCellValue());
            b.setNama(r.getCell(1).getStringCellValue());
            b.setSatuan(r.getCell(4).getStringCellValue());
            b.setStok(Integer.parseInt(r.getCell(3).getStringCellValue()));l.add(b);
        }for(int x=0;x<l.size();x++){
            achmad.rifai.erp1.entity.Barang b=l.get(x);
            new achmad.rifai.erp1.entity.dao.DAOBarang(d).insert(b);
            progBarang.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void absen() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("absen");
        Iterator<Row>i1=s.iterator();java.util.List<achmad.rifai.erp1.entity.BukuAbsen>l=new java.util.LinkedList<>();
        boolean skip=true;while(i1.hasNext()){
            if(skip){
                skip=false;continue;
            }achmad.rifai.erp1.entity.BukuAbsen b=new achmad.rifai.erp1.entity.BukuAbsen();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            b.setDeleted(Boolean.parseBoolean(r.getCell(2).getStringCellValue()));
            b.setTgl(r.getCell(0).getStringCellValue());
            b.setL(genListAbsen(r.getCell(1).getStringCellValue()));
            l.add(b);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOBukuAbsen(d).insert(l.get(x));
            progAbsen.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private List<Absen> genListAbsen(String s) throws ParseException {
        List<Absen>l=new java.util.LinkedList<>();
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s);
        for(int x=0;x<a.size();x++){
            Absen ab=new Absen();
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            ab.setL(Absen.Jenise.valueOf(""+o.get("l")));
            ab.setS(""+o.get("s"));
            l.add(ab);
        }return l;
    }

    private void jabatan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("jabatan");
        Iterator<Row>i1=s.iterator();java.util.List<achmad.rifai.erp1.entity.Jabatan>l=new java.util.LinkedList<>();
        boolean skip=true;while(i1.hasNext()){
            if(skip){
                skip=false;continue;
            }achmad.rifai.erp1.entity.Jabatan j=new achmad.rifai.erp1.entity.Jabatan();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            j.setDeleted(Boolean.parseBoolean(r.getCell(3).getStringCellValue()));
            j.setGaji(org.joda.money.Money.parse(r.getCell(1).getStringCellValue()));
            j.setKapasitas(Integer.parseInt(r.getCell(2).getStringCellValue()));
            j.setNama(r.getCell(0).getStringCellValue());l.add(j);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOJabatan(d).insert(l.get(x));
            progJabatan.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void jurnal() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("jurnal");
        Iterator<Row>i1=s.iterator();java.util.List<achmad.rifai.erp1.entity.Jurnal>l=new java.util.LinkedList<>();
        boolean skip=true;while(i1.hasNext()){
            if(skip){
                skip=false;continue;
            }achmad.rifai.erp1.entity.Jurnal j=new achmad.rifai.erp1.entity.Jurnal();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            j.setDebit(org.joda.money.Money.parse(r.getCell(6).getStringCellValue()));
            j.setDeleted(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));
            j.setKet(r.getCell(2).getStringCellValue());
            j.setKode(r.getCell(0).getStringCellValue());
            j.setKredit(org.joda.money.Money.parse(r.getCell(5).getStringCellValue()));
            j.setNo(Integer.parseInt(r.getCell(3).getStringCellValue()));
            j.setTgl(java.sql.Date.valueOf(r.getCell(1).getStringCellValue()));l.add(j);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOJurnal(d).insert(l.get(x));
            progJurnal.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void karyawan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("karyawan");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Karyawan>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Karyawan k=new achmad.rifai.erp1.entity.Karyawan();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            k.setId(r.getCell(0).getStringCellValue());k.setNama(r.getCell(1).getStringCellValue());k.setPass(r.getCell(2).getStringCellValue());
            k.setJabatan(r.getCell(3).getStringCellValue());k.setEmail(r.getCell(4).getStringCellValue());
            k.setAlamat(generateAlamat(r.getCell(5).getStringCellValue()));k.setTelp(r.getCell(6).getStringCellValue());
            k.setHiredate(java.sql.Date.valueOf(r.getCell(7).getStringCellValue()));
            k.setBlocked(Boolean.parseBoolean(r.getCell(9).getStringCellValue()));
            k.setMasuk(Boolean.parseBoolean(r.getCell(10).getStringCellValue()));
            k.setDeleted(Boolean.parseBoolean(r.getCell(11).getStringCellValue()));l.add(k);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOKaryawan(d).insert(l.get(x));
            progKaryawan.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private List<String> generateAlamat(String s) throws ParseException {
        List<String>l=new java.util.LinkedList<>();
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s);
        for(int x=0;x<a.size();x++)l.add(""+a.get(x));
        return l;
    }

    private void keluar() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("expenses");Iterator<Row>i1=s.iterator();
        java.util.List<achmad.rifai.erp1.entity.Keluar>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Keluar k=new achmad.rifai.erp1.entity.Keluar();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            k.setKode(r.getCell(0).getStringCellValue());k.setTgl(org.joda.time.DateTime.parse(r.getCell(1).getStringCellValue()));
            k.setJurnal(r.getCell(2).getStringCellValue());k.setUang(org.joda.money.Money.parse(r.getCell(3).getStringCellValue()));
            k.setDeleted(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));l.add(k);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOKeluar(d).insert(l.get(x));
            progKeluar.setValue((x*100)/l.size());
        }d.close();i.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void ledger() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("ledger");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Ledger>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Ledger le=new achmad.rifai.erp1.entity.Ledger();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            le.setKode(r.getCell(0).getStringCellValue());le.setTgl(java.sql.Date.valueOf(r.getCell(1).getStringCellValue()));
            le.setKet(r.getCell(2).getStringCellValue());le.setNo(Integer.parseInt(r.getCell(3).getStringCellValue()));
            le.setDebit(org.joda.money.Money.parse(r.getCell(4).getStringCellValue()));
            le.setKredit(org.joda.money.Money.parse(r.getCell(5).getStringCellValue()));
            le.setDeleted(Boolean.parseBoolean(r.getCell(6).getStringCellValue()));l.add(le);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOLedger(d).insert(l.get(x));
            progLedger.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void pelanggan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("pelanggan");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Pelanggan>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Pelanggan p=new achmad.rifai.erp1.entity.Pelanggan();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            p.setKode(r.getCell(0).getStringCellValue());p.setNama(r.getCell(1).getStringCellValue());
            p.setAlamat(generateAlamat(r.getCell(2).getStringCellValue()));p.setTelp(generateAlamat(r.getCell(3).getStringCellValue()));
            p.setBlocked(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));
            p.setDeleted(Boolean.parseBoolean(r.getCell(5).getStringCellValue()));l.add(p);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOPelanggan(d).insert(l.get(x));
            progPelanggan.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void pembelian() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("pembelian");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Pembelian>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Pembelian p=new achmad.rifai.erp1.entity.Pembelian();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            p.setStruk(r.getCell(0).getStringCellValue());p.setSuplier(r.getCell(1).getStringCellValue());
            p.setTgl(java.sql.Date.valueOf(r.getCell(2).getStringCellValue()));p.setItems(genBeli(r.getCell(4).getStringCellValue()));
            p.setHarga(org.joda.money.Money.parse(r.getCell(3).getStringCellValue()));
            p.setDeleted(Boolean.parseBoolean(r.getCell(5).getStringCellValue()));l.add(p);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOPembelian(d).insert(l.get(x));
            progPembelian.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private List<ItemBeli> genBeli(String s) throws ParseException {
        List<ItemBeli>l=new java.util.LinkedList<>();
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s);
        for(int x=0;x<a.size();x++){
            ItemBeli i=new ItemBeli();
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            i.setBarang(""+o.get("barang"));
            i.setSatuan(""+o.get("satuan"));
            i.setJumlah(Integer.parseInt(""+o.get("jumlah")));
            i.setHarga(org.joda.money.Money.parse(""+o.get("harga")));l.add(i);
        }return l;
    }

    private void penjualan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("penjualan");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Penjualan>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Penjualan p=new achmad.rifai.erp1.entity.Penjualan();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            p.setNota(r.getCell(0).getStringCellValue());p.setPelanggan(r.getCell(1).getStringCellValue());
            p.setItems(genJual(r.getCell(2).getStringCellValue()));p.setTgl(java.sql.Date.valueOf(r.getCell(3).getStringCellValue()));
            p.setTerbayar(org.joda.money.Money.parse(r.getCell(4).getStringCellValue()));p.setKet(r.getCell(6).getStringCellValue());
            p.setTotal(org.joda.money.Money.parse(r.getCell(5).getStringCellValue()));
            p.setDeleted(Boolean.parseBoolean(r.getCell(7).getStringCellValue()));l.add(p);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOPenjualan(d).insert(l.get(x));
            progPenjualan.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private List<ItemJual> genJual(String s) throws ParseException {
        List<ItemJual>l=new java.util.LinkedList<>();
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s);
        for(int x=0;x<a.size();x++){
            ItemJual i=new ItemJual();
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            i.setBarang(""+o.get("barang"));
            i.setJumlah(Integer.parseInt(""+o.get("jumlah")));
            i.setUang(org.joda.money.Money.parse(""+o.get("uang")));l.add(i);
        }return l;
    }

    private void pesan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("pesan");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Pesan>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Pesan p=new achmad.rifai.erp1.entity.Pesan();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            p.setKode(r.getCell(0).getStringCellValue());p.setPengirim(r.getCell(1).getStringCellValue());
            p.setPesan(r.getCell(2).getStringCellValue());p.setWaktu(org.joda.time.DateTime.parse(r.getCell(3).getStringCellValue()));
            p.setKe(genPenerima(r.getCell(4).getStringCellValue()));p.setDeleted(Boolean.parseBoolean(r.getCell(5).getStringCellValue()));l.add(p);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOPesan(d).insert(l.get(x));
            progPesan.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private List<Penerima> genPenerima(String s) throws ParseException {
        List<Penerima>l=new java.util.LinkedList<>();
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s);
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Penerima pe=new Penerima();
            pe.setAkun(""+o.get("akun"));
            pe.setTerbaca(Boolean.parseBoolean(""+o.get("terbaca")));
            l.add(pe);
        }return l;
    }

    private void rekening() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("aset");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Rekening>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Rekening re=new achmad.rifai.erp1.entity.Rekening();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            re.setKode(r.getCell(0).getStringCellValue());re.setPosisi(r.getCell(1).getStringCellValue());
            re.setGolongan(r.getCell(2).getStringCellValue());re.setKet(r.getCell(3).getStringCellValue());
            re.setDeleted(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));l.add(re);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAORekening(d).insert(l.get(x));
            progRekening.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void suplier() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("suplier");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Suplier>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Suplier su=new achmad.rifai.erp1.entity.Suplier();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            su.setKode(r.getCell(0).getStringCellValue());su.setNama(r.getCell(1).getStringCellValue());
            su.setAlamat(generateAlamat(r.getCell(2).getStringCellValue()));su.setTelp(generateAlamat(r.getCell(3).getStringCellValue()));
            su.setDeleted(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));l.add(su);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOSuplier(d).insert(l.get(x));
            progSuplier.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void income() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("income");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Terima>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Terima t=new achmad.rifai.erp1.entity.Terima();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            t.setKode(r.getCell(0).getStringCellValue());t.setTgl(org.joda.time.DateTime.parse(r.getCell(1).getStringCellValue()));
            t.setJurnal(r.getCell(2).getStringCellValue());t.setUang(org.joda.money.Money.parse(r.getCell(3).getStringCellValue()));
            t.setDeleted(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));l.add(t);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOTerima(d).insert(l.get(x));
            progIncome.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void tracks() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet(id);Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Tracks>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Tracks t=new achmad.rifai.erp1.entity.Tracks();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            t.setKode(r.getCell(0).getStringCellValue());t.setId(r.getCell(1).getStringCellValue());
            t.setBln(Month.valueOf(r.getCell(2).getStringCellValue()));t.setTahun(Year.parse(r.getCell(3).getStringCellValue()));
            t.setL(genJejak(r.getCell(4).getStringCellValue()));t.setDeleted(Boolean.parseBoolean(r.getCell(5).getStringCellValue()));l.add(t);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOTracks(d).insert(l.get(x));
            progTrack.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private List<Jejak> genJejak(String s) throws ParseException {
        List<Jejak>l=new java.util.LinkedList<>();
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s);
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Jejak j=new Jejak();
            j.setAksi(""+o.get("aksi"));
            j.setWaktu(org.joda.time.DateTime.parse(""+o.get("waktu")));
            l.add(j);
        }return l;
    }

    private void tugas() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();java.io.FileInputStream i=new java.io.FileInputStream(f);
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(i);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("tugas");Iterator<Row>i1=s.iterator();
        List<achmad.rifai.erp1.entity.Tugas>l=new java.util.LinkedList<>();boolean sk=true;while(i1.hasNext()){
            if(sk){
                sk=false;continue;
            }achmad.rifai.erp1.entity.Tugas t=new achmad.rifai.erp1.entity.Tugas();
            org.apache.poi.xssf.usermodel.XSSFRow r=(org.apache.poi.xssf.usermodel.XSSFRow) i1.next();
            t.setKode(r.getCell(0).getStringCellValue());t.setNo(Integer.parseInt(r.getCell(1).getStringCellValue()));
            t.setTgl(java.sql.Date.valueOf(r.getCell(2).getStringCellValue()));t.setL(genPetugas(r.getCell(3).getStringCellValue()));
            t.setPending(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));t.setBatal(Boolean.parseBoolean(r.getCell(5).getStringCellValue()));
            t.setKet(r.getCell(6).getStringCellValue());t.setDeleted(Boolean.parseBoolean(r.getCell(7).getStringCellValue()));l.add(t);
        }for(int x=0;x<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOTugas(d).insert(l.get(x));
            progTugas.setValue((x*100)/l.size());
        }i.close();d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private List<Petugas> genPetugas(String s) throws ParseException {
        List<Petugas>l=new java.util.LinkedList<>();
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONArray a=(org.json.simple.JSONArray) p.parse(s);
        for(int x=0;x<a.size();x++){
            org.json.simple.JSONObject o=(org.json.simple.JSONObject) a.get(x);
            Petugas pe=new Petugas();
            pe.setKaryawan(""+o.get("karyawan"));
            pe.setDiambil(Boolean.parseBoolean(""+o.get("diambil")));
            pe.setSedang(Boolean.parseBoolean(""+o.get("sedang")));
            pe.setTerlaksana(Boolean.parseBoolean(""+o.get("terlaksana")));
            l.add(pe);
        }return l;
    }

    private void bonus() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("bonus");
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }
}