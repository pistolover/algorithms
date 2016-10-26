package headfirst.design.template;

public class Tea extends Template {

	@Override
	public void brew() {
		System.err.println("沏茶");
		
	}

	@Override
	public void addCondis() {
		System.err.println("茶不需要添加东西");
		
	}

	@Override
	public boolean customerwantsconditions() {
		return false;
	}

	
	
}
