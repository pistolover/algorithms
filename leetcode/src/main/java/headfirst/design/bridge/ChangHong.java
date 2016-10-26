package headfirst.design.bridge;

public class ChangHong implements ITV{

	@Override
	public void on() {
		System.err.println("ChangHong on...");
	}

	@Override
	public void off() {
		System.err.println("ChangHong off...");
	}

	@Override
	public void switchChannel(int channel) {
		System.err.println("ChangHong on..."+channel);
	}

}
