package concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestReenTrantLock {

    private static volatile AtomicInteger locknumber = new AtomicInteger();
    private static volatile AtomicInteger syncnumber = new AtomicInteger();

    public static void main(String[] args) {

        Long st = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Lock().start();
        }

        if (10 == locknumber.get()) {
            System.err.println("lock: " + (System.currentTimeMillis() - st));
        }

        st = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new synchron().start();
        }
        while (10 == syncnumber.get()) {
            System.err.println("synchron: " + (System.currentTimeMillis() - st));
            break;
        }
    }

    static class synchron extends Thread {
        @Override
        public void run() {
            syncnumber.incrementAndGet();
        }
    }

    static class Lock extends Thread {
        final static ReentrantLock lk = new ReentrantLock();

        @Override
        public void run() {
            lk.lock();
            locknumber.incrementAndGet();
            lk.unlock();
        }

    }

}