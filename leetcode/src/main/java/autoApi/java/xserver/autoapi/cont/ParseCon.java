package autoApi.java.xserver.autoapi.cont;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.constant.ConfigConstant;
import autoApi.java.xserver.autoapi.utils.ConUtil;

/**
 * 解析controller文件，将其内容存入缓存中
 * contName2Interface：cont类和接口名称
 * controllerNames：cont类名称
 * url2name：接口地址和名称信息
 * url2params：接口地址和字段信息
 * url2resposne：接口地址和响应信息
 */
public class ParseCon extends CachedConstant {
    private final static Logger log = LoggerFactory.getLogger(ParseCon.class);

    //遇到类注释的flag
	private static boolean clazzFlag = true;

	//遇到接口注释的flag
	private static boolean paramFlag = false;

	//遇到@RequestParam的flag
	private static boolean requestParamFlag = false;

	//记录每次读取的数据
	private static StringBuffer buffer = null;

	//接口的参数列表
	private static Map<String, String> params = null;

	//接口的名称列表
	private static List<String> interfaceNames = null;

	//接口名称
	private static String interName = "";

    //类上面的requestMapping
	private static String clazzMapName = "";

	//类之前遇到requestMapping
    private static boolean beforeClazz = false;

    //是否遇到类名
    private static boolean countClazz = false;

    
    /**
     * 解析con文件
     */
	public static void parseConFiles() {
		if (CachedConstant.controllerNames != null) {
			for (String name : CachedConstant.controllerNames) {
				parseConFile(name);
			}
		}
	}

	
	/**
	 * 解析
	 * @param name 类名
	 */
	public static void parseConFile(String name) {
		readyParse(name);

		try {
			FileReader freader = new FileReader(detailsPath.get(name));
			BufferedReader breader = new BufferedReader(freader);
			String temp = "";

			//设置标记判断一个接口的开始和截止
			while ((temp = breader.readLine()) != null) {
				temp = temp.trim();

				if(ConUtil.checkStr(temp)) {
					continue;
				}

				//遇到类名的时候
				if(temp.contains("public class " + name)){
					countClazz = true;
				}

				//在类之前遇到requestMapping
				if(temp.startsWith("@RequestMapping") && beforeClazz == false && countClazz == false) {
				    clazzMapName = temp.substring(temp.indexOf("\"") + 1, temp.lastIndexOf("\""));
				    beforeClazz = true;
				    countClazz = true;
				    continue;
				}

				//遇到类注释时
				if (clazzFlag) {
					if (getClazzInfo(temp, name)) {
						continue;
					}
				}

				//接口上的注释
				if (paramFlag) {
					if (getRequestInfo(temp)) {
						continue;
					}
				}

				//接口方法的参数列表 对 @RequestParam("orderId")和 @RequestParam(value ="orderId")
				if (requestParamFlag) {
					if (requestParamInfo(temp)) {
						continue;
					}
				}
			}

			//保存接口的名称
			saveInterfaceName(name);

			breader.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}



	/**
	 * 保存接口名称
	 * @param name
	 */
	private static void saveInterfaceName(String name) {
		if(contName2Interface.get(name) !=null){
			if(contName2Interface.get(name).equals("") || contName2Interface.get(name).size() == 0){
				contName2Interface.put(name, interfaceNames);
			}
		}else{
			contName2Interface.put(name, interfaceNames);
		}
	}

	/**
	 * 准备解析
	 * @param name
	 */
	private static void readyParse(String name) {
		//对于后来新增的接口，默认先放入其它中，待后续再处理
		if(ConfigConstant.Controllers != null) {
			if(!ArrayUtils.contains(ConfigConstant.Controllers, name)) {
				List<String> otherList = ConfigConstant.IntersMaps.get(ConfigConstant.OTHER_CONT);
				otherList = new ArrayList<String>(otherList);
				otherList.add(name);
				ConfigConstant.IntersMaps.put(ConfigConstant.OTHER_CONT, otherList);
			}
		}
		buffer = new StringBuffer();
		params = new HashMap<String, String>();
		interfaceNames = new LinkedList<String>();
		clazzFlag = true;
		paramFlag = false;
		requestParamFlag = false;
		clazzMapName ="";
		interName = "";
		beforeClazz = false;
		countClazz = false;
	}
	
	

	/**
	 * 获取接口参数
	 * @param temp
	 * @return
	 */
	public static boolean requestParamInfo(String temp) {
		if (buffer == null) {
			buffer = new StringBuffer();
		}

		if(temp.startsWith("//")){
			return false;
		}

		// 去掉接口中影响的注释
		if(temp.contains("//")) {
			int st = temp.indexOf("//");
			temp = temp.substring(0, st).trim();
		}

		buffer.append(temp);
		if(buffer.toString().contains("@ResponseBody")){
			buffer = new StringBuffer(buffer.toString().replace("@ResponseBody", ""));
		}

		if (temp.contains("{")) {
			if (buffer.toString().contains("//") && buffer.toString().contains("public")) {
				return true;
			}

			//处理请求
			String[] split = buffer.toString().trim().split("@");

			String str = split[0].replace("public", "").replace("/", "").replace("*", "").trim();

			//响应类型
			putResponseData(str);

			Map<String, String> pms = new HashMap<String, String>();

			ConUtil.getHttpServletRequest(split, pms);

			for (int i = 1; i < split.length; i++) {
				fillParamValue(split, pms, i);
			}

			//保存结果
			saveResult(pms);
		}
		return false;
	}

	/**
	 * 填充字段
	 * @param split
	 * @param pms
	 * @param i
	 */
	private static void fillParamValue(String[] split, Map<String, String> pms, int i) {
		StringBuffer pramList = new StringBuffer();
		String s = split[i];
		String parmName = "";

		//包含http请求
		pramList = ConUtil.gethttp(pms, pramList, s);

		//CommonParam参数的处理
		if (s.contains("ModelAttribute")) {
			parmName = ConUtil.modelAttribute(pms, pramList, s, parmName);
			return;
		}
		pramList = new StringBuffer();

		//没有value，则没有其他的
		if (!s.contains("value")) {
			if(s.indexOf("\"") <= 0 || s.lastIndexOf("\"") <= 0 || s.indexOf("\"") + 1 > s.lastIndexOf("\"")) {
				return;
			}
			nodefaultValue(pms, pramList, s);
			return;
		}

		//参数名
		parmName = paramsValue(pramList, s, parmName);

		//缺省值
		defaultValue(pramList, s);

		//是否必须
		ConUtil.neededValue(pms, pramList, s, parmName);
	}

	/**
	 * 保存接口地址和字段信息
	 * @param pms
	 */
	private static void saveResult(Map<String, String> pms) {
		if(url2params.get(interName) != null){
			if(url2params.get(interName).equals("") || url2params.get(interName).size() == 0){
				url2params.put(interName, pms);
			}
		}else {
			url2params.put(interName, pms);
		}

		log.info("-------------------------------------------------");
		log.info("接口名称："+ interName);
		log.info("接口请求参数："+ pms.toString());

		buffer = null;
		requestParamFlag = false;
		paramFlag = true;
	}

	/**
	 * 没有default的情况
	 * @param pms
	 * @param pramList
	 * @param s
	 * @return
	 */
	private static String nodefaultValue(Map<String, String> pms, StringBuffer pramList, String s) {
		String pm = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\""));
		ConUtil.getCommomParm(pramList, s);
		if (params.get(pm) != null) {
			pramList.append(params.get(pm));
			pramList.append("@").append(" ").append("@").append("是");
		} else {
		    pramList.append(" ").append("@").append(" ").append("@").append("是");
		}

