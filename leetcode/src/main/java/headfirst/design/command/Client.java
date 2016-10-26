package headfirst.design.command;

public class Client {

	
	public static void main(String[] args) {
		
		//创建接收者对象 即播放器
		AudioPlayer player = new AudioPlayer();
		 
		
		//创建命令对象
		Command playCommand = new PlayCommand(player);
		
		Command stopCommand = new StopCommand(player);
		
		Command reCommand = new ReviewCommand(player);
		
		
		//创建请求对象
		KeyPad pad = new KeyPad();
		pad.setPlayCommand(playCommand);
		pad.setStopCommand(stopCommand);
		pad.setReviewCommand(reCommand);
		
		
		//测试
		pad.play();
		pad.stop();
		pad.review();
		pad.stop();
		
		
	}
	
	
}
