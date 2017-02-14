package test.concurrent;

import java.util.HashMap;
import java.util.Map;

import com.google.common.util.concurrent.ListeningExecutorService;

public class Main {

	public static void main(String[] args) {
		
		ListeningExecutorService commomExecutorService = ThreadPoolFactory.getCommomExecutorService(2, 2);
		BatchExec.batchExec(null, new Tk<>(), commomExecutorService);
	}
	
	
	static class Tk<T, V> implements BatchFuture<T, V>{
		@Override
		public V callback(T param) throws Exception {
			return null;
		}
		
	}
}
