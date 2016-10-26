package headfirst.design.factoryMethod;

public  abstract class Create {

	//�������������ʲô��Ʒ
	public Car factoryMethod(String name){
		Car car = createCar(name);
		//go
		car.run("120");
		return car;
	}

	public abstract Car createCar(String name);
}
