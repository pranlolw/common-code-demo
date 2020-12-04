package com.st.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    //private boolean next=false;
    private volatile boolean next=false;
    public void setTrue(){
        next=true;
    }

    private volatile int num=0;
    public void addNum(){
        num++;
    }

    //用AtomicInteger保证原子性
   private AtomicInteger atomicInteger=new AtomicInteger();

    /**
     * 不保证原子性
     * 可以用AtomicInteger保证原子性
     */
    @Test
    public void yuanZiXing(){
        VolatileDemo v=new VolatileDemo();
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<1000;j++){
                    v.num++;
                    v.atomicInteger.getAndIncrement();
                }
            }).start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(v.num);
        System.out.println(v.atomicInteger.get());
    }



    /**
     * 保证可见性
     */
    @Test
    public void keJianXing(){
        VolatileDemo v=new VolatileDemo();
        new Thread(()->{
            System.out.println("进入线程1");
            try {
                Thread.sleep(5000);
                v.next=true;
                //v.setTrue();
                System.out.println("改变next后next="+v.next);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while(true){
            if(v.next){
                System.out.println("next发生变化");
                break;
            }
        }
    }



}
