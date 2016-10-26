package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Anagrams {
	public List<String> anagrams(String[] strs) {
		if (strs == null || strs.length < 2)
			return new ArrayList<String>();
		Map<String, List<String>> maps = new HashMap<String, List<String>>();
		for (String str : strs) {
			char[] arr = str.toCharArray();
			Arrays.sort(arr);
			String key = new String(arr);
			if (!maps.containsKey(key)) {
				maps.put(key, new ArrayList<String>());
			}
			List<String> list = maps.get(key);
			list.add(new String(str));
		}

		List<String> result = new ArrayList<String>();
		for (Iterator<String> iterator = maps.keySet().iterator(); iterator
				.hasNext();) {
			String key = iterator.next();
			if (maps.get(key).size() > 1) {
				result.addAll(maps.get(key));
			}
		}
		return result;
	}
}
