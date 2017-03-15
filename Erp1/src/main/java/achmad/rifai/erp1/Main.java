/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1;

import javax.swing.UnsupportedLookAndFeelException;

/**
 *string = 525
 * 1000(duwek) = 525
 * saiki(wayah) = 525
 * saiki(tgl) = 525
 * float = 525
 * @author ai
 */
public class Main {
    public static void main(String[]args){
        try {
            achmad.rifai.erp1.util.Work.styling();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            achmad.rifai.erp1.util.Db.hindar(ex);
        }java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            mlaku();
            }
            });
    }

    private static void mlaku() {
        new achmad.rifai.erp1.ui.Splash().setVisible(true);
    }
}
