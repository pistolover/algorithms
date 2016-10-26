package headfirst.design.abstractFactory;

public class ConcreateFactory2  extends Factory2{

	@Override
	public IproductA getproductA2() {
		return new ProductA2();
	}

	@Override
	public IproductB getproductB2() {
		return new ProductB2();
	}
}
