package others;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorMoney {

    public static void main(String[] args) {
        int[] blue = new int[]{1,2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
            31,32,33};
        int[] red = new int[]{1,2,3,4,5,6,7,8,9,10,11,
                12,13,14,15,16};
        
        List<Integer> rt = new ArrayList<Integer>();
        Random random = new Random();
        for(int i = 0; i< 6; i++) {
            int b = blue[random.nextInt(33)];
            if(rt.contains(b)){
                i--;
            }else{
                rt.add(b);
            }
        }
        System.err.print(rt.toString());
        System.err.print("  ");
        int r = red[random.nextInt(16)];
        System.err.print(r);
    }
}
