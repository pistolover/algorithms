package headfirst.design.bridge;

public class TCL implements ITV {

	@Override
	public void on() {
		System.err.println("TCL on...");
	}

	@Override
	public void off() {
		System.err.println("TCL off...");
	}

	@Override
	public void switchChannel(int channel) {
		System.err.println("Switch on..."+channel);
	}
}
