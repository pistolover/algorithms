package autoApi.java.xserver.autoapi.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.constant.ConfigConstant;

/**
 * dto处理工具类
 * @author liqqc
 *
 */
public class DtoUtil {
    private final static Logger log = LoggerFactory.getLogger(DtoUtil.class);

    /**
     * 获取dto文件
     * @param equpiment
     */
	public static void getDtoFiles(String equpiment) {
		File file = new File(ConfigConstant.path);
		getDtoFiles(file, equpiment);
		System.err.println();
	}

	private static void getDtoFiles(File dir, String equipment) {
		if (dir != null) {
			File[] listFiles = dir.listFiles();
			for (File file : listFiles) {
				if (file.isFile()) {
					if (file.getName().toLowerCase().endsWith(ConfigConstant.DOCJAVA)
							&& !file.getName().contains(ConfigConstant.CON)
							|| (file.getName().contains(ConfigConstant.COMMENTPARAM) && file.getName()
									.substring(file.getName().lastIndexOf("/") + 1, file.getName().length())
									.equals(ConfigConstant.COMMENTPARAM))
							|| file.getName().endsWith(ConfigConstant.BASERESPONSE_JAVA)) {

						if(file.getName().endsWith("Response.java")){
							System.err.println();
						}
						switch (equipment) {
						case "mobile":
							if (file.getAbsolutePath().endsWith(ConfigConstant.BASERESPONSE_JAVA)) {
								if (file.getAbsolutePath().contains(ConfigConstant.filterpath)) {
									parserCommonFile(file);
								}
							} else {
								if(file.getAbsolutePath().contains(ConfigConstant.DTO_JAVA)){
									if (file.getAbsolutePath().contains(ConfigConstant.filterpath)) {
										parserCommonFile(file);
									} else if(file.getAbsolutePath().contains("mobile-lib")){
										parserCommonFile(file);
									}
								} else {
									parserCommonFile(file);
								}
							}
							break;

						case "tv":
							if (file.getAbsolutePath().endsWith(ConfigConstant.BASERESPONSE_JAVA)) {
								if (file.getAbsolutePath().contains("/letv/itv/v2/api/")) {
									parserCommonFile(file);
								}
							} else {
								if (file.getAbsolutePath().contains(ConfigConstant.DTO_JAVA)) {
									if (file.getAbsolutePath().contains("/letv/itv/v2/api/")
											&& file.getAbsolutePath().contains(ConfigConstant.filterpath)) {
										parserCommonFile(file);
									} else if(file.getAbsolutePath().contains("tvserver-lib")){
										parserCommonFile(file);
									}
								} else {
									parserCommonFile(file);
								}
							}
							break;

						case "live":
							if (file.getAbsolutePath().endsWith(ConfigConstant.BASERESPONSE_JAVA)) {
								if (file.getAbsolutePath().contains(ConfigConstant.filterpath)) {
									parserCommonFile(file);
								} 
							} else {
								if(file.getAbsolutePath().contains(ConfigConstant.DTO_JAVA)){
									if (file.getAbsolutePath().contains(ConfigConstant.filterpath)) {
										parserCommonFile(file);
									} else if(file.getAbsolutePath().contains("mobile-lib")){
										parserCommonFile(file);
									}
								} else {
									parserCommonFile(file);
								}
							}
							break;
						default:
							if (file.getAbsolutePath().endsWith(ConfigConstant.BASERESPONSE_JAVA)) {
								if (file.getAbsolutePath().contains(ConfigConstant.filterpath)) {
									parserCommonFile(file);
								}
							} else {
								parserCommonFile(file);
							}
							break;
						}
					}
				} else if (file.isDirectory()) {
					getDtoFiles(file, equipment);
				}
			}
		}
	}

	/**
	 * 解析
	 * @param file
	 */
	private static void parserCommonFile(File file) {
		CachedConstant.dtoPaths.add(file.getAbsolutePath());
		parseJavaFiles(file);
	}

	/**
	 * 获取待解析文件地址
	 * @param file
	 */
	public static void parseJavaFiles(File file) {
		String path = file.getAbsolutePath();
		String fileName = path.substring(path.lastIndexOf("/") + 1, path.length() - 5);
//		if(!CachedConstant.dtoNames.contains(fileName)) {
			if(!CachedConstant.detailsPath.containsKey(fileName)){
				CachedConstant.detailsPath.put(fileName, path);
			}
			CachedConstant.dtoNames.add(fileName);
			if(fileName.equalsIgnoreCase("Response")){
				System.err.println();
			}
//		}

		log.info("-------------------------------------------------");
		log.info("  文件名：" + fileName);
		log.info("  路径：" + path);
		if(fileName.equals("PageResponse")){
			log.info(fileName);
		}
	}

