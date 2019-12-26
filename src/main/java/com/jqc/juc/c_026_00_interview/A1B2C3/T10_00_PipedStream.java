package com.jqc.juc.c_026_00_interview.A1B2C3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class T10_00_PipedStream {
    static Thread t1= null,t2=null;

    public static void main(String[] args) throws IOException {

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        t1 = new Thread(()->{
            for (int c: CONSTANTS.numbers) {
                try {
                    input1.read(new byte[9]);
                    System.out.print(c);
                    output1.write("you turn".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t2 = new Thread(()->{
            for (char c: CONSTANTS.letters) {
                try {
                    System.out.print(c);
                    output2.write("you turn".getBytes());
                    input2.read(new byte[9]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
