package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import org.springframework.util.CollectionUtils;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class FutureDemo<V> {
	private static final int PoolSize = 50;
	private static final ListeningExecutorService service = MoreExecutors
			.listeningDecorator(Executors.newFixedThreadPool(PoolSize));

	private static final Map<Integer,Person> persons = new HashMap<Integer,Person>();
	
	static{
		persons.put(1, new Person(1, "test1", 1, "aaa", 8888888888l));
		persons.put(2, new Person(2, "test2", 2, "bbb", 8888888888l));
		persons.put(3, new Person(3, "test3", 3, "ccc", 8888888888l));
		persons.put(4, new Person(4, "test4", 4, "ddd", 8888888888l));
		persons.put(5, new Person(5, "test5", 5, "eee", 8888888888l));
	}
	
	
	public static void main(String[] args) throws Exception {
		FutureDemo<Person> futureDemo = new FutureDemo<Person>();
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		ids.add(4);
		ids.add(5);
		
		List<Person> batchExec = futureDemo.batchExec(ids);
		
		System.err.println(batchExec);
	}

	public <T> List<V> batchExec(List<T> params) throws Exception {
		if (CollectionUtils.isEmpty(params)) {
			return null;
		}

		final List<V> value = new ArrayList<V>();
		try {
			List<ListenableFuture<V>> futures = new ArrayList<ListenableFuture<V>>();
			for (T t : params) {
				ListenableFuture<V> sfuture = service.submit(new SingleTask<T>(t));
				Futures.addCallback(sfuture, new FutureCallback<V>() {
					public void onSuccess(V result) {
						value.add(result);
					}
					public void onFailure(Throwable t) {
						throw new RuntimeException(t);
					}
				});
				futures.add(sfuture);
			}
			
			ListenableFuture<List<V>> allAsList = Futures.allAsList(futures);
			allAsList.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return value;

	}

	class SingleTask<T> implements batchFuture<T, V> {
		private T t;

		public SingleTask(T t) throws Exception {
			this.t = t;
			call();
		}

		public V call() throws Exception {
			return (V) persons.get(t);
		}

	}

	interface batchFuture<T, V> extends Callable<V> {
	}

	static class Person {
		private int id;
		private String name;
		private int age;
		private String address;
		private long telephone;

		
		public Person(){}
		
		public Person(int id, String name, int age, String address, long tel){
			this.id = id;
			this.name = name;
			this.age = age;
			this.address = address;
			this.telephone = tel;
		}

		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public long getTelephone() {
			return telephone;
		}

		public void setTelephone(long telephone) {
			this.telephone = telephone;
		}
	}

}
