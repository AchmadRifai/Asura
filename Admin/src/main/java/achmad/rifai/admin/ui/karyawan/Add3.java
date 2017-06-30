/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.karyawan;

/**
 *
 * @author ai
 */
public class Add3 extends javax.swing.JDialog {
private String kode;
private achmad.rifai.erp1.entity.Karyawan k;
private java.awt.Frame p;
private int x,y;
    /**
     * Creates new form Add3
     * @param parent
     * @param modal
     * @param kode
     * @param k
     */
    public Add3(java.awt.Frame parent, boolean modal,String kode,achmad.rifai.erp1.entity.Karyawan k) {
        super(parent, modal);
        p=parent;
        this.kode=kode;
        this.k=k;
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

        tbh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        items = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pendataan Karyawan 3");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tbh.setText("+");
        tbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbhActionPerformed(evt);
            }
        });

        jButton1.setText("FINISH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        items.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(items);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbh)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        x=0;
        y=0;
        k.getAlamat().forEach((s)->{
            addData(s);
        });
    }//GEN-LAST:event_formWindowOpened

    private void tbhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbhActionPerformed
        addData("");
    }//GEN-LAST:event_tbhActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        saving();
        simpan();
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saving();
        new Add1(p,true,kode,k).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane items;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton tbh;
    // End of variables declaration//GEN-END:variables

    private void saving() {
        java.util.List<String>l=new java.util.LinkedList<>();
        for(java.awt.Component c:items.getAllFrames()){
            Alamate a=(Alamate) c;
            l.add(a.getData());
        }k.setAlamat(l);
    }

    private void simpan() {
        try {
        achmad.rifai.erp1.entity.Karyawan b=k;
        achmad.rifai.admin.util.Work.jejak(kode, "Menyimpan perubahan karyawan "+k.getId());
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.Karyawan a=achmad.rifai.erp1.entity.Karyawan.of(d, b.getId());
        achmad.rifai.erp1.entity.dao.DAOKaryawan dao=new achmad.rifai.erp1.entity.dao.DAOKaryawan(d);
        if(a==null){
            jabatanAdd();
            dao.insert(b);
        }else dao.update(a, b);
        d.close();
    } catch (Exception ex) {
        achmad.rifai.erp1.util.Db.hindar(ex);
    }
    }

    private void addData(String s) {
        java.awt.GridBagConstraints gbc=new java.awt.GridBagConstraints();
        if(y>0){
            gbc.gridx=x;
            gbc.gridy=y;
        }Alamate a=new Alamate();
        a.setData(s);
        y++;
        a.setVisible(true);
        items.add(a, gbc);
    }

    private void jabatanAdd() throws Exception {
        achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
        achmad.rifai.erp1.entity.dao.DAOJabatan dao=new achmad.rifai.erp1.entity.dao.DAOJabatan(d);
        achmad.rifai.erp1.entity.Jabatan a=achmad.rifai.erp1.entity.Jabatan.of(d, k.getJabatan()),
                b=achmad.rifai.erp1.entity.Jabatan.of(d, k.getJabatan());
        b.setIsi(b.getIsi()+1);
        dao.update(a, b);
        d.close();
    }
}
