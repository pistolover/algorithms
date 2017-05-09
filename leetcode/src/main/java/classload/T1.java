package classload;

public class T1 {
	public static void main(String ags[]) {
		System.err.println(ConstantClass.a);
	}
}

class ConstantClass {
	public static int a = 100;

	static {
		System.err.println("static...");
	}

	{
		System.err.println("default...");
	}
}