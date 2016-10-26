package headfirst.design.builder;

public class Client {

	public static void main(String[] args) {
		IBulider bulider = new ConcreateBuilder();
		Desinger desinger = new Desinger();
		desinger.order2design(bulider);
	}
}
