package leetcode;

//超时

/*Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3*/
public class Range_Sum_Query_Immutable {
    public class NumArray {

        private int[] arrs;

        public NumArray(int[] nums) {
            this.arrs = nums;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            if (arrs != null && arrs.length != 0) {
                int len = arrs.length;
                if (i >= 0 && j < len) {
                    for (int k = i; k <= j; k++) {
                        sum = sum + arrs[k];
                    }
                }
            }
            return sum;
        }
    }
}
