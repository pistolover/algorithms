package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class T15 {

    public static void main(String[] args) throws UnsupportedEncodingException {

//        String s = "I am a good~.!@#$%^&*)('\"=_`:;?~|+-\\//[]{}<>“”·‘’¡￥¿€£¢©®™℃℉°§№†‡‥…‰※‾｛｝<>";
//
        String encode = URLEncoder.encode("I am     # ", "utf-8");
        System.err.println(encode);
        
//        String replaceAll = encode.replaceAll("+", " ");
//
//        System.err.println(URLEncoder.encode("I am a good~.!@#$%^&*)('\"=_`:;?~|+-\\//[]{}<>“”·‘’¡￥¿€£¢©®™℃℉°§№†‡‥…‰※‾｛｝<>", "utf-8"));
//        System.err.println(URLEncoder.encode(s, "utf-8"));
//        System.err.println(URLEncoder.encode(s, "utf-8"));
//        System.err.println(URLEncoder.encode(s, "utf-8"));
//        System.err.println(URLEncoder.encode(s, "utf-8"));
//        System.err.println(URLEncoder.encode(s, "utf-8"));
//        
        
        
//        
        System.err.println();
        String decode = URLDecoder.decode("%2520%2520%2520%2520%27%22%3D_%60%3A%3B%3F%21%40%23%24%25%5E%26*%28%291234567890%C3%A2%C2%82%C2%AC%C3%82%C2%A3%C3%82%C2%A2%C3%82%C2%A9%C3%82%C2%AE%C3%A2%C2%84%C2%A2%C3%A2%C2%84%C2%83%C3%A2%C2%84%C2%89", "utf-8");
        System.err.println(decode);

    }
}
