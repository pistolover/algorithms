package test.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.RuntimeErrorException;

public class Bank {
    private static final Map<Integer, Double> accounts = new ConcurrentHashMap<Integer, Double>();
    private final Lock lock = new ReentrantLock();

    public Bank() {
    }

    public synchronized void transform(int from, int to, double money) {
        try {
            if (accounts.get(from) == null || accounts.get(from) < money) {
                throw new RuntimeErrorException(new Error("transform error"));
            }
            Double fromAccount = accounts.get(from);
            accounts.put(from, fromAccount - money);
            Double toAccount = accounts.get(to);
            accounts.put(to, toAccount + money);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
        }
    }

    public void save(Integer account, double money) {
        if (accounts.get(account) == null) {
            accounts.put(account, money);
        } else {
            double fromAccount = accounts.get(account);
            accounts.put(account, fromAccount + money);
        }
    }

    public double get(Integer account) {
        if (accounts.get(account) != null) {
            return accounts.get(account);
        }
        return 0d;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return accounts.toString();
    }

}
