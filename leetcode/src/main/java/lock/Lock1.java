package lock;

public class Lock1 {

	public synchronized void get() {

	}

	public int getValue() {
		synchronized (this) {

		}

		return 0;
	}

}
