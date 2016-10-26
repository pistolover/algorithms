package autoApi.java.xserver.autoapi.html;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class DtoHtml extends AbstractHtml{

    public DtoHtml(Collection<String> paths, Map<String,String> file2htmlPath){
    	this.paths = paths;
    	this.file2htmlPath = file2htmlPath;
    	create();
    }

    public void create() {
    	super.create();
    }

	public static DtoHtml getInstance(Collection<String> paths, Map<String,String> file2htmlPath) {
		return new DtoHtml(paths, file2htmlPath);
	}

	@Override
	public void writeData(BufferedWriter bufferWritter) throws IOException {
		
	}
}
