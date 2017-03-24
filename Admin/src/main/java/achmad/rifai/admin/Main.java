/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin;

import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ai
 */
public class Main {
    public static void main(String[]args){
        //try {
            try {
            achmad.rifai.erp1.util.Work.styling();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
            }java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            jalan();
            }
            });
            /*jajal1();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    private static void jalan() {
        new achmad.rifai.erp1.ui.Splash(new achmad.rifai.admin.ui.Login()).setVisible(true);
    }
}