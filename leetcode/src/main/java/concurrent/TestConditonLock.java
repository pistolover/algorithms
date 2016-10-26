package concurrent;

public class TestConditonLock {

    public static void main(String[] args) {
        ConditonLock lock = new ConditonLock();
        lock.start();
    }

    static class ConditonLock extends Thread {

        @Override
        public void run() {
            System.err.println("start waiting");
            try {
                Thread.sleep(100000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
