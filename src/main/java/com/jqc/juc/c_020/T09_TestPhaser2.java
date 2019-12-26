package com.jqc.juc.c_020;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T09_TestPhaser2 {//不同阶段的成员变量  //遗传算法能用到
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            final int nameIndex = i;
            new Thread(new Person("person" + nameIndex)).start();
        }
        new Thread(new Person("新浪")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了!" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了！" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束!新浪新娘抱抱！" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf(name + " 离开！\n");
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf(name + " 吃完！\n");
            phaser.arriveAndAwaitAdvance();
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf(name + " 到达现场\n");
            phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if (name.equals("新浪") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.println(name + " 洞房！");
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
}

