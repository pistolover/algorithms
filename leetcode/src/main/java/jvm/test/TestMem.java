package jvm.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMem {

    static class Mem {
        private static final List<Object> objs = new ArrayList<>(10000);
        private Map<String, String> map = new HashMap<>(2);
        public Mem() {
        }
    }

    public static void main(String[] args) {
//        long size = 300000000;
//        List<Mem> objects = new ArrayList<>();
//        while (size-- > 0) {
//            Mem mem = new Mem();
//            objects.add(mem);
//        }
        
        String str="dfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfdsfsdfasfsd";
        for(long i=0;i<1000000000000L;i++){
            str=str+str+i;
        }
        
        
//        cycle(1, 2, 3);
    }
    
    
    public static void cycle(int a, int b, int c){
        cycle(a++, b++, c++);
    }
    
    
    public static void metedateOver(){
         Map<String, Object> classLeakingMap = new HashMap<String, Object>();
        try {
            for (int i = 0; i < 50000; i++) {

               String fictiousClassloaderJAR = "file:" + i + ".jar";

               URL[] fictiousClassloaderURL = new URL[] { new URL(fictiousClassloaderJAR) };

               // Create a new classloader instance
               URLClassLoader newClassLoader = new URLClassLoader(fictiousClassloaderURL);
               
               // Create a new Proxy instance
               Object t = Proxy.newProxyInstance(newClassLoader,
                     new Class<?>[] { Object.class },new InvocationHandler() {
                        
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (Object.class == method.getDeclaringClass()) {
                                String name = method.getName();
                                if ("equals".equals(name)) {
                                    return proxy == args[0];
                                } else if ("hashCode".equals(name)) {
                                    return System.identityHashCode(proxy);
                                } else if ("toString".equals(name)) {
                                    return proxy.getClass().getName() + "@" +
                                            Integer.toHexString(System.identityHashCode(proxy)) +
                                            ", with InvocationHandler " + this;
                                } else {
                                    throw new IllegalStateException(String.valueOf(method));
                                }
                            }

                            return method.invoke(Object.class, args);
                        }
                    });
               
               // Add the new Proxy instance to the leaking HashMap
               classLeakingMap.put(fictiousClassloaderJAR, t);
            }
         } 
         catch (Throwable any) {
            System.out.println("ERROR: " + any);
         }

         System.out.println("Done!");
    }
}
