package com.st.thread;

import java.util.concurrent.Semaphore;

public class MySemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(4);//总数为4，占完要等待释放
        for (int i = 0; i <7 ; i++) {
            new Thread(()->{
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"进入方法");
                    semaphore.acquire();
                    System.out.println("线程"+Thread.currentThread().getName()+"占用资源");
                    Thread.sleep(5000);
                    System.out.println("线程"+Thread.currentThread().getName()+"释放资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
