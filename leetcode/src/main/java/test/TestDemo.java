package test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class TestDemo {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			FileWriter writer = new FileWriter("H:\\EAST20161231\2017-7-18.txt");
			int s = 1;
			while (s++ < 100) {
				writer.write("delete SuperLiveChannelId_base_" + s);
				System.err.println("delete SuperLiveChannelId_base_" + s);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
