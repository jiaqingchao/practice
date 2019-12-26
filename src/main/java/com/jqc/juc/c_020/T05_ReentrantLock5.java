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
 * <p>
 * ReentrantLock 还可以被指定为公平锁
 *
 * @author jiaqingchao
 */
package com.jqc.juc.c_020;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T05_ReentrantLock5 extends Thread {
    private static Lock lock = new ReentrantLock(true);//参数为true表示为公平锁，请对比输出结果

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 rl = new T05_ReentrantLock5();
        Thread th1 = new Thread(rl);
        Thread th2 = new Thread(rl);
        th1.start();
        th2.start();

    }
}
