/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

import achmad.rifai.erp1.util.Db;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ai
 */
public class Rekening {
    public static Rekening of(Db d, String kode) throws Exception{
        Rekening v=null;
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.datastax.driver.core.ResultSet rs=d.getS().execute(
                QueryBuilder.select("bin").from("rekening").where(QueryBuilder.eq("berkas", kode)));
        for(com.datastax.driver.core.Row ro:rs){
            String json="";
            for(String s:ro.getList("bin", String.class))json+=r.decrypt(s);
            v=new Rekening(json);
        }return v;
    }

    private String kode,ket,golongan,posisi;
    private boolean deleted;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public Rekening(String json) throws ParseException {
        parsing(json);
    }

    private void parsing(String json) throws ParseException {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject)p.parse(json);
        kode=""+o.get("kode");
        ket=""+o.get("ket");
        golongan=""+o.get("golongan");
        posisi=""+o.get("posisi");
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("kode", kode);
        o.put("ket", ket);
        o.put("golongan", golongan);
        o.put("posisi", posisi);
        o.put("deleted", ""+deleted);
        return o.toJSONString();
    }

    public Rekening() {
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}