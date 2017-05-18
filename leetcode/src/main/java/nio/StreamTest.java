package nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

public class StreamTest {

	/**
	 * 将文件作为流进行读写
	 */
	@Test
	public void fileStreamTest() {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\test1.txt");
			fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test2.txt");
			byte[] bytes = new byte[1024];
			while (fileInputStream.read(bytes) != -1) {
				fileOutputStream.write(bytes);
			}
			System.err.println("fileStreamTest: " + fileOutputStream.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 内存中缓冲区作为流进行读写
	 */
	@Test
	public void byteArrayStreamTest() {
		ByteArrayInputStream byteArrayInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;

		String testStr = "Test the byte array stream！";
		byte[] bytes = new byte[1024];
		try {
			byteArrayInputStream = new ByteArrayInputStream(testStr.getBytes());
			byteArrayOutputStream = new ByteArrayOutputStream();
			while (byteArrayInputStream.read(bytes) != -1) {
				byteArrayOutputStream.write(bytes);
			}
			System.err.println("byteArrayStreamTest: " + byteArrayOutputStream.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (byteArrayInputStream != null) {
					byteArrayInputStream.close();
				}
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 带有缓冲的流输入输出
	 * 
	 */
	@Test
	public void bufferStreamTest() {
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;

		byte[] bytes = new byte[1024];
		try {
			bufferedInputStream = new BufferedInputStream(
					new FileInputStream("C:\\Users\\Administrator\\Desktop\\test1.txt"));
			bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test2.txt"));
			while (bufferedInputStream.read(bytes) != -1) {
				bufferedOutputStream.write(bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
				if (bufferedOutputStream != null) {
					bufferedOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * 基于文件的字符流
	 */
	@Test
	public void fileReader2WriterTest() {
		FileReader fileReader = null;
		FileWriter fileWriter = null;
		try {
			fileReader = new FileReader("C:\\Users\\Administrator\\Desktop\\test1.txt");
			fileWriter = new FileWriter("C:\\Users\\Administrator\\Desktop\\test2.txt");
			char[] chars = new char[1024];
			while (fileReader.read(chars) != -1) {
				fileWriter.write(chars);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 字节流和字符流间的转换 InputStreamReader、OutputStreamWriter  
	 */
	@Test
	public void inputStreamReader2WriterTest() {
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			//多种实现
//			inputStreamReader = new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\test1.txt"));
//			inputStreamReader = new InputStreamReader(new BufferedInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\test1.txt")));
			inputStreamReader = new InputStreamReader(new ByteArrayInputStream(new String("I am just test inputStreamReader2WriterTest").getBytes()));
			
//			outputStreamWriter = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test2.txt")));
//			outputStreamWriter = new OutputStreamWriter(new ByteArrayOutputStream());
			outputStreamWriter = new OutputStreamWriter(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test2.txt"));
			char[] chars = new char[1024];
			while (inputStreamReader.read(chars) != -1) {
				outputStreamWriter.write(chars);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (outputStreamWriter != null) {
					outputStreamWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 对字符串进行读写的字符流
	 */
	@Test
	public void stringReader2WriterTest() {
		StringReader stringReader = null;
		StringWriter stringWriter = null;
		try {
			stringReader = new StringReader("I am just test stringReader2WriterTest");
			stringWriter = new StringWriter();
			char[] chars = new char[1024];
			while (stringReader.read(chars) != -1) {
				stringWriter.write(chars);
			}
			System.out.println(stringWriter.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stringReader != null) {
					stringReader.close();
				}
				if (stringWriter != null) {
					stringWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void t1() {
		ByteArrayInputStream byteArrayInputStream = null;
		FileOutputStream fileOutputStream = null;

		String aString = "abcdefghijklmnopqrstuvwxyz";
		byte[] bytes = new byte[1024];
		try {
			byteArrayInputStream = new ByteArrayInputStream(aString.getBytes());
			fileOutputStream = new FileOutputStream("/Users/liqqc/Desktop/test02.txt");
			while (byteArrayInputStream.read(bytes) != -1) {
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
