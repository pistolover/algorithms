package headfirst.design.JDKproxy;

public class ConcreateTarget implements ITarget {

	@Override
	public void update() {
		System.err.println("I am jdk proxy");
		
	}

}
