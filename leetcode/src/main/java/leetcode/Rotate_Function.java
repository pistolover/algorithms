package leetcode;

import java.util.LinkedList;

public class Rotate_Function {

    public static void main(String[] args) {
        maxRotateFunction(new int[] { -2147483648, -2147483648 });
    }

    public static int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0)
            return 0;

        int max = Integer.MIN_VALUE;
        int length = A.length;
        if (A != null && length != 0) {
            LinkedList<Integer> list = new LinkedList<>();
            for (Integer v : A) {
                list.add(v);
            }
            int max3 = getMax(list,length);
            max = max > max3 ? max : max3;

            for (int i = 1; i < length; i++) {
                Integer removeFirst = list.removeFirst();
                list.add(removeFirst);
                int max2 = getMax(list,length);
                max = max > max2 ? max : max2;
            }
        }
        return max;

    }

    private static int getMax(LinkedList<Integer> list, int length) {
        int max = 0;
        for (int i = 1; i < length; i++) {
            max = max + list.get(i) * i;
        }
        return max;
    }
}
