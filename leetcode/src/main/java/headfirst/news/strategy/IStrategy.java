package headfirst.news.strategy;

/**
 * 策略接口类
 * @author liqqc
 *
 */
public interface IStrategy {

    /*
     * 获取价格
     */
    public double getPrice(double price);
}
