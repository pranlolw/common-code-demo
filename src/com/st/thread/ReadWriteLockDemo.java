package com.st.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadWriteLockDemo {
    private volatile Map<Integer,Integer> map=new HashMap<>();
    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();

    public void put(Integer key,Integer value){
        try{
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"开始写入，key=>："+key);
            Thread.sleep(200);
            map.put(key,key);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(Integer key){
        try{
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"开始读取，key=>："+key);
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName()+"读取结果，key=>："+key+",value=>"+map.get(key));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo=new ReadWriteLockDemo();
        for (int i = 0; i <5 ; i++) {
            int finalI = i;
            new Thread(()->{
                readWriteLockDemo.put(finalI,finalI);
            }).start();
        }

        for (int i = 0; i <5 ; i++) {
            int finalI = i;
            new Thread(()->{
                readWriteLockDemo.get(finalI);
            }).start();
        }
    }

}
