package com.jqc.designpattern.allDP;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Flyweight {
    public static void main(String[] args) {
//        testString();
        for(int i = 0;i <10;i++){
            System.out.println("uuid : " +BulletPool.getBullet().uuid);
        }
    }
    static void testString(){
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");
        System.out.println(s1==s2);//true
        System.out.println(s1==s3);//false
        System.out.println(s3==s4);//false
        System.out.println(s3.intern()==s2);//true
        System.out.println(s3.intern()==s4.intern());//true
    }
}
class Bullet{
    public UUID uuid = UUID.randomUUID();
    public boolean living = false;
    public Bullet() {
        System.out.println("uuid ：" + uuid);
    }
}
class BulletPool{
    static List<Bullet> bullets = new ArrayList<>();
    static{
        for(int i= 0 ; i < 5; i++){
            Bullet bullet = new Bullet();
            bullets.add(bullet);
        }
    }
    public static Bullet getBullet(){
        for (Bullet bullet : bullets) {
            if(!bullet.living){
                bullet.living = true;
                return bullet;
            }
        }
        return new Bullet();
    }
}
