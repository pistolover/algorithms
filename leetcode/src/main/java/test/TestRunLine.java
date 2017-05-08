package test;

public class TestRunLine {

	public static void main(String[] args) {
		new Son();
	}

	static class Father {
		
		public Father(String a) {
			System.out.println("Father cons parameter");
		}
		
		public Father() {
			System.out.println("Father cons ");
		}

		static {
			System.out.println("Father static ");
		}

		{
			System.out.println("Father default ");
		}
	}

	static class Son extends Father {
		public Son(String s ) {
			super(s);
			System.out.println("Son cons parameter");
		}
		public Son() {
			System.out.println("Son cons ");
			new Father("a");
		}

		static {
			System.out.println("Son static ");
		}

		{
			System.out.println("Son default ");
		}
	}

}
