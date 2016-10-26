package headfirst.design.state;

public class ConcreateStateA implements State {

	@Override
	public void handle(String statePram) {
		System.err.println("开始执行" + statePram);
	}

}
