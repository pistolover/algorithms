package headfirst.news.strategy;

public class Main {

    public static void main(String[] args) {
        IStrategy iStrategy = new HighVipUser();

        Context context = new Context(iStrategy);

        Double price = context.getQuota(100.0);

        System.err.println(price);
    }

}
