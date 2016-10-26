package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Ransom_Note {

    /**
     * magazine可以由ransomNote组成
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine == null)
            return false;
        if (ransomNote == null)
            return true;

        Map<Character, Integer> st = new HashMap<Character, Integer>();

        for (Character character : magazine.toCharArray()) {
            if (st.get(character) == null) {
                st.put(character, 1);
            } else {
                st.put(character, st.get(character) + 1);
            }
        }

        for (Character character : ransomNote.toCharArray()) {
            if (st.get(character) != null  && st.get(character)>0) {
                st.put(character, st.get(character) - 1);
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
}
