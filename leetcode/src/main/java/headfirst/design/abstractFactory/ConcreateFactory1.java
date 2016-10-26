package headfirst.design.abstractFactory;

//具体的工厂生产产品
public class ConcreateFactory1 extends Factory1 {

	@Override
	public IproductA getproductA1() {
		return new ProductA1();
	}

	@Override
	public IproductB getProductB1() {
		return new ProductB1();
	}

}
