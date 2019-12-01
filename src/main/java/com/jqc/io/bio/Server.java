package com.jqc.io.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        ServerSocket ss = null;
        try {
            ss = new ServerSocket();
            ss.bind(new InetSocketAddress("127.0.0.1",8888));
            while (true){
                Socket s = ss.accept();//accept 阻塞
                new Thread(()->{
                    handle(s);
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static void handle(Socket s){
        try{
            byte[] bytes = new byte[1024];
            int len = s.getInputStream().read(bytes);//read 阻塞
            System.out.println(new String(bytes,0,len));

            s.getOutputStream().write(bytes,0,len);//write 阻塞
            s.getOutputStream().flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
