package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriteLock {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static ReadLock readLock = readWriteLock.readLock();
    private static WriteLock writeLock = readWriteLock.writeLock();
    private static Map<Integer, String> map = new HashMap<>();
    static {
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                get(1);
            }
        });
        t1.start();
        
        
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                put(4, "4");
            }
        });
        t2.start(); 
    }

    public static void get(int key) {
        readLock.lock();
        System.err.println(map.get(1));
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        readLock.unlock();
    }

    public static void put(int key, String value) {
        readLock.lock();
        System.err.println("......");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readLock.unlock();

        writeLock.lock();
        map.put(key, value);
        System.err.println(map.get(key));
        writeLock.unlock();
    }
}