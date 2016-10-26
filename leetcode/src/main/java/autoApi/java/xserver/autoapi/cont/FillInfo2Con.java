package autoApi.java.xserver.autoapi.cont;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.constant.ConfigConstant;
import autoApi.java.xserver.autoapi.utils.ConUtil;
import autoApi.java.xserver.autoapi.utils.FileUtil;

/**
 * 填充信息到controller.html文件中
 */
public class FillInfo2Con extends CachedConstant {

	private final static Logger log = LoggerFactory.getLogger(FillInfo2Con.class);

	private final static String apidocs = ConfigConstant.APIDOCS;

	//依据类名获取到所有的接口地址
	private static List<String> interfaceNames = null;

	//获取html文件的地址
	private static String filePath = null;

	//获取类注释
	private static String clazzMean = null;

	/**
	 * 填充con文件
	 */
	public static void FileConInfo() {
		if (CachedConstant.controllerNames != null) {
			for (String name : CachedConstant.controllerNames) {
				fillInfo(name);
			}

			//获取请求实例地址
//			getShells();
		}
	}



	/**
	 * 通过con类名填充信息
	 * 
	 * @param name
	 */
	public static void fillInfo(String name) {
		log.info("-------------------------------------------------");
		log.info(name + "中的接口如下：");

		//依据类名获取到所有的接口地址
		interfaceNames = contName2Interface.get(name);

		//获取html文件的地址
		filePath = controller2HtmlPath.get(name);

		//获取类注释
		clazzMean = contName2Means.get(name);

		//针对每个接口写入,一个接口对应一个html
		getHtml(name);

	}



	/**
	 * 创建shell
	 */
//	private static void getShells() {
//		try {
//			PropertiesUtil.getInstance().getPropertiesByClassPath("/url.sh");
//		} catch (Exception e1) {
//			log.error(e1.getMessage());
//		}
//		String path = System.getProperty("conf.dir");
//		File f = new File(path + "/url.sh");
//		if (f.exists()) {
//			if (f.length() != 0) {
//				f.delete();
//				f = new File(f.getAbsolutePath());
//				try {
//					f.createNewFile();
//				} catch (IOException e) {
//					log.error(e.getMessage());
//				}
//			}
//		}
//
//		try {
//			FileWriter fileWritter = new FileWriter(f.getCanonicalPath(), true);
//			BufferedWriter bw = new BufferedWriter(fileWritter);
//			bw.write("#!/bin/sh");
//			bw.newLine();
//			bw.newLine();
//			for (String url : urls) {
//				bw.write("cat /letv/nginx_logs/access.log | awk -F'GET' '{print $2}' | awk -F'HTTP/' '{print $1}' | grep ");
//				bw.write(url+ " | tail -1 ");
//				bw.newLine();
//			}
//			bw.flush();
//			bw.close();
//		} catch (Exception e) {
//			log.error("fill shell error");
//		}
//	}



