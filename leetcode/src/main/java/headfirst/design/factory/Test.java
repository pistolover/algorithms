package headfirst.design.factory;

public class Test {
	public static void main(String[] args) {
		Car Benchi = Factory.getInstance("Benchi");
		Benchi.run();
		Benchi.start();
		
		System.err.println("------------");
		
		Car Biyadi = Factory.getInstance("Biyadi");
		Biyadi.run();
		Biyadi.start();
		
		System.err.println("------------");
		
		Car Dazhong = Factory.getInstance("Dazhong");
		Dazhong.run();
		Dazhong.start();
		
	}
}
