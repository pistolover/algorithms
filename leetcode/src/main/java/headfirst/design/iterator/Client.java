package headfirst.design.iterator;

public class Client {

	public static void main(String[] args) {
		
		ConcreateAggregate aggregate = new ConcreateAggregate();
		aggregate.add("张三");
		aggregate.add("李四");
		aggregate.add("王五");
		
		Iterator tIterator = new ConcreateIterator(aggregate);
		
		while (tIterator.isend()) {
			System.err.println(tIterator.currentItem());
			tIterator.next();
		}
		
	}
}
