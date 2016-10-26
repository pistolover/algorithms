package headfirst.design.flyweight;


//单纯享元对象
public class ConcreateFlyweight implements Flyweight {

	private Character character;
	
	public ConcreateFlyweight(Character state){
		this.character = state;
	}
	
	@Override
	public void method(String state) {
		System.err.println("linner..."+this.character);
		System.err.println("outer..."+state);
	}

}
