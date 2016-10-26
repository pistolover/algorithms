package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DKRJ {

	
	public static void main(String[] args) {
		List<Object[]> obj = new ArrayList<Object[]>();
		
		obj.add(new Object[]{1});
		obj.add(new Object[]{2});
		obj.add(new Object[]{3});

		
		List<List<Object>> combineAlg = combineAlg(obj);
		
		System.err.println(combineAlg);
	}
	
	
    private static  List<List<Object>> combineAlg(List<Object[]> nArray) {
		List<List<Object>> values = new LinkedList<List<Object>>();
		int[] x = new int[nArray.size()];
		for (int i = 0; i < x.length; i++) {
			x[i] = 0;
		}

		int flag = 0;
		do {
			/**一种组合形式**/
			List<Object> objs = new LinkedList<Object>();
			for (int looper = 0; looper < nArray.size(); looper++) {
				objs.add(nArray.get(looper)[x[looper]]);
			}
			flag = NextPermutation(x, nArray);
			values.add(objs);
		} while (flag != 1);
		/**所有组合形式**/
		return values;
	}

	private  static int NextPermutation(int[] x, List<Object[]> nArray) {
		int carry = 0;
		for (int looper = nArray.size() - 1; looper >= 0; looper--) {
			if (x[looper] + 1 == nArray.get(looper).length) {
				carry = 1;
				x[looper] = 0;
			} else {
				x[looper] = x[looper] + 1;
				carry = 0;
				return 0;
			}
		}

		if (carry == 1)
			return 1;
		else
			return 0;
	}
	
	
}
