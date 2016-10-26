package autoApi.java.xserver.autoapi.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import autoApi.java.xserver.autoapi.utils.HtmlUtil;

/**
 * 获取index.html文件
 * 
 * @author liqiangqiang
 *
 */
public class IndexHtml extends AbstractHtml {
	
	public static IndexHtml getInstance() {
		return new IndexHtml();
	}

	public IndexHtml() {
		createIndex();
	}

	private void createIndex() {
		try {
			FileWriter fileWritter = new FileWriter(getFile().getCanonicalPath(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			writeData(bufferWritter);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	private File getFile() {
		return super.getFile(INDEX);
	}

	@Override
	public void writeData(BufferedWriter bufferWritter) throws IOException {
		HtmlUtil.writeIndex(bufferWritter);
	}

}
