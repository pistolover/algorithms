package concurrent;

public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Join01 join01 = testJoin.new Join01();
        Join02 join02 = testJoin.new Join02();
        Join03 join03 = testJoin.new Join03();
        join01.start();
        join01.join();
        join02.start();
        join02.join();
        join03.start();
    }

    class Join01 extends Thread {

        @Override
        public void run() {
            System.err.println("join1");
        }
    }

    class Join02 extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.err.println("join2");
        }
    }

    class Join03 extends Thread {

        @Override
        public void run() {
            System.err.println("join3");
        }
    }
}
