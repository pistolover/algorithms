package headfirst.design.state;

//环境角色
public class Context {
	private State state; //持有一个state类对象


	public void setState(State state) {
		this.state = state;
	}


	//调用感兴趣的方法
	public void request(String stateParam) {
		state.handle(stateParam); //让状态来处理
	}

}
