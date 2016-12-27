package headfirst.news.strategy;

/**
 * 高级会员
 * @author liqqc
 *
 */
public class HighVipUser implements IStrategy {

    @Override
    public double getPrice(double price) {
        System.err.println("you are high vip, can get a high discount");
        return price * 0.5;
    }
}
