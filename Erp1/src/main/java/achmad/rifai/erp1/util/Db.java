/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.util;

import java.io.FileNotFoundException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ai
 */
public class Db {
    public static void hindar(Exception ex) {
        try {
            org.joda.time.DateTime d=org.joda.time.DateTime.now();
            java.io.File f=new java.io.File(System.getProperty("user.home")+"/.asura/error/"+Work.MD5(d.toString())+"log");
            if(!f.getParentFile().exists())f.getParentFile().mkdirs();
            java.io.PrintWriter o = new java.io.PrintWriter(f);
            ex.printStackTrace(o);
            o.close();
        } catch (FileNotFoundException | GeneralSecurityException ex1) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

    private com.datastax.driver.core.Cluster c;
    private com.datastax.driver.core.Session s;
    private String host,name;

    public Db(String host, String name) throws Exception{
        this.host = host;
        this.name = name;
        start();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) throws Exception {
        close();
        this.host = host;
        start();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        close();
        this.name = name;
        start();
    }

    private void start() throws InstantiationException, IllegalAccessException, SQLException {
        c=com.datastax.driver.core.Cluster.builder().addContactPoint(host).build();
        s=c.connect(name);
    }

    public void close() throws Exception {
        s.close();
        c.close();
    }

    public com.datastax.driver.core.PreparedStatement getPS(String cql)throws Exception{
        return s.prepare(cql);
    }

    public com.datastax.driver.core.ResultSet getRS(String cql)throws Exception{
        return s.execute(cql);
    }

    public com.datastax.driver.core.Session getS() {
        return s;
    }
}