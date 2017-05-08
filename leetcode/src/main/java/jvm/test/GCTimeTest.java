package jvm.test;

import java.util.HashMap;


/*使用参数-Xmx512M -Xms512M -XX:+UseParNewGC 运行代码，输出如下：

clean map 8565

cost time=1655

使用参数-Xmx512M -Xms512M -XX:+UseParallelOldGC –XX:ParallelGCThreads=8 运行代码，输出如下：

clean map 8798

cost time=1998

使用参数-Xmx512M -Xms512M -XX:+UseSerialGC 运行代码，输出如下：

clean map 8864

cost time=1717

使用参数-Xmx512M -Xms512M -XX:+UseConcMarkSweepGC 运行代码，输出如下：

clean map 8862

cost time=1530*/
public class GCTimeTest {
	static HashMap map = new HashMap();

	public static void main(String[] args) {
		long begintime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			if (map.size() * 512 / 1024 / 1024 >= 400) {
				map.clear();// 保护内存不溢出
				System.out.println("clean map");
			}
			byte[] b1;
			for (int j = 0; j < 100; j++) {
				b1 = new byte[512];
				map.put(System.nanoTime(), b1);// 不断消耗内存
			}
		}
		long endtime = System.currentTimeMillis();
		System.out.println(endtime - begintime);
	}
}