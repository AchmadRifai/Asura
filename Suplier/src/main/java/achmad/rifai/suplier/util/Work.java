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
    public static void jejak(String id, String s, Db d) throws Exception {
        achmad.rifai.erp1.entity.Tracks t=new achmad.rifai.erp1.entity.dao.DAOTracks(d).current(id);
        java.util.List<achmad.rifai.erp1.entity.Jejak>l=t.getL();
        l.add(new achmad.rifai.erp1.entity.Jejak(s, id));
        achmad.rifai.erp1.entity.Tracks b=achmad.rifai.erp1.entity.Tracks.of(d, t.getId());
        b.setL(l);
        new achmad.rifai.erp1.entity.dao.DAOTracks(d).update(t, b);
    }
}