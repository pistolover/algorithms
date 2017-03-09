package nio.io;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IoTest {

    public static void test() throws Exception {
        FileInputStream fileInput = new FileInputStream("/Users/liqqc/Desktop/t1.txt");
        FileOutputStream fileOutput = new FileOutputStream("/Users/liqqc/Desktop/t2.txt");
        try {
            int a = 0;
            byte[] data = new byte[1024];
            long start = System.currentTimeMillis();
            while ((a=fileInput.read(data)) != -1) {
                fileOutput.write(data, 0, a);
            }
            fileOutput.flush();
            long end = System.currentTimeMillis();
            System.out.println("file花费 :" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInput.close();
            } catch (IOException e) {
            }
            try {
                fileOutput.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 20; i++) {
            test();
        }
    }
}

