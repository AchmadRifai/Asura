/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.util;

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

    private com.mongodb.MongoClient c;
    private com.mongodb.DB d;
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

    private void start() throws Exception {
        c=new com.mongodb.MongoClient("localhost", 27017);
        d=c.getDB(name);
    }

    public void close() throws Exception {
        c.close();
    }

    public com.mongodb.DB getD(){
        return d;
    }
}