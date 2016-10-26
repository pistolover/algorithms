package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Given a non-empty array of integers, return the k most frequent elements.
//
//For example,
//Given [1,1,1,2,2,3] and k = 2, return [1,2].

public class Top_K_Frequent_Elements {
//	public int[] countBits(int num) {
//        if(num<0) return null;
//        int[] arr = new int[num+1];
//        for (int k = 0; k <= num; k++) {
//			int i = 0;
//			int j = k;
//			for (i = 0; j > 0; i++) {
//				j = j & (j - 1);
//			}
//			arr[k]=i;
//		}
//		return arr;
//    }
	
	public static void main(String[] args) {
		int[] a = new int[]{1,1,1,2,2,3};
		topKFrequent(a,2);
	}
	
	public static List<Integer> topKFrequent(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return new ArrayList<Integer>();
		
		
		List<Integer> rt = new ArrayList<>();
		int[] tac = new int[99999];
		for (int i : nums) {
			if(tac[i] == 0) {
				tac[i] =1;
			}else{
				tac[i] = tac[i] + 1;
			}
		}
		
		return rt;
	}
}
