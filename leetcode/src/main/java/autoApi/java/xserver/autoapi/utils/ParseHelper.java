package autoApi.java.xserver.autoapi.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.CachedConstant;

public class ParseHelper {
    private final static Logger log = LoggerFactory.getLogger(ParseHelper.class);

	public static File getFile(String filePath){
		File f = new File(filePath);///Users/liqqc/Desktop/mobile_apidocs/_advertisement_data_response.html
		//清空文件内容
		if (f.exists()) {
			if (f.length() != 0) {
				f.delete();
				f = new File(filePath);
				try {
					f.createNewFile();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
		return f;
	}
	
	
	/**
	 * 拆分字符串
	 * @param response
	 * @return
	 */
	public static List<String> splitStr(String response) {
		List<String> evey = new LinkedList<String>();
		if(StringUtils.isNotBlank(response)) {
			StringBuffer buffer = new StringBuffer(10);
			for (int i = 0; i < response.length(); i++) {
				if(response.charAt(i) == '>' || response.charAt(i)== '<' || response.charAt(i) == ',') {
					evey.add(buffer.toString());
					evey.add(String.valueOf(response.charAt(i)));
					buffer = new StringBuffer();
				} else {
					buffer.append(response.charAt(i));
					if(i == response.length() -1) {
						evey.add(buffer.toString());
					}
				}
			}
		}
		return evey;
	}
	
	
	/**
	 * 获取数组
	 * @param str
	 * @return
	 */
	public static String[] getArrs(String str) {
		String[] arrs;
		if (str.contains("Map")) {
			int s0 = str.lastIndexOf(">");
			arrs = new String[2];
			arrs[0] = str.substring(0,s0 + 1);
			arrs[1] = str.substring(s0 + 1, str.length());
		} else {
			arrs = str.trim().split(" ");
		}
		return arrs;
	}
	
	
	
	/**
	 * 字段本身
	 * @param bufferWritter
	 * @param str
	 * @param arrs
	 * @throws IOException
	 */
	public static void paramSelf(BufferedWriter bufferWritter, String str, String[] arrs) throws IOException {
		if (arrs.length >= 2) {
			bufferWritter.write("<td>");
			if(str.contains("=")) {
				int s0 = str.indexOf("=");
				String nm = str.substring(s0 + 1, str.length() - 1);
				bufferWritter.write(arrs[1].trim().replace(";", ""));
				bufferWritter.write("=");
				bufferWritter.write(nm);
				bufferWritter.write("</td>");
			} else {
				bufferWritter.write(arrs[1].trim().replace(";", ""));
				bufferWritter.write("</td>");
			}
		}
	}
	
	/**
	 * 字段说明
	 * @param paraMap
	 * @param bufferWritter
	 * @param str
	 * @throws IOException
	 */
	public static void paramMean(Map<String, String> paraMap, BufferedWriter bufferWritter, String str)throws IOException {
		bufferWritter.write("<td>");
		bufferWritter.write(paraMap.get(str).replace("<", CachedConstant.fis).replace(">", CachedConstant.end));
		bufferWritter.write("</td>");
	}

	
	/**
	 * 字段修饰
	 * @param bufferWritter
	 * @param poll
	 * @param clazzs
	 * @param arrs
	 * @param son2Father 
	 * @param dtoHtml 
	 * @throws IOException
	 */
	public static void paramDecorate(BufferedWriter bufferWritter, String poll, Queue<String> clazzs, String[] arrs, 
			Map<String, String> son2Father, String dtoHtml) throws IOException {
		String ch = arrs[0];
		List<String> evey = ParseHelper.splitStr(ch);
		bufferWritter.write("<td>");
		for (String ev : evey) {
			//是一个类
			//<a href="_advertisement_data_response.html#mobile_apidocs/AdOutputDto">AdOutputDto</a>
			if(checkParamIsClazz(clazzs, poll, ev,son2Father)) {
				String urlByNames = "<a href=\"" + dtoHtml + "#" + ev + "\">" + ev + "</a>";
				bufferWritter.write(urlByNames);
			} else {
				bufferWritter.write(ev.replace("<", CachedConstant.fis).replace(">", CachedConstant.end));
			}
		}
		bufferWritter.write("</td>");
	}
	
	/**
	 * 判断参数是否为类
	 * @param clazzs
	 * @param poll
	 * @param parmClazz
	 */
	private static Boolean checkParamIsClazz(Queue<String> clazzs, String poll, String parmClazz, Map<String, String> son2Father) {
		if(CachedConstant.dtoname2params.get(parmClazz) != null){
			clazzs.add(parmClazz);
			return true;
		}else {
			Set<String> keySet = CachedConstant.dto2ChildsPrams.keySet();
			for (String string : keySet) {
				Map<String, Map<String, String>> map = CachedConstant.dto2ChildsPrams.get(string);
				Set<String> keySet2 = map.keySet();
				for (String string2 : keySet2) {
					if(string2.equals(parmClazz)){
						clazzs.add(parmClazz);
						son2Father.put(parmClazz, poll);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 类信息说明
	 * @param bufferWritter
	 * @param clazzs
	 * @return
	 * @throws IOException
	 */
	public static String getClassname2Mean(BufferedWriter bufferWritter, Queue<String> clazzs) throws IOException {
		bufferWritter.write("<div id=" + clazzs.peek() + ">");
		bufferWritter.write("<h4>" + clazzs.peek() +"：");

		String clazzMean = CachedConstant.dtoname2means.get(clazzs.peek());
		if (clazzMean != null && !"".equals(clazzMean.trim())) {
			bufferWritter.write(clazzMean.replace("<", CachedConstant.fis).replace(">",CachedConstant.end));
		}

		bufferWritter.write("</h4></div>");

		return clazzMean;
	}
	
	
	/**
	 * 判断类是否为一个“子类”
	 * @param poll
	 * @param other
	 * @param son2Father 
	 * @return
	 */
	public static Map<String, String> getAndPutChild(String name, Map<String, String> other, Map<String, String> son2Father) {
		Map<String, String> child = null;
		if(other == null && son2Father.get(name) !=null) {
			if(CachedConstant.dto2ChildsPrams.get(son2Father.get(name)) != null){
				child = CachedConstant.dto2ChildsPrams.get(son2Father.get(name)).get(name);
			}

			if(child == null){
				Set<String> keySet = CachedConstant.dto2ChildsPrams.keySet();
				for (String string : keySet) {
					Map<String, Map<String, String>> map = CachedConstant.dto2ChildsPrams.get(string);
					if(map.get(name) != null){
						child = map.get(name);
						break;
					}
				}
			}

			son2Father.remove(name);
		}
		return child;
	}
}
