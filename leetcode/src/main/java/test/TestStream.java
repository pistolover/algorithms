package test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TestStream {

    public static void main(String[] args) {
        testStream();
    }

    private static void testStream() {
        try {
            InputStream inputStream = new FileInputStream("/Users/liqqc/Desktop/test.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[]  bytes = new byte[1024];
            try {
                while(bufferedInputStream.read(bytes)!=-1){
                    System.err.println(new String(bytes));
                   
                }
                System.err.println(bufferedInputStream.available());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        
        
        
    }
    
}
