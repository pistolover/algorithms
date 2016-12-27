package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/*Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
*/
public class Third_Maximum_Number {

    public static void main(String[] args) {
        System.err.println(thirdMax(new int[]{3,2,1}));
    }

    public static int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums != null && nums.length == 1)
            return nums[0];

        // 可以用堆排序实现
        Arrays.sort(nums);
        //1 1 1 2 3 4 
        
        
        
        
        
        
        return 0;

    }

}
