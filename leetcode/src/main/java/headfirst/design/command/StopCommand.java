package headfirst.design.command;

//具体命令角色
public class StopCommand implements Command {
	
	private AudioPlayer audioPlayer;
	
	public StopCommand(AudioPlayer player){
		this.audioPlayer  = player;
	}
	
	@Override
	public void excute() {
		audioPlayer.stop();
	}


}
