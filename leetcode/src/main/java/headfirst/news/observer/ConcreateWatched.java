package headfirst.news.observer;

import java.util.Vector;

/**
 * 具体的被观察者
 */
public class ConcreateWatched implements Watched {
    private boolean changed = false;
    private Vector<Watcher> obs;

    public ConcreateWatched() {
        obs = new Vector<>();
    }

    @Override
    public synchronized void addObserver(Watcher o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    @Override
    public synchronized void deleteObserver(Watcher o) {
        obs.removeElement(o);
    }

    @Override
    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg) {
        Object[] arrLocal;

        synchronized (this) {
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--)
            ((Watcher) arrLocal[i]).update(this, arg);
    }

    @Override
    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return obs.size();
    }
}
