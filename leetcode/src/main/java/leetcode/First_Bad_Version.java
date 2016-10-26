package leetcode;

public class First_Bad_Version {
	
	// 找出第一个坏的
    public int firstBadVersion(int n) {
        
    	int start = 0;
    	int end = n;
    	
    	while(start <=end) {
    		int mid = start +(end -start) >> 1;
    	
    	    // 是坏的，则从当前往后找
    		if(isBadVersion(mid)){
    			end = mid;
    			
    			
    		}else {
    			start = mid + 1;
    			
    		}
    	}
    	
    	return end;
    	
    }
    
    static boolean isBadVersion(int version){
    	return true;
    }
}
