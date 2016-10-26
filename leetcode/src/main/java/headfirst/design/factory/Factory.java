package headfirst.design.factory;

public class Factory {

	public static Car getInstance(String clazz) {
		Car car = null;
		try {
			car = (Car) Class.forName("com.design.factory." + clazz)
					.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return car;
	}
}
