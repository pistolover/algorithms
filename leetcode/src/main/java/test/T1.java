package test;

import java.util.HashMap;

import org.junit.Test;

public class T1 {

    @Test
    public void t1() {
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY = (1 << COUNT_BITS) - 1;
        System.err.println(1 << COUNT_BITS);

        for (;;) {
            System.err.println(111);
        }
    }

    static HashMap<Integer, Integer> maps = new HashMap<>();

    public static void main(String[] args) {
        Thread thread = new Thread() {
            public void run() {
                for (int i = 0; i < 200000; i++) {
                    maps.put(i, i);
                }
                System.err.println("t1 over");
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 200000; i++) {
                    maps.put(i, i);
                }
                System.err.println("t2 over");
                System.err.println(maps);

            }
        };

        thread.start();
        thread2.start();

    }
}
