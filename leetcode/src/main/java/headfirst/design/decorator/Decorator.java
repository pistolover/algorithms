package headfirst.design.decorator;

public class Decorator  implements Componet{

	private Componet componet;
	
	public Decorator(Componet cp){
		this.componet = cp;
	}
	
	@Override
	public void method() {
		componet.method();
	}

}
