package autoApi.java.xserver.autoapi.constant;

public class FileConstant {

	public static enum Dirs {
		css, font, js, picture
	}

	public static Boolean contains(String name){
		Dirs[] values = Dirs.values();
		for (Dirs dirs : values) {
			if(dirs.toString().equals(name)){
				return true;
			}
		}
		return false;
	}
}
