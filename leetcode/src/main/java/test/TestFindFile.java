package test;

import java.io.File;
import java.io.IOException;

public class TestFindFile {

    static Integer num = 100000;

    public static void main(String[] args) throws IOException {
        String root = "/Users/liqqc/test/";
        Integer data = 20160101;

        StringBuffer buff;
        for (int i = 0; i < 100; i++) {
            buff = new StringBuffer(root);
            buff.append(data++);
            File f = new File(buff.toString());
            if (!f.exists()) {
                f.mkdirs();
                System.err.println(f.getAbsolutePath());
            } else {
                buff.append("/");
                for (int j = 0; j < 5000; j++) {
                    StringBuffer b = new StringBuffer();
                    b.append(buff.toString() + (num++) + ".xml");
                    File file = new File(b.toString());
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                }
            }
        }
    }
}
