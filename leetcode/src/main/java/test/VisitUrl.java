package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class VisitUrl {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 1000; i++) {
		getURLByPost("http://blog.csdn.net/pistolove/article/details/53389646", null);
		}
		
	}
	
	public static String getURLByPost(String urlStr,String params)throws Exception{  
	       URL url=new URL(urlStr);  
	       HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
	       conn.setRequestMethod("POST");  
	       BufferedReader in = null;   
	       StringBuilder sb = new StringBuilder();   
	       try{     
	           in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8") );   
	           String str = null;    
	           while((str = in.readLine()) != null) {    
	               sb.append( str );   
	               System.err.println(str);
	           }     
	        } catch (Exception ex) {   
	           throw ex;   
	        } finally{    
	         try{   
	             conn.disconnect();  
	             if(in!=null){  
	                 in.close();  
	             }  
	         }catch(IOException ex) {     
	             throw ex;   
	         }     
	        }     
	        return sb.toString();   
	   }  
}
