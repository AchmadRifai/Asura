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
public class Penerima {
    private String akun;
    private boolean terbaca;

    public String getAkun() {
        return akun;
    }

    public void setAkun(String akun) {
        this.akun = akun;
    }

    public boolean isTerbaca() {
        return terbaca;
    }

    public void setTerbaca(boolean terbaca) {
        this.terbaca = terbaca;
    }
}