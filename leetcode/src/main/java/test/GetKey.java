package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GetKey {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            FileReader reader = new FileReader("/Users/liqqc/Desktop/427.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);  
            FileWriter writer = new FileWriter("/Users/liqqc/Desktop/4277.txt");
            String s = null;
            while ((s= bufferedReader.readLine()) != null) {  
                writer.write("delete SuperLiveChannelId_base_" + s);
                System.err.println("delete SuperLiveChannelId_base_"+s);
            }  
            reader.close();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        
    }
    
}
