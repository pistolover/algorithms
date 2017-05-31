package concurrent;

public class TheadLocalDemo {
	public static void main(String[] args) {
		ThreadLocal local = new ThreadLocal();
		local.set("aaaaaaa");
		Object object = local.get();
		System.err.println(object);
		
	}
}
