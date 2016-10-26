package reflect;

import java.util.Random;

import org.junit.Test;

public class TestObj {

    public static void main(String[] args) {
        int[] a = new int[] { 1, 2, 33 };
        int[] b = a;
        a = null;
        System.err.println(b);

    }

    @Test
    public void getRandom() {
        for (int i = 0; i < 111; i++) {
            System.err.println("" + Math.abs(new Random().nextInt()));
        }
    }
}
