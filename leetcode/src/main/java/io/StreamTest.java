package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

public class StreamTest {

    @Test
    public void inputStreamTest() {
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
    
    @Test
    public void testFilterInputStream(){
        FilterInputStream byteArrayInputStream = null;
        FileOutputStream fileOutputStream = null;
        
       String aString = "abcdefghijklmnopqrstuvwxyz";
       byte[] bytes = new byte[1024];
        try {
            byteArrayInputStream = new BufferedInputStream(new ByteArrayInputStream(aString.getBytes()));
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
    
    @Test
    public void testBufferedInputStream(){
        ByteArrayInputStream byteArrayInputStream = null;
        OutputStream fileOutputStream = null;
        
       String aString = "abcdefghijklmnopqrstuvwxyz";
       byte[] bytes = new byte[1024];
        try {
            byteArrayInputStream = new ByteArrayInputStream(aString.getBytes());
            fileOutputStream = new BufferedOutputStream(new FileOutputStream("/Users/liqqc/Desktop/test02.txt"));
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
    
    
    @Test
    public void testBufferedOutputStream(){
        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
       String aString = "abcdefghijklmnopqrstuvwxyz";
       byte[] bytes = new byte[1024];
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream("/Users/liqqc/Desktop/test02.txt"));
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (bufferedInputStream.read(bytes)!=-1) {
                byteArrayOutputStream.write(bytes);
                System.err.println(new String(bytes));
            }
            System.err.println();
        } catch (Exception e) {

        } finally {
            try {
                bufferedInputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
