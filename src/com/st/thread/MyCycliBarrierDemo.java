package com.st.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyCycliBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5,()->{
            System.out.println("执行完成");
        });
        for (int i = 0; i <5 ; i++) {
            new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"进入");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
        }

    }
}
