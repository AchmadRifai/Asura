/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.ui.pesan;

import achmad.rifai.erp1.entity.Karyawan;

/**
 *
 * @author janoko
 */
public class Pesan extends javax.swing.JDialog {
    public static boolean isDapet(Karyawan k) throws Exception {
        boolean b=false;
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        for(achmad.rifai.erp1.entity.Pesan p:new achmad.rifai.erp1.entity.dao.DAOPesan(d).all())if(isIntuk(p,k)){
            b=true;
            break;
        }d.close();
        return b;
    }

    private static boolean isIntuk(achmad.rifai.erp1.entity.Pesan p, Karyawan k) {
        boolean b=false;
        for(achmad.rifai.erp1.entity.Penerima pe:p.getKe())if(k.getId() == null ? pe.getAkun() == null : k.getId().equals(pe.getAkun())){
            b=!pe.isTerbaca();
            break;
        }return b;
    }

    private Karyawan k;
    private java.awt.Frame fra;
    private achmad.rifai.erp1.entity.Pesan sInbox,sDraft;
    /**
     * Creates new form Pesan
     */
    public Pesan(java.awt.Frame parent, boolean modal, Karyawan ka) {
        super(parent, modal);
        fra=parent;
        k=ka;
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        bInbox = new javax.swing.JButton();
        bpt = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInbox = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOnline = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKirim = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setRollover(true);

        jButton1.setText("Tulis");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        bInbox.setText("Baca Kotak Masuk");
        bInbox.setEnabled(false);
        bInbox.setFocusable(false);
        bInbox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bInbox.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bInbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInboxActionPerformed(evt);
            }
        });
        jToolBar1.add(bInbox);

        bpt.setText("Baca Pesan Terkirim");
        bpt.setEnabled(false);
        bpt.setFocusable(false);
        bpt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bpt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bptActionPerformed(evt);
            }
        });
        jToolBar1.add(bpt);

        jTabbedPane1.addTab("HOME", jToolBar1);

        tblInbox.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblInbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInboxMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblInbox);

        jTabbedPane2.addTab("Kotak Masuk", jScrollPane2);

        tblOnline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblOnline);

        jTabbedPane2.addTab("Yang Online", jScrollPane1);

        tblKirim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKirim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKirimMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblKirim);

        jTabbedPane2.addTab("Pesan Terkirim", jScrollPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        new Thread(this::meninggalKanJejak).start();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(()->{while(isVisible())try {
            reload();
            } catch (Exception ex) {
                achmad.rifai.erp1.util.Db.hindar(ex);
            }}).start();
        this.setTitle("Kotak masuk "+k.getId());
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Thread(()->{try {
            akanMenulis();
            } catch (Exception ex) {
                achmad.rifai.erp1.util.Db.hindar(ex);
            }
}).start();new Tulis(fra, true, k).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblInboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInboxMouseClicked
        int x=tblInbox.getSelectedRow();
        boolean b=tblInbox.isRowSelected(x);
        if(b)try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            sInbox=achmad.rifai.erp1.entity.Pesan.of(d, ""+tblInbox.getValueAt(x, 0));
            d.close();
            bInbox.setEnabled(b);
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }
    }//GEN-LAST:event_tblInboxMouseClicked

    private void bInboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInboxActionPerformed
        final achmad.rifai.erp1.entity.Pesan p=sInbox;
        new Thread(()->{try {
            akanMembaca(p);
            } catch (Exception ex) {
                achmad.rifai.erp1.util.Db.hindar(ex);
            }}).start();
        new Baca(fra, true, k, p).setVisible(true);
    }//GEN-LAST:event_bInboxActionPerformed

    private void tblKirimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKirimMouseClicked
        int x=tblKirim.getSelectedRow();
        boolean b=tblKirim.isRowSelected(x);
        if(b)try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            sDraft=achmad.rifai.erp1.entity.Pesan.of(d, ""+tblKirim.getValueAt(x, 0));
            d.close();
            bpt.setEnabled(b);
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }
    }//GEN-LAST:event_tblKirimMouseClicked

    private void bptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bptActionPerformed
        new Thread(()->{try {
            menilikPesan();
            } catch (Exception ex) {
                achmad.rifai.erp1.util.Db.hindar(ex);
            }
        }).start();
        new Terkirim(fra, true, k, sDraft).setVisible(true);
    }//GEN-LAST:event_bptActionPerformed

    private void meninggalKanJejak() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks t=dao.current(k.getId()),b=dao.current(k.getId());
        java.util.List<achmad.rifai.erp1.entity.Jejak>l=t.getL();
        l.add(new achmad.rifai.erp1.entity.Jejak("menutup kotak pesan", k.getId()));
        b.setL(l);
        dao.update(t, b);
        d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bInbox;
    private javax.swing.JButton bpt;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblInbox;
    private javax.swing.JTable tblKirim;
    private javax.swing.JTable tblOnline;
    // End of variables declaration//GEN-END:variables

    private void reload() throws Exception {
        loadInbox();
        loadOnline();
        loadKirim();
        Thread.sleep(5000);
    }

    private void loadInbox() throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Kode","Pengirim","Waktu","Terbaca"}, 0){
            Class[]c=new Class[]{String.class,String.class,org.joda.time.DateTime.class,Boolean.class};
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return c[columnIndex];
            }
        };tblInbox.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        new achmad.rifai.erp1.entity.dao.DAOPesan(d).all().forEach((p)->{
            for(achmad.rifai.erp1.entity.Penerima pe:p.getKe())if(pe.getAkun() == null ? k.getId() == null : pe.getAkun().equals(k.getId())){
                m.addRow(new Object[]{p.getKode(),p.getPengirim(),p.getWaktu(),pe.isTerbaca()});
                break;
            }
        });d.close();
    }

    private void loadOnline() throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"ID","Nama"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblOnline.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        new achmad.rifai.erp1.entity.dao.DAOKaryawan(d).all().forEach((ka)->{
            if(ka.isMasuk())m.addRow(new Object[]{ka.getId(),ka.getNama()});
        });d.close();
    }

    private void loadKirim() throws Exception {
        javax.swing.table.DefaultTableModel m=new javax.swing.table.DefaultTableModel(new String[]{"Kode","Isi","Waktu"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };tblKirim.setModel(m);
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        new achmad.rifai.erp1.entity.dao.DAOPesan(d).all().forEach((p)->{
            if(k.getId() == null ? p.getPengirim() == null : k.getId().equals(p.getPengirim()))
                m.addRow(new Object[]{p.getKode(),p.getPesan(),p.getWaktu()});
        });d.close();
    }

    private void akanMenulis() throws Exception {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks a=dao.current(k.getId()),b=dao.current(k.getId());
        java.util.List<achmad.rifai.erp1.entity.Jejak>l=a.getL();
        l.add(new achmad.rifai.erp1.entity.Jejak("Akan menulis pesan", k.getId()));
        b.setL(l);
        dao.update(a, b);
        d.close();
    }

    private void akanMembaca(achmad.rifai.erp1.entity.Pesan p) throws Exception {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks a=dao.current(k.getId()),b=dao.current(k.getId());
        java.util.List<achmad.rifai.erp1.entity.Jejak>l=a.getL();
        l.add(new achmad.rifai.erp1.entity.Jejak("Akan membaca pesan "+p.getKode(), k.getId()));
        b.setL(l);
        dao.update(a, b);
        d.close();
    }

    private void menilikPesan() throws Exception {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks a=dao.current(k.getId()),b=dao.current(k.getId());
        java.util.List<achmad.rifai.erp1.entity.Jejak>l=a.getL();
        l.add(new achmad.rifai.erp1.entity.Jejak("Akan menilik pesan "+sDraft.getKode(), k.getId()));
        b.setL(l);
        dao.update(a, b);
        d.close();
    }
}
