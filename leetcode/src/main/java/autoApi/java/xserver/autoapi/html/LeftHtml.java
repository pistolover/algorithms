package autoApi.java.xserver.autoapi.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import autoApi.java.xserver.autoapi.constant.CachedConstant;
import autoApi.java.xserver.autoapi.utils.HtmlUtil;

/**
 * 创建索引页和主页
 */
public class LeftHtml extends AbstractHtml {

	public LeftHtml() {
		create();
	}

	public void create() {
		if (CachedConstant.contName2Interface != null) {
			try {
				writeData(HtmlUtil.fillLeftHeadData(getFile()));
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
	}

	private File getFile() {
		return super.getFile(LEFT);
	}

	@Override
	public void writeData(BufferedWriter bufferWritter) throws IOException {
		HtmlUtil.writeLeft(bufferWritter);
	}

	public static LeftHtml getInstance() {
		return new LeftHtml();
	}
}