	private static void getHtml(String name) {
		try {
			if (interfaceNames != null) {
				for (int i = 0; i < interfaceNames.size(); i++) {
					File f = getFile(name, i);

					//具体的一个接口
					FileWriter fileWritter = new FileWriter(f.getCanonicalPath(), true);
					BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

					//填充网页头部信息
					ConUtil.getHtmlHeadMsg(bufferWritter);

					//填充一个具体的接口
					getOneInterFaceMsg(bufferWritter, i, name);

					//填充网页尾部信息
					ConUtil.writeEndData(bufferWritter);

				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}



	/**
	 * 获取文件
	 * 
	 * @param name
	 * @param i
	 * @return
	 */
	private static File getFile(String name, int i) {
		String interPath = filePath.replace(name, interfaceNames.get(i).replace("/", "_"));
		if (interPath.contains(", ") || interPath.contains(",")) {
			interPath = interPath.replace("\"", "").replace(", ", "_").replace(",", "_");
		}
		if (interPath.contains(", ") || interPath.contains(",")) {
			interPath = interPath.replace("\"", "").replace(", ", "_").replace(",", "_");
		}

		File f = new File(interPath);

		f = cleanFile(interPath, f);

		return f;
	}

	/**
	 * 清空文件内容
	 */
	private static File cleanFile(String interPath, File f) {
		if (f.exists()) {
			if (f.length() != 0) {
				f.delete();
				f = new File(interPath);
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
	 * 写入一个具体接口的信息
	 * 
	 * @param bufferWritter
	 * @param i
	 * @param name
	 * @throws IOException
	 */
	private static void getOneInterFaceMsg(BufferedWriter bufferWritter, int i, String name) throws IOException {
		bufferWritter.write("<div class=\"container-fluid\">");
		bufferWritter.write("<h2>" + name + ":" + clazzMean + "</h2>");

		/**
		 * 获取字段注释 name为接口信息
		 */
		Map<String, String> paraMap = url2params.get(interfaceNames.get(i).toString());

		/**
		 * 获取接口对应的响应信息
		 */
		String responseInfo = url2resposne.get(interfaceNames.get(i));

		/**
		 * 接口的意义
		 */
		getMeaning(bufferWritter, i);

		/**
		 * 获取参数
		 */
		getParams(bufferWritter, paraMap);

		/**
		 * 增加新的响应类型
		 */
		getResponse(bufferWritter, i, responseInfo);

		/**
		 * 增加请求实例
		 */
		getRequestExample(bufferWritter, i);

		/**
		 * 响应json
		 */
//		getJSONResponse(bufferWritter, i);
	}

	/**
	 * json样式
	 * @param bufferWritter
	 * @param i
	 */
	private static void getJSONResponse(BufferedWriter bufferWritter, int i) {
		try {
			bufferWritter.write("<p><strong>JSON样式</strong></p>");
			bufferWritter.write("<div class=\"blockquote\">");
			String url = "http://www.baidu.com";
			bufferWritter.write("<a href=\"javascript:;\"" + " data-url=\"" + url + "\"" + " class=\"openLayer\">" + "JSON" + "</a>");
			bufferWritter.write("</div>");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 请求实例
	 * @param bufferWritter
	 * @param i
	 * @throws IOException
	 */
	private static void getRequestExample(BufferedWriter bufferWritter, int i) throws IOException {
		//目前只对手机领先版处理
		if(ConfigConstant.mobile2Tv.equals("mobile")) {
			String rp = interfaceNames.get(i);
			if (rp != null) {
				Map<String, String> urlMap = FileUtil.getUrlMap();
				if(urlMap != null && urlMap.size() != 0 && urlMap.get(rp) != null) {
					bufferWritter.write("<p><strong>请求示例</strong></p>");
					bufferWritter.write("<div class=\"blockquote\">");
					String url = "http://10.11.144.183" + urlMap.get(rp);
					bufferWritter.write("<a href=\"" + url + "\" target=\"_blank\">" + "example" + "</a>");
					bufferWritter.write("</div>");
				}
			}
		}
	}

	/**
	 * 获取响应
	 * 
	 * @param bufferWritter
	 * @param i
	 * @param responseInfo
	 * @throws IOException
	 */
	private static void getResponse(BufferedWriter bufferWritter, int i, String responseInfo) throws IOException {
		bufferWritter.write("<p><strong>返回数据</strong></p>");
		bufferWritter.write("<div class=\"blockquote\">");
		String rp = interface2ResponseHtml.get(interfaceNames.get(i));
		if (rp != null) {
			String substr = rp.substring(rp.indexOf(apidocs) + 8, rp.length());
			String rs = responseInfo.replace("<", fis).replace(">", end);
			bufferWritter.write("<a href=\"javascript:;\"" + " data-url=\"" + substr + "\"" + " class=\"openLayer\">"
					+ rs + "</a>");
			bufferWritter.write("</div>");
		}

	}

	/**
	 * 获取接口说明
	 * 
	 * @param bufferWritter
	 * @param i
	 * @throws IOException
	 */
	private static void getMeaning(BufferedWriter bufferWritter, int i) throws IOException {
		String interMeans = url2name.get(interfaceNames.get(i));
		log.info("  " + interfaceNames.get(i));
 
		bufferWritter.write("<div class=\"split-line\"></div>");
		bufferWritter.write("<article>");
		bufferWritter.write("<p><strong>接口说明</strong></p>");
		bufferWritter.write("<div class=\"blockquote\">");

		if (interMeans != null && !"".equals(interMeans.trim())) {
			bufferWritter.write(interMeans);
		}

		bufferWritter.write("</div>");
		bufferWritter.write("<p><strong>请求地址</strong></p>");

		String upath = "";
		if("mobile".equalsIgnoreCase(ConfigConstant.mobile2Tv) ||
				"live".equalsIgnoreCase(ConfigConstant.mobile2Tv)) {
			upath = "http://d.itv.letv.com";
		}else if("tv".equalsIgnoreCase(ConfigConstant.mobile2Tv)){
			upath = "http://api.itv.letv.com";
		}
		bufferWritter.write("<div class=\"blockquote\">" + upath + interfaceNames.get(i) + ".json" + "</div>");
	}

	/**
	 * 获取参数信息
	 * 
	 * @param bufferWritter
	 * @param paraMap
	 * @throws IOException
	 */
	private static void getParams(BufferedWriter bufferWritter, Map<String, String> paraMap) throws IOException {
		//填充参数固定信息
		ConUtil.getParmHead(bufferWritter);

		//填充具体数据
		ConUtil.writeData(bufferWritter, paraMap);
	}

}
