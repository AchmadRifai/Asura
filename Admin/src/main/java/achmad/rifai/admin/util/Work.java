/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.util;

import achmad.rifai.erp1.util.Db;
import javax.swing.JTable;

/**
 *
 * @author ai
 */
public class Work {
    public static void readKaryawan(JTable tblData, String id) throws Exception {
        Db d=achmad.rifai.erp1.util.Work.loadDB();
        jejak(id,"Melihat Data Karyawan");
        dataKaryawan(d,tblData);
        d.close();
    }

    public static void jejak(String id, String aksi) throws Exception {
        Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks t1=dao.current(id),t2=dao.current(id);
        java.util.List<achmad.rifai.erp1.entity.Jejak>l=t1.getL();
        l.add(new achmad.rifai.erp1.entity.Jejak(aksi, id));
        dao.update(t1, t2);
        d.close();
    }

    private static void dataKaryawan(Db d, JTable tblData) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"ID","NAMA","PASSWORD","TANGGAL DITERIMA",
            "JABATAN","E-MAIL","TELP","DICEKAL?","SEDANG LOGIN?"
        }, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }};
        tblData.setModel(m);
        achmad.rifai.erp1.entity.dao.DAOKaryawan dao=new achmad.rifai.erp1.entity.dao.DAOKaryawan(d);
        for(achmad.rifai.erp1.entity.Karyawan k:dao.all()){
            m.addRow(new Object[]{k.getId(),k.getNama(),k.getPass(),k.getHiredate(),k.getJabatan(),k.getEmail(),k.getTelp(),k.isBlocked(),
            k.isMasuk()});
        }
    }

    public static void readJabatan(JTable tblData, String id) throws Exception {
        jejak(id,"Melihat Data Jabatan");
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"NAMA","GAJI","KAPASITAS"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOJabatan dao=new achmad.rifai.erp1.entity.dao.DAOJabatan(d);
        for(achmad.rifai.erp1.entity.Jabatan j:dao.all())m.addRow(new Object[]{j.getNama(),j.getGaji(),j.getKapasitas()});
        d.close();
    }

    public static void readBarang(JTable tblData, String id) throws Exception {
        jejak(id,"Melihat Data Barang");
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"KODE","NAMA","HARGA","STOK"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOBarang dao=new achmad.rifai.erp1.entity.dao.DAOBarang(d);
        for(achmad.rifai.erp1.entity.Barang b:dao.all())m.addRow(new Object[]{b.getKode(),b.getNama(),b.getHarga(),""+b.getStok()+" "+
        b.getSatuan()});d.close();
    }

    public static void readKeluar(JTable tblData, String id) throws Exception {
        jejak(id,"Melihat Data Pengeluaran");
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"KODE","JURNAL","TANGGAL","UANG"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOKeluar dao=new achmad.rifai.erp1.entity.dao.DAOKeluar(d);
        for(achmad.rifai.erp1.entity.Keluar k:dao.all())m.addRow(new Object[]{k.getKode(),k.getJurnal(),k.getTgl(),k.getUang()});
        d.close();
    }

    public static void readLedger(JTable tblData, String id) throws Exception {
        jejak(id,"Melihat Data Buku Besar");
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Kode","Tanggal","Ket","No","Debit",
        "Kredit"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOLedger dao=new achmad.rifai.erp1.entity.dao.DAOLedger(d);
        for(achmad.rifai.erp1.entity.Ledger l:dao.all())m.addRow(new Object[]{l.getKode(),l.getTgl(),l.getKet(),l.getNo(),l.getDebit(),
        l.getKredit()});d.close();
    }

    public static void readPelanggan(JTable tblData, String id) throws Exception {
        jejak(id,"Melihat Data Pelanggan");
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Kode","Nama","Dicekal"}, 0){
            private Class[]c=new Class[]{String.class,String.class,Boolean.class};
            @Override
            public Class<?> getColumnClass(int x) {
                return c[x];
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOPelanggan dao=new achmad.rifai.erp1.entity.dao.DAOPelanggan(d);
        for(achmad.rifai.erp1.entity.Pelanggan p:dao.all())m.addRow(new Object[]{p.getKode(),p.getNama(),p.isBlocked()});
        d.close();
    }

    public static void readPembelian(JTable tblData, String id) throws Exception {
        jejak(id,"Melihat Data Pembelian");
    }
}