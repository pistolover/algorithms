package autoApi.java.xserver.autoapi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.constant.ConfigConstant;

/**
 * controller处理工具类
 * @author liqqc
 *
 */
public class ConUtil{
    private final static Logger log = LoggerFactory.getLogger(ConUtil.class);

    /**
     * 通过设备获取con
     * @param equipment
     * @throws IOException
     */
	public static void getConFiles(String equipment) throws IOException {
		File file = new File(ConfigConstant.path);
		getConFiles(file, equipment);
	}

	/**
	 * 获取con
	 * @param dir
	 * @param eqipment
	 */
	private static void getConFiles(File dir, String eqipment) {
		if (dir != null) {
			File[] listFiles = dir.listFiles();
			for (File file : listFiles) {
				if (file.isFile()) {
					if (file.getName().toLowerCase().endsWith(ConfigConstant.DOCJAVA)
							&& file.getName().contains(ConfigConstant.CON)) {
						getConFile(eqipment, file);
					}
				} else if (file.isDirectory()) {
					getConFiles(file, eqipment);
				}
			}
		}
	}

	/**
	 * 获取每一个Con
	 * @param eqipment
	 * @param file
	 */
	private static void getConFile(String eqipment, File file) {
		switch (eqipment) {
		case "mobile":
			if (file.getAbsolutePath().contains("/xserver/api/module")) {
				getConFiles(file);
			}
			break;

		case "tv":
			if (file.getAbsolutePath().contains("/letv/itv/v2/api/module")
					&& file.getAbsolutePath().contains("/tvserver-api")) {
				getConFiles(file);
			}
			break;

		case "live":
			if (file.getAbsolutePath().contains("/xserver/api/module")) {
				getConFiles(file);
			}
			break;
		default:
			log.info("conDefault: " + eqipment);
			if(file.getAbsolutePath().contains(eqipment + "/api/module")) {
				getConFiles(file);
			}
		}
	}

	private static void getConFiles(File file) {
		CachedConstant.controllerPaths.add(file.getAbsolutePath());
		parseConFiles(file);
	}

	/**
	 * con名称、路径存放
	 * @param file
	 */
	public static void parseConFiles(File file) {
		String path = file.getAbsolutePath();
		String fileName = path.substring(path.lastIndexOf("/") + 1, path.length() - 5);
		CachedConstant.detailsPath.put(fileName, path);
		CachedConstant.controllerNames.add(fileName);
		log.info("-------------------------------------------------");
		log.info("  文件名：" + fileName);
		log.info("  路径：" + path);

		// 获取分类名称
		String parent = file.getParent();
		File pFile = new File(parent);
		File[] listFiles = pFile.listFiles();
		for (File each : listFiles) {
			if(each != null && each.isFile() && each.getName().equals(ConfigConstant.PACKAGE_INFO)) {
				// 解析注释类并保存
				String packageInfo = getPackageInfoFromFile(each);
				if(packageInfo.trim().length()>=5){
					packageInfo = packageInfo.trim().substring(0, 5);
				}
				if(CachedConstant.controller2PackageInfo.get(packageInfo) == null) {
					List<String> ls = new ArrayList<String>();
					ls.add(fileName);
					CachedConstant.controller2PackageInfo.put(packageInfo, ls);
				} else {
					List<String> lt = CachedConstant.controller2PackageInfo.get(packageInfo);
					lt.add(fileName);
					CachedConstant.controller2PackageInfo.put(packageInfo, lt);
				}
			}
		}
	}

	/**
	 * 获取包info
	 * @param each
	 * @return
	 */
	private static String getPackageInfoFromFile(File file) {
		StringBuilder builder = new StringBuilder();
		if(file.exists()) {
			try {
				FileReader freader = new FileReader(file.getAbsolutePath());
				BufferedReader breader = new BufferedReader(freader);
				String temp = "";
				while ((temp = breader.readLine()) != null) {
					if(temp.contains("//") || temp.contains("*")) {
						builder.append(temp);
					}
				}
				breader.close();
				return builder.toString().replace("//", "").replace("/*", "").replace("*/", "").replace("*", "");
			} catch (FileNotFoundException e) {
				log.info(e.getMessage());
			} catch (IOException e) {
				log.info(e.getMessage());
			}
		}
		return builder.toString();
	}