	/**
	 * 网页头部信息 和 Dto名称和意义
	 * 
	 * @param name
	 * @param clazzMean
	 * @param bufferWritter
	 * @throws IOException
	 */
	public static void getDtoHtmlHeadMsg(String name, String clazzMean, BufferedWriter bufferWritter)
			throws IOException {
		bufferWritter.write("<html>");
		bufferWritter.write("<head>");
		bufferWritter.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		bufferWritter.write("<style type=\"text/css\">");
		bufferWritter.write("#dtos");
		bufferWritter.write("{");
		bufferWritter.write("font-family:\"Trebuchet MS\", Arial, Helvetica, sans-serif;");
		bufferWritter.write("width:80%;");
		bufferWritter.write("border-collapse:collapse;");
		bufferWritter.write("}");
		bufferWritter.write("#dtos td, #dtos th");
		bufferWritter.write("{");
		bufferWritter.write("font-size:1em;");
		bufferWritter.write("border:1px solid #98bf21;");
		bufferWritter.write("padding:3px 7px 2px 7px;");
		bufferWritter.write("}");
		bufferWritter.write("#dtos th");
		bufferWritter.write("{");
		bufferWritter.write("font-size:1.1em;");
		bufferWritter.write("text-align:left;");
		bufferWritter.write("padding-top:5px;");
		bufferWritter.write("padding-bottom:4px;");
		bufferWritter.write("background-color:#A7C942;");
		bufferWritter.write("color:#ffffff;");
		bufferWritter.write("}");
		bufferWritter.write("#dtos tr.alt td");
		bufferWritter.write("{");
		bufferWritter.write("color:#000000;");
		bufferWritter.write("background-color:#EAF2D3;");
		bufferWritter.write("}");
		bufferWritter.write("</style>");
		bufferWritter.write("</head>");

		bufferWritter.write("<h4>" + name + " ");
		if (clazzMean != null && !"".equals(clazzMean.trim())) {
			bufferWritter.write(clazzMean.replace("<", CachedConstant.fis).replace(">", CachedConstant.end));
		}
		bufferWritter.write("</h4>");
	}


	/**
	 * 获取字段说明
	 * @param paraMap
	 * @param bufferWritter
	 * @param str
	 * @throws IOException
	 */
	public static void paramMean(Map<String, String> paraMap, BufferedWriter bufferWritter, String str)
			throws IOException {
		bufferWritter.write("<td>");
		bufferWritter.write(paraMap.get(str).replace("<", CachedConstant.fis).replace(">", CachedConstant.end));
		bufferWritter.write("</td>");
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
	 * 字段修饰
	 * @param bufferWritter
	 * @param arrs
	 * @throws IOException
	 */
	public static void paramDecorate(BufferedWriter bufferWritter, String[] arrs) throws IOException {
		String ch = arrs[0];
		List<String> evey = new LinkedList<String>();
		if(StringUtils.isNotBlank(ch)) {
			StringBuffer buffer = new StringBuffer(10);
			for (int i = 0; i < ch.length(); i++) {
				if(ch.charAt(i) == '>' || ch.charAt(i)== '<' || ch.charAt(i) == ',') {
					evey.add(buffer.toString());
					evey.add(String.valueOf(ch.charAt(i)));
					buffer = new StringBuffer();
				} else {
					buffer.append(ch.charAt(i));
				}
			}
		}
		bufferWritter.write("<td>");
		if(evey.size() == 0) {
			parseHelper(bufferWritter, ch);
		} else {
			for (String ev : evey) {
				parseHelper(bufferWritter, ev);
			}
		}
		bufferWritter.write("</td>");
	}


	/**
	 * 普通类型解析
	 * @param bufferWritter
	 * @param arrs
	 * @throws IOException
	 */
	private static void parseHelper(BufferedWriter bufferWritter, String arrs) throws IOException {
		String realName = arrs.trim();
		if (CachedConstant.dto2HtmlPath.get(realName) != null) {
			String parmPath = CachedConstant.dto2HtmlPath.get(realName);
			String subs = parmPath.substring(parmPath.indexOf(ConfigConstant.APIDOCS) + 8, parmPath.length());
			bufferWritter.write("<a href=\"" + subs + "\">" + realName + "</a>");
		} else {
			bufferWritter.write(arrs.replace("<", CachedConstant.fis).replace(">", CachedConstant.end));
		}
	}


	/**
	 * 获取数组
	 * @param str
	 * @return
	 */
	public static String[] getArrs(String param) {
		String[] arrs = null;
		if (param.contains("Map")) {
			int s0 = param.lastIndexOf(">");
			arrs = new String[2];
			arrs[0] = param.substring(0,s0 + 1);
			arrs[1] = param.substring(s0 + 1, param.length());
		} else {
			arrs = param.trim().split(" ");
		}
		return arrs;
	}
	
	/**
	 *字符串检查
	 * @param name
	 * @param temp
	 * @return
	 */
	public static boolean checkStr(String name, String temp) {
		if (temp.contains("import")
				|| temp.contains("package")
				|| "".equals(temp.trim())
				|| temp.contains("public " + name)
				|| temp.contains("super(")
				|| temp.startsWith("@")
				|| temp.startsWith("return")
				|| temp.contains("public abstract " + name)
				|| temp.contains(" static final ")
				|| temp.contains(" final static ")
				|| temp.contains("continue")
				|| temp.contains("break")
				|| temp.contains("()")
				|| temp.contains("+")
				|| temp.contains("=")){
			return true;
		}
		return false;
	}
}
