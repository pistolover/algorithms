package headfirst.design.adapter;

public class Test {
	public static void main(String[] args) {

		Men men = new Men(); //创建一个男人
		Women women = new Women(); //创建一个女人
		IWomen awomen = new Adapter(men);//将男人包装到一个女人适配器，让他看起来像女人
		awomen.womenrun();
		awomen.womensleep();
		awomen.womencreatBaby();
		
		System.err.println("women....");
		women.womenrun();
		women.womensleep();
		women.womencreatBaby();
	}
}
