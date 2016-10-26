package headfirst.design.state;

public class Client {

	public static void main(String[] args) {
		
		//创建状态
		State stateA = new ConcreateStateA();
		State stateB = new ConcreateStateB();
		
		
		//创建环境
		Context context1 = new Context();
		Context context2 = new Context();
		
		
		//将状态放入环境
		context1.setState(stateA);
		context2.setState(stateB);
		
		//请求处理
		context1.request("状态1");
		context2.request("状态2");
		
		
		
	}
	
	
}
