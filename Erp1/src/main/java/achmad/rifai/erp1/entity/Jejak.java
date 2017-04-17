/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import org.joda.time.DateTime;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Jejak {
    private String aksi;
    private org.joda.time.DateTime waktu;

    public Jejak(String aksi, String pelaku) {
        this.aksi = aksi;
        waktu=org.joda.time.DateTime.now();
    }

    public Jejak() {}

    public Jejak(String json) throws ParseException{
        parsing(json);
    }

    public String getAksi() {
        return aksi;
    }

    public void setAksi(String aksi) {
        this.aksi = aksi;
    }

    public DateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(DateTime waktu) {
        this.waktu = waktu;
    }

    private void parsing(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(json);
        aksi=""+o.get("aksi");
        waktu=org.joda.time.DateTime.parse(""+o.get("waktu"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("aksi", aksi);
        o.put("waktu", ""+waktu);
        return o.toJSONString();
    }
}