package headfirst.news.strategy;

/**
 * 普通会员
 * @author liqqc
 *
 */
public class VipUser implements IStrategy {

    @Override
    public double getPrice(double price) {
        System.err.println("you are vip ,can get disaccount");
        return price * 0.9;
    }
}
