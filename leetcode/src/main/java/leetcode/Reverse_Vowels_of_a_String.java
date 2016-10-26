package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a function that takes a string as input and reverse only the vowels of
 * a string.
 * Example 1:
 * Given s = "hello", return "holle".
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 * Subscribe to see which companies asked this question
 * @author liqqc
 */
public class Reverse_Vowels_of_a_String {

    public static void main(String[] args) {
        String reverseVowels = reverseVowels("leetcode");
        System.err.println(reverseVowels);
    }

    public static String reverseVowels(String s) {
        if (s == null || s.length() == 0)
            return "";
        int len = s.length();
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('o');
        set.add('e');
        set.add('i');
        set.add('u');
        set.add('A');
        set.add('O');
        set.add('E');
        set.add('I');
        set.add('U');

        int i = 0;// 第一个
        int j = len - 1;// 第二个
        char[] arr = s.toCharArray();
        while (i < j) {
            if (!set.contains(arr[i])) {
                i++;
                continue;
            }
            if (!set.contains(arr[j])) {
                j--;
                continue;
            }
            // 交换
            if (set.contains(arr[j]) && set.contains(arr[i])) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        return new String(arr);
    }
}
