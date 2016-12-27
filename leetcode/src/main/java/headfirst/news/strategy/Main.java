package headfirst.news.strategy;

/**
 * 测试
 * @author liqqc
 *
 */
public class Main {

    
    public static void main(String[] args) {
        final Double price = 1000.0;
        
        //high vip 
        IStrategy highVipUser = new HighVipUser();
        Context context1 = new Context(highVipUser);
        Double discount1 = context1.getQuota(price);
        System.err.println("you need pay : " + discount1 + "RMB");
        
        System.err.println();
        
        //vip 
        IStrategy vipUser = new VipUser();
        Context context2 = new Context(vipUser);
        Double discount2 = context2.getQuota(price);
        System.err.println("you need pay : " + discount2 + "RMB");
        
        System.err.println();
        
        
        //general 
        IStrategy generalUser = new GeneralUser();
        Context context3 = new Context(generalUser);
        Double discount3 = context3.getQuota(price);
        System.err.println("you need pay : " + discount3 + "RMB");
      
        
    }

}
