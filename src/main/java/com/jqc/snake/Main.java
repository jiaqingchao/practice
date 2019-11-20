package com.jqc.snake;

public class Main {
    public static void main(String[] args)throws InterruptedException {
        SnakeFrame sf = new SnakeFrame();

        while (true){
            Thread.sleep(100);
            sf.repaint();
        }
    }
}
