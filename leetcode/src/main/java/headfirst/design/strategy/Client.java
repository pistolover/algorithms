package headfirst.design.strategy;

public class Client {

	public static void main(String[] args) {
		
		IStrategy strategy = new ConcreateStrategyA();
		
		Context context = new Context(strategy);
		
		context.doStrategy(" run ....");
		
		
	}
	
	
}
