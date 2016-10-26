package test;

public class T11 extends T12{
    public int cut = 10;
    
    public T11(){
        super();
        System.err.println(cut);
        cut=20;
    }
    
    public static void main(String[] args) {
        System.err.println((new T11()).cut);
    }

    static{
        System.err.println("static method");
    }
    
    
    {
        System.err.println("nonononon");
    }
}

class T12{
    public T12() {
       System.err.println(((T11)this).cut);
    }
}

//父类的静态方法
//父类静态初始化块
//子类静态初始化块
//父类非静态初始化块
//父类的构造方法
//子类非静态初始化块
//子类的构造方法







