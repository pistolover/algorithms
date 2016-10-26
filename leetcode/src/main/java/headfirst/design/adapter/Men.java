package headfirst.design.adapter;

/**
 * 具体类
 */
public class Men implements IMen {

	@Override
	public void run() {

		System.err.println("Men run fast");
	}

	@Override
	public void sleep() {
		System.err.println("Men want sleep");

	}

	@Override
	public void groupBaby() {
		System.err.println("men can group baby");
	}

}
