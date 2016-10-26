package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Bulls_and_Cows {

	public static void main(String[] args) {
		System.err.println(getHint("1122", "1222"));
	}

	public static String getHint(String secret, String guess) {
		char[] se = secret.toCharArray();
		char[] gu = guess.toCharArray();

		int len = se.length;
		int bull = 0;
		int cow = 0;

		Map<Character, Integer> seHas = new HashMap<Character, Integer>();
		for (int i = 0; i < len; i++) {
			if (!seHas.containsKey(se[i])) {
				seHas.put(se[i], 1);
			} else {
				seHas.put(se[i], seHas.get(se[i]) + 1);
			}
		}

		Boolean[] b = new Boolean[len];
		for (int i = 0; i < len; i++) {
			if (se[i] == gu[i]) {
				b[i]  = true;
				seHas.put(gu[i], seHas.get(gu[i])-1);
				cow++;
			}else {
				b[i] = false;
			}
		}

		for (int j = 0; j < len; j++) {
			if(b[j] == false && seHas.get(gu[j])!=null && seHas.get(gu[j])>0) {
				bull ++;
				seHas.put(gu[j], seHas.get(gu[j])-1);
			}
		}
		
		return cow + "A" + bull + "B";
	}
}
