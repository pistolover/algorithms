package jvm.reflect;

public class TestRef2 {

    private static String abc = "abc";

    void print() {
        System.err.println(abc);
    }

    class Ref001 {
        void print() {
            System.err.println(abc);
        }
    }

    public static class Ref002 {
        void print() {
            System.err.println(abc);
        }
    }
}
