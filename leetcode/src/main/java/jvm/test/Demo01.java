package jvm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Demo01 {
    static Map map =new HashMap<>();
    public static void main(String[] args) {
        Long sum = 0l;
        for(long i = 0; i< 10000000000l; i++) {
            sum+=i;
            map.put(i, new ArrayList<>(100));
        }
        System.err.println(sum);
    }
}
