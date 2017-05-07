/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.jurnal;

import java.awt.Color;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import org.joda.money.CurrencyUnit;

/**
 *
 * @author ai
 */
public class Add extends javax.swing.JDialog {
private String id;
private achmad.rifai.erp1.entity.Jurnal j;
    /**
     * Creates new form Add
     */
    public Add(java.awt.Frame parent, boolean modal,String i) {
        super(parent, modal);
        id=i;
        j=null;
        initComponents();
    }

    public Add(java.awt.Frame parent, boolean modal,String i,achmad.rifai.erp1.entity.Jurnal ju) {
        super(parent, modal);
        id=i;
        j=ju;
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ket = new javax.swing.JTextArea();
        kredit = new javax.swing.JFormattedTextField();
        debit = new javax.swing.JFormattedTextField();
        tgl = new javax.swing.JSpinner();
        no = new javax.swing.JSpinner();
        kode = new javax.swing.JTextField();
        s = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Kode");

        jLabel2.setText("Nomor");

        jLabel3.setText("Tanggal");

        jLabel4.setText("Debit");

        jLabel5.setText("Kredit");

        jLabel6.setText("Keterangan");

        ket.setColumns(20);
        ket.setRows(5);
        ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ketKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(ket);

        kredit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kreditKeyReleased(evt);
            }
        });

        debit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                debitKeyReleased(evt);
            }
        });

        tgl.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, new java.util.Date(), java.util.Calendar.DAY_OF_MONTH));
        tgl.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tglStateChanged(evt);
            }
        });

        no.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        no.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                noStateChanged(evt);
            }
        });

        kode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kodeKeyReleased(evt);
            }
        });

        s.setText("SAVE");
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(kredit)
                            .addComponent(debit)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(no, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tgl, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(kode))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(s, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(debit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(kredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(j!=null){
            saving();
            int x=JOptionPane.showConfirmDialog(rootPane, "Apa anda ingin menyimpan perubahan?", "SIMPAN?", JOptionPane.YES_NO_OPTION);
            if(x==JOptionPane.YES_OPTION)writeDB();
        }this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(j!=null){
            kode.setEnabled(false);
            kode.setText(j.getKode());
            no.setValue(j.getNo());
            tgl.setValue(j.getTgl());
            debit.setText(""+j.getDebit().getAmount().longValue());
            kredit.setText(""+j.getKredit().getAmount().longValue());
            ket.setText(j.getKet());
        }
    }//GEN-LAST:event_formWindowOpened

    private void kodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeKeyReleased
        if(!kode.getText().isEmpty())try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.entity.Jurnal ju=achmad.rifai.erp1.entity.Jurnal.of(d, kode.getText());
            if(ju!=null)kode.setForeground(Color.red);
            else kode.setForeground(Color.BLACK);
            d.close();
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }refresh();
    }//GEN-LAST:event_kodeKeyReleased

    private void noStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_noStateChanged
        refresh();
    }//GEN-LAST:event_noStateChanged

    private void tglStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tglStateChanged
        refresh();
    }//GEN-LAST:event_tglStateChanged

    private void debitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debitKeyReleased
        if(!debit.getText().isEmpty()&&debit.isValid()){
            int x=Integer.parseInt(debit.getText());
            if(x<0)debit.setForeground(Color.red);
            else debit.setForeground(Color.BLACK);
        }refresh();
    }//GEN-LAST:event_debitKeyReleased

    private void kreditKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kreditKeyReleased
        if(!kredit.getText().isEmpty()&&kredit.isValid()){
            int x=Integer.parseInt(kredit.getText());
            if(x<0)kredit.setForeground(Color.red);
            else kredit.setForeground(Color.BLACK);
        }refresh();
    }//GEN-LAST:event_kreditKeyReleased

    private void ketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ketKeyReleased
        refresh();
    }//GEN-LAST:event_ketKeyReleased

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        saving();
        writeDB();
        this.setVisible(false);
    }//GEN-LAST:event_sActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField debit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ket;
    private javax.swing.JTextField kode;
    private javax.swing.JFormattedTextField kredit;
    private javax.swing.JSpinner no;
    private javax.swing.JButton s;
    private javax.swing.JSpinner tgl;
    // End of variables declaration//GEN-END:variables

    private void saving() {
        if(j==null){
            j=new achmad.rifai.erp1.entity.Jurnal();
            j.setDeleted(false);
        }j.setDebit(org.joda.money.Money.of(CurrencyUnit.of("IDR"), Long.parseLong(debit.getText())));
        j.setKet(ket.getText());
        j.setKode(kode.getText());
        j.setKredit(org.joda.money.Money.of(CurrencyUnit.of("IDR"), Long.parseLong(kredit.getText())));
        j.setNo(Integer.parseInt(""+no.getValue()));
        java.util.Date d=(java.util.Date) tgl.getValue();
        j.setTgl(java.sql.Date.valueOf(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
    }

    private void writeDB() {
    try {
        achmad.rifai.admin.util.Work.jejak(id, "Mendata jurnal "+j.getKode());
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.Jurnal ju=achmad.rifai.erp1.entity.Jurnal.of(d, j.getKode());
        achmad.rifai.erp1.entity.dao.DAOJurnal dao=new achmad.rifai.erp1.entity.dao.DAOJurnal(d);
        if(ju==null)dao.insert(j);
        else dao.update(ju, j);
        d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void refresh() {
        s.setEnabled(!kode.getText().isEmpty()&&!ket.getText().isEmpty()&&!debit.getText().isEmpty()&&!kredit.getText().isEmpty()&&
        debit.isValid()&&kredit.isValid()&&Color.BLACK==debit.getForeground()&&Color.BLACK==kredit.getForeground());
    }
}