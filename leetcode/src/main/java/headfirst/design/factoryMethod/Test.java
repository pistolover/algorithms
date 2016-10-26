package headfirst.design.factoryMethod;

public class Test {
	public static void main(String[] args) {
	
		Create create = new CarCreate();
		Car car = create.factoryMethod("Biyadi");
		car.say();
		
		
		Create create2 = new CarCreate();
		Car car2 = create2.factoryMethod("Benchi");
		car2.say();
		
	}
}
