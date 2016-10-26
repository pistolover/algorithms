package headfirst.design.strategy;

public class ConcreateStrategyB implements IStrategy{

	@Override
	public void dosomething(String something) {
		System.err.println("ConcreateStrategyB"+something);
	}

}
