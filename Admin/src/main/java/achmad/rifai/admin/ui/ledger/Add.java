/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.ledger;

import java.awt.Color;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import org.joda.money.CurrencyUnit;

/**
 *
 * @author ai
 */
public class Add extends javax.swing.JDialog {
private String id;
private achmad.rifai.erp1.entity.Ledger l;
    /**
     * Creates new form Add
     */
    public Add(java.awt.Frame parent, boolean modal,String i,achmad.rifai.erp1.entity.Ledger le) {
        super(parent, modal);
        id=i;
        l=le;
        initComponents();
    }

    public Add(java.awt.Frame parent, boolean modal,String i) {
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ket = new javax.swing.JTextArea();
        kredit = new javax.swing.JFormattedTextField();
        debit = new javax.swing.JFormattedTextField();
        no = new javax.swing.JSpinner();
        kode = new javax.swing.JTextField();
        s = new javax.swing.JButton();
        tgl = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pendataan Buku Besar");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Kode");

        jLabel2.setText("Nomer");

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

        kredit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        kredit.setText("0");
        kredit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kreditKeyReleased(evt);
            }
        });

        debit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        debit.setText("0");
        debit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                debitKeyReleased(evt);
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

        s.setText("SIMPAN");
        s.setEnabled(false);
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });

        tgl.setToolTipText("<Tahun>-<Bulan>-<Tanggal>");
        tgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tglKeyReleased(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1)
                            .addComponent(kredit)
                            .addComponent(debit)
                            .addComponent(kode)
                            .addComponent(tgl))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(s, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(s)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeKeyReleased
        if(!kode.getText().isEmpty())try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.entity.Ledger l=achmad.rifai.erp1.entity.Ledger.of(d, kode.getText());
            if(l!=null)kode.setForeground(Color.red);
            else kode.setForeground(Color.BLACK);
            d.close();
        } catch (Exception ex) {
            kode.setForeground(Color.red);
            achmad.rifai.erp1.util.Db.hindar(ex);
        }refresh();
    }//GEN-LAST:event_kodeKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(l!=null)inisial();
    }//GEN-LAST:event_formWindowOpened

    private void noStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_noStateChanged
        refresh();
    }//GEN-LAST:event_noStateChanged

    private void debitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debitKeyReleased
        if(!debit.getText().isEmpty()){
            if(debit.isValid()){
                long lo=Long.parseLong(debit.getText());
                if(lo>=0){
                    if(lo>0)kredit.setText("0");
                    debit.setForeground(Color.BLACK);
                }else debit.setForeground(Color.red);
            }else debit.setForeground(Color.red);
        }refresh();
    }//GEN-LAST:event_debitKeyReleased

    private void kreditKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kreditKeyReleased
        if(!kredit.getText().isEmpty()){
            if(kredit.isValid()){
                long lo=Long.parseLong(kredit.getText());
                if(lo>=0){
                    if(lo>0)debit.setText("0");
                    kredit.setForeground(Color.BLACK);
                }else kredit.setForeground(Color.red);
            }else kredit.setForeground(Color.red);
        }refresh();
    }//GEN-LAST:event_kreditKeyReleased

    private void ketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ketKeyReleased
        refresh();
    }//GEN-LAST:event_ketKeyReleased

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        saving();
        this.setVisible(false);
    }//GEN-LAST:event_sActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int x=JOptionPane.showConfirmDialog(rootPane, "Apa anda ingin menyimpan perubahan data?", "SIMPAN?", JOptionPane.YES_NO_OPTION);
        if(x==JOptionPane.YES_OPTION)saving();
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void tglKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglKeyReleased
        refresh();
    }//GEN-LAST:event_tglKeyReleased

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
    private javax.swing.JTextField tgl;
    // End of variables declaration//GEN-END:variables

    private void inisial() {
        debit.setText(""+l.getDebit().getAmount().longValueExact());
        ket.setText(l.getKet());
        kode.setText(l.getKode());
        kredit.setText(""+l.getKredit().getAmount().longValueExact());
        no.setValue(l.getNo());
        tgl.setText(""+l.getTgl());
        s.setEnabled(false);
    }

    private void refresh() {
        s.setEnabled(!kode.getText().isEmpty()&&Color.BLACK==kode.getForeground()&&!debit.getText().isEmpty()&&debit.isValid()&&
        Color.BLACK==debit.getForeground()&&!kredit.getText().isEmpty()&&kredit.isValid()&&Color.BLACK==kredit.getForeground()&&
        !ket.getText().isEmpty()&&achmad.rifai.admin.util.TglModel.isValidDate(tgl.getText()));
    }

    private void saving() {
    try {
        achmad.rifai.admin.util.Work.jejak(id, "Menyimpan ledger ");
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.Ledger b;
        if(l!=null)b=achmad.rifai.erp1.entity.Ledger.of(d, l.getKode());
        else b=new achmad.rifai.erp1.entity.Ledger();
        b.setDebit(org.joda.money.Money.of(CurrencyUnit.of("IDR"), Long.parseLong(debit.getText())));
        b.setKet(ket.getText());
        b.setKode(kode.getText());
        b.setKredit(org.joda.money.Money.of(CurrencyUnit.of("IDR"), Long.parseLong(kredit.getText())));
        b.setNo(Integer.parseInt(""+no.getValue()));
        b.setTgl(java.sql.Date.valueOf(tgl.getText()));
        achmad.rifai.admin.util.Work.jejak(id, "Menyimpan ledger "+b.getKode());
        achmad.rifai.erp1.entity.dao.DAOLedger dao=new achmad.rifai.erp1.entity.dao.DAOLedger(d);
        if(l!=null)dao.update(l, b);
        else dao.insert(b);
        d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }
}
