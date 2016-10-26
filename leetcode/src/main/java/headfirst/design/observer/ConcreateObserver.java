package headfirst.design.observer;

public class ConcreateObserver implements Observer {
	private String name;
	
	public ConcreateObserver(String name){
		this.name = name;
	}
	
	@Override
	public void update(String mess) {
		System.err.println(name+mess);
	}

}
