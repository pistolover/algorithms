package headfirst.design.facade;

public class Facade {

    public void test(){
        testA();
        testB();
        testC();
        testD();
    }
    
    public void testA() {
        SubjectA subjectA = new SubjectA();
        subjectA.getUp();
    }

    public void testB() {
        SubjectB subjectB = new SubjectB();
        subjectB.washing();
    }

    public void testC() {
        SubjectC subjectC = new SubjectC();
        subjectC.eating();
    }

    public void testD() {
        SubjectD subjectD = new SubjectD();
        subjectD.working();
    }

}
