/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity;

/**
 *
 * @author ai
 */
public class Jabatan {
    private String nama;
    private org.joda.money.Money gaji;
    private int kapasitas;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public org.joda.money.Money getGaji() {
        return gaji;
    }

    public void setGaji(org.joda.money.Money gaji) {
        this.gaji = gaji;
    }

    public Jabatan(String k,com.mongodb.DB d)throws Exception{
        achmad.rifai.erp1.util.RSA r=achmad.rifai.erp1.util.Work.loadRSA();
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put(achmad.rifai.erp1.util.Work.MD5("nama"), r.encrypt(k));
        com.mongodb.DBCursor c=d.getCollection("jabatan").find(w);
        for(com.mongodb.DBObject o:c.toArray(1))
            parsing(r.decrypt(""+o.get(achmad.rifai.erp1.util.Work.MD5("data"))));
    }

    public Jabatan(String json) throws Exception{
        parsing(json);
    }

    public Jabatan(){}

    private void parsing(String json) throws Exception {
        org.json.simple.parser.JSONParser p=new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject o=(org.json.simple.JSONObject) p.parse(json);
        nama=""+o.get("nama");
        gaji=org.joda.money.Money.parse(""+o.get("gaji"));
        kapasitas=Integer.parseInt(""+o.get("kap"));
    }

    @Override
    public String toString() {
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        o.put("nama", nama);
        o.put("gaji", gaji);
        o.put("kap", kapasitas);
        return o.toJSONString();
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
}