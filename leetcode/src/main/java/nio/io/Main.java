package nio.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			FileInputStream inputStream = new FileInputStream(
					"H:\\EAST20161231\\B0107H211000001-ZZHJQKMB-20161231.txt");
			reader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));

			FileOutputStream writerStream = new FileOutputStream("H:\\test1.txt");
			writer = new BufferedWriter(new OutputStreamWriter(writerStream, "gbk"));
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				String[] split = temp.split(",");
				if (split != null) {
					if ("B0107S231000006".equalsIgnoreCase(split[split.length - 2])) {
					}else{
						writer.write(temp);
					}
					writer.newLine();
					writer.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
