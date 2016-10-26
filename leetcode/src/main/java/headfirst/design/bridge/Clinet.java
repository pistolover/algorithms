package headfirst.design.bridge;

public class Clinet {

	public static void main(String[] args) {
		ITV tv = new ChangHong();

		DefineControl control = new DefineControl(tv);

		control.switchChannelOn(120);

	}
}
