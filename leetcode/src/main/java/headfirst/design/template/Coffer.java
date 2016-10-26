package headfirst.design.template;

public class Coffer extends Template {

	@Override
	public void brew() {
		System.err.println("搅拌咖啡");
	}

	@Override
	public void addCondis() {
		System.err.println("咖啡加入糖");
	}

	@Override
	public boolean customerwantsconditions() {
		String answer = "yes";
		if (answer.equals("yes")) {
			return true;
		}
		return false;
	}

}
