/**
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this,只有m1执行完毕的时候，m2才能执行
 * 这里复习synchronized最原始的语义
 * <p>
 * 使用reetrantlock可以完成同样的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但lock必须手动释放锁，
 * 因此经常在finally中进行锁的释放
 * <p>
 * 使用reentrantlock 可以进行“尝试锁定”tryLOck,这样无法锁定,
 * 或者在指定时间内无法获得锁，线程可以决定是否继续执行
 *
 * @author jiaqingchao
 */
package com.jqc.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T03_ReentrantLock3 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock 进行锁定，不管锁定与否，方法都将继续执行
     * 可以根据tryLock的返回值来判断是否锁定
     * 也可以根据tryLock的时间，由于tryLock(time)抛出异常，所以要注意unclock 要放在 finally 中
     */
    synchronized void m2() {
//        boolean locked = lock.tryLock();
//        System.out.println("m2..."+locked);
//        if(locked){
//            lock.unlock();
//        }
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 start..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock3 rl = new T03_ReentrantLock3();
        new Thread(rl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(rl::m2).start();
    }
}