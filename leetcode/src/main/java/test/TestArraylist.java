package test;

import java.util.ArrayList;
import java.util.List;

public class TestArraylist {
	static int i = 0;

	public static void main(String[] args) {
		ArrayList<List<Integer>> list = new ArrayList<>();
		for (int j = 0; j < 90; j++) {
			List<Integer> son = new ArrayList<>();
			list.add(son);
			add(son);
		}
		
		Long st = System.currentTimeMillis();
		for (List<Integer> son : list) {
			for (Integer value : son) {
				if(value.toString().startsWith("99")) {
					System.err.println(value);
					break;
				}
			}
		}
		
		System.err.println(System.currentTimeMillis() -st +"ms");
	}

	private static void add(List<Integer> son) {
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		
		
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
		son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);son.add(i++);
	}
}
