package com.jqc.designpattern.adapter_wrapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("h:/test.text");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        while (line != null && !line.equals("")){
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
    }
}
