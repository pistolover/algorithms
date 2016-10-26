package headfirst.design.flyweight;

import java.util.HashMap;
import java.util.Map;


//复合享元对象
public class ConcreateCompositeFlyweight implements Flyweight {

	private Map<Character, Flyweight> maps = new HashMap<Character, Flyweight>();

	public void add(Character character, Flyweight flyweight) {
		maps.put(character, flyweight);
	}

	@Override
	public void method(String state) {
		Flyweight flyweight = null;
		for (Character c : maps.keySet()) {
			flyweight = maps.get(c);
			flyweight.method(state);
		}
	}

}
