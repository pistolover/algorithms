package autoApi.java.xserver.autoapi.html;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.constant.ConfigConstant;
import autoApi.java.xserver.autoapi.utils.HtmlUtil;
import autoApi.java.xserver.autoapi.utils.ParseHelper;

/**
 * 接口响应
 * @author liqiangqiang
 *
 */
public class ResponseHtml extends AbstractHtml{

    public static ResponseHtml getInstance() {
    	return new ResponseHtml();
    }

    public ResponseHtml(){
    	create();
    }

	//dto.html文件
	private static String dtoHtml = "";

	//二次调用需重置
	static {
		dtoHtml = "";
	}

	/**
	 * 创建响应文件
	 */
	public void create() {
		if (CachedConstant.url2resposne != null) {
			Set<String> keySet = CachedConstant.url2resposne.keySet();
			for (String name : keySet) {
				parseHelper(name);
			}
		}
	}

	public void parseHelper(String name) {
		dtoHtml = name.replace("/", "_").replace("\"", "").replace(",", "") + "_response.html";

		String filePath = ConfigConstant.rootpath + name.replace("/", "_").replace("\"", "").replace(",", "") + "_response.html";

		//创建文件
		ParseHelper.getFile(filePath);

		//得到结果
		CachedConstant.interface2ResponseHtml.put(name, filePath);

		//解析
		parseStart(CachedConstant.url2resposne.get(name), filePath);
	}
	
	/**
	 * 解析
	 * @param response
	 */
	public static void parseStart(String response, String filePath) {
		try {
			BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(filePath, true));

			// 获取网页头部信息
			HtmlUtil.getResponseHeadMsg(bufferWritter);

			//解析具体字符串
			parseConcrete(bufferWritter, response);

			bufferWritter.write("</html>");
			bufferWritter.flush();
			bufferWritter.close();
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}


	/**
	 * 解析帮助类
	 * @param bufferWritter
	 * @param response
	 * @throws IOException
	 */
	private static void parseConcrete(BufferedWriter bufferWritter, String response) throws IOException {
		List<String> evey = ParseHelper.splitStr(response);
		if(evey.size() != 0) {
			for (String str : evey) {
				if(str.trim().equals("<") || str.trim().equals(">") || "".equals(str.trim())) {
					continue;
				}
				parseSingle(bufferWritter, str);
			}
		}else {
			parseSingle(bufferWritter, response);
		}
	}


	/**
	 * 解析每一个
	 * @param bufferWritter
	 * @param response
	 * @throws IOException
	 */
	private static void parseSingle(BufferedWriter bufferWritter, String response) throws IOException {
		if(StringUtils.isNotBlank(response)) {
			if(CachedConstant.dtoname2params.get(response) != null) {
				parseHelper(bufferWritter, response, CachedConstant.dtoname2params.get(response));
			} else {
				bufferWritter.write("<div id=" + response + ">");
				bufferWritter.write("<h4>" + response +"：");
				String clazzMean = CachedConstant.dtoname2means.get(response);
				if (clazzMean != null && !"".equals(clazzMean.trim())) {
					bufferWritter.write(clazzMean.replace("<", CachedConstant.fis).replace(">",CachedConstant.end));
				}
				bufferWritter.write("</h4></div>");
			}
		}
	}

	 /**
	  * 解析帮助
	  * @param bufferWritter
	  * @param dtos
	  * @param map1
	  * @throws IOException
	  */
	private static void parseHelper(BufferedWriter bufferWritter, String dtos, Map<String, String> map) throws IOException {
		Map<String, String> son2Father = new HashMap<String, String>();
		//直接判断其本身
		Queue<String> clazzs = new LinkedList<String>();
		clazzs.add(dtos);

		//设置一个过滤器对当前已遍历到的不做处理
		Set<String> set = new HashSet<String>();
		while (!clazzs.isEmpty()) {
			String fist = clazzs.peek();
			if(set.contains(fist)){
				clazzs.poll();
				continue;
			}

			//第一次遍历完成后加入
			set.add(fist);

			//类名+类注释
			ParseHelper.getClassname2Mean(bufferWritter, clazzs);

			//参数
			String name = clazzs.poll();
			Map<String, String> child = ParseHelper.getAndPutChild(name, CachedConstant.dtoname2params.get(name), son2Father);

			//写入参数
			writeParams(bufferWritter, clazzs, name, CachedConstant.dtoname2params.get(name), child, son2Father);
		}
	}

	/**
	 * 写入参数
	 * @param bufferWritter
	 * @param clazzs
	 * @param poll
	 * @param other
	 * @param child
	 * @param son2Father 
	 * @return
	 * @throws IOException
	 */
	private static Map<String, String> writeParams(BufferedWriter bufferWritter, Queue<String> clazzs, String name,
			Map<String, String> dtoParams, Map<String, String> child, Map<String, String> son2Father) throws IOException {
		if (dtoParams != null || child !=null) {
			dtoParams = dtoParams == null ? child : dtoParams;
			bufferWritter.write("<table id=\"dtos\">");
			bufferWritter.write("<tr>");
			bufferWritter.write("<th>paramType</th>");
			bufferWritter.write("<th>paramName</th>");
			bufferWritter.write("<th>paramMean</th>");
			bufferWritter.write("</tr>");

			if (dtoParams != null) {
				Set<String> keySet = dtoParams.keySet();
				if (keySet != null) {
					for (String str : keySet) {
						getEachParam(dtoParams, bufferWritter, str, name, clazzs, son2Father);
					}
				}
			}

			bufferWritter.write("</tr>");
			bufferWritter.write("</table>");
			bufferWritter.write("<p></p>");
		}
		return dtoParams;
	}


	/**
	 * 依次获取字段信息
	 * @param paraMap
	 * @param bufferWritter
	 * @param str
	 * @param poll
	 * @param clazzs
	 * @param son2Father 
	 * @return
	 * @throws IOException
	 */
	private static String getEachParam(Map<String, String> dtoparaMap,
			BufferedWriter bufferWritter, String str, String name, Queue<String> clazzs, Map<String, String> son2Father) throws IOException {
		bufferWritter.write("<tr>");

		//strs中包含字段修饰和字段信息
		String[] arrs = ParseHelper.getArrs(str.trim());

		//字段修饰
		ParseHelper.paramDecorate(bufferWritter, name, clazzs, arrs, son2Father, dtoHtml);

		//字段本身
		ParseHelper.paramSelf(bufferWritter, str, arrs);

		//字段的意义
		ParseHelper.paramMean(dtoparaMap, bufferWritter, str);

		return str;
	}

	@Override
	public void writeData(BufferedWriter bufferWritter) throws IOException {
		// TODO Auto-generated method stub
	}

}
