package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
 * You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.*/

// 1 至少含有两个字符串之间不存在相同的字符
//2 不存在包含关系的字符串中取长度较大的字符串
//

public class Maximum_Product_of_Word_Lengths {

    public static void main(String[] args) {

        System.err.println(maxProduct(new String[] { "abcw", "baz", "foo", "bar", "xtfn", "abcdef" }));

    }

    public static int maxProduct(String[] words) {
        int len = words.length;
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int tmp = 0;
                if (check(words[i], words[j])) {
                    tmp = words[i].length() * words[j].length();
                }
                if (tmp >= max) {
                    max = tmp;
                }
            }
        }

        return max;
    }

    private static boolean check(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 过滤重复的字符

    public static int maxProduct2(String[] words) {
        int len = words.length;
        int max = 0;
        Set<Character> set = null;
        List<String> rt = new ArrayList<String>();
        for (int i = 0; i < len; i++) {
            set = new HashSet<Character>();
            String r = "";
            for (int j = 0; j < words[i].length(); j++) {
                if (!set.contains(words[i].charAt(j))) {
                    r = r + words[i].charAt(j);
                }
            }
            rt.add(r);
        }

        for (int i = 0; i < rt.size(); i++) {
            for (int j = i + 1; j < rt.size(); j++) {
                int tmp = 0;
                if (check(rt.get(i), rt.get(j))) {
                    tmp = rt.get(i).length() * rt.get(i).length();
                }
                if (tmp >= max) {
                    max = tmp;
                }
            }
        }

        return max;
    }
}
