package headfirst.news.strategy;

public class GeneralUser implements IStrategy {

    @Override
    public double getPrice(double price) {
        System.err.println("you are general user");
        return price;
    }

}
