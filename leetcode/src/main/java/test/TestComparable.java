package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestComparable {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        List<Compare01> numbers = new ArrayList<>();
        TestComparable newInstance = TestComparable.class.newInstance();
        for (int i = 0; i < 100; i++) {
            Compare01 compare01 = newInstance.new Compare01();
            compare01.setSum(new Random().nextInt(100));
            numbers.add(compare01);
        }
        Collections.sort(numbers);
        System.err.println(numbers);
    }

    public class Compare01 implements Comparable<Compare01> {

        private Integer sum;

        @Override
        public int compareTo(Compare01 o) {
            if (sum > o.getSum())
                return 1;
            else if (sum < o.getSum())
                return -1;
            return 0;
        }

        public Integer getSum() {
            return sum;
        }

        public void setSum(Integer sum) {
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Compare01 [sum=" + sum + "]";
        }

    }

}
