/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.pelanggan;

import javax.swing.JOptionPane;

/**
 *
 * @author ai
 */
public class Detail extends javax.swing.JDialog {
private String id;
private achmad.rifai.erp1.entity.Pelanggan p;
private java.awt.Frame f;
    /**
     * Creates new form Detail
     */
    public Detail(java.awt.Frame parent, boolean modal,String i,achmad.rifai.erp1.entity.Pelanggan pe) {
        super(parent, modal);
        f=parent;
        id=i;
        p=pe;
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

        nama = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        telp = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        blocked = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detail Pelanggan");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        nama.setText("Nama : ???");

        alamat.setEditable(false);
        alamat.setColumns(20);
        alamat.setRows(5);
        alamat.setToolTipText("alamat");
        jScrollPane1.setViewportView(alamat);

        telp.setEditable(false);
        telp.setColumns(20);
        telp.setRows(5);
        telp.setToolTipText("telepon");
        jScrollPane2.setViewportView(telp);

        jToolBar1.setRollover(true);

        blocked.setText("???");
        jToolBar1.add(blocked);

        jButton1.setText("Ralat");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Hapus");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nama)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setTitle("Detail Pelanggan "+p.getKode());
        nama.setText("Nama : "+p.getNama());
        alamat.setText("");
        p.getAlamat().forEach((s)->{alamat.setText(alamat.getText()+s+"\n");});
        telp.setText("");
        p.getTelp().forEach((s)->{telp.setText(telp.getText()+s+"\n");});
        if(p.isBlocked())blocked.setText("Sedang dicekal");
        else blocked.setText("Tidak dicekal");
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Add(f,true,id,p).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int x=JOptionPane.showConfirmDialog(rootPane, "Apa anda ingin menghapus "+p.getKode()+"?", "Hapus?", JOptionPane.YES_NO_OPTION);
        if(x==JOptionPane.YES_OPTION)new Thread(() -> {
            try {
                setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                achmad.rifai.admin.util.Work.jejak(id, "menghapus pelanggan "+p.getKode());
                new Thread(() -> {achmad.rifai.admin.util.Work.hapusPelanggan(p);}).start();
                achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
                new achmad.rifai.erp1.entity.dao.DAOPelanggan(d).delete(p);
                d.close();
            } catch (Exception ex) {
                achmad.rifai.erp1.util.Db.hindar(ex);
            }setVisible(false);
        }).start();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat;
    private javax.swing.JLabel blocked;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel nama;
    private javax.swing.JTextArea telp;
    // End of variables declaration//GEN-END:variables
}
