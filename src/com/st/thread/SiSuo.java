package com.st.thread;

public class SiSuo {
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (Object.class){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (SiSuo.class){

                }
            }
        }).start();
        new Thread(()->{
            synchronized (SiSuo.class){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Object.class){

                }
            }
        }).start();
    }
}
