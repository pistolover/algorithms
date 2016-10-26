package headfirst.design.builder;

//构建者接口，负责构建什么
public interface IBulider {

	public void makeDoor();

	public void makeWindow();

	public void makeFloor();

	public void makeRoom();

	public void fitup();

}
