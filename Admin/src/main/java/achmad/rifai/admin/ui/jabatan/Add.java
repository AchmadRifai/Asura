/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.jabatan;

import java.awt.Color;
import javax.swing.JOptionPane;
import org.joda.money.CurrencyUnit;

/**
 *
 * @author ai
 */
public class Add extends javax.swing.JDialog {
private String id;
private achmad.rifai.erp1.entity.Jabatan j;
    /**
     * Creates new form Add
     */
    public Add(java.awt.Frame parent, boolean modal,String i,achmad.rifai.erp1.entity.Jabatan ja) {
        super(parent, modal);
        id=i;
        j=ja;
        initComponents();
    }

    public Add(java.awt.Frame parent, boolean modal,String i){
        super(parent, modal);
        id=i;
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
        jLabel3 = new javax.swing.JLabel();
        kap = new javax.swing.JSpinner();
        gaji = new javax.swing.JSpinner();
        nama = new javax.swing.JTextField();
        s = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pendataan Jabatan");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Nama");

        jLabel2.setText("Gaji");

        jLabel3.setText("Kapasitas");

        kap.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        kap.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                kapStateChanged(evt);
            }
        });

        gaji.setModel(new javax.swing.SpinnerNumberModel(100000L, 100000L, null, 1000L));
        gaji.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gajiStateChanged(evt);
            }
        });

        nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaKeyReleased(evt);
            }
        });

        s.setText("SIMPAN");
        s.setEnabled(false);
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gaji)
                            .addComponent(kap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(gaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(kap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(s)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int x=JOptionPane.showConfirmDialog(rootPane, "Apa anda ingin menyimpan perubahan?", "SIMPAN?", JOptionPane.YES_NO_OPTION);
        if(x==JOptionPane.YES_OPTION)saving();
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(j!=null){
            nama.setText(j.getNama());
            gaji.setValue(j.getGaji().getAmount().longValue());
            kap.setValue(j.getKapasitas());
        }else j=new achmad.rifai.erp1.entity.Jabatan();
    }//GEN-LAST:event_formWindowOpened

    private void namaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaKeyReleased
        if(!nama.getText().isEmpty())try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.entity.Jabatan ja=achmad.rifai.erp1.entity.Jabatan.of(d, nama.getText());
            if(ja!=null)nama.setForeground(Color.red);
            else nama.setForeground(Color.BLACK);
            d.close();
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }refresh();
    }//GEN-LAST:event_namaKeyReleased

    private void gajiStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_gajiStateChanged
        refresh();
    }//GEN-LAST:event_gajiStateChanged

    private void kapStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_kapStateChanged
        refresh();
    }//GEN-LAST:event_kapStateChanged

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        saving();
        this.setVisible(false);
    }//GEN-LAST:event_sActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner gaji;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSpinner kap;
    private javax.swing.JTextField nama;
    private javax.swing.JButton s;
    // End of variables declaration//GEN-END:variables

    private void refresh() {
        s.setEnabled(!nama.getText().isEmpty()&&Color.BLACK==nama.getForeground());
    }

    private void saving() {
    try {
        storing();
        achmad.rifai.admin.util.Work.jejak(id, "Menyimpan perubahan jabatan "+j.getNama());
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.Jabatan a=achmad.rifai.erp1.entity.Jabatan.of(d, j.getNama());
        achmad.rifai.erp1.entity.dao.DAOJabatan dao=new achmad.rifai.erp1.entity.dao.DAOJabatan(d);
        if(a!=null)dao.update(a, j);
        else dao.insert(j);
        d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void storing() {
        j.setGaji(org.joda.money.Money.of(CurrencyUnit.of("IDR"), Long.parseLong(""+gaji.getValue())));
        j.setKapasitas(Integer.parseInt(""+kap.getValue()));
        j.setNama(nama.getText());
    }
}