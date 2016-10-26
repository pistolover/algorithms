package reflect;

import org.junit.Test;

public class TestStringBuffer {

    
    @Test
    public void t1() {
        StringBuffer sb= new StringBuffer();
        sb.append(1);
        sb.append(0x80000000);
        String a = null;
        sb.append(a);
        
        System.err.println(sb.toString());
    }
}
