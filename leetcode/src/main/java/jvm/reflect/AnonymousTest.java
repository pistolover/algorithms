package jvm.reflect;

public class AnonymousTest {

    private static final int a = 1232132;
    private static int b = 1232132;

    public static void main(String[] args) {

        String aString = "aaaeeea";
        Boy boy = new Boy() {
            @Override
            public void method() {
                System.err.println("aaaa");
                System.err.println(aString);
                System.err.println(a);
                System.err.println(b);
            }
        };
        boy.method();
    }

}
