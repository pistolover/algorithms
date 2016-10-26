package headfirst.design.strategy;

public class Context {
	private IStrategy strategy;
	
	public Context(IStrategy strategy){
		this.strategy = strategy;
	}
	
	public void doStrategy(String something){
		this.strategy.dosomething(something);
	}
}
