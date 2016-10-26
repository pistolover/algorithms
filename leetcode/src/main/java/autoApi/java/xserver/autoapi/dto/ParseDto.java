package autoApi.java.xserver.autoapi.dto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.constant.ConfigConstant;
import autoApi.java.xserver.autoapi.utils.DtoUtil;

/**
 * 解析dto文件得到待写入的信息，首先创建出所有的html文件，将其地址进行保存得到<名称，html的地址>
 * 
 * @param name
 */
public class ParseDto extends CachedConstant {
	private final static Logger log = LoggerFactory.getLogger(ParseDto.class);

	//抽象的基础响应类
	private static final String baseresponse = ConfigConstant.BASERESPONSE;

	//行号
	private static int lineNumber = 0;

	//针对类中参数的子类
	private static boolean jump = false;

	//针对Response提供的参数
	private static boolean hasFather = false;

	//遇到public class的标识
	private static boolean conutPublicClazz = false;

	//类中参数对应的类存在于该类文件中的情形
	private static Map<String, Map<String, String>> child2Params = new LinkedHashMap<String, Map<String, String>>();

	//遇到参数为类时的标识
	private static boolean nextFlag = true;

	static {
		child2Params = new LinkedHashMap<String, Map<String, String>>();
		lineNumber = 0;
		jump = false;
		hasFather = false;
		conutPublicClazz = false;
		nextFlag = true;
	}

	public static void parseDtoFile() {
		if (CachedConstant.dtoNames != null) {
			for (String name : CachedConstant.dtoNames) {
				parseDto(name);
			}
		}
//		log.info(dtoname2params.toString());
	}

