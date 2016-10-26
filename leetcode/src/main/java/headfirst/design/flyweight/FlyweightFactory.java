package headfirst.design.flyweight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//享元工厂 负责创建享元对象
public class FlyweightFactory {
	
	private Map<Character, Flyweight> maps = new  HashMap<Character, Flyweight>();
	
	public Flyweight factory(List<Character> list){
		ConcreateCompositeFlyweight compositeFlyweight = new ConcreateCompositeFlyweight();
		for (Character c : list) {
			compositeFlyweight.add(c, this.factory(c));
		}
		
		return compositeFlyweight;
		
	}
	
	public Flyweight  factory(Character c){
		Flyweight flyweight = maps.get(c);
		
		if(flyweight==null){
			flyweight = new ConcreateFlyweight(c);
			maps.put(c, flyweight);
		}
		
		return flyweight;
		
	}
}
