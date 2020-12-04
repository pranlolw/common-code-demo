package com.st.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class MySpinLockDemo {
    AtomicReference<Thread> atomicReference=new AtomicReference<>();
    public void myLock(){
        System.out.println(Thread.currentThread().getName()+"进入lock");
        while(!atomicReference.compareAndSet(null,Thread.currentThread())){

        }
    }
    public void myUnLock(){
        System.out.println(Thread.currentThread().getName()+"准备解锁");
        atomicReference.compareAndSet(Thread.currentThread(),null);
    }

    public static void main(String[] args) throws InterruptedException {
        MySpinLockDemo mySpinLockDemo=new MySpinLockDemo();
        new Thread(()->{
            mySpinLockDemo.myLock();
            System.out.println("线程A加锁，并等待5s");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySpinLockDemo.myUnLock();
            System.out.println("线程A解锁");
        },"线程A").start();
        Thread.sleep(1000);
        new Thread(()->{
            mySpinLockDemo.myLock();
            System.out.println("线程B加锁");
            mySpinLockDemo.myUnLock();
            System.out.println("线程B解锁");
        },"线程B").start();
    }
}
