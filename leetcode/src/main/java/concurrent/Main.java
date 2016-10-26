package concurrent;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        
        DecimalFormat df = new DecimalFormat("0%");  
        //可以设置精确几位小数  
        df.setMaximumFractionDigits(4);  
        //模式 例如四舍五入  
        df.setRoundingMode(RoundingMode.HALF_UP);
        
        int[] a = {
                140517,
                329545,
                25691,
                17349,
                5031,
                9369,
                394,
                384,
                314,
                225,
                96};
        
        int sum = 530157;
        System.err.println();
        for (int i : a) {
            System.err.println(df.format((double)((i * 1.0) /(sum * 1.0))));
        }
        
    }
    
    
}
