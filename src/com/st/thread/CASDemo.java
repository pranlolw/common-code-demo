package com.st.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * cas:compare and swap
 */
public class CASDemo {
    /**
     * 自定义类实现cas
     */
    @Test
    public void myCas(){
        Integer num=0;
        AtomicReference<Integer> atomicReference=new AtomicReference<>();
        atomicReference.set(num);
        atomicReference.compareAndSet(0,1);
        atomicReference.compareAndSet(1,2);

        System.out.println(atomicReference.get());
    }

    /**
     * ABA问题
     * 两个线程，初始值为A，如果线程1执行较慢，线程2较快，当线程2将值改为B后又将值改为A，然后线程1也会执行成功，但这个A并不是最开始的A
     */
    @Test
    public void aba() throws InterruptedException {
        Integer num=0;
        AtomicReference<Integer> atomicReference=new AtomicReference<>();
        final CountDownLatch cdl = new CountDownLatch(2);//参数为线程个数
        atomicReference.set(num);
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+atomicReference.compareAndSet(0, 1));
            System.out.println(Thread.currentThread().getName()+"\t"+atomicReference.compareAndSet(1, 0));
            cdl.countDown();
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"\t"+atomicReference.compareAndSet(0, 3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cdl.countDown();
        }).start();

        cdl.await();
    }

    /**
     * 解决ABA问题，加版本号
     */
    @Test
    public void resolveABA() throws InterruptedException {

        AtomicStampedReference<Integer> reference=new AtomicStampedReference(0,1);
        CountDownLatch countDownLatch=new CountDownLatch(2);
        new Thread(()->{
            int stamp=reference.getStamp();//版本号
            System.out.println(Thread.currentThread().getName()+"/t"+stamp);
            try {
                Thread.sleep(2000);
                reference.compareAndSet(0,2,reference.getStamp(),reference.getStamp()+1);
                reference.compareAndSet(2,0,reference.getStamp(),reference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"/t,当前版本号"+reference.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        },"线程1").start();
        new Thread(()->{
            int stamp=reference.getStamp();//版本号
            System.out.println(Thread.currentThread().getName()+"/t"+stamp);
            try {
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName()+"/t 转换结果："+reference.compareAndSet(0, 4, stamp, reference.getStamp() + 1));
                System.out.println(Thread.currentThread().getName()+"/t,当前版本号"+reference.getStamp()+"/t 当前值："+reference.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        },"线程2").start();
        countDownLatch.await();
    }

}

