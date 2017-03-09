package concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在公平的锁上，线程按照他们发出请求的顺序获取锁，但在非公平锁上，则允许‘插队’：当一个线程请求非公平锁时，如果在发出请求的同时该锁变成可用状态，
 * 那么这个线程会跳过队列中所有的等待线程而获得锁。     非公平的ReentrantLock 并不提倡 插队行为，但是无法防止某个线程在合适的时候进行插队。

在公平的锁中，如果有另一个线程持有锁或者有其他线程在等待队列中等待这个所，那么新发出的请求的线程将被放入到队列中。
而非公平锁上，只有当锁被某个线程持有时，新发出请求的线程才会被放入队列中。

非公平锁性能高于公平锁性能的原因：

在恢复一个被挂起的线程与该线程真正运行之间存在着严重的延迟。

假设线程A持有一个锁，并且线程B请求这个锁。由于锁被A持有，因此B将被挂起。当A释放锁时，B将被唤醒，因此B会再次尝试获取这个锁。
与此同时，如果线程C也请求这个锁，那么C很可能会在B被完全唤醒之前获得、使用以及释放这个锁。这样就是一种双赢的局面：B获得锁的时刻并没有推迟，C更早的获得了锁，并且吞吐量也提高了。

当持有锁的时间相对较长或者请求锁的平均时间间隔较长，应该使用公平锁。在这些情况下，插队带来的吞吐量提升（当锁处于可用状态时，线程却还处于被唤醒的过程中）可能不会出现。
 * @author liqqc
 *
 */
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