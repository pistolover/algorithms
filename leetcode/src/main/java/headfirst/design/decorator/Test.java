package headfirst.design.decorator;

public class Test {
	
	public static void main(String[] args) {
		Componet cp = new ConcreateComponet();
		Decorator decorator = new ConcreateDecotator(cp);
		decorator.method();
	}
}
