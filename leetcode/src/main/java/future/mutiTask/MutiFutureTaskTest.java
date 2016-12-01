package future.mutiTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MutiFutureTaskTest {
    private static final Map<Integer,Person> persons = new HashMap<Integer,Person>();
    static{
        persons.put(1, new Person(1, "test1", 1, "aaa", 8888888888l));
        persons.put(2, new Person(2, "test2", 2, "bbb", 8888888888l));
        persons.put(3, new Person(3, "test3", 3, "ccc", 8888888888l));
        persons.put(4, new Person(4, "test4", 4, "ddd", 8888888888l));
        persons.put(5, new Person(5, "test5", 5, "eee", 8888888888l));
    }

    @Test
    public  void doRun() {
        List<Integer> param = new ArrayList<Integer>();
        param.add(1);
        param.add(2);
        param.add(3);
        param.add(4);
        param.add(5);
        List<Person> result = MutiFutureTaskTemplate.batchExec(param, new BatchFuture<Integer, Person>() {
            @Override
            public Person callback(Integer param) {
                return persons.get(param);
            }
        });
        System.out.println(result.size());
    }
}
