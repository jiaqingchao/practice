package com.jqc.designpattern.allDP;

import java.io.*;

public class Mementto {

    public static void main(String[] args) {
        Tank2 t = new Tank2(10, 15);
        System.out.println(t);
        save(t);
        t.x = 11;
        t.y = 11;
        System.out.println(t);
        Tank2 t2 = load();
        System.out.println(t2);
    }

    private static void save(Tank2 t) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("h:/data/test.data");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Tank2 load() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Tank2 t = null;
        try {
            fis = new FileInputStream("h:/data/test.data");
            ois = new ObjectInputStream(fis);
            t = (Tank2) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

}

class Tank2 implements Serializable {
    int x, y;

    public Tank2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Tank2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}