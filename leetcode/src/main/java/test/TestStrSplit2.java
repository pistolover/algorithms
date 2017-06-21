package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestStrSplit2 {

	public TestStrSplit2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		TestStrSplit2 testStrSplit2 = new TestStrSplit2();
		String str = "zs,22,ls,13,ws,88,z54";
		String[] split = str.split(",");
		List<SortTest> list = new ArrayList();
		for (int i = 0; i < split.length - 2; i = i + 2) {
			list.add(testStrSplit2.new SortTest(split[i + 1], split[i]));
		}
		Collections.sort(list);
		System.out.println(list);
	}

	public class SortTest implements Comparable<SortTest> {
		private Integer age;
		private String name;

		public SortTest() {
		}

		public SortTest(String string, String string2) {
			age = Integer.parseInt(string);
			name = string2;
		}

		@Override
		public int compareTo(SortTest o) {
			if (age > o.getAge())
				return 1;
			else if (age ==  o.getAge())
				return 0;
			return -1;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
