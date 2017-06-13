package concurrent;

public class DispatchTest {

    public static void main(String[] args) {
        Base b = new Sub();
        System.out.println(b.x);
    }
}

class Base {
    int x = 10;

    public Base() {
        System.err.println(this.getClass());
        this.printMessage();
        x = 20;
    }

    private void printMessage() {
        System.out.println("Base.x = " + x);
    }

}

class Sub extends Base {
    int x = 30;

    public Sub() {
        this.printMessage();
        x = 40;
    }

    private void printMessage() {
        System.out.println("Sub.x = " + x);
    }
}
