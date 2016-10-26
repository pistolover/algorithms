package headfirst.design.adapter;

/**
 * 将男人进行适配
 *
 */
public class Adapter implements IWomen {
	private IMen men;

	public Adapter(IMen men) {
		super();
		this.men = men;
	}

	@Override
	public void womenrun() {
		men.run();
	}

	@Override
	public void womensleep() {
		men.sleep();
	}

	@Override
	public void womencreatBaby() {
		men.groupBaby();
	}

}
