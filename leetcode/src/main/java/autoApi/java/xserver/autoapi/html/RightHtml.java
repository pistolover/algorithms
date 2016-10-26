package autoApi.java.xserver.autoapi.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import autoApi.java.xserver.autoapi.utils.HtmlUtil;

/**
 * 右侧的html文件
 * 
 * @author liqiangqiang
 *
 */
public class RightHtml extends AbstractHtml {
	
	public static RightHtml getInstance() {
		return new RightHtml();
	}

	public RightHtml() {
		create();
	}

	@Override
	public void create() {
		try {
			BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(getFile(RIGHT).getCanonicalPath(), true));
			writeData(bufferWritter);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public void writeData(BufferedWriter bufferWritter) throws IOException {
		HtmlUtil.writeRight(bufferWritter);
	}



	@Override
	public File getFile(String path) {
		return super.getFile(path);
	}

}
