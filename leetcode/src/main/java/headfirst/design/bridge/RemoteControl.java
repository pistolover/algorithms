package headfirst.design.bridge;

public abstract class RemoteControl {
	
	private ITV tv;
	
	public RemoteControl(ITV tv){
		this.tv = tv;
	}
	
	public void turnOn(){
		tv.on();
	}
	
	public void turnOff(){
		tv.off();
	}
	
	public void switchChanne(int channel){
		tv.switchChannel(channel);
	}
	
}
