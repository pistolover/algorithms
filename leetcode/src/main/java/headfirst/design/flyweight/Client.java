package headfirst.design.flyweight;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		
		List<Character> states = new ArrayList<Character>();
		states.add('a');
		states.add('b');
		states.add('c');
		states.add('d');
		states.add('e');
		states.add('f');

		
		FlyweightFactory factory = new FlyweightFactory();
		
		Flyweight factory2 = factory.factory(states);
		Flyweight factory3 = factory.factory(states);
		
		factory2.method("gooddd");
		
		
		Character c= 'm';
		Flyweight factory4 = factory.factory(c);
		Flyweight factory5 = factory.factory(c);
		
		System.err.println(factory4 == factory5);
		
		
		
		
	}
	
	
	
}
