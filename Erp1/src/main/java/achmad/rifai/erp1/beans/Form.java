/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.beans;

import achmad.rifai.erp1.util.Work;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ai
 */
public class Form {
    private java.util.List<String>data;

    public Form(Object o) throws Exception{
        data=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(String s:achmad.rifai.erp1.Main.spliting(o.toString()))data.add(r.encrypt(s));
    }

    public Form(String json) throws Exception {
        parsing(json);
    }

    private void parsing(String json) throws Exception {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        data=new java.util.LinkedList<>();
        achmad.rifai.erp1.util.RSA r=Work.loadRSA();
        for(int x=0;x<o.size();x++)
            data.add(r.decrypt(""+o.get("bin"+x)));
    }

    public Form() {
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();try {
            achmad.rifai.erp1.util.RSA r=Work.loadRSA();
            for(int x=0;x<data.size();x++)
                o.put("bin"+x, r.encrypt(data.get(x)));
        } catch (Exception ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }return o.toJSONString();
    }

    public achmad.rifai.erp1.entity.Jejak toJejak() throws Exception{
        String s="";
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        for(String st:data)s+=r.decrypt(st);
        return new achmad.rifai.erp1.entity.Jejak(s);
    }

    public java.util.List<String> getData() {
        return data;
    }

    public void setData(java.util.List<String> data) {
        this.data = data;
    }
}