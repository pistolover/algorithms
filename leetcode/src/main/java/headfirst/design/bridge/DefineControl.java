package headfirst.design.bridge;

public class DefineControl extends RemoteControl {

	public DefineControl(ITV tv) {
		super(tv);
	}

	public void switchChannelOn(int channel){
		switchChanne(channel);
		System.err.println("use board to set channel");
	}
	
}
