package headfirst.design.template;

public class Test {
	public static void main(String[] args) {
		Template template = new Tea();
		template.prepare();
		
		Template template2 = new Coffer();
		template2.prepare();
	}
}
