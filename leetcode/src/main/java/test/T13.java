package test;

import java.text.DecimalFormat;

public class T13 {

    public static void main(String[] args) {
        // Long a = null;
        // if (a > 10) {
        // System.err.println(1);
        // }
//
//        Long a = 1578567l;
//        float v = (float) a / 1000;
//        System.err.println(v);
//        
//        DecimalFormat df = new DecimalFormat("0.0");
//        System.err.println(df.format(v));
//
//        Integer i =1;
//       System.err.println(null == i);
        
        String streamUrl = "https://live.g3proxy.lecloud.com/gslb?stream_id=lb_test_forPublicFree1_350&tag=live&ext=m3u8&sign=live_tv";
        int start = streamUrl.indexOf(":");
        String aString1 = streamUrl.substring(start);
        String aString = "https" + aString1;
        System.err.println(aString);
    }
    

}
