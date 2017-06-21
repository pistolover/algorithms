package test;

import test.T22.Nest;
import test.T22.Nestatic;

public class T23 {

	public static void main(String ags[]) {
		T22 t22 = new T22();
		Nest nest = t22.new Nest();
		nest.print();
		Nestatic nestatic = new Nestatic();
		nestatic.print();
	}
}
