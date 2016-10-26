package autoApi.java.xserver.autoapi.constant;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 缓存所有的参数和对象
 * @author liqiangqiang
 *
 */
public class CachedConstant {

// ----------------------------------得到待解析文件的地址------------------------------------------------
	/**
	 * 所有dto的绝对地址 硬盘中的位置
	 */
	public static Set<String> dtoPaths = new LinkedHashSet<String>();

	/**
	 * 所有controller绝对地址 硬盘中的位置
	 */
	public static Set<String> controllerPaths = new LinkedHashSet<String>();

// -----------------------------------解析dto类，生成html文件------------------------------------------------------
	/**
	 * 获取dto文件地址，解析地址得到名称和应该创建的地址，然后存入到map中，然后解析文件然后加入内容
	 */

	/**
	 * 所有dto类和其对应的html链接地址 commentDto,...commentDto.html 最后添加
	 */
	public static Map<String, String> dto2HtmlPath = new LinkedHashMap<String, String>();

	/**
	 * 所有dto的类名称
	 */
	public static Set<String> dtoNames = new LinkedHashSet<String>();

	/**
	 * 所有dto、con名称和对应的详细地址 commentDto,....硬盘中的位置  因为引用中可能还有引用
	 */
	public static Map<String, String> detailsPath = new LinkedHashMap<String, String>();

	/**
	 *  解析dto文件，得到类名、类的解释、类中字段、字段注释
	 */
	public static Map<String,String> dtoname2means = new LinkedHashMap<String,String>();
	public static Map<String,Map<String,String>> dtoname2params = new LinkedHashMap<String,Map<String,String>>();

//*--------------------------------------------------------------------------------------------------------------------*/	
	/**
	 * 所有controller类名称
	 */
	public static Set<String> controllerNames = new LinkedHashSet<String>();

	/**
	 * 所有controller类中的 接口
	 */
	public static Map<String,List<String>> contName2Interface = new TreeMap<String,List<String>>();

	/**
	 * 所有接口的地址 /comment/list
	 */
	public static Set<String> urls = new LinkedHashSet<String>();

	/**
	 * 接口地址和名称信息 /comment/list, 列表信息
	 */
	public static Map<String, String> url2name = new LinkedHashMap<String, String>();

	/**
	 * 接口地址和字段信息 /comment/list, params
	 */
	public static Map<String, Map<String, String>> url2params = new LinkedHashMap<String, Map<String, String>>();

	/**
	 * 接口地址和响应信息 /comment/list,respose
	 */
	public static Map<String, String> url2resposne = new LinkedHashMap<String, String>();

	/**
	 * controller类注释 及其名称
	 */
	public static Map<String, String> contName2Means = new LinkedHashMap<String,String>();

	/**
	 * controller类和对应的html文件地址
	 */
	public static Map<String, String> controller2HtmlPath = new LinkedHashMap<String, String>();

	/**
	 * Controller类中接口对应的分类名称
	 */
	public static Map<String, List<String>> controller2PackageInfo = new HashMap<String, List<String>>();

	/**
	 * dto类中子类的信息 <father,<childs,params>>
	 */
	public static Map<String,Map<String,Map<String,String>>> dto2ChildsPrams = new LinkedHashMap<String,Map<String,Map<String,String>>>();

	/**
	 * dto类中子类和其父类Map
	 */
	public static Map<String, String> son2father = new HashMap<String, String>();

	/**
	 * html中的"<"和">"符号
	 */
	public static final String end = "&gt;";
	public static final String fis = "&lt;";

	/**
	 * 接口名称和对应的example
	 */
	public static Map<String,String> interface2Example = new HashMap<String,String>();

	/**
	 * 接口名称和对应的响应链接 ，命名规则接口名称+responseHtml
	 */
	public static Map<String,String> interface2ResponseHtml = new HashMap<String, String>();

	/**
	 * 释放内存
	 */
	public static void releseMemory() {
		dtoPaths = null;
		controllerPaths = null;
		dto2HtmlPath = null;
		dtoNames = null;
		detailsPath = null;
		dtoname2means = null;
		dtoname2params = null;
		controllerNames =null;
		contName2Interface =null;
		urls = null;
		url2name =null;
		url2params = null;
		url2resposne = null;
		contName2Means =null;
		controller2HtmlPath =null; 
		dto2ChildsPrams = null;
		son2father = null;
		interface2Example =null; 
		interface2ResponseHtml = null;
		dtoPaths = new LinkedHashSet<String>();
		controllerPaths = new LinkedHashSet<String>();
		dto2HtmlPath = new LinkedHashMap<String, String>();
		dtoNames = new LinkedHashSet<String>();
		detailsPath = new LinkedHashMap<String, String>();
		dtoname2means = new LinkedHashMap<String,String>();
		dtoname2params = new LinkedHashMap<String,Map<String,String>>();
		controllerNames = new LinkedHashSet<String>();
		contName2Interface = new TreeMap<String,List<String>>();
		urls = new LinkedHashSet<String>();
		url2name = new LinkedHashMap<String, String>();
		url2params = new LinkedHashMap<String, Map<String, String>>();
		url2resposne = new LinkedHashMap<String, String>();
		contName2Means = new LinkedHashMap<String,String>();
		controller2HtmlPath = new LinkedHashMap<String, String>();
		dto2ChildsPrams = new LinkedHashMap<String,Map<String,Map<String,String>>>();
		son2father = new HashMap<String, String>();
		interface2Example = new HashMap<String,String>();
		interface2ResponseHtml = new HashMap<String, String>();
	}
}
