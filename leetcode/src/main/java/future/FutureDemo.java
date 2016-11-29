package future;

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
    //线程池中线程个数
	private static final int PoolSize = 50;
	//带有回调机制的线程池
	private static final ListeningExecutorService service = MoreExecutors
			.listeningDecorator(Executors.newFixedThreadPool(PoolSize));

	//模拟从数据库查询数据的操作，key为查询条件，value为查询结果
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
		//增加查询条件
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		ids.add(4);
		ids.add(5);
		
		//查询并得到结果
		List<Person> batchExec = futureDemo.batchExec(ids);
		
		System.err.println(batchExec);
	}

	/**
	 * 
	 * @param params 查询集合
	 * @return
	 * @throws Exception
	 */
	public <T> List<V> batchExec(List<T> params) throws Exception {
	    long start = System.currentTimeMillis();
	    
		if (CollectionUtils.isEmpty(params)) {
			return null;
		}

		final List<V> value = new ArrayList<V>();
		try {
			List<ListenableFuture<V>> futures = new ArrayList<ListenableFuture<V>>();
			for (T t : params) {
			    //将实现了callable的任务放入到线程池中，得到一个带有回调机制的ListenableFuture实例，
			    //通过Futures.addCallback方法对得到的ListenableFuture实例进行监听，一旦得到结果就进入到onSuccess方法中，
			    //在onSuccess方法中将查询的结果存入到集合中
				ListenableFuture<V> sfuture = service.submit(new SingleTask<T>(t));
				Futures.addCallback(sfuture, new FutureCallback<V>() {
					public void onSuccess(V result) {
						value.add(result);
					}

					public void onFailure(Throwable t) {
						throw new RuntimeException(t);
					}
				});
				//将每一次查询得到的ListenableFuture放入到集合中
				futures.add(sfuture);
			}
			
			//这里将集合中的若干ListenableFuture形成一个新的ListenableFuture
			//目的是为了异步阻塞，直到所有的ListenableFuture都得到结果才继续当前线程
			//这里的时间取的是所有任务中用时最长的一个
			ListenableFuture<List<V>> allAsList = Futures.allAsList(futures);
			allAsList.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.err.println("总的执行时间： " + (System.currentTimeMillis() - start));
		return value;

	}

	/**
	 * 业务实现类
	 * @author liqqc
	 *
	 * @param <T>
	 */
	class SingleTask<T> implements batchFuture<T, V> {
		private T t;

		public SingleTask(T t) throws Exception {
			this.t = t;
		}

		public V call() throws Exception {
		    //具体的查询操作，需要自己实现
		    int millis = (int) (Math.random() * 1000);
		    Thread.sleep(millis);
		    System.err.println("执行时的时间：" + System.currentTimeMillis() + " 随机sleep的时间：" + millis);
			return (V) persons.get(t);
		}

	}

	//必须继承Callable
	//可以增加业务需要的接口
	interface batchFuture<T, V> extends Callable<V> {
	}

	//数据库中的实体bean
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
