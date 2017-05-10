package concurrent.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Consume {

    
    
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition condition = reentrantLock.newCondition();
    
    private static final Integer MAXSIZE = 10;
    
    private final List<String> goods = new ArrayList<>(0);
    
    
    public static void main(String[] args) {
        Consume consume = new Consume();
        MyThreadA a = new MyThreadA(consume);
        a.start();
        MyThreadB b = new MyThreadB(consume);
        b.start();
    }
    
    public void consumer(){
        try {
            reentrantLock.lock();
            if(goods.size() == 0){
                condition.await();
            }
            String string = goods.remove(0);
            System.err.println("consumer: " + string);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void producter(){
        try {
            reentrantLock.lock();
            if(goods.size() == MAXSIZE){
                condition.await();
            }
            goods.add("a");
            condition.signal();
            System.err.println("producter: " + "a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
     static class MyThreadA extends Thread{
        private Consume myService;

        public MyThreadA(Consume myService){
            super();
            this.myService = myService;
        }

        @Override
        public void run(){
            for (int i = 0 ; i < Integer.MAX_VALUE ; i++){
                myService.producter();;
            }
        }
    }
     
     static class MyThreadB extends Thread{
         private Consume myService;

         public MyThreadB(Consume myService){
             super();
             this.myService = myService;
         }

         @Override
         public void run(){
             for (int i = 0 ; i < Integer.MAX_VALUE ; i++){
                 myService.consumer();;
             }
         }
     }
}
