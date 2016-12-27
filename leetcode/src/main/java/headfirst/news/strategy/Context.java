package headfirst.news.strategy;

/**
 * 
 * @author liqqc
 *
 */
public class Context {

    private IStrategy iStrategy;

    public Context(IStrategy strategy) {
        this.iStrategy = strategy;
    }

    public Double getQuota(Double price) {
        return this.iStrategy.getPrice(price);
    }
}
