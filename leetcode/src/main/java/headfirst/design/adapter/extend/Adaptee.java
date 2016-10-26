package headfirst.design.adapter.extend;

/**
 * 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
 */
public class Adaptee {
	public void specialMethod() {
		System.err.println("这是一个经过适配的特殊的方法");
	}
}
