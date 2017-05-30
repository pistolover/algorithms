package test.bank;

public class TransferRunnable extends Thread {
    private volatile Bank bank;
    private Integer fromAccount;
    private Integer toAccount;
    private double money = 0;

    public TransferRunnable(Bank b, Integer fromAccount, Integer toAccount, double money) {
        this.bank = b;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.money = money;
    }

    
    
    @Override
    public synchronized void start() {
        super.start();
        run();
    }



    @Override
    public void run() {
        bank.transform(fromAccount, toAccount, money);
    }
}