		if (!"".equals(pm)) {
			pms.put(pm, pramList.toString());
		}
		return pm;
	}



	/**
	 * 接口地址和响应信息
	 * @param str
	 */
	private static void putResponseData(String str) {
		String reponse = "";
		if (str.lastIndexOf(">") == -1) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == ' ') {
					reponse = str.substring(0, i);
				}
			}
		} else {
			reponse = str.substring(0, str.lastIndexOf(">") + 1);
		}

		if (!"".equals(interName)) {
			if(url2resposne.get(interName) != null){
				if(url2resposne.get(interName).equals("") || url2resposne.get(interName).length() == 0){
					url2resposne.put(interName, reponse);
				}
			}else {
				url2resposne.put(interName, reponse);
			}
		}
	}


	/**
	 * 解析value参数
	 * @param pramList
	 * @param s
	 * @param parmName
	 * @return
	 */
	private static String paramsValue(StringBuffer pramList, String s, String parmName) {
		if (s.contains("value")) {
			int st = 0;
			int ed = 0;
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '\"' && st == 0) {
					st = j;
				} else if (s.charAt(j) == '\"' && st != 0) {
					ed = j;
					break;
				}
			}

			parmName = s.substring(st + 1, ed);
			ConUtil.getCommomParm(pramList, s);
			if (params.get(parmName) != null) {
				pramList.append(params.get(parmName));
			} else {
				pramList.append(" ");
			}
			pramList.append("@");
		}
		return parmName;
	}

	/**
	 * 解析defaultValue参数
	 * @param pramList
	 * @param s
	 */
	private static void defaultValue(StringBuffer pramList, String s) {
		if (s.contains("defaultValue")) {
			String s2 = s.substring(s.indexOf("defaultValue"), s.length());
			String defaultValue = "";
			if (!s2.contains("\"")) {
				defaultValue = "";
			} else {
				defaultValue = s2.substring(s2.indexOf("\"") + 1, s2.lastIndexOf("\""));
			}
			pramList.append(defaultValue).append("@");
		} else {
			pramList.append(" ").append("@");
		}
	}


	
	/**
	 * 获取controller类信息
	 * @param temp
	 * @param name
	 * @return
	 */
	public static boolean getClazzInfo(String temp, String name) {
		if (temp.contains("*")) {
			//得到接口的注释串
			if (buffer == null) {
				buffer = new StringBuffer();
			}
			buffer.append(temp);
			return true;
		} else if (temp.contains("@Controller") || temp.contains("public class")) {
			//进到这里面说明类注释解析完成 类名称和类注释 可能没有类注释
			if (buffer == null) {
				buffer = new StringBuffer();
			}
			contName2Means.put(name, buffer.toString().replace("/*", "").replace("*/", "").replace("*", "").trim());
			buffer = null;
			clazzFlag = false;
			paramFlag = true;
			return true;
		}
		return false;
	}

	/**
	 * 获取接口本身相关信息
	 * @param temp
	 * @return
	 */
	public static boolean getRequestInfo(String temp) {
		if (temp.contains("public class")) {
			return true;
		}

		if (temp.contains("*")) {
			if (buffer == null) {
				buffer = new StringBuffer();
			}
			buffer.append(temp);
		} else if (temp.contains("@RequestMapping")) {
			//遇到@RequestMapping 说明接口注释结束
			if (buffer == null) {
				buffer = new StringBuffer();
			}

			//得到接口的名称
			String interfaceName = temp.substring(temp.indexOf("\"") + 1, temp.lastIndexOf("\""));

			//处理是否有注释，没有就不处理
			//保存接口的地址、接口名等
			saveInteName(interfaceName, getInteMean());

			//param中增加字段
			String[] split = buffer.toString().trim().replace("/*", "").replace("*/", "").replace("*", "").replace("/", "").trim().split("@param");
			for (int i = 1; i < split.length; i++) {
				//字段和意思
				params2means(ConUtil.getParamValue(split, i));
			}

			buffer = null;
			paramFlag = false;
			requestParamFlag = true;

			return true;
		}
		return false;
	}

	/**
	 * 接口说明信息
	 * @return
	 */
	private static String getInteMean() {
		String interfaceMean = "";
		String str = buffer.toString().trim();
		int index = str.indexOf("@");
		if (index != -1 && str.substring(0, index).trim().length() != 0) {
			interfaceMean = str.substring(0, index).replace("/*", "").replace("*/", "").replace("*", "").trim();
		} else {
			interfaceMean = "";
		}
		return interfaceMean;
	}

	/**
	 * 保存类名和接口名称
	 * @param interfaceName
	 * @param interfaceMean
	 */
	private static void saveInteName(String interfaceName, String interfaceMean) {
		if(!"".equals(clazzMapName)){
		    interfaceName = clazzMapName + interfaceName;
		}
		if(clazzMapName.equals(interfaceName.trim())){
			log.info(clazzMapName + " " + interfaceName);
		}else{
			 url2name.put(interfaceName, interfaceMean);
		     interName = interfaceName;
		     if(!interfaceNames.contains(interfaceName)){
		         interfaceNames.add(interfaceName);
		     }
		     urls.add(interfaceName);	
		}
	}


	/**
	 * 接口参数保存
	 * @param value
	 */
	private static void params2means(StringBuffer value) {
		String[] split2 = value.toString().split(" ");
		if (split2.length == 2) {
			if (!split2[1].contains("@")) {
				params.put(split2[0].trim(), split2[1].trim().replace("@return", ""));
			} else {
				params.put(split2[0].trim(), split2[1].substring(0, split2[1].indexOf("@")).trim().replace("@return", ""));
			}
		} else if (split2.length == 1) {
			params.put(split2[0].trim(), "");
		} else {
			params.put(split2[0].trim(), split2[1].trim().replace("@return", ""));
		}
	}
}
