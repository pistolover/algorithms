package headfirst.news.observer;

public class ConcreateBook extends ConcreateWatched {
	private String name = "";
	private double price = 0.0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// 当书的价格修改时调用该方法
	public void modifyPrice(ConcreateBook b) {
		// 调用父类的方法，改变被观察者的状态
		setChanged();

		// 通知客户该书已降价
		notifyObservers(b);
	}
}
