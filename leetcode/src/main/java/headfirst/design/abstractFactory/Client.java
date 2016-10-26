package headfirst.design.abstractFactory;

public class Client {
	public static void main(String[] args) {

		Factory1 factory = new ConcreateFactory1();

		IproductA a1 = factory.getproductA1();

		IproductB b1 = factory.getProductB1();

		a1.method();

		b1.method();

	}
}
