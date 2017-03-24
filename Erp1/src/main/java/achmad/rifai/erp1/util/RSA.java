/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.util;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author ai
 */
public class RSA {
    private File pri,pub;

    public RSA(File pri, File pub) throws GeneralSecurityException, IOException {
        this.pri = pri;
        this.pub = pub;
        if(!pri.exists()&&!pub.exists())init();
    }

    private void init() throws GeneralSecurityException, IOException{
        java.security.KeyPairGenerator kpg=java.security.KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2101);
        java.security.KeyPair kp=kpg.genKeyPair();
        savePri(kp.getPrivate());
        savePub(kp.getPublic());
    }

    private void savePri(PrivateKey k) throws IOException {
        if(!pri.getParentFile().exists())pri.getParentFile().mkdirs();
        if(pri.exists())pri.delete();
        java.io.FileOutputStream f=new java.io.FileOutputStream(pri);
        java.io.ObjectOutputStream o=new java.io.ObjectOutputStream(f);
        o.writeObject(k);
        o.close();
        f.close();
    }

    private void savePub(PublicKey k) throws IOException {
        if(!pub.getParentFile().exists())pub.getParentFile().mkdirs();
        if(pub.exists())pub.delete();
        java.io.FileOutputStream f=new java.io.FileOutputStream(pub);
        java.io.ObjectOutputStream o=new java.io.ObjectOutputStream(f);
        o.writeObject(k);
        o.close();
        f.close();
    }

    public String encrypt(String v) throws GeneralSecurityException, IOException, ClassNotFoundException{
        javax.crypto.Cipher c=javax.crypto.Cipher.getInstance("RSA");
        PublicKey k=loadPub();
        c.init(javax.crypto.Cipher.ENCRYPT_MODE, k);
        return hex_string(c.doFinal(v.getBytes()));
    }

    public String decrypt(String e) throws GeneralSecurityException, IOException, ClassNotFoundException{
        byte[]b=hex_bit(e);
        javax.crypto.Cipher c=javax.crypto.Cipher.getInstance("RSA");
        PrivateKey k=loadPri();
        c.init(javax.crypto.Cipher.DECRYPT_MODE, k);
        return new String(c.doFinal(b));
    }

    private PublicKey loadPub() throws IOException, ClassNotFoundException {
        java.io.FileInputStream f=new java.io.FileInputStream(pub);
        java.io.ObjectInputStream i=new java.io.ObjectInputStream(f);
        PublicKey k=(PublicKey) i.readObject();
        i.close();
        f.close();
        return k;
    }

    private String hex_string(byte[] b) {
        java.math.BigInteger bi=new java.math.BigInteger(b);
        return bi.toString(46);
    }

    private byte[] hex_bit(String e) {
        java.math.BigInteger bi=new java.math.BigInteger(e, 46);
        return bi.toByteArray();
    }

    private PrivateKey loadPri() throws IOException, ClassNotFoundException {
        java.io.FileInputStream f=new java.io.FileInputStream(pri);
        java.io.ObjectInputStream i=new java.io.ObjectInputStream(f);
        PrivateKey k=(PrivateKey) i.readObject();
        i.close();
        f.close();
        return k;
    }
}