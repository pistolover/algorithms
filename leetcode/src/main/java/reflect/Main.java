package reflect;

import java.io.File;
import java.lang.reflect.Field;

public class Main {

	public static void main(String[] args) {
		String root = "/Users/liqqc/git/mobile/xserver/xserver-lib/src/main/java/xserver/lib/tp";
		File rfile = new File(root);
		if (rfile.exists()) {
			getConFiles(rfile);
		}
	}

	private static void getConFiles(File dir) {
		if (dir != null) {
			File[] listFiles = dir.listFiles();
			for (File file : listFiles) {
				if (file.isFile() && file.getAbsolutePath().endsWith("Request.java")) {
					getPrams(file);
				} else if (file.isDirectory()) {
					getConFiles(file);
				}
			}
		}
	}

	private static void getPrams(File file) {
		String path = file.getAbsolutePath();
		try {
			Class<?> clazz = Class.forName(path.replace(".java", "").replace("/", ".").substring(1));
			Object obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				fields[j].setAccessible(true);
				// 字段名
				System.out.print(fields[j].getName() + ":");
				// 字段值
				System.out.print(fields[j].get(obj) + "     ");
			}

		} catch (ClassNotFoundException | InstantiationException |

		IllegalAccessException e)

		{
			e.printStackTrace();
		}
	}

}
