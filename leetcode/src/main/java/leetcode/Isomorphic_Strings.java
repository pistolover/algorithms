package leetcode;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Isomorphic_Strings {

	public static void main(String[] args) {
		System.err.println(isIsomorphic("pipipper", "fgfgffhv"));
	}

	public static  boolean isIsomorphic(String s, String t) {

		char[] ch1 = s.toCharArray();
		char[] ch2 = t.toCharArray();
		int len = ch1.length;
		
		Map<Character, StringBuffer> _map1 = new LinkedHashMap<Character, StringBuffer>();
		Map<Character, StringBuffer> _map2 = new LinkedHashMap<Character, StringBuffer>();
		
		for (int i = 0; i < len; i++) {
			if(_map1.get(ch1[i])==null){
				_map1.put(ch1[i], new StringBuffer());
				_map1.get(ch1[i]).append(i);
			}else{
				_map1.get(ch1[i]).append(i);
			}
			
			if(_map2.get(ch2[i])==null){
				_map2.put(ch2[i], new StringBuffer());
				_map2.get(ch2[i]).append(i);
			}else{
				_map2.get(ch2[i]).append(i);
			}
		}
		
		if(_map1.values().size()!=_map2.values().size()){
			return false;
		}
		
		StringBuffer b1 = new StringBuffer();
		StringBuffer b2 = new StringBuffer();
		for (Iterator<StringBuffer> iterator1 = _map1.values().iterator(),iterator2 = _map2.values().iterator(); 
				iterator1.hasNext()&&iterator2.hasNext();) {
			b1.append(iterator1.next());
			b2.append(iterator2.next());
		}
		
		return b1.toString().equals(b2.toString());
	}
}
