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

/**
 * 基于ListenableFuture异步执行多任务模板
 * 注意：需要引入guava包
 * @author liqqc
 * @param <V>
 */
public class FutureDemo<T,V> {
    // 线程池中线程个数
    private static final int PoolSize = 50;
    // 带有回调机制的线程池
    private static final ListeningExecutorService service = MoreExecutors
            .listeningDecorator(Executors.newFixedThreadPool(PoolSize));

    // 模拟从数据库查询数据的操作，key为查询条件，value为查询结果
    private static final Map<Integer, Person> persons = new HashMap<Integer, Person>();
    static {
        persons.put(1, new Person(1, "test1", 1, "aaa", 8888888888l));
        persons.put(2, new Person(2, "test2", 2, "bbb", 8888888888l));
        persons.put(3, new Person(3, "test3", 3, "ccc", 8888888888l));
        persons.put(4, new Person(4, "test4", 4, "ddd", 8888888888l));
        persons.put(5, new Person(5, "test5", 5, "eee", 8888888888l));
    }

    public static <T,V> void main(String[] args) throws Exception {
		FutureDemo<Integer,V> futureDemo = new FutureDemo<Integer,V>();
		//增加查询条件
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		ids.add(4);
		ids.add(5);
		
		//batchExecByParams
		TaskTemplate<Integer,V> task = new PersonTask<Integer,V>();
		List<Person> batchExec = (List<Person>) futureDemo.batchExecByParams(ids, task);
		System.err.println("batchExecByParams: "+ batchExec);
		
		
		System.err.println();
		
		
		//batchExecByTaskList
		Callable<V> task1= new PersonTask<Integer,V>(1);
		Callable<V>  task2 = new PersonTask<Integer,V>(1);
		Callable<V>  task3 = new PersonTask<Integer,V>(1);
		Callable<V>  task4 = new PersonTask<Integer,V>(1);
		Callable<V>  task5 = new PersonTask<Integer,V>(1);
		List<Callable<V>> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);
		tasks.add(task3);
		tasks.add(task4);
		tasks.add(task5);
		List<V> batchExecByTaskList = futureDemo.batchExecByTaskList(tasks);
		System.err.println("batchExecByTaskList: "+ batchExecByTaskList);
		
	}

    /**
     * 依据params和模板方法实现
     * @param params
     *            查询集合
     * @return
     * @throws Exception
     */
    public <T> List<V> batchExecByParams(List<T> params, TaskTemplate<T, V> task) throws Exception {
        long start = System.currentTimeMillis();

        if (CollectionUtils.isEmpty(params)) {
            return null;
        }

        final List<V> value = new ArrayList<V>();
        try {
            List<ListenableFuture<V>> futures = new ArrayList<ListenableFuture<V>>();
            for (T t : params) {
                // 将实现了callable的任务放入到线程池中，得到一个带有回调机制的ListenableFuture实例，
                // 通过Futures.addCallback方法对得到的ListenableFuture实例进行监听，一旦得到结果就进入到onSuccess方法中，
                // 在onSuccess方法中将查询的结果存入到集合中
                task.setValue(t);
                ListenableFuture<V> sfuture = service.submit(task);
                Futures.addCallback(sfuture, new FutureCallback<V>() {
                    public void onSuccess(V result) {
                        value.add(result);
                    }

                    public void onFailure(Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
                // 将每一次查询得到的ListenableFuture放入到集合中
                futures.add(sfuture);
            }

            // 这里将集合中的若干ListenableFuture形成一个新的ListenableFuture
            // 目的是为了异步阻塞，直到所有的ListenableFuture都得到结果才继续当前线程
            // 这里的时间取的是所有任务中用时最长的一个
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
     * 批量查询任务结果
     * @param params
     *            任务集合
     * @return
     * @throws Exception
     */
    public List<V> batchExecByTaskList(List<Callable<V>> tasks) throws Exception {
        long start = System.currentTimeMillis();

        if (CollectionUtils.isEmpty(tasks)) {
            return null;
        }

        final List<V> value = new ArrayList<V>();
        try {
            List<ListenableFuture<V>> futures = new ArrayList<ListenableFuture<V>>();
            for (Callable<V> task : tasks) {
                // 将实现了callable的任务放入到线程池中，得到一个带有回调机制的ListenableFuture实例，
                // 通过Futures.addCallback方法对得到的ListenableFuture实例进行监听，一旦得到结果就进入到onSuccess方法中，
                // 在onSuccess方法中将查询的结果存入到集合中
                ListenableFuture<V> sfuture = service.submit(task);
                Futures.addCallback(sfuture, new FutureCallback<V>() {
                    public void onSuccess(V result) {
                        value.add(result);
                    }

                    public void onFailure(Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
                // 将每一次查询得到的ListenableFuture放入到集合中
                futures.add(sfuture);
            }

            // 这里将集合中的若干ListenableFuture形成一个新的ListenableFuture
            // 目的是为了异步阻塞，直到所有的ListenableFuture都得到结果才继续当前线程
            // 这里的时间取的是所有任务中用时最长的一个
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
     * 具体业务实现类
     * @author liqqc
     * @param <T>
     */
    static class PersonTask<T, V> extends TaskTemplate<T, V> {
        public PersonTask() throws Exception{}
        public PersonTask(T t) throws Exception {
            super(t);
        }

        @Override
        public V call() throws Exception {
            int millis = (int) (Math.random() * 1000);
            Thread.sleep(millis);
            System.err.println("执行时的时间：" + System.currentTimeMillis() + " 随机sleep的时间：" + millis);
            return (V) persons.get(t);
        }

        @Override
        T getType() {
            // TODO Auto-generated method stub
            return null;
        }

    }

    /**
     * 抽象业务实现类
     * @author liqqc
     * @param <T>
     */
    abstract static class TaskTemplate<T, V> implements batchFuture<T, V> {
        // 可以增加其他抽象方法
        protected T t;

        public TaskTemplate() throws Exception {
        }

        public TaskTemplate(T t) throws Exception {
            this.t = t;
        }

        private void setValue(T t2) {
            this.t = t2;
        }

        abstract T getType();
    }

    // 必须继承Callable
    // 可以增加业务需要的接口
    interface batchFuture<T, V> extends Callable<V> {
    }

    // 数据库中的实体bean
    static class Person {
        private int id;
        private String name;
        private int age;
        private String address;
        private long telephone;

        public Person() {
        }

        public Person(int id, String name, int age, String address, long tel) {
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
