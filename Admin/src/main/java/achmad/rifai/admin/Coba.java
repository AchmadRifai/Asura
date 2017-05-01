/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin;

import org.joda.time.DateTimeZone;

/**
 *
 * @author ai
 */
public class Coba {
    public static void main(String[]args){
        org.joda.time.DateTime a=org.joda.time.DateTime.now();
        System.out.println(""+a);
        java.util.Date b=a.toDate();
        System.out.println(""+b);
        org.joda.time.DateTime c=new org.joda.time.DateTime(b.getTime(), DateTimeZone.getDefault());
        System.out.println(""+c);
    }
}