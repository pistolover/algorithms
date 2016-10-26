package test;

import java.io.File;

public class TestFindContent {

    public static void main(String[] args) {
        String root = "/Users/liqqc/test/";

        Long st = System.currentTimeMillis();
        File f = new File(root);
        if (f.exists()) {
            if (f.isDirectory()) {
                File[] listFiles = f.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File file : listFiles) {
                        if (file != null) {
                            if (file.isDirectory()) {
                                File[] listFiles2 = file.listFiles();
                                for (File file2 : listFiles2) {
                                    if (file2.isFile()) {
                                        if (file2.getName().contains("219870")) {
                                            System.err.println(file2.getAbsolutePath());
                                            System.err.println("time: " + (System.currentTimeMillis() - st));
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
