package test;

public class TestCu2 {

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(20000);
        System.err.println("start");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (Integer k = Integer.MAX_VALUE; i > 0; i--) {
                    i = i + k;
                    System.err.println("j: " + i);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (Integer m= Integer.MAX_VALUE; m > 0; m--) {
                    i = i + m;
                    System.err.println("i: " + i);
                }
            }
        });
        thread1.start();
        thread2.start();
    }

}
