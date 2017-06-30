/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.util;

import achmad.rifai.erp1.entity.Barang;
import achmad.rifai.erp1.entity.Jabatan;
import achmad.rifai.erp1.entity.Karyawan;
import achmad.rifai.erp1.entity.Pelanggan;
import achmad.rifai.erp1.util.Db;
import javax.swing.JTable;

/**
 *
 * @author ai
 */
public class Work {
    public static void readKaryawan(JTable tblData, String id) throws Exception {
        Db d=achmad.rifai.erp1.util.Work.loadDB();
        dataKaryawan(d,tblData);
        d.close();
    }

    public static void jejak(String id, String aksi) throws Exception {
        Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks t1=dao.current(id),t2=dao.current(id);
        java.util.List<achmad.rifai.erp1.entity.Jejak>l=t1.getL();
        l.add(new achmad.rifai.erp1.entity.Jejak(aksi, id));
        t2.setL(l);
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
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"NAMA","GAJI","KAPASITAS"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOJabatan dao=new achmad.rifai.erp1.entity.dao.DAOJabatan(d);
        for(achmad.rifai.erp1.entity.Jabatan j:dao.all())m.addRow(new Object[]{j.getNama(),j.getGaji(),j.getKapasitas()-j.getIsi()});
        d.close();
    }

    public static void readBarang(JTable tblData, String id) throws Exception {
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
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"STRUK","SUPLIER","TANGGAL",
        "UANG YANG DIKELUARKAN"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOPembelian dao=new achmad.rifai.erp1.entity.dao.DAOPembelian(d);
        for(achmad.rifai.erp1.entity.Pembelian p:dao.all())m.addRow(new Object[]{p.getStruk(),p.getSuplier(),p.getTgl(),p.getHarga()});
        d.close();
    }

