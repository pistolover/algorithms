package headfirst.design.command;

//具体命令角色
public class PlayCommand implements Command {
	private AudioPlayer audioPlayer;
	
	public PlayCommand(AudioPlayer player){
		this.audioPlayer  = player;
	}
	
	
	@Override
	public void excute() {
		audioPlayer.play();
	}


}
