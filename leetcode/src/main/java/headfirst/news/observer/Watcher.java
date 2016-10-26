package headfirst.news.observer;

/**
 * 观察者接口 提供update方法
 */
public interface Watcher {
    void update(Watched o, Object arg);
}
