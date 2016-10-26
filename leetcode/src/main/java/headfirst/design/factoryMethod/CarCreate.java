package headfirst.design.factoryMethod;

public class CarCreate	extends Create {

	@Override
	public Car createCar(String name) {
		Car car = null;
		try {
			car = (Car) Class.forName("com.design.factoryMethod."+name).newInstance();
		} catch (Exception e) {
		}
		return car;
	}

}
