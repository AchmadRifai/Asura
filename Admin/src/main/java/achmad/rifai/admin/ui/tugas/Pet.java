/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.ui.tugas;

/**
 *
 * @author ai
 */
class Pet extends javax.swing.JInternalFrame {

    /**
     * Creates new form Pet
     */
    public Pet() {
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

        karyawan = new javax.swing.JLabel();
        ambil = new javax.swing.JCheckBox();
        sedang = new javax.swing.JCheckBox();
        terlaksana = new javax.swing.JCheckBox();

        karyawan.setText("???");

        ambil.setText("Ambil");

        sedang.setText("Sedang");

        terlaksana.setText("Selesai");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(karyawan)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ambil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sedang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(terlaksana)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(karyawan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ambil)
                    .addComponent(sedang)
                    .addComponent(terlaksana))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ambil;
    private javax.swing.JLabel karyawan;
    private javax.swing.JCheckBox sedang;
    private javax.swing.JCheckBox terlaksana;
    // End of variables declaration//GEN-END:variables

    public void setAmbil(boolean b){
        ambil.setSelected(b);
    }

    public boolean isAmbil(){
        return ambil.isSelected();
    }

    public void setKaryawan(String s){
        karyawan.setText(s);
    }

    public String getKaryawan(){
        return karyawan.getText();
    }

    public void setSedang(boolean b){
        sedang.setSelected(b);
    }

    public boolean isSedang(){
        return sedang.isSelected();
    }

    public void setTerlaksana(boolean b){
        terlaksana.setSelected(b);
    }

    public boolean isTerlaksana(){
        return terlaksana.isSelected();
    }

    public achmad.rifai.erp1.entity.Petugas genPetugas(){
        achmad.rifai.erp1.entity.Petugas p=new achmad.rifai.erp1.entity.Petugas();
        p.setDiambil(ambil.isSelected());
        p.setKaryawan(karyawan.getText());
        p.setSedang(sedang.isSelected());
        p.setTerlaksana(terlaksana.isSelected());
        return p;
    }
}