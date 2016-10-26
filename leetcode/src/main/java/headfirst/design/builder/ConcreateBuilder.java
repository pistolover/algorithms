package headfirst.design.builder;

//工人负责构建房子
public class ConcreateBuilder implements IBulider {

	@Override
	public void makeDoor() {
		// TODO Auto-generated method stub
		System.err.println("start makedoor...");
	}

	@Override
	public void makeWindow() {
		// TODO Auto-generated method stub
		System.err.println("start makeWindow...");

	}

	@Override
	public void makeFloor() {
		// TODO Auto-generated method stub
		System.err.println("start makeFloor...");

	}

	@Override
	public void makeRoom() {
		// TODO Auto-generated method stub
		System.err.println("start makeRoom...");
	}

	@Override
	public void fitup() {
		// TODO Auto-generated method stub
		System.err.println("last fit up the house...");
	}

}
