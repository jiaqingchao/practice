package com.jqc.io.bio;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("127.0.0.1", 8888);
            s.getOutputStream().write("HelloServer".getBytes());//write 阻塞
            s.getOutputStream().flush();
            //s.getOutputStream().close();
            System.out.println("write over,waiting for msg back...");
            byte[] bytes = new byte[1024];
            int len = s.getInputStream().read(bytes);//read 阻塞
            System.out.println(new String(bytes, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
