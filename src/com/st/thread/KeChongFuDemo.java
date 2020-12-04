package com.st.thread;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 证明可重入锁synchronized、reentrantlock
 */
public class KeChongFuDemo {
    public synchronized void one(){
        System.out.println(Thread.currentThread().getName()+" 进入one()");
        two();
    }
    public synchronized void two(){
        System.out.println(Thread.currentThread().getName()+" 进入two()");
    }

    @Test
    public void synchronizedDemo(){
        KeChongFuDemo keChongFuDemo=new KeChongFuDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                keChongFuDemo.one();
            },"线程"+i).start();
        }
    }


    Lock lock=new ReentrantLock();

    public void three(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" 进入three()");
            four();
        }finally {
            lock.unlock();
        }
    }
    public void four(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" 进入four()");
        }finally {
            lock.unlock();
        }
    }
    @Test
    public void lockDemo(){
        KeChongFuDemo keChongFuDemo=new KeChongFuDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                keChongFuDemo.three();
            },"线程"+i).start();
        }
    }

}
