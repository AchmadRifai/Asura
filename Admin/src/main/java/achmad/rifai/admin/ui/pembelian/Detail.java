/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.pembelian;

import javax.swing.JOptionPane;

/**
 *
 * @author ai
 */
public class Detail extends javax.swing.JDialog {
private String id;
private achmad.rifai.erp1.entity.Pembelian p;
private java.awt.Frame f;
    /**
     * Creates new form Detail
     */
    public Detail(java.awt.Frame parent, boolean modal,String i,achmad.rifai.erp1.entity.Pembelian pe) {
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

        struk = new javax.swing.JLabel();
        suplier = new javax.swing.JLabel();
        harga = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        items = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        struk.setText("Struk : ???");

        suplier.setText("Suplier : ???");

        harga.setText("Harga : ???");

        tgl.setText("Tanggal : ???");

        items.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barang", "Harga", "Jumlah"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(items);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(struk)
                            .addComponent(suplier)
                            .addComponent(harga)
                            .addComponent(tgl))
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
                .addContainerGap()
                .addComponent(struk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(suplier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(harga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setTitle("Detail Pembelian "+p.getStruk());
        harga.setText("Total : "+p.getHarga());
        struk.setText("Struk : "+p.getStruk());
        suplier.setText("Suplier : "+p.getSuplier());
        tgl.setText("Tanggal : "+p.getTgl());
        javax.swing.table.DefaultTableModel m=(javax.swing.table.DefaultTableModel) items.getModel();
        for(int x=m.getRowCount()-1;x>=0;x--)m.removeRow(x);
        p.getItems().forEach((i)->{m.addRow(new Object[]{i.getBarang(),i.getHarga(),""+i.getJumlah()+" "+i.getSatuan()});});
    }//GEN-LAST:event_formWindowOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int x=JOptionPane.showConfirmDialog(rootPane, "Apa anda ingin menghapus pembelian "+p.getStruk()+"?", "HAPUS?", JOptionPane.YES_NO_OPTION);
        if(x==JOptionPane.YES_OPTION)new Thread(()->{
            try {
                setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                achmad.rifai.admin.util.Work.jejak(id, "Menghapus pembelian dengan struk "+p.getStruk());
                achmad.rifai.erp1.util.Db d=achmad.rifai.erp1.util.Work.loadDB();
                achmad.rifai.erp1.entity.dao.DAOPembelian dao=new achmad.rifai.erp1.entity.dao.DAOPembelian(d);
                dao.delete(p);
                d.close();
            } catch (Exception ex) {
                achmad.rifai.erp1.util.Db.hindar(ex);
            }setVisible(false);
        }).start();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Add(f,true,id,p).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel harga;
    private javax.swing.JTable items;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel struk;
    private javax.swing.JLabel suplier;
    private javax.swing.JLabel tgl;
    // End of variables declaration//GEN-END:variables
}
