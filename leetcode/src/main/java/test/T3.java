package test;

import java.util.concurrent.locks.ReentrantLock;

public class T3 {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dosomething(this);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                dosomething(this);
            }
        });

        t1.start();
        t2.start();

    }

    static void dosomething(Runnable runnable) {
        lock.lock();
        System.err.println(runnable.getClass());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        lock.unlock();
    }
}
