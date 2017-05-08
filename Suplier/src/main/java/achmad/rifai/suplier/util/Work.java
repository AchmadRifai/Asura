/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.suplier.util;

import achmad.rifai.erp1.util.Db;

/**
 *
 * @author ai
 */
public class Work {
    public static void jejak(String id, String aksi, Db d) throws Exception {
        achmad.rifai.erp1.entity.dao.DAOTracks dao=new achmad.rifai.erp1.entity.dao.DAOTracks(d);
        achmad.rifai.erp1.entity.Tracks t1=dao.current(id),t2=dao.current(id);
        java.util.List<achmad.rifai.erp1.entity.Jejak>l=t1.getL();
        l.add(new achmad.rifai.erp1.entity.Jejak(aksi, id));
        t2.setL(l);
        dao.update(t1, t2);
    }
}