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

    private static void jejak(String id, String aksi) throws Exception {
        Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.Jejak j=new achmad.rifai.erp1.entity.Jejak(aksi, id);
        new achmad.rifai.erp1.entity.dao.DAOJejak(d.getD()).insert(j);
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
        achmad.rifai.erp1.entity.dao.DAOKaryawan dao=new achmad.rifai.erp1.entity.dao.DAOKaryawan(d.getD());
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
        achmad.rifai.erp1.entity.dao.DAOJabatan dao=new achmad.rifai.erp1.entity.dao.DAOJabatan(d.getD());
        for(achmad.rifai.erp1.entity.Jabatan j:dao.all())m.addRow(new Object[]{j.getNama(),j.getGaji(),j.getKapasitas()});
        d.close();
    }

    public static void readAbsen(JTable tblData, String id) throws Exception {
        jejak(id,"Melihat Data Absen");
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"KARYAWAN","TANGGAL","KET"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOAbsen dao=new achmad.rifai.erp1.entity.dao.DAOAbsen(d.getD());
        for(achmad.rifai.erp1.entity.Absen a:dao.all())m.addRow(new Object[]{a.getS(),a.getTgl(),a.getL()});
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
        achmad.rifai.erp1.entity.dao.DAOBarang dao=new achmad.rifai.erp1.entity.dao.DAOBarang(d.getD());
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
        achmad.rifai.erp1.entity.dao.DAOKeluar dao=new achmad.rifai.erp1.entity.dao.DAOKeluar(d.getD());
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
        achmad.rifai.erp1.entity.dao.DAOLedger dao=new achmad.rifai.erp1.entity.dao.DAOLedger(d.getD());
        for(achmad.rifai.erp1.entity.Ledger l:dao.all())m.addRow(new Object[]{l.getKode(),l.getTgl(),l.getKet(),l.getNo(),l.getDebit(),
        l.getKredit()});d.close();
    }

    public static void readJejak(JTable tblData, String id) throws Exception {
        jejak(id,"Melihat Data Jejak");
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Pelaku","Aksi","Waktu"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOJejak dao=new achmad.rifai.erp1.entity.dao.DAOJejak(d.getD());
        for(achmad.rifai.erp1.entity.Jejak j:dao.all())m.addColumn(new Object[]{j.getPelaku(),j.getAksi(),j.getWaktu()});
        d.close();
    }
}