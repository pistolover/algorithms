package headfirst.design.proxy;

public class PorxyObject implements Itraget{
	private Itraget target;
	
	PorxyObject() {
		this.target = new TargetObject();
	}
	
	
	@Override
	public void say() {
		this.target.say();
	}

}
