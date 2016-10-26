package headfirst.design.strategy;

public class ConcreateStrategyA implements IStrategy{

	@Override
	public void dosomething(String something) {
		System.err.println("ConcreateStrategyA"+something);
	}

}
