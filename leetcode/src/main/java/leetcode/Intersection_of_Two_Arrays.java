package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection. Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2]. Note: Each element in
 * the result must be unique. The result can be in any order.
 * 
 * @author liqqcd
 */
public class Intersection_of_Two_Arrays {

	public static void main(String[] args) {
		interserctions(new int[] { 1, 2, 2, 4 }, new int[] { 2, 2 });
	}

	public static int[] interserctions(int[] a1, int[] a2) {
		if (a1 == null || a2 == null || a1.length == 0 || a2.length == 0)
			return new int[] {};

		int[] r = new int[4];
		int m = 0;
		for (int i = 0; i < a1.length; i++) {
			for (int j = 0; j < a2.length; j++) {
				if (a1[i] == a2[j]) {
					r[m++] = a1[i];
					
					break;
				}
			}
		}
		return r;
	}

	public static int[] intersection(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
			return new int[] {};

		Set<Integer> set = new HashSet<Integer>();
		for (int i : nums1) {
			set.add(i);
		}

		Set<Integer> rt = new HashSet<>();
		for (int j : nums2) {
			if (set.contains(j)) {
				rt.add(j);
			}
		}

		int[] arr = new int[rt.size()];
		int index = 0;
		for (Integer v : rt) {
			arr[index++] = v;
		}
		return arr;

	}
}
