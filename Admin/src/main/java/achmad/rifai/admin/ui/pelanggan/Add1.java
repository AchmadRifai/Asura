/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.pelanggan;

/**
 *
 * @author ai
 */
public class Add1 extends javax.swing.JDialog {
private String id;
private achmad.rifai.erp1.entity.Pelanggan p;
private java.awt.Frame f;
    /**
     * Creates new form Add1
     */
    Add1(java.awt.Frame parent, boolean modal,String i,achmad.rifai.erp1.entity.Pelanggan pe) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        telp = new javax.swing.JDesktopPane();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pendataan Pelanggan 2");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        alamat.setToolTipText("alamat");
        alamat.setLayout(new javax.swing.BoxLayout(alamat, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(alamat);

        jButton1.setText("Tambah Alamat");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        telp.setLayout(new javax.swing.BoxLayout(telp, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(telp);

        jButton2.setText("Tambah Telepon");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Finish");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(p!=null)inisial();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saving();
        new achmad.rifai.admin.ui.pelanggan.Add(f, true, id, p).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Data d=new Data();
        d.setData("");
        d.setVisible(true);
        alamat.add(d);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Data d=new Data();
        d.setData("");
        d.setVisible(true);
        telp.add(d);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        saving();
        storing();
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane alamat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JDesktopPane telp;
    // End of variables declaration//GEN-END:variables

    private void inisial() {
        if(null!=p.getAlamat())p.getAlamat().forEach((s)->{
            Data d=new Data();
            d.setData(s);
            d.setVisible(true);
            alamat.add(d);
        });if(null!=p.getTelp())p.getTelp().forEach((s)->{
            Data d=new Data();
            d.setData(s);
            d.setVisible(true);
            telp.add(d);
        });
    }

    private void saving() {
        alamate();
        telpe();
    }

    private void alamate() {
        java.util.List<String>l=new java.util.LinkedList<>();
        for(javax.swing.JInternalFrame i:alamat.getAllFrames()){
            Data d=(Data) i;
            l.add(d.getData());
        }p.setAlamat(l);
    }

    private void telpe() {
        java.util.List<String>l=new java.util.LinkedList<>();
        for(javax.swing.JInternalFrame i:telp.getAllFrames()){
            Data d=(Data) i;
            l.add(d.getData());
        }p.setTelp(l);
    }

    private void storing() {
    try {
        achmad.rifai.admin.util.Work.jejak(id, "Menyimpan pelanggan "+p.getKode());
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOPelanggan dao=new achmad.rifai.erp1.entity.dao.DAOPelanggan(d);
        achmad.rifai.erp1.entity.Pelanggan a=achmad.rifai.erp1.entity.Pelanggan.of(d, p.getKode());
        if(a==null)dao.insert(p);
        else dao.update(a, p);
        d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }
}
