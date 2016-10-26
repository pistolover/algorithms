package autoApi.java.xserver.autoapi.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.ConfigConstant;
import autoApi.java.xserver.autoapi.constant.FileConstant;

/**
 * 文件处理工具类
 * @author liqqc
 *
 */
public class FileUtil {

	public FileUtil(){
		super();
	}

	private final static Logger log = LoggerFactory.getLogger(FileUtil.class);
	public static Map<String, String> urlMap = new HashMap<String, String>();

	static {
		urlMap = getUrls();
	}

	public static Map<String, String> getUrlMap(){
		return urlMap;
	}

	/**
	 * 获取实例中到URL地址
	 * @return
	 */
	public static Map<String, String> getUrls() {
		Map<String, String> urlMap = new HashMap<String, String>();
		String path = System.getProperty("conf.dir");
		try {
			FileReader freader = new FileReader(path + "/urls.txt");
			BufferedReader breader = new BufferedReader(freader);
			String temp = "";
			while ((temp = breader.readLine()) != null) {
				if (StringUtils.isNotBlank(temp)) {
					String u = temp.trim();
					if (u.indexOf(".") != -1) {
						if(u.startsWith("/mobile")){
							String key = u.replace("/mobile", "").trim();
							urlMap.put(key.substring(0, key.indexOf(".")), u);
						}else{
							urlMap.put(u.substring(0, u.indexOf(".")), u);
						}
					}
				}
			}
			breader.close();
		} catch (Exception e) {
			log.error("fill shell error");
		}
		return urlMap;
	}



	/**
	 * 拷贝css js font 到指定文件夹
	 */
	public static void copyCss2Dir() {
        File catalog = new File(ConfigConstant.rootpath);
        if(!catalog.exists()) {
        	catalog.mkdir();
        }

//      String filePath = FileUtil.class.getClassLoader().getResource(".").getPath();
//      String filePath = System.getProperty("user.dir");
        String filePath = System.getProperty("conf.dir");
        log.info(filePath);

        if(filePath != null) {
        	File resources = new File(filePath);
        	if(resources.isDirectory()) {
        		for(File f :resources.listFiles()) {
        			if(f.isDirectory()) {
        				if(FileConstant.contains(f.getName())) {
        					log.info("copy: " + f.getName());
							copyDirectory(f, catalog);
						}
        			}
        		}
        	}
        }
        log.info(filePath);
	}

	/**
	 * 拷贝文件到指定位置
	 * @param f
	 * @param catalog
	 */
	private static void copyDirectory(File f, File catalog) {
		if(f != null) {
			try {
				File dir = new File(catalog.getAbsolutePath() + "/" + f.getName());
				org.apache.commons.io.FileUtils.copyDirectory(f, dir);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}

	/**
	 * 获取过滤
	 * @param path
	 * @return
	 */
	public static String getFilterPath(String path) {
		String filter = null;
		if (path.lastIndexOf("/") > 0) {
			int ls = path.lastIndexOf("/");
			String skipLast = path.substring(0, ls);
			if (skipLast.lastIndexOf("/") > 0) {
				int fs = skipLast.lastIndexOf("/");
				filter = path.substring(fs, path.length());
			} else {
				log.error("get path error: " + path);
			}
		} else {
			log.error("get path error: " + path);
		}
		return filter;
	}

	/**
	 * 加载properties
	 * @return
	 */
	public static ArrayList<String> loadProperty(String dir, String ppath) {
		String path = System.getProperty(dir);
		Properties tmp = new Properties();
		InputStream inputStream = null;
		ArrayList<String> paths = new ArrayList<String>(3);
		try {
			inputStream = new FileInputStream(path + "/" + ppath);
			tmp.load(inputStream);
			Enumeration<?> names = tmp.propertyNames();
			while (names.hasMoreElements()) {
				String key = (String) names.nextElement();
				String value = tmp.getProperty(key);
				paths.add(value + "," + key);
			}
		} catch (Exception e) {
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
		return paths;
	}

}
