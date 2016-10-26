package leetcode;

public class F {

	public static void main(String[] args) {
		for (int i = 47; i < 50; i++) {
			System.err.println(i+"===="+addDigits(i));
		}
	}
	
    public static  int addDigits(int num) {
        if(num < 10) return num;
        
        int rt = 0;
        int x = num;
        
        while(x !=0 ){
            rt += x % 10;
            x = x /10;
        }
 
        if(rt >= 10) {
            return addDigits(rt);
        }else {
            return rt;
        }
    }
}
