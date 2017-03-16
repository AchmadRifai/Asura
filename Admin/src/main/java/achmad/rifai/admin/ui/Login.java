/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui;

import achmad.rifai.erp1.entity.Absen;
import achmad.rifai.erp1.entity.Karyawan;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author ai
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
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
        pass = new javax.swing.JPasswordField();
        user = new javax.swing.JTextField();
        s = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LOGIN For Admin");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("User");

        jLabel2.setText("Password");

        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passKeyReleased(evt);
            }
        });

        user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                userKeyReleased(evt);
            }
        });

        s.setText("LOGIN");
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
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pass)
                            .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(s))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userKeyReleased
        refresh();
    }//GEN-LAST:event_userKeyReleased

    private void passKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyReleased
        refresh();
    }//GEN-LAST:event_passKeyReleased

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                jalan();
            }
        }).start();
        disableAll();
    }//GEN-LAST:event_sActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int x=javax.swing.JOptionPane.showConfirmDialog(rootPane, "Apa anda ingin menghentikan aplikasi ini?", "HENTI?", 
                javax.swing.JOptionPane.YES_NO_OPTION);
        if(x==javax.swing.JOptionPane.NO_OPTION){
            new Login().setVisible(true);
            this.setVisible(false);
        }else System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField pass;
    private javax.swing.JButton s;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables

    private void refresh() {
        s.setEnabled(!pass.getText().isEmpty()&&!user.getText().isEmpty());
    }

    private void jalan() {
        try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.entity.Karyawan k=new achmad.rifai.erp1.entity.Karyawan(d.getD(), user.getText());
            if(null!=k.getId()){
                if(!k.isDeleted()&&!k.isBlocked()){
                    if("admin".equals(k.getJabatan())){
                        if(!k.isMasuk())next(k);
                        else JOptionPane.showMessageDialog(rootPane, "Duplikat login!");
                    }else JOptionPane.showMessageDialog(rootPane, "Akses ditolak!");
                }else JOptionPane.showMessageDialog(rootPane, "Karyawan tidak terdaftar!");
            }else JOptionPane.showMessageDialog(rootPane, "Karyawan tidak terdaftar!");d.close();
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }enableAll();
    }

    private void disableAll() {
        s.setEnabled(false);
        user.setEnabled(false);
        pass.setEnabled(false);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
    }

    private void enableAll() {
        user.setText("");
        user.setEnabled(true);
        pass.setText("");
        pass.setEnabled(true);
        refresh();
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }

    private void init() {
        try {
            achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
            achmad.rifai.erp1.util.Work.initDb(d.getHost(), d.getName(), d.getPort());
            d.close();
        } catch (Exception ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }
    }

    private void next(Karyawan k) throws Exception{
        if(pass.getText() == null ? k.getPass() == null : pass.getText().equals(k.getPass())){
            halau(k);
            absen(k);
            jejak(k);
            JOptionPane.showMessageDialog(rootPane, "Halo "+k.getNama());
            Dash d=new Dash();
            d.id=user.getText();
            d.setVisible(true);
            this.setVisible(false);
        }else JOptionPane.showMessageDialog(rootPane, "Password salah!");
    }

    private void absen(Karyawan k) throws Exception {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        Absen a=new Absen();
        a.setTgl(java.sql.Date.valueOf(LocalDate.now()));
        a.setL(Absen.Jenise.MASUK);
        a.setS(k.getId());
        new achmad.rifai.erp1.entity.dao.DAOAbsen(d.getD()).insert(a);
        d.close();
    }

    private void jejak(Karyawan k) throws Exception {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.Jejak j=new achmad.rifai.erp1.entity.Jejak("Masuk kerja", k.getId());
        new achmad.rifai.erp1.entity.dao.DAOJejak(d.getD()).insert(j);
        d.close();
    }

    private void halau(Karyawan k) throws Exception {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        Karyawan b=new Karyawan(d.getD(),k.getId());
        b.setMasuk(true);
        new achmad.rifai.erp1.entity.dao.DAOKaryawan(d.getD()).update(k, b);
        d.close();
    }
}