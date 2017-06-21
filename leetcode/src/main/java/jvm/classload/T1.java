package jvm.classload;

import java.util.Random;

public class T1 {
	public static void main(String ags[]) {
		System.err.println(new Random().nextInt(100));
		
		System.err.println(new Son().a);
	}

	public static class ConstantClass {
		public static int a = 100;

		public ConstantClass() {
			System.err.println("father cons");
		}

		static {
			System.err.println(" father static...");
		}

		{
			System.err.println("father default...");
		}
	}

	public static class Son extends ConstantClass {
		public Son() {
			System.err.println("son cons");
		}

		static {
			System.err.println("son static...");
		}

		{
			System.err.println("son default...");
		}
	}
}
