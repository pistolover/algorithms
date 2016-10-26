package headfirst.design.single;

/*
 * Unlazy
 */
public class SingleUnLazy {
	private static final SingleUnLazy UN_LAZY = new SingleUnLazy();
	
	private SingleUnLazy(){}
	
	public static synchronized SingleUnLazy getInstance(){
		return UN_LAZY;
	}
}
