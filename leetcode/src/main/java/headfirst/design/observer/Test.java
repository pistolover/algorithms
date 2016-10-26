package headfirst.design.observer;

public class Test {
	public static void main(String[] args) {
		Observer observer1 = new ConcreateObserver("one  ");
		Observer observer2 = new ConcreateObserver("two  ");
		Observer observer3 = new ConcreateObserver("three  ");

		Subject sub = new ConcreateSubject();
		sub.addObserver(observer1);
		sub.addObserver(observer2);
		sub.addObserver(observer3);

		sub.notifyall();

	}

}
