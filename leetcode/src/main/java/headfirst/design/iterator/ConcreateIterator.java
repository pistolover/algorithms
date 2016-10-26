package headfirst.design.iterator;

public class ConcreateIterator implements Iterator {

	private ConcreateAggregate aggregate;
	private static int count=0;
	
	public ConcreateIterator(ConcreateAggregate concreateAggregate) {
		this.aggregate = concreateAggregate;
	}

	@Override
	public Object first() {
		return aggregate.get(0);
	}

	@Override
	public Object next() {
		Object obj = aggregate.get(count);
		count++;
		return obj;
	}

	@Override
	public boolean isend() {

		return count>=aggregate.count()||aggregate.get(count)==null?false:true;
	}

	@Override
	public Object currentItem() {
		return aggregate.get(count);
	}

}
