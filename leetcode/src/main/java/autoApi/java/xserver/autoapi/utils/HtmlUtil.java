package autoApi.java.xserver.autoapi.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.constant.ConfigConstant;

public class HtmlUtil {
	public final static String apidocs = ConfigConstant.APIDOCS;

	protected final static Logger log = LoggerFactory.getLogger(HtmlUtil.class);

	public static void writeIndex(BufferedWriter bufferWritter) throws IOException {
		bufferWritter.write("<html>");
		bufferWritter.write("<head>");
		bufferWritter.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		bufferWritter.write("<title>领先版服务器端api</title>");
		bufferWritter.write("</head>");
		bufferWritter.write("<frameset  rows=\"98%,2%\">");
		bufferWritter.write("<frameset cols=\"15%,*\">");
		bufferWritter.write("<frameset  rows=\"99%\">");
		bufferWritter.write("<frame src=\"left.html\" scrolling=\"auto\" name=left id=\"left\">");
		bufferWritter.write("</frameset>");
		bufferWritter.write("<frame src=\"right.html\"  scrolling=\"auto\" name=right id=\"right\">");
		bufferWritter.write("</frameset>");
		bufferWritter.write("</frameset>");
		bufferWritter.write("</html>");
		bufferWritter.flush();
		bufferWritter.close();
	}
	
	
	/**
	 * 头部信息
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static BufferedWriter fillLeftHeadData(File f) throws IOException {
		FileWriter fileWritter = new FileWriter(f.getCanonicalPath(), true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		bufferWritter.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		bufferWritter.write("<head>");
		bufferWritter.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		bufferWritter.write("<title>接口列表</title>");
		bufferWritter.write("<link rel=\"stylesheet\" href=\"css/screen.css\" />");
		bufferWritter.write("<link rel=\"stylesheet\" href=\"css/font-awesome.min.css\"/>");
		bufferWritter.write("<link rel=\"stylesheet\" href=\"css/font-awesome-ie7.min.css\"/>");
		bufferWritter.write("<link rel=\"stylesheet\" href=\"css/treeview.css\"/>");
		bufferWritter.write("<script src=\"js/jquery.js\" type=\"text/javascript\"></script>");
		bufferWritter.write("<script src=\"js/jquery.cookie.js\" type=\"text/javascript\"></script>");
		bufferWritter.write("<script src=\"js/jquery.treeview.js\" type=\"text/javascript\"></script>");
		bufferWritter.write("<script type=\"text/javascript\">");
		bufferWritter.write("$(function() {");
		bufferWritter.write("$(\"#tree\").treeview({");
		bufferWritter.write("collapsed : true,");
		bufferWritter.write("animated : \"fast\",");
		bufferWritter.write("prerendered : true,");
		bufferWritter.write("persist : \"location\"");
		bufferWritter.write("});");
		bufferWritter.write(" })");
		bufferWritter.write("</script>");
		bufferWritter.write("</head>");
		bufferWritter.write("<body  style=\"overflow-x: hidden;overflow-y: auto\">");
		bufferWritter.write(" <div id=\"sidetree\">");
		bufferWritter.write("<div class=\"treeheader\">&nbsp;</div>");
		bufferWritter.write("<ul class=\"treeview\" id=\"tree\">");
		return bufferWritter;
	}
	
	/**
	 * 尾部信息
	 * 
	 * @param bufferWritter
	 * @throws IOException
	 */
	public static void fillHtmlEndData(BufferedWriter bufferWritter) throws IOException {
		bufferWritter.write("</ul>");
		bufferWritter.write("</div>");
		bufferWritter.write("</body>");
		bufferWritter.write("</html>");
		bufferWritter.flush();
		bufferWritter.close();
	}

	public static void fillData(BufferedWriter bufferWritter) throws IOException {
		if (ConfigConstant.IntersMaps != null) {
			Set<String> names = ConfigConstant.IntersMaps.keySet();
			if (names != null) {
				log.info("-------------------------------------------------");
				log.info("最终显示接口列表:");
				for (String title : names) {
					bufferWritter.write("<li class=\"expandable\"><div class=\"hitarea expandable-hitarea hit\">");
					bufferWritter.write("<i class=\"portal_arrow\"></i>");
					bufferWritter.write(title);
					bufferWritter.write("</div>");
					bufferWritter.write("<ul style=\"display: none;\">");

					writeData(bufferWritter, title);

					bufferWritter.write("</ul>");
					bufferWritter.write("</li>");
				}
			}
		}
	}

	public static void writeData(BufferedWriter bufferWritter, String title) throws IOException {
		List<String> list = ConfigConstant.IntersMaps.get(title);
		for (String name : list) {
			List<String> inters = CachedConstant.contName2Interface.get(name);
			if (inters != null) {
				for (String inter : inters) {
					log.info(" " + inter);
					bufferWritter.write("<li>");
					bufferWritter.write("<a href = \"");
					String conName = CachedConstant.controller2HtmlPath.get(name);
					String substring = conName.substring(conName.indexOf(apidocs) + 8, conName.length());
					bufferWritter.write(substring.replace(name, inter).replace("/", "_"));
					bufferWritter.write("\" target=\"right\">");
					bufferWritter.write(inter + "</a></li>");
				}
			}
		}
	}

	/**
	 * 获取网页头部信息
	 * @param bufferWritter
	 * @throws IOException
	 */
	public static void getResponseHeadMsg(BufferedWriter bufferWritter) throws IOException {
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
	}
	
	public static void writeRight(BufferedWriter bufferWritter) throws IOException {
		bufferWritter.write("<html>");
		bufferWritter.write("<head>");
		bufferWritter.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		bufferWritter.write("</head>");
		bufferWritter.write("<body>");
		bufferWritter.write("<img src=\"./picture/toll.jpeg\" width=\"100%\" height=\"100%\" />");
		bufferWritter.write("</body>");
		bufferWritter.write("</html>");
		bufferWritter.flush();
		bufferWritter.close();
	}


	public static void writeLeft(BufferedWriter bufferWritter) throws IOException {
		// 填充数据
		fillData(bufferWritter);

		// 填充网页尾部信息
		fillHtmlEndData(bufferWritter);
	}
}