	/**
	 * 解析
	 * 
	 * @param name
	 */
	public static void parseDto(String name) {
		if("SearchResponse".equalsIgnoreCase(name)) {
			System.err.println(1);
		}
		hasFather = false;
		conutPublicClazz = false;
		Map<String, String> params = new LinkedHashMap<String, String>();

		try {
			if (detailsPath.get(name) == null) {
				return;
			}

			FileReader freader = new FileReader(detailsPath.get(name));
			BufferedReader breader = new BufferedReader(freader);
			String temp = "";
			StringBuffer buffer = null;
			boolean flag = true;
			boolean paramflag = false;

			if (!jump) {
				while ((temp = breader.readLine()) != null) {
					lineNumber++;
					temp = temp.trim();

					//1、引入包不用解析
					if(DtoUtil.checkStr(name, temp)) {
						continue;
					}

					//对于BaseResponse的子类
					if (temp.contains("extends " + baseresponse)) {
						hasFather = true;
					}

					//对dto类包含子类的判断
					if (temp.contains(" class ") && temp.contains("{") && conutPublicClazz == true) {
						jump = true;
						lineNumber--;
						break;
					}

					if (temp.contains("public class") && conutPublicClazz == false) {
						conutPublicClazz = true;
						if (temp.contains("class")) {
							String dtoMean = "";
							if (buffer != null) {
								//将类名存入
								dtoMean = buffer.toString().replace("/", "").replace("*", "").replace("\\", "").trim();
								if (dtoMean.contains("@")) {
									dtoMean = dtoMean.substring(0, dtoMean.indexOf("@"));
								}
							}

							if (dtoname2means.get(name) == null) {
								dtoname2means.put(name, dtoMean.trim());
							}

							buffer = null;
							flag = false;
						}
					}

					//2、开始解析类注释 默认都是 /**来进行解析,默认都进行解析
					else if (temp.contains("/*") && flag) {
						buffer = new StringBuffer();
						buffer.append(temp);
						continue;
					} else if (buffer != null && temp.contains("*")) {
						buffer.append(temp);
					}

					//4、解析字段值 name, 可能是/** 也可能是//
					else {
						if (temp.startsWith("//") || temp.contains("this") || temp.contains("}")
								|| temp.contains("{")) {
							continue;
						}

						//以//为开始的注释
						if (!temp.trim().startsWith("//") && temp.contains("//")) {
							temp = temp.trim();
							String value = temp.substring(temp.indexOf("//") + 2, temp.length()).trim();
							String parm = temp.trim();
							params.put(parm.substring(0, temp.indexOf("//") - 1).replace("private", "")
									.replace("public", "").replace("protected", "").replace("static", "")
									.replace("final", "").replace("//", "").trim(), value);
						}

						//以/**开始的话
						else if (temp.contains("*")) {
							buffer = new StringBuffer();
							buffer.append(temp);
							paramflag = true;
						} else if (paramflag == true && !temp.contains("//") && !temp.trim().startsWith("//")) {
							//如果字段上有/**
							String dtoMean = "";
							if (buffer != null) {
								//将类名放入
								dtoMean = buffer.toString().replace("/", "").replace("*", "").replace("\\", "").trim();
								if (dtoMean.contains("@")) {
									dtoMean = dtoMean.substring(0, dtoMean.indexOf("@"));
								}
							}

							params.put(temp.replace("private", "").replace("public", "").replace("protected", "")
									.replace("static", "").replace("final", "").trim(), dtoMean);
							buffer = null;
							paramflag = false;
						} else {
							//其他情况存入空值
							String parm = "";
							if (!temp.startsWith("//") && temp != null && !"".equals(temp)) {
								parm = temp;
							}
							if(!(parm.contains("(") || temp.contains(")") || temp.contains("new"))) {
								params.put(parm.replace("private", "").replace("public", "").replace("protected", "")
										.replace("static", "").replace("final", "").trim(), "");
							}
						}
					}
				}

				if (dtoname2params.get(name) != null) {
					if (dtoname2params.get(name).size() == 0) {
						dtoname2params.put(name, params);
					}
				} else {
					dtoname2params.put(name, params);
				}

				log.info("-------------------------------------------------");
				log.info("Dto类名称：" + name);
				log.info("参数列表：" + params);

				//将BaseResponse放人该类中
				if (hasFather) {
					Map<String, Map<String, String>> childPams = new LinkedHashMap<String, Map<String, String>>();
					Map<String, String> map = dtoname2params.get(baseresponse);
					if (map == null) {
						parseDto(baseresponse);
					}
					childPams.put(baseresponse, map);

					if (dto2ChildsPrams.get(name) != null) {
						if (dto2ChildsPrams.get(name).size() == 0) {
							dto2ChildsPrams.put(name, childPams);
						}
					} else {
						dto2ChildsPrams.put(name, childPams);
					}
				}
			}

			//解析子类 如果有子类的话
			if (jump) {
				child2Params = new LinkedHashMap<String, Map<String, String>>();
				//填充"子类"参数
				fillChildParams(name);
				if (dto2ChildsPrams.get(name) != null) {
					if (dto2ChildsPrams.get(name).size() == 0) {
						dto2ChildsPrams.put(name, child2Params);
					}
				} else {
					dto2ChildsPrams.put(name, child2Params);
				}
			}

			log.info("-------------------------------------------------");
			log.info("Dto类名称：" + name);
			log.info("子类参数列表：" + child2Params);

			breader.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		jump = false;
		lineNumber = 0;
		nextFlag = true;

		//放入<子类,父类> map中
		if (child2Params != null && child2Params.keySet() != null) {
			for (String str : child2Params.keySet()) {
				if (son2father.get(str) == null || "".equals(son2father.get(str))) {
					son2father.put(str, name);
				}
			}
		}
	}



	/**
	 * 子类 params的保存
	 * 
	 * @param freader
	 * @param fatherName
	 */
	private static void fillChildParams(String fatherName) {
		try {
			FileReader freader = new FileReader(detailsPath.get(fatherName));
			BufferedReader reader = new BufferedReader(freader);
			String child = "";
			String childClazzName = "";
			StringBuffer buffer = null;
			Map<String, String> childparm = new LinkedHashMap<String, String>();
			int line = 0;
			boolean flag = true;
			boolean paramflag = false;

			while ((child = reader.readLine()) != null) {
				child = child.trim();
				if (line != lineNumber && flag) {
					line++;
					continue;
				}
				flag = false;
				lineNumber++;

				//子类的名称
				if (child.contains(" class ") && nextFlag) {
					childClazzName = child.substring(child.indexOf("class") + 5, child.length() - 1).trim()
							.split(" ")[0];
					nextFlag = false;
					continue;
				}

				//如果遇到第二个子类
				if (child.contains(" class ") && !"".equals(childClazzName)) {
					if (child2Params.get(childClazzName) == null) {
						child2Params.put(childClazzName, childparm);
					}
					nextFlag = true;
					lineNumber--;
					fillChildParams(fatherName);
					break;
				}

				if (child.startsWith("//") || child.contains("this") || child.contains("}") || child.contains("{")
						|| child.contains("static final")
						|| child.contains("final static") && !child.contains(" class ")
						|| child.contains("=")) {
					continue;
				}

				if (child.contains("import") || child.contains("package") || "".equals(child.trim())
						|| child.contains("super(") || child.startsWith("@") || child.startsWith("return")) {
					continue;
				}

				//以//为开始的注释
				if (!child.trim().startsWith("//") && child.contains("//")) {
					String value = child.substring(child.indexOf("//") + 2, child.length()).trim();
					childparm.put(child.substring(0, child.indexOf("//") - 1).replace("private", "")
							.replace("public", "").replace("protected", "").replace("static", "").replace("final", "")
							.replace("//", "").trim(), value);
				}

				//以/***开始
				else if (child.contains("*") && !paramflag) {
					buffer = new StringBuffer();
					buffer.append(child);
					paramflag = true;
				} else if (buffer != null && paramflag && child.contains("*")) {
					buffer.append(child);
				}

				else if (paramflag == true && !child.contains("//") && !child.trim().startsWith("//")) {
					//如果字段上面有 /**
					String dtoMean = "";
					if (buffer != null) {
						dtoMean = buffer.toString().replace("/", "").replace("*", "").replace("\\", "").trim();
					}
					childparm.put(
							child.substring(0, child.trim().length() - 1).replace("private", "").replace("public", "")
									.replace("protected", "").replace("static", "").replace("final", "").trim(),
							dtoMean);
					paramflag = false;
					buffer = null;
				} else {
					//其他情况存入空值
					if (!child.startsWith("//") && child != null && !"".equals(child.trim())) {
						if(!(child.contains("(") || child.contains(")") || child.contains("new"))) {
							childparm.put(child.replace("private", "").replace("public", "").replace("protected", "")
									.replace("static", "").replace("final", "").trim(), "");
						}
					} else {
						continue;
					}
				}
			}

			if (child2Params.get(childClazzName) != null) {
				if (child2Params.get(childClazzName).size() == 0) {
					child2Params.put(childClazzName, childparm);
				}
			} else {
				child2Params.put(childClazzName, childparm);
			}
			reader.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
