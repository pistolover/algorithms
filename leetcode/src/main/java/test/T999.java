package test;

public class T999 {
	public static void main(String ags[]) {

		Base base = new Son11();
		System.err.println(base.i);

	}

	static class Base {
		private int i = 22;

		public Base() {
			getValue();
			i = 222;
		}

		private void getValue() {
			System.err.println("Base: " + i);
		}
	}

	static class Son11 extends Base {
		private int i = 10;

		public Son11() {
			getValue();
			i = 100;
		}

		private void getValue() {
			System.err.println("Son11: " + i);
		}
	}
}