	/**
	 * 填充网页头部信息
	 * @param bufferWritter
	 * @throws IOException
	 */
	public static void getHtmlHeadMsg(BufferedWriter bufferWritter) throws IOException {
		bufferWritter.write("<html>");
		bufferWritter.write("<head>");
		bufferWritter.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		bufferWritter.write("<link rel=\"stylesheet\" href=\"./css/main.css\"/>");
		bufferWritter.write("</head>");
		bufferWritter.write("<body>");
	}

	/**
	 * 填充网页结束信息
	 * @param bufferWritter
	 * @throws IOException
	 */
	public static void writeEndData(BufferedWriter bufferWritter) throws IOException {
		bufferWritter.write("</article>");
		bufferWritter.write("</div>");
		bufferWritter.write("<script src=\"./js/jquery-1.9.1.min.js\"></script>");
		bufferWritter.write("<script src=\"./js/layer.js\"></script>");
		bufferWritter.write("<script src=\"./js/main.js\"></script>");
		bufferWritter.write("</body>");
		bufferWritter.write("</html>");
		bufferWritter.flush();
		bufferWritter.close();
	}

	/**
	 * 填充参数固定信息
	 * @param bufferWritter
	 * @throws IOException
	 */
	public static void getParmHead(BufferedWriter bufferWritter) throws IOException {
		bufferWritter.write("<p><strong>请求参数说明</strong></p>");
		bufferWritter.write("<div class=\"blockquote\">");
		bufferWritter.write("<table class=\"table table-hover\">");
		bufferWritter.write("<thead>");

		bufferWritter.write("<tr>");
		bufferWritter.write("<th>参数类型</th>");
		bufferWritter.write("<th>参数名称</th>");
		bufferWritter.write("<th>参数描述</th>");
		bufferWritter.write("<th>缺省值</th>");
		bufferWritter.write("<th>必选</th>");
		bufferWritter.write("</tr>");

		bufferWritter.write("</thead>");
		bufferWritter.write("<tbody>");
	}

	/**
	 * 获取接口通用参数类型
	 * @param pramList
	 * @param s
	 */
	public static void getCommomParm(StringBuffer pramList, String s) {
		if (s.contains("String")) {
			pramList.append("String").append("@");
		} else if (s.contains("int")) {
			pramList.append("int").append("@");
		} else if (s.contains("Integer")) {
			pramList.append("Integer").append("@");
		} else if (s.contains("Long")) {
			pramList.append("Long").append("@");
		} else if (s.contains("Boolean")) {
			pramList.append("Boolean").append("@");
		} else if (s.contains("Float")) {
			pramList.append("Float").append("@");
		}
	}


	/**
	 * 写入参数
	 */
	public static void writeData(BufferedWriter bufferWritter, Map<String, String> paraMap) throws IOException {
		if (paraMap != null) {
			Set<String> pm = paraMap.keySet();
			for (String s : pm) {
				bufferWritter.write("<tr>");
				String[] split = paraMap.get(s).toString().split("@");

				//第一列
				getFirstCol(bufferWritter, split);

				//第二列
				getSecondCol(bufferWritter, s);

				//第三列～第五列
				getOtherCol(bufferWritter, split);
			}
		}

		bufferWritter.write("</tr>");
		bufferWritter.write("</tbody>");
		bufferWritter.write("</table>");
		bufferWritter.write("</div>");

	}

	private static void getOtherCol(BufferedWriter bufferWritter, String[] split) throws IOException {
		for (int k = 1; k < split.length; k++) {
			bufferWritter.write("<td>");
			if (split[k].trim().equals(ConfigConstant.COMMONPARAM)) {
				String res = CachedConstant.dto2HtmlPath.get(split[k].trim());
				String subs = res.substring(res.indexOf(ConfigConstant.APIDOCS) + 8, res.length());
				bufferWritter.write("<a href=\"" + subs + "\">" + split[k].trim().replace(",", "") + "</a>");
			} else {
				bufferWritter.write(split[k].trim());
			}
			bufferWritter.write("</td>");
		}
	}

	private static void getSecondCol(BufferedWriter bufferWritter, String s) throws IOException {
		bufferWritter.write("<td>");
		bufferWritter.write(s.trim().replace(",", ""));
		bufferWritter.write("</td>");
	}

