package headfirst.design.iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreateAggregate implements Aggregate {

	private List<Object> lists = new ArrayList<Object>();
	
	
	@Override
	public Iterator getInIterator() {
		return new ConcreateIterator(this);
	}
	
	public void add(Object obj){
		lists.add(obj);
	}
	
	
	public Object get(int index){
		return lists.get(index);
	}
	
	public void remove(Object obj){
		lists.remove(obj);
	}
	
	public int count(){
		return lists.size();
	}
	
	

}
