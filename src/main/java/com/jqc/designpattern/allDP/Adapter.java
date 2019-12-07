package com.jqc.designpattern.allDP;

import java.io.*;

public class Adapter {
    public static void main(String[] args) {
        FileInputStream fis = null;
        InputStreamReader isr= null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream("h:/data/test.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null && !line.equals("")) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(isr != null){
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
