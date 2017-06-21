package test;

public class T22 {

	public static int a = 1;

	public class Nest {
		void print() {
			System.out.println(a);
		}
	}

	public static class Nestatic {
		void print() {
			System.out.println(a);
		}
	}
}
