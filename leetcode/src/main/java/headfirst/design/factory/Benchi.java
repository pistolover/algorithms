package headfirst.design.factory;

public class Benchi implements Car {

	@Override
	public void run() {
		System.err.println("Benchi run");

	}

	@Override
	public void start() {
		System.err.println("Benchi start");
	}
}
