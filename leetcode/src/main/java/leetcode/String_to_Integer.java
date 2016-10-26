package leetcode;

public class String_to_Integer {
	 public int atoi(String str) {       
			if(str.length()==0) return 0;
			long sum = 0;
			str = str.trim();
			int len = str.length();
			boolean flag = false;
			boolean negative = false;
			for (int i = 0; i < len; i++) {
				char c = str.charAt(i);
				if(!Character.isDigit(c)){
					if(flag == true)
					{
						if(sum>Integer.MAX_VALUE ){
							return Integer.MAX_VALUE;
						}else if(sum<Integer.MIN_VALUE){
							return Integer.MIN_VALUE;
						}else{
							return (int)sum;
						}
					}
					continue;
				}
				
				
				if(Character.isDigit(c)){
					flag = true;
					if(i-2>=0 && (str.charAt(i-1)=='+' 
						|| str.charAt(i-1)=='-' || str.charAt(i-1)==' ') 
						&& (str.charAt(i-2)=='+' || str.charAt(i-2)=='-'
						|| str.charAt(i-2)==' ')){
						return 0;
					}else if(i-1>=0 && str.charAt(i-1)=='+'){
						sum = sum * 10 + Integer.parseInt(String.valueOf(c));
					}else if(i-1>=0 && str.charAt(i-1) == '-'){
						negative = true;
						sum = -sum * 10 - Integer.parseInt(String.valueOf(c));
					}else if(i-1>=0 && (str.charAt(i-1)!='+' || str.charAt(i-1) != '-') && 
						!Character.isDigit(str.charAt(i-1))){
						return 0;
					}
					else{
						if(sum<0 || negative == true){
							if(sum<Integer.MIN_VALUE){
								return Integer.MIN_VALUE;
							}
							sum = sum * 10 - Integer.parseInt(String.valueOf(c));
						}else{
							if(sum>Integer.MAX_VALUE){
								return Integer.MAX_VALUE;
							}
							sum = sum * 10 + Integer.parseInt(String.valueOf(c));
						}
						
					}
				}
			}
			
			if(sum>Integer.MAX_VALUE ){
				return Integer.MAX_VALUE;
			}else if(sum<Integer.MIN_VALUE){
				return Integer.MIN_VALUE;
			}else{
				return (int)sum;
			}
	    }
}
