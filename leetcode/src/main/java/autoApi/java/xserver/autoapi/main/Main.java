package autoApi.java.xserver.autoapi.main;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.constant.ConfigConstant;
import autoApi.java.xserver.autoapi.cont.FillInfo2Con;
import autoApi.java.xserver.autoapi.cont.ParseCon;
import autoApi.java.xserver.autoapi.dto.FillInfo2Dto;
import autoApi.java.xserver.autoapi.dto.ParseDto;
import autoApi.java.xserver.autoapi.html.ConHtml;
import autoApi.java.xserver.autoapi.html.DtoHtml;
import autoApi.java.xserver.autoapi.html.IndexHtml;
import autoApi.java.xserver.autoapi.html.LeftHtml;
import autoApi.java.xserver.autoapi.html.ResponseHtml;
import autoApi.java.xserver.autoapi.html.RightHtml;
import autoApi.java.xserver.autoapi.utils.ConUtil;
import autoApi.java.xserver.autoapi.utils.DtoUtil;
import autoApi.java.xserver.autoapi.utils.FileUtil;
import autoApi.java.xserver.autoapi.utils.PropertiesUtil;

public class Main {
	private final static Logger log = LoggerFactory.getLogger(Main.class);

	/**
	 * 入口方法，只需要配置本地待解析文件夹路径即可
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Long start = System.currentTimeMillis();
		PropertiesUtil.getInstance().getPropertiesByClassPath("/log4j.properties");
		ArrayList<String> params = FileUtil.loadProperty("conf.dir", "path.properties");

		if (params != null && params.size() > 0) {
			for (String parm : params) {
				String[] p = parm.split(",");
				createDocs(p[0], p[1]);
				CachedConstant.releseMemory();
			}
		} else {
			log.error("get param error");
		}

		log.info("use time：" + ((Long) System.currentTimeMillis() - start));
	}



	/**
	 * create docs
	 */
	private static void createDocs(String path, String equipment) {
		log.info("parse request parms");

		String filter = FileUtil.getFilterPath(path);
		ConfigConstant.init(path, equipment + "_apidocs", filter, equipment);

		log.info("解析参数：" + path + ";" + equipment + "_apidocs;" + filter + "; " + equipment);

		// 解析
		startParse();
	}



	/**
	 * parse files
	 */
	public static void startParse() {
		log.info("start parse");

		// 常规解析
		parseReady();

		// 处理dto文件
		parseDto();

		// 处理controller文件
		parseController();

		// 创建页面
		createHtml();

		// 拷贝css元素
		FileUtil.copyCss2Dir();
		log.info("-----parse end-----");
	}



	/**
	 * 解析前存储名称、路径等信息
	 */
	private static void parseReady() {
		try {
			log.info("start parse ConFiles" + ConfigConstant.mobile2Tv);

			// controller文件
			ConUtil.getConFiles(ConfigConstant.mobile2Tv);

			log.info(" ");
			log.info("start parse DtoFiles" + ConfigConstant.mobile2Tv);

			// dto文件
			DtoUtil.getDtoFiles(ConfigConstant.mobile2Tv);

		} catch (IOException e) {
			log.error("parse error" + e.getMessage());
		}
	}


	private static void createHtml() {
		// 创建index 索引页面
		IndexHtml.getInstance();

		// 创建left 索引页面
		LeftHtml.getInstance();;

		// 创建 right索引页面
		RightHtml.getInstance();
	}

	private static void parseController() {
		// 解析controller类
		ParseCon.parseConFiles();

		// 创建controller静态文件
		ConHtml.getInstance(CachedConstant.controllerPaths, CachedConstant.controller2HtmlPath);

		// 增加接口的响应地址
		ResponseHtml.getInstance();

		// 将信息添加到controller网页中
		FillInfo2Con.FileConInfo();
	}

	private static void parseDto() {
		// 解析dto文件
		ParseDto.parseDtoFile();

		// 创建dto静态文件
		DtoHtml.getInstance(CachedConstant.dtoPaths, CachedConstant.dto2HtmlPath);

		// 填充信息
		FillInfo2Dto.fillDtoInfo();
	}
}
