/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.pesan;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author ai
 */
public class Add extends javax.swing.JDialog {
private String id;
private achmad.rifai.erp1.entity.Pesan p;
private java.awt.Frame f;
    /**
     * Creates new form Add
     */
    public Add(java.awt.Frame parent, boolean modal,String i,achmad.rifai.erp1.entity.Pesan pe) {
        super(parent, modal);
        f=parent;
        id=i;
        p=pe;
        initComponents();
    }

    public Add(java.awt.Frame parent, boolean modal,String i) {
        super(parent, modal);
        f=parent;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        isi = new javax.swing.JTextArea();
        pengirim = new javax.swing.JComboBox<>();
        waktu = new javax.swing.JComboBox<>();
        kode = new javax.swing.JTextField();
        n = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pendataan Pesan 1");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Kode");

        jLabel2.setText("Pengirim");

        jLabel3.setText("Waktu");

        isi.setColumns(20);
        isi.setRows(5);
        isi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                isiKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(isi);

        pengirim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pengirimItemStateChanged(evt);
            }
        });

        waktu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                waktuItemStateChanged(evt);
            }
        });

        kode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kodeKeyReleased(evt);
            }
        });

        n.setText("Next");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(waktu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kode, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(n, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(pengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(waktu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(n)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeKeyReleased
        if(!kode.getText().isEmpty())try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.entity.Pesan pe=achmad.rifai.erp1.entity.Pesan.of(d, kode.getText());
            if(pe!=null)kode.setForeground(Color.red);
            else kode.setForeground(Color.BLACK);
            d.close();
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }refresh();
    }//GEN-LAST:event_kodeKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        pengirime();
        waktune();
        if(p!=null)inisial();
    }//GEN-LAST:event_formWindowOpened

    private void pengirimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pengirimItemStateChanged
        kode.setText(pengirim.getItemAt(pengirim.getSelectedIndex())+waktu.getItemAt(waktu.getSelectedIndex()));
        kodeKeyReleased(null);
    }//GEN-LAST:event_pengirimItemStateChanged

    private void waktuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_waktuItemStateChanged
        kode.setText(pengirim.getItemAt(pengirim.getSelectedIndex())+waktu.getItemAt(waktu.getSelectedIndex()));
        kodeKeyReleased(null);
    }//GEN-LAST:event_waktuItemStateChanged

    private void isiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isiKeyReleased
        refresh();
    }//GEN-LAST:event_isiKeyReleased

    private void nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nActionPerformed
        saving();
        new Add1(f,true,id,p).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_nActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int x=JOptionPane.showConfirmDialog(rootPane, "Apa anda ingin melanjutkan pendataan?", "Lanjut?", JOptionPane.YES_NO_OPTION);
        if(x==JOptionPane.YES_OPTION){
            saving();
            new Add(f,true,id,p).setVisible(true);
        }this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea isi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kode;
    private javax.swing.JButton n;
    private javax.swing.JComboBox<String> pengirim;
    private javax.swing.JComboBox<org.joda.time.DateTime> waktu;
    // End of variables declaration//GEN-END:variables

    private void pengirime() {
    try {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOKaryawan k=new achmad.rifai.erp1.entity.dao.DAOKaryawan(d);
        k.all().forEach((i)->{pengirim.addItem(i.getId());});
        d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void waktune() {
        org.joda.time.DateTime d1=org.joda.time.DateTime.now(),d2;
        if(p!=null){
            if(d1.minusYears(3).isAfter(p.getWaktu()))d2=p.getWaktu();
            else d2=d1.minusYears(3);
        }else d2=d1.minusYears(3);
        while(!d1.isBefore(d2)){
            waktu.addItem(d1);
            d1=d1.minusHours(1);
        }if(p!=null)waktu.addItem(p.getWaktu());
    }

    private void inisial() {
        isi.setText(p.getPesan());
        pengirim.setSelectedItem(p.getPengirim());
        waktu.setSelectedItem(p.getWaktu());
        kode.setText(p.getKode());
    }

    private void refresh() {
        n.setEnabled(!kode.getText().isEmpty()&&!isi.getText().isEmpty()&&Color.BLACK==kode.getForeground());
    }

    private void saving() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}