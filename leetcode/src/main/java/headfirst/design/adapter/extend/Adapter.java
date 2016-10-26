package headfirst.design.adapter.extend;

/**
 *适配器，表面上调用method()方法，实际调用specialMethod()
 */
public class Adapter extends Adaptee implements Itarget{
	@Override
	public void method() {
		super.specialMethod();
	}
}
