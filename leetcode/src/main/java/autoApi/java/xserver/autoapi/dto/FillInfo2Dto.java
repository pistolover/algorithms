package autoApi.java.xserver.autoapi.dto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.utils.DtoUtil;

/**
 * 将dto文件中的注释添加到html文件中
 */
public class FillInfo2Dto extends CachedConstant {
    private final static Logger log = LoggerFactory.getLogger(FillInfo2Dto.class);
    
   /**
    * 填充信息
    */
	public static void fillDtoInfo() {
		if (CachedConstant.dtoNames != null) {
			for (String name : CachedConstant.dtoNames) {
				FillInfo2Dto.fillInfo(name);
			}
		}
	}

	/**
	 * 填充Dto文件信息
	 * @param name
	 */
	public static void fillInfo(String name) {

		//获取html文件的地址
		String filePath = dto2HtmlPath.get(name);

		//获取类注释
		String clazzMean = dtoname2means.get(name);

		//获取字段注释
		Map<String, String> paraMap = dtoname2params.get(name);

		log.info("name: " + name +"; path: " + filePath);

		File f = new File(filePath);
		//清空文件内容
		if (f.exists()) {

			//得到文件
			f = cleanFile(filePath, f);

			//填充
			fillInfo(name, clazzMean, paraMap, f);
		}
	}

	/**
	 * 填充信息
	 * @param name
	 * @param clazzMean
	 * @param paraMap
	 * @param f
	 */
	private static void fillInfo(String name, String clazzMean, Map<String, String> paraMap, File f) {
		try {
			FileWriter fileWritter = new FileWriter(f.getCanonicalPath(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

			//网页头部信息 网页 名称和意思
			DtoUtil.getDtoHtmlHeadMsg(name, clazzMean, bufferWritter);

			//获取Dto类中的参数列表
			getDtoParams(paraMap, bufferWritter);

			//获取Dto类的子类的参数列表
			getSubDtoParams(name, bufferWritter);

			bufferWritter.write("</html>");
			bufferWritter.flush();
			bufferWritter.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 清空文件内容
	 * @param filePath
	 * @param f
	 * @return
	 */
	private static File cleanFile(String filePath, File f) {
		if (f.length() != 0) {
			f.delete();
			f = new File(filePath);
			try {
				f.createNewFile();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return f;
	}

	/**
	 * 子类的参数列表
	 * @param name
	 * @param bufferWritter
	 * @throws IOException
	 */
	private static void getSubDtoParams(String name, BufferedWriter bufferWritter) throws IOException {
		if (dto2ChildsPrams != null) {
			if (dto2ChildsPrams.get(name) != null) {
				Map<String, Map<String, String>> map = dto2ChildsPrams.get(name);
				Set<String> childSet = map.keySet();
				if (childSet != null) {
					for (String chid : childSet) {
						bufferWritter.write("<h2><font color=\"#005AB5\">" + chid + "</font></h2>");
						bufferWritter.write("<table id=\"dtos\">");
						bufferWritter.write("<tr>");
						bufferWritter.write("<th>paramType</th>");
						bufferWritter.write("<th>paramName</th>");
						bufferWritter.write("<th>paramMean</th>");
						bufferWritter.write("</tr>");

						Map<String, String> pams = map.get(chid);
						if (pams != null) {
							Set<String> keySet = pams.keySet();
							if (keySet != null) {
								for (String str : keySet) {
									getEachParam(pams, bufferWritter, str, true);
								}
							}
						}
						bufferWritter.write("</tr>");
						bufferWritter.write("</table>");
					}
				}
			}
		}
	}

	/**
	 * 当前Dto类的参数列表
	 * @param paraMap
	 * @param bufferWritter
	 * @throws IOException
	 */
	private static void getDtoParams(Map<String, String> paraMap, BufferedWriter bufferWritter) throws IOException {
		bufferWritter.write("<table id=\"dtos\">");
		bufferWritter.write("<tr>");
		bufferWritter.write("<th>paramType</th>");
		bufferWritter.write("<th>paramName</th>");
		bufferWritter.write("<th>paramMean</th>");
		bufferWritter.write("</tr>");

		if (paraMap != null) {
			Set<String> keySet = paraMap.keySet();
			if (keySet != null) {
				for (String param : keySet) {
					getEachParam(paraMap, bufferWritter, param, null);
				}
			}
		}

		bufferWritter.write("</tr>");
		bufferWritter.write("</table>");
	}

	/**
	 * 每一个独立参数的获取
	 * @param paraMap
	 * @param bufferWritter
	 * @param str
	 * @return
	 * @throws IOException
	 */
	private static String getEachParam(Map<String, String> paraMap,
			BufferedWriter bufferWritter, String param, Boolean isSon) throws IOException {
		bufferWritter.write("<tr>");
		String[] arrs = DtoUtil.getArrs(param.trim());

		//字段修饰
		DtoUtil.paramDecorate(bufferWritter, arrs);

		//字段本身
		DtoUtil.paramSelf(bufferWritter, param, arrs);

		//字段的意义
		DtoUtil.paramMean(paraMap, bufferWritter, param);

		return param;
	}

}
