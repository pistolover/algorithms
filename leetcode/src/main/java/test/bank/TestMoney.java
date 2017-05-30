package test.bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.AtomicDouble;

public class TestMoney {

    public static final ExecutorService executorService = Executors.newFixedThreadPool(20);

    static Bank bank = new Bank();

    private static volatile AtomicDouble atomicDouble = new AtomicDouble();
    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            bank.save(i, i * 10);
        }
        for (int i = 0; i < 100; i++) {
            double d = Math.random() * 10;
            atomicDouble.set(d + atomicDouble.get());
            Runnable transferRunnable = new TransferRunnable(bank, 1000, 1, d);
            executorService.execute(transferRunnable);
        }
        System.err.println("account 1: " + bank.get(1));
        System.err.println("account 1000: " + bank.get(1000));
        System.err.println("sum: " + atomicDouble.get());

        System.err.println("need: " + (bank.get(1000) + atomicDouble.get()));
        System.err.println("need: " + (bank.get(1) - atomicDouble.get()));
    }
}
