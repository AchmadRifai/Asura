/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.admin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ai
 */
public class Coba {
    public static void main(String[]args){
        try {
            java.io.File f=new java.io.File("jajalan.xlsx");
            if(f.exists())f.delete();
            org.apache.poi.xssf.usermodel.XSSFWorkbook w=new org.apache.poi.xssf.usermodel.XSSFWorkbook();
            org.apache.poi.xssf.usermodel.XSSFSheet s=w.createSheet("jajalan");org.apache.poi.xssf.usermodel.XSSFRow r=s.createRow(0);
            int x=s.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 1, 0, 1));r.createCell(0).setCellValue("Jajal");
            System.out.println(x);r.createCell(2).setCellValue("Oke");java.io.FileOutputStream o=new java.io.FileOutputStream(f);
            w.write(o);o.close();
        } catch (IOException ex) {
            Logger.getLogger(Coba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}