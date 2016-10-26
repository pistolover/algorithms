package test;

import java.text.DecimalFormat;

public class T13 {

    public static void main(String[] args) {
        // Long a = null;
        // if (a > 10) {
        // System.err.println(1);
        // }

        Long a = 1578567l;
        float v = (float) a / 1000;
        System.err.println(v);
        
        DecimalFormat df = new DecimalFormat("0.0");
        System.err.println(df.format(v));

        
    }
    

}
