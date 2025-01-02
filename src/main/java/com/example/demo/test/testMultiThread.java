package com.example.demo.test;

public class testMultiThread {
    public static void main(String[] args) {
//        MyThread myThread1 = new MyThread();
//        MyThread myThread2 = new MyThread();
//        MyThread myThread3 = new MyThread();
//        myThread1.setName("窗口1");
//        myThread2.setName("窗口2");
//        myThread3.setName("窗口3");
//        myThread1.start();
//        myThread2.start();
//        myThread3.start();

        Ticket ticket = new Ticket();
        Thread thread = new Thread(ticket);
        thread.setName("窗口1");
        Thread thread1 = new Thread(ticket);
        thread1.setName("窗口2");
        Thread thread2 = new Thread(ticket);
        thread2.setName("窗口3");

        thread.start();
        thread1.start();
        thread2.start();
    }
}

//class MyThread extends Thread{
//    static int ticket = 0;
//    static final Object obj = new Object();
//
//    @Override
//    public void run() {
//        while (true) {
//            synchronized (obj) {
//                if (ticket < 100) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    ticket++;
//                    System.out.println(getName() + "正在卖第" + ticket + "张票");
//                } else break;
//            }
//        }
//    }
//}

class Ticket implements Runnable {
    int ticket = 0;

    public synchronized boolean sale() {
        if (ticket < 100) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "t正在卖第" + ++ticket + "张票");
        } else return false;
        return true;
    }

    @Override
    public void run() {
        while (sale()) {
        }
    }
}