    public static void readPenjualan(JTable tblData, String id) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Nota","Pelanggan","Tanggal","Total",
        "UANG YANG DIBAYAR","Keterangan"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOPenjualan dao=new achmad.rifai.erp1.entity.dao.DAOPenjualan(d);
        for(achmad.rifai.erp1.entity.Penjualan p:dao.all())m.addRow(new Object[]{p.getNota(),p.getPelanggan(),p.getTgl(),p.getTotal(),
        p.getTerbayar(),p.getKet()});
        d.close();
    }

    public static void readPesan(JTable tblData, String id) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Kode","Isi Pesan","Pengirim",
        "Waktu"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOPesan dao=new achmad.rifai.erp1.entity.dao.DAOPesan(d);
        for(achmad.rifai.erp1.entity.Pesan p:dao.all())m.addColumn(new Object[]{p.getKode(),p.getPesan(),p.getPengirim(),p.getWaktu()});
        d.close();
    }

    public static void readAset(JTable tblData, String id) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Kode","Golongan","Posisi","Keterangan"
        }, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAORekening dao=new achmad.rifai.erp1.entity.dao.DAORekening(d);
        for(achmad.rifai.erp1.entity.Rekening r:dao.all())m.addRow(new Object[]{r.getKode(),r.getGolongan(),r.getPosisi(),r.getKet()});
        d.close();
    }

    public static void readSuplier(JTable tblData, String id) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Kode","Nama"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOSuplier dao=new achmad.rifai.erp1.entity.dao.DAOSuplier(d);
        for(achmad.rifai.erp1.entity.Suplier s:dao.all())m.addRow(new Object[]{s.getKode(),s.getNama()});
        d.close();
    }

    public static void readIncome(JTable tblData, String id) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"KODE","Tanggal","Jurnal","Jumlah"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOTerima dao=new achmad.rifai.erp1.entity.dao.DAOTerima(d);
        for(achmad.rifai.erp1.entity.Terima t:dao.all())m.addRow(new Object[]{t.getKode(),t.getTgl(),t.getJurnal(),t.getUang()});
        d.close();
    }

    public static void readTugas(JTable tblData, String id) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"KODE","URUTAN","TANGGAL","Keterangan",
        "Dibatalkan","Dihentikan"}, 0){
            private Class[]c=new Class[]{String.class,String.class,java.sql.Date.class,String.class,Boolean.class,Boolean.class};
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
        achmad.rifai.erp1.entity.dao.DAOTugas dao=new achmad.rifai.erp1.entity.dao.DAOTugas(d);
        for(achmad.rifai.erp1.entity.Tugas t:dao.all())
            m.addRow(new Object[]{t.getKode(),t.getNo(),t.getTgl(),t.getKet(),t.isBatal(),t.isPending()});
        d.close();
    }

    public static void readBukuAbsen(JTable tblData, String id) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Tanggal"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOBukuAbsen dao=new achmad.rifai.erp1.entity.dao.DAOBukuAbsen(d);
        for(achmad.rifai.erp1.entity.BukuAbsen b:dao.all())m.addRow(new Object[]{b.getTgl()});
        d.close();
    }

    public static void readBukuJejak(JTable tblData, String id) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Kode","Pelaku","Bulan","Tahun"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        for(achmad.rifai.erp1.entity.Tracks t:dao.all())m.addRow(new Object[]{t.getKode(),t.getId(),t.getBln(),t.getTahun()});
        d.close();
    }

    public static void hapusKaryawanWithJabatan(Jabatan j, Db d) throws Exception {
        achmad.rifai.erp1.entity.dao.DAOKaryawan dao=new achmad.rifai.erp1.entity.dao.DAOKaryawan(d);
        for(achmad.rifai.erp1.entity.Karyawan k:dao.all())if(j.getNama() == null ? k.getJabatan() == null : j.getNama().equals(k.getJabatan()))
            dao.delete(k);
    }

    public static void hapusKaryawan(Karyawan k) {
        try {
            Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.entity.dao.DAOJabatan dao=new achmad.rifai.erp1.entity.dao.DAOJabatan(d);
            achmad.rifai.erp1.entity.Jabatan a=achmad.rifai.erp1.entity.Jabatan.of(d, k.getJabatan()),
                    b=achmad.rifai.erp1.entity.Jabatan.of(d, k.getJabatan());
            b.setIsi(b.getIsi()-1);
            dao.update(a, b);
            d.close();
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }
    }

    public static void readJurnal(JTable tblData, String id) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Kode","No","Tanggal","Keterangan",
        "Debit","Kredit"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblData.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOJurnal dao=new achmad.rifai.erp1.entity.dao.DAOJurnal(d);
        for(achmad.rifai.erp1.entity.Jurnal j:dao.all())m.addRow(new Object[]{j.getKode(),j.getNo(),j.getTgl(),j.getKet(),j.getDebit(),
            j.getKredit()});
        d.close();
    }

    public static void hapusPelanggan(Pelanggan p) {
        try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.entity.dao.DAOPenjualan dao=new achmad.rifai.erp1.entity.dao.DAOPenjualan(d);
            for(achmad.rifai.erp1.entity.Penjualan pe:dao.all())
                if(p.getKode() == null ? pe.getPelanggan() == null : p.getKode().equals(pe.getPelanggan()))dao.delete(pe);
            d.close();
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }
    }

    public static void readBonus(JTable tblData) throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new Object[]{"Kode","Pegawai","Bulan","Tahun"},0){
            private Class[]c=new Class[]{String.class,String.class,java.time.Month.class,java.time.Year.class};
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }@Override
            public Class<?> getColumnClass(int i) {
                return c[i];
            }
        };achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        java.util.List<achmad.rifai.erp1.entity.BulanBonus>l=new achmad.rifai.erp1.entity.dao.DAOBulanBonus(d).all();
        tblData.setModel(m);
        l.forEach((b)->{m.addRow(new Object[]{b.getKode(),b.getPeg(),b.getBln(),b.getThn()});});
        d.close();
    }
}