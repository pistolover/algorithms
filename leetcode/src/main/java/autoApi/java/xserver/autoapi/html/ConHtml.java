package autoApi.java.xserver.autoapi.html;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * 创建controller对应的html文件
 */
public class ConHtml extends AbstractHtml {

	public ConHtml(Collection<String> paths, Map<String, String> file2htmlPath) {
		this.paths = paths;
		this.file2htmlPath = file2htmlPath;
		create();
	}

	public void create() {
		super.create();
	}

	public static ConHtml getInstance(Collection<String> paths, Map<String, String> file2htmlPath) {
		return new ConHtml(paths, file2htmlPath);
	}

	@Override
	public void writeData(BufferedWriter bufferWritter) throws IOException {
		
	}
}
