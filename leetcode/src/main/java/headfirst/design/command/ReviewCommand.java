package headfirst.design.command;

//具体命令角色
public class ReviewCommand implements Command {
	private AudioPlayer audioPlayer;
	
	public ReviewCommand(AudioPlayer player){
		this.audioPlayer  = player;
	}
	
	@Override
	public void excute() {
		audioPlayer.review();
	}


}
