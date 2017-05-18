package concurrent;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakMap2 {

    private static Map<Integer, String> map = new WeakHashMap<>();

    public static void main(String[] args) {
        Integer s1 = new Integer(888);
        Integer s2 = new Integer(999);
        map.put(s1, "hahahah");
        map.put(s2, "bababab");
        s1 = null;
        System.gc();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.err.println(map.get(888));
        System.err.println(map.get(999));

    }
}
