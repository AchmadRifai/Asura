/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui;

import achmad.rifai.erp1.entity.Absen;
import java.time.Month;
import java.util.List;

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
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("barang");
        java.util.List<achmad.rifai.erp1.entity.Barang>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            org.apache.poi.xssf.usermodel.XSSFRow r=s.getRow(x);
            achmad.rifai.erp1.entity.Barang b=new achmad.rifai.erp1.entity.Barang();
            b.setKode(r.getCell(0).getStringCellValue());
            b.setNama(r.getCell(1).getStringCellValue());
            b.setHarga(org.joda.money.Money.parse(r.getCell(2).getStringCellValue()));
            b.setStok(Integer.parseInt(r.getCell(3).getStringCellValue()));
            b.setSatuan(r.getCell(4).getStringCellValue());
            b.setDeleted(Boolean.parseBoolean(r.getCell(5).getStringCellValue()));
            x++;st=s.getRow(x).getCell(0).getStringCellValue();l.add(b);
        }progBarang.setValue(50);for(int c=0;c<l.size();x++){
            new achmad.rifai.erp1.entity.dao.DAOBarang(d).insert(l.get(c));
            progBarang.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progBarang.setValue(100);
    }

    private void absen() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("absen");
        java.util.List<achmad.rifai.erp1.entity.BukuAbsen>l=new java.util.LinkedList<>();
        int x=2;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.BukuAbsen b=new achmad.rifai.erp1.entity.BukuAbsen();
            int y=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y);boolean trus=true;
            b.setTgl(r1.getCell(0).getStringCellValue());
            b.setDeleted(Boolean.parseBoolean(r1.getCell(3).getStringCellValue()));
            java.util.List<achmad.rifai.erp1.entity.Absen>l2=new java.util.LinkedList<>();
            while(trus||null==r2.getCell(0)){
                trus=false;
                Absen a=new Absen();
                a.setS(r2.getCell(1).getStringCellValue());
                a.setL(Absen.Jenise.valueOf(r2.getCell(2).getStringCellValue()));
                l2.add(a);y++;r2=s.getRow(y);
            }b.setL(l2);l.add(b);x=y+1;st=s.getRow(x).getCell(0).getStringCellValue();
        }progAbsen.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOBukuAbsen(d).insert(l.get(c));
            progAbsen.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progAbsen.setValue(100);
    }

    private void jabatan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("jabatan");
        java.util.List<achmad.rifai.erp1.entity.Jabatan>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            org.apache.poi.xssf.usermodel.XSSFRow r=s.getRow(x);
            achmad.rifai.erp1.entity.Jabatan j=new achmad.rifai.erp1.entity.Jabatan();
            j.setNama(r.getCell(0).getStringCellValue());
            j.setGaji(org.joda.money.Money.parse(r.getCell(1).getStringCellValue()));
            j.setKapasitas(Integer.parseInt(r.getCell(2).getStringCellValue()));
            j.setDeleted(Boolean.parseBoolean(r.getCell(3).getStringCellValue()));
            x++;st=s.getRow(x).getCell(0).getStringCellValue();l.add(j);
        }progJabatan.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOJabatan(d).insert(l.get(c));
            progJabatan.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progJabatan.setValue(100);
    }

    private void jurnal() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("jurnal");
        java.util.List<achmad.rifai.erp1.entity.Jurnal>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            org.apache.poi.xssf.usermodel.XSSFRow r=s.getRow(x);
            achmad.rifai.erp1.entity.Jurnal j=new achmad.rifai.erp1.entity.Jurnal();
            j.setKode(r.getCell(0).getStringCellValue());j.setTgl(java.sql.Date.valueOf(r.getCell(1).getStringCellValue()));
            j.setKet(r.getCell(2).getStringCellValue());j.setNo(Integer.parseInt(r.getCell(3).getStringCellValue()));
            j.setDeleted(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));
            j.setKredit(org.joda.money.Money.parse(r.getCell(5).getStringCellValue()));
            j.setDebit(org.joda.money.Money.parse(r.getCell(6).getStringCellValue()));x++;st=s.getRow(x).getCell(0).getStringCellValue();l.add(j);
        }progJurnal.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOJurnal(d).insert(l.get(c));
            progJurnal.setValue(50+((50*(1+c))/l.size()));
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progJurnal.setValue(100);
    }

    private void karyawan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("karyawan");
        java.util.List<achmad.rifai.erp1.entity.Karyawan>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Karyawan k=new achmad.rifai.erp1.entity.Karyawan();
            int y=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y);
            k.setId(r1.getCell(0).getStringCellValue());k.setNama(r1.getCell(1).getStringCellValue());boolean trus=true;
            List<String>l2=new java.util.LinkedList<>();while(trus||null==r2.getCell(0)){
                l2.add(r2.getCell(2).getStringCellValue());y++;r2=s.getRow(y);trus=false;
            }k.setAlamat(l2);k.setPass(r1.getCell(3).getStringCellValue());k.setEmail(r1.getCell(4).getStringCellValue());
            k.setJabatan(r1.getCell(5).getStringCellValue());k.setHiredate(java.sql.Date.valueOf(r1.getCell(6).getStringCellValue()));
            k.setTelp(r1.getCell(7).getStringCellValue());k.setMasuk(Boolean.parseBoolean(r1.getCell(8).getStringCellValue()));
            k.setBlocked(Boolean.parseBoolean(r1.getCell(9).getStringCellValue()));
            k.setDeleted(Boolean.parseBoolean(r1.getCell(10).getStringCellValue()));x=y+1;st=s.getRow(x).getCell(0).getStringCellValue();l.add(k);
        }progKaryawan.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOKaryawan(d).insert(l.get(c));
            progKaryawan.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progKaryawan.setValue(100);
    }

    private void keluar() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("expenses");
        List<achmad.rifai.erp1.entity.Keluar>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Keluar k=new achmad.rifai.erp1.entity.Keluar();
            org.apache.poi.xssf.usermodel.XSSFRow r=s.getRow(x);
            k.setKode(r.getCell(0).getStringCellValue());
            k.setTgl(org.joda.time.DateTime.parse(r.getCell(1).getStringCellValue()));
            k.setJurnal(r.getCell(2).getStringCellValue());
            k.setUang(org.joda.money.Money.parse(r.getCell(3).getStringCellValue()));
            k.setDeleted(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));
            x++;l.add(k);st=s.getRow(x).getCell(0).getStringCellValue();
        }progKeluar.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOKeluar(d).insert(l.get(c));
            progKeluar.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progKeluar.setValue(100);
    }

    private void ledger() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("ledger");
        List<achmad.rifai.erp1.entity.Ledger>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Ledger le=new achmad.rifai.erp1.entity.Ledger();
            org.apache.poi.xssf.usermodel.XSSFRow r=s.getRow(x);
            le.setKode(r.getCell(0).getStringCellValue());
            le.setTgl(java.sql.Date.valueOf(r.getCell(1).getStringCellValue()));
            le.setKet(r.getCell(2).getStringCellValue());
            le.setNo(Integer.parseInt(r.getCell(3).getStringCellValue()));
            le.setDebit(org.joda.money.Money.parse(r.getCell(4).getStringCellValue()));
            le.setKredit(org.joda.money.Money.parse(r.getCell(5).getStringCellValue()));
            le.setDeleted(Boolean.parseBoolean(r.getCell(6).getStringCellValue()));x++;st=s.getRow(x).getCell(0).getStringCellValue();l.add(le);
        }progLedger.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOLedger(d).insert(l.get(c));
            progLedger.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progLedger.setValue(100);
    }

    private void pelanggan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("pelanggan");
        List<achmad.rifai.erp1.entity.Pelanggan>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Pelanggan p=new achmad.rifai.erp1.entity.Pelanggan();boolean trus=true;
            int y=x,c,z=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y),r3=s.getRow(z);
            List<String>ls1=new java.util.LinkedList<>(),ls2=new java.util.LinkedList<>();
            while(trus||(null==r2.getCell(0)&&!r2.getCell(2).getStringCellValue().isEmpty())){
                ls1.add(r2.getCell(2).getStringCellValue());trus=false;y++;r2=s.getRow(y);
            }p.setAlamat(ls1);trus=true;while(trus||(null==r3.getCell(0)&&!r3.getCell(3).getStringCellValue().isEmpty())){
                ls2.add(r3.getCell(3).getStringCellValue());trus=false;z++;r3=s.getRow(z);
            }p.setTelp(ls2);if(y<z)c=z; else c=y;p.setKode(r1.getCell(0).getStringCellValue());p.setNama(r1.getCell(1).getStringCellValue());
            p.setBlocked(Boolean.parseBoolean(r1.getCell(4).getStringCellValue()));
            p.setDeleted(Boolean.parseBoolean(r1.getCell(5).getStringCellValue()));l.add(p);x=c+1;st=s.getRow(x).getCell(0).getStringCellValue();
        }progPelanggan.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOPelanggan(d).insert(l.get(c));
            progPelanggan.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progPelanggan.setValue(100);
    }

    private void pembelian() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("pembelian");
        List<achmad.rifai.erp1.entity.Pembelian>l=new java.util.LinkedList<>();
        int x=2;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Pembelian p=new achmad.rifai.erp1.entity.Pembelian();
            int y=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y);
            List<achmad.rifai.erp1.entity.ItemBeli>l1=new java.util.LinkedList<>();
            boolean trus=true;while(trus||r2.getCell(0)==null){
                achmad.rifai.erp1.entity.ItemBeli i=new achmad.rifai.erp1.entity.ItemBeli();
                i.setBarang(r2.getCell(3).getStringCellValue());i.setJumlah(Integer.parseInt(r2.getCell(4).getStringCellValue()));
                i.setSatuan(r2.getCell(5).getStringCellValue());i.setHarga(org.joda.money.Money.parse(r2.getCell(6).getStringCellValue()));
                trus=false;y++;l1.add(i);r2=s.getRow(y);
            }p.setItems(l1);p.setStruk(r1.getCell(0).getStringCellValue());p.setSuplier(r1.getCell(1).getStringCellValue());
            p.setTgl(java.sql.Date.valueOf(r1.getCell(2).getStringCellValue()));
            p.setHarga(org.joda.money.Money.parse(r1.getCell(7).getStringCellValue()));
            p.setDeleted(Boolean.parseBoolean(r1.getCell(8).getStringCellValue()));l.add(p);x=y+1;st=s.getRow(x).getCell(0).getStringCellValue();
        }progPembelian.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOPembelian(d).insert(l.get(c));
            progPembelian.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progPembelian.setValue(100);
    }

    private void penjualan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("penjualan");
        List<achmad.rifai.erp1.entity.Penjualan>l=new java.util.LinkedList<>();
        int x=2;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Penjualan p=new achmad.rifai.erp1.entity.Penjualan();
            int y=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y);
            List<achmad.rifai.erp1.entity.ItemJual>l1=new java.util.LinkedList<>();boolean trus=true;while(trus||null==r2.getCell(0)){
                achmad.rifai.erp1.entity.ItemJual i=new achmad.rifai.erp1.entity.ItemJual();i.setBarang(r2.getCell(3).getStringCellValue());
                i.setJumlah(Integer.parseInt(r2.getCell(4).getStringCellValue()));
                i.setUang(org.joda.money.Money.parse(r2.getCell(5).getStringCellValue()));trus=false;l1.add(i);y++;r2=s.getRow(y);
            }p.setItems(l1);p.setNota(r1.getCell(0).getStringCellValue());p.setPelanggan(r1.getCell(1).getStringCellValue());
            p.setTgl(java.sql.Date.valueOf(r1.getCell(2).getStringCellValue()));
            p.setTotal(org.joda.money.Money.parse(r1.getCell(6).getStringCellValue()));
            p.setTerbayar(org.joda.money.Money.parse(r1.getCell(7).getStringCellValue()));p.setKet(r1.getCell(8).getStringCellValue());
            p.setDeleted(Boolean.parseBoolean(r1.getCell(9).getStringCellValue()));x=y+1;st=s.getRow(x).getCell(0).getStringCellValue();l.add(p);
        }progPenjualan.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOPenjualan(d).insert(l.get(c));
            progPenjualan.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progPenjualan.setValue(100);
    }

    private void pesan() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("pesan");
        List<achmad.rifai.erp1.entity.Pesan>l=new java.util.LinkedList<>();
        int x=2;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Pesan p=new achmad.rifai.erp1.entity.Pesan();
            int y=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y);
            List<achmad.rifai.erp1.entity.Penerima>l1=new java.util.LinkedList<>();boolean trus=true;while(trus||null==r2.getCell(0)){
                achmad.rifai.erp1.entity.Penerima pe=new achmad.rifai.erp1.entity.Penerima();pe.setAkun(r2.getCell(3).getStringCellValue());
                pe.setTerbaca(Boolean.parseBoolean(r2.getCell(4).getStringCellValue()));trus=false;y++;r2=s.getRow(y);l1.add(pe);
            }p.setKe(l1);p.setKode(r1.getCell(0).getStringCellValue());p.setPengirim(r1.getCell(1).getStringCellValue());
            p.setWaktu(org.joda.time.DateTime.parse(r1.getCell(2).getStringCellValue()));p.setPesan(r1.getCell(5).getStringCellValue());
            p.setDeleted(Boolean.parseBoolean(r1.getCell(6).getStringCellValue()));x=y+1;st=s.getRow(x).getCell(0).getStringCellValue();l.add(p);
        }progPesan.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOPesan(d).insert(l.get(c));
            progPesan.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progPesan.setValue(100);
    }

    private void rekening() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("Rekening");
        List<achmad.rifai.erp1.entity.Rekening>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Rekening r=new achmad.rifai.erp1.entity.Rekening();
            org.apache.poi.xssf.usermodel.XSSFRow ro=s.getRow(x);r.setKode(ro.getCell(0).getStringCellValue());
            r.setGolongan(ro.getCell(1).getStringCellValue());r.setPosisi(ro.getCell(2).getStringCellValue());
            r.setKet(ro.getCell(3).getStringCellValue());r.setDeleted(Boolean.parseBoolean(ro.getCell(4).getStringCellValue()));
            x++;st=s.getRow(x).getCell(0).getStringCellValue();l.add(r);
        }progRekening.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAORekening(d).insert(l.get(c));
            progRekening.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progRekening.setValue(100);
    }

    private void suplier() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("Pemasok");
        List<achmad.rifai.erp1.entity.Suplier>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Suplier su=new achmad.rifai.erp1.entity.Suplier();
            int y=x,c,z=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y),r3=s.getRow(z);
            List<String>l1=new java.util.LinkedList<>(),l2=new java.util.LinkedList<>();boolean trus=true;
            while(trus||(null==r2.getCell(0)&&!r2.getCell(2).getStringCellValue().isEmpty())){
                trus=false;l1.add(r2.getCell(2).getStringCellValue());y++;r2=s.getRow(y);
            }su.setAlamat(l1);trus=true;while(trus||(null==r3.getCell(0)&&!r3.getCell(3).getStringCellValue().isEmpty())){
                trus=false;l2.add(r3.getCell(3).getStringCellValue());z++;r3=s.getRow(z);
            }su.setTelp(l2);if(y>z)c=y; else c=z;su.setKode(r1.getCell(0).getStringCellValue());su.setNama(r1.getCell(1).getStringCellValue());
            su.setDeleted(Boolean.parseBoolean(r1.getCell(4).getStringCellValue()));l.add(su);x=c+1;st=s.getRow(x).getCell(0).getStringCellValue();
        }progSuplier.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOSuplier(d).insert(l.get(c));
            progSuplier.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progSuplier.setValue(100);
    }

    private void income() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("Pemasukan");
        List<achmad.rifai.erp1.entity.Terima>l=new java.util.LinkedList<>();
        int x=1;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Terima t=new achmad.rifai.erp1.entity.Terima();
            org.apache.poi.xssf.usermodel.XSSFRow r=s.getRow(x);
            t.setKode(r.getCell(0).getStringCellValue());t.setJurnal(r.getCell(1).getStringCellValue());
            t.setTgl(org.joda.time.DateTime.parse(r.getCell(2).getStringCellValue()));
            t.setUang(org.joda.money.Money.parse(r.getCell(3).getStringCellValue()));
            t.setDeleted(Boolean.parseBoolean(r.getCell(4).getStringCellValue()));
            x++;st=s.getRow(x).getCell(0).getStringCellValue();l.add(t);
        }progIncome.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOTerima(d).insert(l.get(c));
            progIncome.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progIncome.setValue(100);
    }

    private void tracks() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("Buku Perilaku");
        List<achmad.rifai.erp1.entity.Tracks>l=new java.util.LinkedList<>();
        int x=2;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Tracks t=new achmad.rifai.erp1.entity.Tracks();
            int y=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y);
            List<achmad.rifai.erp1.entity.Jejak>l1=new java.util.LinkedList<>();boolean trus=true;while(trus||null==r2.getCell(0)){
                achmad.rifai.erp1.entity.Jejak j=new achmad.rifai.erp1.entity.Jejak();
                j.setAksi(r2.getCell(2).getStringCellValue());
                j.setWaktu(org.joda.time.DateTime.parse(r2.getCell(3).getStringCellValue()));
                l1.add(j);trus=false;y++;r2=s.getRow(y);
            }t.setL(l1);t.setKode(r1.getCell(0).getStringCellValue());t.setId(r1.getCell(1).getStringCellValue());
            t.setBln(Month.valueOf(r1.getCell(4).getStringCellValue()));t.setTahun(Integer.parseInt(r1.getCell(5).getStringCellValue()));
            t.setDeleted(Boolean.parseBoolean(r1.getCell(6).getStringCellValue()));l.add(t);x=y+1;st=s.getRow(x).getCell(0).getStringCellValue();
        }progTrack.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOTracks(d).insert(l.get(c));
            progTrack.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progTrack.setValue(100);
    }

    private void tugas() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("Tugas");
        List<achmad.rifai.erp1.entity.Tugas>l=new java.util.LinkedList<>();
        int x=2;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.Tugas t=new achmad.rifai.erp1.entity.Tugas();
            int y=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y);
            List<achmad.rifai.erp1.entity.Petugas>l1=new java.util.LinkedList<>();boolean trus=true;while(trus||null==r2.getCell(0)){
                achmad.rifai.erp1.entity.Petugas p=new achmad.rifai.erp1.entity.Petugas();p.setKaryawan(r2.getCell(3).getStringCellValue());
                p.setDiambil(Boolean.parseBoolean(r2.getCell(4).getStringCellValue()));
                p.setSedang(Boolean.parseBoolean(r2.getCell(5).getStringCellValue()));
                p.setTerlaksana(Boolean.parseBoolean(r2.getCell(6).getStringCellValue()));l1.add(p);y++;r2=s.getRow(y);trus=false;
            }t.setL(l1);t.setKode(r1.getCell(0).getStringCellValue());t.setNo(Integer.parseInt(r1.getCell(1).getStringCellValue()));
            t.setTgl(java.sql.Date.valueOf(r1.getCell(2).getStringCellValue()));t.setKet(r1.getCell(7).getStringCellValue());
            t.setBatal(Boolean.parseBoolean(r1.getCell(8).getStringCellValue()));t.setPending(Boolean.parseBoolean(r1.getCell(9).getStringCellValue()));
            t.setDeleted(Boolean.parseBoolean(r1.getCell(10).getStringCellValue()));x=y+1;l.add(t);st=s.getRow(x).getCell(0).getStringCellValue();
        }progTugas.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOTugas(d).insert(l.get(c));
            progTugas.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progTugas.setValue(100);
    }

    private void bonus() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook(f);
        org.apache.poi.xssf.usermodel.XSSFSheet s=w.getSheet("bonus");
        List<achmad.rifai.erp1.entity.BulanBonus>l=new java.util.LinkedList<>();
        int x=2;String st=s.getRow(x).getCell(0).getStringCellValue();while(!st.isEmpty()){
            achmad.rifai.erp1.entity.BulanBonus b=new achmad.rifai.erp1.entity.BulanBonus();
            int y=x;org.apache.poi.xssf.usermodel.XSSFRow r1=s.getRow(x),r2=s.getRow(y);
            List<achmad.rifai.erp1.entity.Bonusan>l1=new java.util.LinkedList<>();boolean trus=true;while(trus||null==r2.getCell(0)){
                achmad.rifai.erp1.entity.Bonusan bo=new achmad.rifai.erp1.entity.Bonusan();
                bo.setNomer(Integer.parseInt(r2.getCell(2).getStringCellValue()));
                bo.setJumlah(org.joda.money.Money.parse(r2.getCell(3).getStringCellValue()));l1.add(bo);y++;r2=s.getRow(y);trus=false;
            }b.setL(l1);b.setKode(r1.getCell(0).getStringCellValue());b.setPeg(r1.getCell(1).getStringCellValue());
            b.setBln(Month.valueOf(r1.getCell(4).getStringCellValue()));b.setThn(java.time.Year.parse(r1.getCell(5).getStringCellValue()));
            b.setDeleted(Boolean.parseBoolean(r1.getCell(6).getStringCellValue()));l.add(b);x=y+1;st=s.getRow(x).getCell(0).getStringCellValue();
        }progBonus.setValue(50);for(int c=0;c<l.size();c++){
            new achmad.rifai.erp1.entity.dao.DAOBulanBonus(d).insert(l.get(c));
            progBonus.setValue((((1+c)*50)/l.size())+50);
        }d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }progBonus.setValue(100);
    }
}