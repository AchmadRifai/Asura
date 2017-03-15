/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.util;

import com.mongodb.DB;
import java.io.FileNotFoundException;
import java.security.GeneralSecurityException;
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

    private com.mongodb.MongoClient mc;
    private DB d;
    private String host,name;
    private int port;

    public Db(String host, String name, int port) throws Exception {
        this.host = host;
        this.name = name;
        this.port = port;
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) throws Exception {
        close();
        this.port = port;
        start();
    }

    private void start() {
        mc=new com.mongodb.MongoClient(host, port);
        d=mc.getDB(name);
    }

    public void close() {
        mc.close();
    }

    public DB getD() {
        return d;
    }

    public com.mongodb.MongoClient getMc() {
        return mc;
    }
}