	private static void getFirstCol(BufferedWriter bufferWritter, String[] split) throws IOException {
		for (int k = 0; k < 1; k++) {
			String str = split[k];
			bufferWritter.write("<td>");
			if (str.trim().equals(ConfigConstant.COMMONPARAM)) {
				String res = CachedConstant.dto2HtmlPath.get(str.trim());
				String subs = res==null ? null:res.substring(res.indexOf(ConfigConstant.APIDOCS) + 8, res.length());
				if(subs != null) {
					bufferWritter.write("<a href=\"javascript:;\""
							+ " data-url=\"" + subs + "\""
							+ " class=\"openLayer\">"
							+ str.trim().replace(",", "") + "</a>");
				}else{
					bufferWritter.write(str.trim());
				}
			} else {
				bufferWritter.write(str.trim());
			}
			bufferWritter.write("</td>");
		}
	}

	/**
	 * HttpServletRequest情况的处理
	 * @param pms
	 * @param pramList
	 * @param s
	 * @return
	 */
	public static StringBuffer gethttp(Map<String, String> pms, StringBuffer pramList, String s) {
		if(s.contains("HttpServletRequest")) {
			pramList = new StringBuffer();
			pramList.append("HttpServletRequest").append("@").append("请求参数").append("@").append(" ").append("@").append("是");
			pms.put("request", pramList.toString());
		}
		return pramList;
	}

	/**
	 * 包含HttpServletRequest
	 * @param split
	 * @param pms
	 * @return
	 */
	public static StringBuffer getHttpServletRequest(String[] split, Map<String, String> pms) {
		StringBuffer pramList = null;
		if(split != null && split.length == 1){
			if(split[0].contains("HttpServletRequest")) {
				pramList = new StringBuffer();
				pramList.append("HttpServletRequest").append("@").append("请求参数").append("@").append(" ").append("@").append("是");
				pms.put("request", pramList.toString());
			}
		}
		return pramList;
	}
	
	/**
	 * modelAttribute
	 * @param pms
	 * @param pramList
	 * @param s
	 * @param parmName
	 * @return
	 */
	public static String modelAttribute(Map<String, String> pms, StringBuffer pramList, String s, String parmName) {
		String comm = s.trim().replace("ModelAttribute", "").replace(")", "").replace("}", "").replace("{","");
		String[] split2 = comm.trim().split(" ");
		if (split2.length != 2) {
			String[] split3 = comm.replace("HttpServletRequest", "").replace("request", "").replace(",", "").trim().split(" ");
			if (split3.length != 2) {
			} else {
				if(pms.get("request") == null){
					parmName = split3[1];
					pramList.append(split3[0]).append("@").append("通用参数").append("@").append(" ").append("@").append("是");
				}
			}
		} else {
			parmName = split2[1];
			pramList.append(split2[0]).append("@").append("通用参数").append("@").append(" ").append("@").append("是");
		}
		if (!"".equals(parmName)) {
			pms.put(parmName, pramList.toString());
		}
		return parmName;
	}
	
	/**
	 * 不解析的行
	 * @param temp
	 * @return
	 */
	public static boolean checkStr(String temp) {
		if ("".equals(temp)) {
			return true;
		}
		//对于@等tag处理不够完善
		if(temp.contains("@HttpResponseInterceptorAnnotation")
				|| temp.contains("CommonConstants")
				|| temp.contains("request.")
				|| temp.contains("@CheckLoginInterceptorAnnotation")){
			return true;
		}
		//只获取待解析的行
		if (temp.contains("package")
				|| temp.contains("import")
				|| temp.startsWith("//")
				&& temp.contains("@RequestMapping")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 解析required参数
	 * @param pms
	 * @param pramList
	 * @param s
	 * @param parmName
	 */
	public static void neededValue(Map<String, String> pms, StringBuffer pramList, String s, String parmName) {
		if (s.contains("required")) {
			String isrequired = "";
			if (s.contains("false")) {
				isrequired = "否";
			} else {
				isrequired = "是";
			}
			pramList.append(isrequired);
		} else {
			pramList.append("是");
		}

		if (!"".equals(parmName)) {
			pms.put(parmName, pramList.toString());
		}
	}
	
	/**
	 * 字段和字段说明
	 * @param split
	 * @param i
	 * @return
	 */
	public static StringBuffer getParamValue(String[] split, int i) {
		String s = split[i].replace("*", "").trim();
		StringBuffer value = new StringBuffer();
		for (int j = 0; j < s.length(); j++) {
			if (s.charAt(j) != ' ') {
				value.append(s.charAt(j));
			} else {
				if (!value.toString().contains(" ")) {
					value.append(" ");
				}
			}
		}
		return value;
	}
}
