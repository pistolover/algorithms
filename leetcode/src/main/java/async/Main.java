package async;

import java.util.ArrayList;
import java.util.List;

import async.BatchExec.BatchFuture;

public class Main {

	public static void main(String[] args) {
		List<Integer> param = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			param.add(i);
		}
		List<Integer> resultList = BatchExec.batchExec(param, new BatchFuture<Integer, Integer>() {
			@Override
			public Integer callback(Integer param) throws Exception {
				Thread.sleep(1);
				// System.out.println(param);
				return param++;
			}
		}, ThreadPoolUtil.getOcrService());
		System.out.println("num:" + BatchExec.num);
		System.out.println("sunum:" + BatchExec.suNum);
		System.out.println("fanum:" + BatchExec.faNum);
		System.out.println(resultList.size());
	}
}
