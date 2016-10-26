package headfirst.design.command;

public class KeyPad {
	private Command playCommand;
	
	private Command stopCommand;

	private Command reviewCommand;

	public void setPlayCommand(Command playCommand) {
		this.playCommand = playCommand;
	}

	public void setStopCommand(Command stopCommand) {
		this.stopCommand = stopCommand;
	}

	public void setReviewCommand(Command reviewCommand) {
		this.reviewCommand = reviewCommand;
	}

	
	public void play(){
		playCommand.excute();
	}
	
	
	public void stop(){
		stopCommand.excute();
	}
	
	public void review(){
		reviewCommand.excute();
	}
	
}
