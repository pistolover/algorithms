package headfirst.design.command;


//接收者角色 收音机扮演
public class AudioPlayer {

	public void play(){
		System.err.println("收音机播放");
	}
	
	public void stop() {

		System.err.println("收音机的停止功能");
		
	}
	
	public void review() {

		System.err.println("收音机重放功能");
		
	}
	
}
