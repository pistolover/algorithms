package test;

public class MemcachedDeleted {
    
    public static void main(String[] args) throws InterruptedException {
        int max = 0;
        int mid = 0;
        int low = 0;
       for (int j = 0; j < 100; j++) {
        for (int i = 0; i < 400; i++) {
            double random = Math.random();
            if(random < 0.05){
                low++;
            }
            Thread.sleep(2);
        }
        System.err.println("low: " + low);
        low=0;
       }
    }
    
    
    public static void thrid(){
        int max = 0;
        int mid = 0;
        int low = 0;
        for (int i = 0; i < 1000; i++) {
            double random = Math.random();
            if(random >= 0.66){
                max++;
            }
            if(0.33<= random && random< 0.66){
                mid++;
            }
            if(random < 0.33){
                low++;
            }
        }
        System.err.println("max: " + max + "; mid: " + mid + "; low: " + low);
    }
}
