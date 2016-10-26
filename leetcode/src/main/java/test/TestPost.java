package test;

import javax.annotation.PostConstruct;

public class TestPost {

    public static void main(String[] args) {
        Post post = new Post();
        post.processMmsMessageTrigger();
        System.err.println(999999999);
    }

    static class Post {
        Post() {
        }

        private void processMmsMessageTrigger() {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    System.err.println(123);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread t2 = new Thread() {
                @Override
                public void run() {
                    System.err.println(456);
                }
            };
            t1.start();
            t2.start();
        }

        @PostConstruct
        public void test1() {
            System.err.println(888);
        }
    }
}
