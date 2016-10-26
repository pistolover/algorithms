package headfirst.design.composite;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Worker {

	private List<Worker> workers = new ArrayList<Worker>();
	private String name;
	
	public Manager(String name){
		super();
		this.name = name;
	}
	
	
	public List<Worker> getWorkers() {
		return workers;
	}


	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public void method() {

		System.err.println("我是"+getName()+",我是一名领导，我有"+workers.size()+"下属");
		
		for (Worker worker : workers) {
			worker.method();
		}
		
	}
	
	public void add(Worker worker){
		workers.add(worker);
	}
	
	public void remove(Worker worker){
		workers.remove(worker);
	}
	
	public Worker getChild(int i){
		return workers.get(i);
	}

}
