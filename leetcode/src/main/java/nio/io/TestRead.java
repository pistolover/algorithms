package nio.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TestRead {

	public static void main(String ags[]) throws IOException {
		readFile("H:\\EAST20161231");
	}

	public static void readFile(String path) throws IOException {
		File file = new File(path);
		Set<String> sets = new HashSet<>();
        FileWriter writer = new FileWriter("H:\\EAST20161231\2017-7-18.txt");
		if (file != null) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File file2 : files) {
					if (file2 != null && file2.getName().equals("B0107H211000001-ZZHJQKMB-20161231.txt")) {
						BufferedReader reader = new BufferedReader(new FileReader(file2.getAbsolutePath()));
						try {
							String temp = "";
							while ((temp = reader.readLine()) != null) {
								String[] split = temp.split(",");
								if (split != null) {
									sets.add(split[split.length - 2]);
									if("B0107S231000006".equalsIgnoreCase(split[split.length - 2])){
										System.err.println(temp);
									}
								}
							}
							writer.flush();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							reader.close();
							writer.close();
						}
					}
				}
			}
		}
		
		System.err.println(sets);
	}
}
