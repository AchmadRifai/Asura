/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.pelanggan;

import java.awt.Color;

/**
 *
 * @author ai
 */
public class Add extends javax.swing.JDialog {
private String id;
private achmad.rifai.erp1.entity.Pelanggan p;
private java.awt.Frame f;
    /**
     * Creates new form Add
     */
    public Add(java.awt.Frame parent, boolean modal,String i) {
        super(parent, modal);
        f=parent;
        id=i;
        initComponents();
    }

    public Add(java.awt.Frame parent, boolean modal,String i,achmad.rifai.erp1.entity.Pelanggan pe) {
        super(parent, modal);
        f=parent;id=i;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kode = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        blocked = new javax.swing.JCheckBox();
        n = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pendataan Pelanggan 1");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Kode");

        jLabel2.setText("Nama");

        kode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kodeKeyReleased(evt);
            }
        });

        nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaKeyReleased(evt);
            }
        });

        blocked.setText("Dicekal");
        blocked.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                blockedItemStateChanged(evt);
            }
        });
        blocked.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                blockedStateChanged(evt);
            }
        });

        n.setText("NEXT");
        n.setEnabled(false);
        n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(n, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(kode)
                                    .addComponent(nama, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
                            .addComponent(blocked))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blocked)
                .addGap(18, 18, 18)
                .addComponent(n)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeKeyReleased
        if(!kode.getText().isEmpty())try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.entity.Pelanggan p=achmad.rifai.erp1.entity.Pelanggan.of(d, kode.getText());
            if(p!=null)kode.setForeground(Color.red);
            else kode.setForeground(Color.BLACK);
            d.close();
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }refresh();
    }//GEN-LAST:event_kodeKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(p!=null)inisial();
    }//GEN-LAST:event_formWindowOpened

    private void namaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaKeyReleased
        refresh();
    }//GEN-LAST:event_namaKeyReleased

    private void blockedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_blockedItemStateChanged
        refresh();
    }//GEN-LAST:event_blockedItemStateChanged

    private void blockedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_blockedStateChanged
        refresh();
    }//GEN-LAST:event_blockedStateChanged

    private void nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nActionPerformed
        if(p==null){
            p=new achmad.rifai.erp1.entity.Pelanggan();
            p.setDeleted(false);
        }p.setKode(kode.getText());
        p.setNama(nama.getText());
        p.setBlocked(blocked.isSelected());
        p.setAlamat(new java.util.LinkedList<>());
        p.setTelp(new java.util.LinkedList<>());
        new achmad.rifai.admin.ui.pelanggan.Add1(f, true, id, p).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_nActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox blocked;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField kode;
    private javax.swing.JButton n;
    private javax.swing.JTextField nama;
    // End of variables declaration//GEN-END:variables

    private void inisial() {
        blocked.setSelected(p.isBlocked());
        kode.setText(p.getKode());
        kode.setEnabled(false);
        nama.setText(p.getNama());
        n.setEnabled(false);
    }

    private void refresh() {
        n.setEnabled(!kode.getText().isEmpty()&&Color.BLACK==kode.getForeground()&&!nama.getText().isEmpty());
    }
}
