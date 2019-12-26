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
 * <p>
 * 使用ReentrantLock还可以调用lockInterruptibly方法，
 * 可以对线程interrupt方法做出响应，
 * 在一个线程等待的过程中，可以被打断
 *
 * @author jiaqingchao
 */
package com.jqc.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T04_ReentrantLock4 {
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1.start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("t1 interrupted");
            } finally {
                lock.unlock(); // "Thread-1" java.lang.IllegalMonitorStateException
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
//            lock.lock();
                lock.lockInterruptibly();
                System.out.println("t2.start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("t2 interrupted");
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();//打断t2线程的等待
    }
}
