package headfirst.news.facade;

public class Facade {

    public void test() {
        ModuleA moduleA = new ModuleA();
        moduleA.testA();
        ModuleB moduleB = new ModuleB();
        moduleB.testB();
        ModuleC moduleC = new ModuleC();
        moduleC.testC();
    }
}
