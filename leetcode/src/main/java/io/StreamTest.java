package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.google.common.primitives.Bytes;

public class StreamTest {

    @Test
    public void inpuStreamTest() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("/Users/liqqc/Desktop/test.txt");
            fileOutputStream = new FileOutputStream("/Users/liqqc/Desktop/test2.txt");
            byte[] bytes = new byte[1024];
            while (fileInputStream.read(bytes) != -1) {
                fileOutputStream.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void byteArrayInputStreamTest() {
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream outputStream = null;
        
        byte[] bytes = new byte[1024];
        try {
            outputStream = new ByteArrayOutputStream();
            fileInputStream = new FileInputStream("/Users/liqqc/Desktop/test.txt");
            while (fileInputStream.read(bytes) != -1) {
                outputStream.write(bytes);
            }
            System.err.println(outputStream.size() + " " + outputStream.toString());
        } catch (Exception e) {

        } finally {
            try {
                outputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    @Test
    public void byteArrayOutputStreamt() {
        ByteArrayInputStream byteArrayInputStream = null;
        FileOutputStream fileOutputStream = null;
        
       String aString = "abcdefghijklmnopqrstuvwxyz";
       byte[] bytes = new byte[1024];
        try {
            byteArrayInputStream = new ByteArrayInputStream(aString.getBytes());
            fileOutputStream = new FileOutputStream("/Users/liqqc/Desktop/test02.txt");
            while (byteArrayInputStream.read(bytes)!=-1) {
                fileOutputStream.write(bytes);
            }
            System.err.println();
        } catch (Exception e) {

        } finally {
            try {
                byteArrayInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
