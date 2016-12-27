package headfirst.news.strategy;

/**
 * 一般用户
 * @author liqqc
 *
 */
public class GeneralUser implements IStrategy {

    @Override
    public double getPrice(double price) {
        System.err.println("you are general user");
        return price;
    }

}
