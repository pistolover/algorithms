package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestComparator {

    public static void main(String[] args) {
        List<Compare001> lists = new ArrayList<>();
        lists.add(new Compare001(10003));
        lists.add(new Compare001(1));
        lists.add(new Compare001(345));
        lists.add(new Compare001(23));
        lists.add(new Compare001(889));

        Collections.sort(lists, new Comparator<Compare001>() {
            @Override
            public int compare(Compare001 o1, Compare001 o2) {
                if (o1.getValue() > o2.getValue())
                    return 1;
                else if (o1.getValue() < o2.getValue())
                    return -1;
                return 0;
            }
        });
        System.err.println(lists);
    }

    static class Compare001 {

        private int value;

        public Compare001(int value) {
            super();
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Compare001 [value=" + value + "]";
        }
    }

}
