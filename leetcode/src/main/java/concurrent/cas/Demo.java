package concurrent.cas;

public class Demo {

    public static void main(String[] args) {
        CASUtils<PersonThread> person = new CASUtils<>();
        PersonThread cas = person.cas(new AbstractCAS<PersonThread>() {
            @Override
            boolean isDone(PersonThread t) {
                if (t != null) {
                    return true;
                }
                return false;
            }

            @Override
            PersonThread doSth() {
                PersonThread p = new PersonThread();
                p.run();
                if(p.getPage() != null && p.getPname() != null) {
                    System.out.println("p: " + p);
                    return p;
                }
                System.out.println("p: " + null);
                return null;
            }
        }, 5, 10000);

        
        System.err.println("cas: " + cas);
    }

}
