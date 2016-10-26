package headfirst.design.template;


public abstract class Template {

	void prepare() {
		boiwater();
		brew();
		pourIncup();

		if(customerwantsconditions())
		addCondis();
	}

	public boolean customerwantsconditions() {
		return true;
	}

	private void pourIncup() {
		System.err.println("倒入杯中");
	}

	private void boiwater() {
		System.err.println("烧水");
	}

	public abstract void brew();

	public abstract void addCondis();

	
	
}
