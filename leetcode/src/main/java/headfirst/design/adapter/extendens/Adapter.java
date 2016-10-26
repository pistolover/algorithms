package headfirst.design.adapter.extendens;

public class Adapter extends Adaptee implements Itarger {

    @Override
    public void method1() {
        this.method2();
    }

}
