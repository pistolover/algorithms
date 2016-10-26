package headfirst.design.proxy;

public class TargetObject implements Itraget {

	@Override
	public void say() {
		System.err.println("I want to say something");
	}
	
}
