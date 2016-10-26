package headfirst.design.adapter;

/**
 * 具体类
 * @author liqqc
 *
 */
public class Women implements IWomen {

	@Override
	public void womenrun() {
		System.err.println("women run slow");

	}

	@Override
	public void womensleep() {

		System.err.println("women need to sleep");
	}

	@Override
	public void womencreatBaby() {
		System.err.println("women can create baby");

	}

}
