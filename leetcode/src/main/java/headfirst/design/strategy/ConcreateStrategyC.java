package headfirst.design.strategy;

public class ConcreateStrategyC implements IStrategy{

	@Override
	public void dosomething(String something) {
		System.err.println("ConcreateStrategyC"+something);
	}

}
