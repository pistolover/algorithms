package headfirst.design.factoryMethod;

//抽象产品角色
public abstract class Car {

	public String carname;
	
	public void run(String voice){
		System.err.println("run fast " + voice);
	}
	
	public void say(){
		System.err.println("create "+carname+" success");
	}
}
