package headfirst.design.composite;

public class Employee implements Worker {
	private String name;
	
	public Employee(String name){
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public void method() {
		System.err.println("我是"+getName()+",我是一名IT民工");
	}

}
