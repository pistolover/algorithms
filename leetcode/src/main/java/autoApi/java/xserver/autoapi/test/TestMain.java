package autoApi.java.xserver.autoapi.test;

import org.junit.Test;

public class TestMain {


	@Test
	public void testFiter() {
		String path = "/letv/jenkins_home/jobs/api-branch-git/workspace/xserver/xserver-api-mobile";
		
		int ls = path.lastIndexOf("/");
		String skipLast = path.substring(0, ls);
		int fs = skipLast.lastIndexOf("/");
		String filter = path.substring(fs, path.length());
		
		System.err.println(filter);
	}
	
}
