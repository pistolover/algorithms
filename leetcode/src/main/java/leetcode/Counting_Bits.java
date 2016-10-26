package leetcode;

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤
 * i ≤ num calculate the number of 1's in their binary representation and return
 * them as an array.
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 * Follow up:
 * It is very easy to come up with a solution with run time
 * O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a
 * single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like
 * __builtin_popcount in c++ or in any other language.
 * Show Hint
 */
public class Counting_Bits {
    
    public static void main(String[] args) {
        
        // 110 010
//        System.err.println(3 & 2);
        
        System.err.println(countBits(100000000).toString());
        
    }
    
    
    public static int[] countBits(int num) {
        int[] rt = new int[num];
        for (int i = 0; i < num; i++) {
            int count = 0;
            int j = i;
            while(j !=  0) {
                j = j & (j-1);
                count ++;
            }
            rt[i] = count;
        }
        return rt;
    }
}
