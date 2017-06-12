package jvm.reflect;

import java.lang.reflect.Method;

import jvm.reflect.TestRef2.Ref001;
import jvm.reflect.TestRef2.Ref002;

public class Test101 {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("jvm.reflect.TestRef2");
        // 通过方法名获取方法
        Method method1 = c.getDeclaredMethod("print");
        // 调用外部类方法
        method1.invoke(c.newInstance());

        Class<?> forName = Class.forName("jvm.reflect.TestRef2$Ref001");
        Method method = forName.getDeclaredMethod("print");
        method.invoke(forName.getDeclaredConstructors()[0].newInstance(c.newInstance()));
        
        
        Class<?> forName2 = Class.forName("jvm.reflect.TestRef2$Ref002");
        Method method2 = forName2.getDeclaredMethod("print");
        method2.invoke(forName2.newInstance());
        
        
        
        TestRef2 newInstance = TestRef2.class.newInstance();
        newInstance.print();
        
        Ref001 ref001 = newInstance.new Ref001();
        ref001.print();
        
        Ref002 ref002 = new Ref002();
        ref002.print();
    }
}
