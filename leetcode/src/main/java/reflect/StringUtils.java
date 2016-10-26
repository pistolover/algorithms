package reflect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StringUtils {

    public static void main(String[] args) throws IOException {

        FileReader freader = new FileReader("/Users/liqqc/Desktop/nv.txt");
        BufferedReader breader = new BufferedReader(freader);
        String temp = "";
        StringBuffer buffer = new StringBuffer();
        while ((temp = breader.readLine()) != null) {
            if(temp!=null && !temp.equals("")) {
                buffer.append(temp);
            }else{
                System.err.println(buffer.toString());
                buffer = new StringBuffer(); 
            }
        }
    }
}
