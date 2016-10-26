package leetcode;

import java.util.Arrays;

/**
 * 从t中找出多余一个字符串
 * @author liqqc
 */
public class Find_the_Difference {

    public static void main(String[] args) {
        System.err.println(findTheDifference("abcd", "abd"));
    }

    public static char findTheDifference(String s, String t) {
        if (s == null || t == null)
            return ' ';
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        int len = c1.length > c2.length ? c2.length : c1.length;
        for (int i = 0; i < len; i++) {
            if (c1[i] == c2[i]) {
                continue;
            } else {
                if (c1.length > c2.length) {
                    return c1[i];
                } else {
                    return c2[i];
                }
            }
        }

        return c1.length > c2.length ? c1[c1.length - 1] : c2[c2.length - 1];
    }
    
    
    public static char findTheDifference1(String s, String t) {
        if (s == null || t == null)
            return ' ';
        int len1 = t.length();
        
        return 0;
    }
}




