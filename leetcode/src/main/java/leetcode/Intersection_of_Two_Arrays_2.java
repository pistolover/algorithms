package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * Note:
 * Each element in the result should appear as many times as it shows in both
 * arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your
 * algorithm?
 * What if nums1's size is small compared to num2's size? Which algorithm is
 * better?
 * What if elements of nums2 are stored on disk, and the memory is limited such
 * that you cannot load all elements into the memory at once?
 * Subscribe to see which companies asked this question
 * @author liqqc
 */
public class Intersection_of_Two_Arrays_2 {

    public static void main(String[] args) {
        int[] intersect = intersect(new int[]{1},new int[]{1,2});
        System.err.println(intersect);
        
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 ==null || nums1.length==0 || nums2.length==0) return new int[]{};

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums1) {
            if(map.get(i)==null) {
                map.put(i, 1);
            }else{
                map.put(i,map.get(i)+1);
            }
        }

        List<Integer> rt = new ArrayList<>();
        for (int i : nums2) {
            if(map.get(i) != null &&  map.get(i) !=0) {
                rt.add(i);
                map.put(i, map.get(i)-1);
            }
        }
        int[] arr = new int[rt.size()];
        for (int i = 0; i < rt.size(); i++) {
            arr[i] = rt.get(i);
        }
        return arr;
    }
}
