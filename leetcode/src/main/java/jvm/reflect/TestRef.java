package jvm.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TestRef {
    List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        TestRef ref = new TestRef();
        ref.get();
    }

    void get() {
        Field[] declaredFields = TestRef.class.getDeclaredFields();
        for (Field field : declaredFields) {
            String name = field.getName();
            try {
                Class<?> type = field.getType();
                List value = (List) field.get(this);
                value.add("h");
                value.add("e");
                value.add("l");
                value.add("l");
                value.add("o");
                for (Object object : list) {
                    System.err.print(object);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

}
