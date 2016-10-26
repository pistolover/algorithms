package nio.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class TestIO {

    @Test
    public void t1() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("/Users/liqqc/Desktop/test.txt"));
        String temp = "";
        while ((temp = reader.readLine()) != null) {
            System.err.println(temp);
        }
    }

    @Test
    public void t2() throws IOException {
        BufferedReader r1 = new BufferedReader(
                new InputStreamReader(new FileInputStream("/Users/liqqc/Desktop/test.txt")));
        String temp = "";
        while ((temp = r1.readLine()) != null) {
            System.err.println(temp);
        }
    }

    @Test
    public void t3() throws FileNotFoundException {
        FileReader reader = new FileReader("/Users/liqqc/Desktop/test.txt");
    }

    @Test
    public void t4() throws IOException {
        FileInputStream stream = new FileInputStream(new File("/Users/liqqc/Desktop/test.txt"));
        FileInputStream stream1 = new FileInputStream(new File("/Users/liqqc/Desktop/test1.txt"));
        Long t1 = System.currentTimeMillis();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = stream.read(buffer)) != -1) {
        }
        System.err.println("IO: " + (System.currentTimeMillis() - t1));
        byte[] buffer1 = new byte[1024];
        int len1 = 0;
        while ((len1 = stream1.read(buffer1)) != -1) {
        }
        
        System.err.println("IO: " + (System.currentTimeMillis() - t1));

        Long t2 = System.currentTimeMillis();
        FileChannel fc = stream.getChannel();
        ByteBuffer bytebuf = ByteBuffer.allocate(1024);
        while(fc.read(bytebuf) != -1) {
            bytebuf.clear();
        }
        
        System.err.println("NIO:" + (System.currentTimeMillis() - t2));
        
        FileChannel fc1 = stream1.getChannel();
        ByteBuffer bytebuf1 = ByteBuffer.allocate(1024);
        while(fc1.read(bytebuf1) != -1) {
            bytebuf1.clear();
        }
        
        System.err.println("NIO:" + (System.currentTimeMillis() - t2));
        stream.close();
    }

    @Test
    public void testNIO() throws IOException {
        FileInputStream fis = new FileInputStream(new File("/Users/liqqc/Desktop/test.txt"));
        FileChannel fc = fis.getChannel();
        // 生成一个偏移量为0,容量和最大容量都为1024的ByteBuffer
        ByteBuffer bb = ByteBuffer.allocate(1024);
        // fc向buffer中读入数据
        while (fc.read(bb) != -1) {
            fc.read(bb);
        }
        fis.close();
    }
}
