package concurrent;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**Collections.SynchronizedCollection()实质是搞了一个对象锁
 * 
 * CopyOnWriteArrayList  使用了 ReentrantLock在add方法加锁
 * 实质是使用copy进行才做，当有些操作时，会加锁，将原数组复制一份，将数值加入，
 * 然后将新的数组赋值给原数组
 * 
 * @author liqqc
 *
 */
public class CopyOnArrayList {

    static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
    static Executor executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        executor.execute(t1);
        executor.execute(t2);

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println(list);
    }

    static class T1 implements Runnable {
        @Override
        public void run() {
            list.add(11);
            list.add(12);
            list.add(13);
            list.add(14);
            list.add(11);
            list.add(11);
        }
    }

    static class T2 implements Runnable {
        @Override
        public void run() {
            if (list.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            list.remove(1);
        }
    }
}
