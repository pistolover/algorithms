package headfirst.design.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreateSubject implements Subject {
	private List<Observer> observers = new ArrayList<Observer>();

	@Override
	public void addObserver(Observer observer) {
		if (observer != null) {
			observers.add(observer);
		}
	}

	@Override
	public void deleteObserver(Observer observer) {
		if (observer != null) {
			observers.remove(observer);
		}
	}

	@Override
	public void notifyall() {
		for (Observer observer : observers) {
			observer.update("update now");
		}
	}
}
