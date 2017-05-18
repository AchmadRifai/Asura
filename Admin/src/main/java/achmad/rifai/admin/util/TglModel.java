/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin.util;

/**
 *
 * @author ai
 */
public class TglModel {
    public static boolean isValidDate(String df){
        boolean b;
        try{
            java.sql.Date d=java.sql.Date.valueOf(df);
            b=d!=null;
        }catch(Exception e){
            b=false;
            System.out.println(e.getLocalizedMessage());
        }return b;
    }
}