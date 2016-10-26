package headfirst.design.decorator;

public class ConcreateDecotator extends Decorator{
	public ConcreateDecotator(Componet cp) {
		super(cp);
	}

	public void method(){
		//可在之前添加方法
		addmethodbefore();
		
		super.method();
		
		//可在之后添加方法
		addmethodafter();
	}

	private void addmethodafter() {
		System.err.println("add method after");
	}

	private void addmethodbefore() {
		System.err.println("add method before");
	}

}
