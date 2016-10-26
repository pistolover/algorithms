package autoApi.java.xserver.autoapi.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import autoApi.java.xserver.autoapi.constant.ConfigConstant;

public interface Html {
	public final static Logger LOG = LoggerFactory.getLogger(AbstractHtml.class);
	public final static String ROOTDIR = ConfigConstant.rootpath;
	public final static String APIDOCS = ConfigConstant.APIDOCS;
	public final static String DOCHTML = ConfigConstant.DOCHTML;
	public final static String INDEX = ConfigConstant.INDEX;
	public final static String LEFT = ConfigConstant.LEFT;
	public final static String RIGHT = ConfigConstant.RIGHT;


	public void create();

	public File getFile(String path);

	public void writeData(BufferedWriter bufferWritter) throws IOException;
}
