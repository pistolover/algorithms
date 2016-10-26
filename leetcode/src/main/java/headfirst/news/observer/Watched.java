package headfirst.news.observer;

/**
 * 被观察者接口
 */
public interface Watched {
	public void addObserver(Watcher o);

	public void deleteObserver(Watcher o);

	public void notifyObservers();

	public void deleteObservers();
}