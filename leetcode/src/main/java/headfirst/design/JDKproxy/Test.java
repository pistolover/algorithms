package headfirst.design.JDKproxy;


public class Test {

	
	public static void main(String[] args) {
		
		PorxyHandle handle = new PorxyHandle();
		
		ITarget tItraget = (ITarget) handle.bind(new ConcreateTarget());
		
		tItraget.update();
		
	}
}
