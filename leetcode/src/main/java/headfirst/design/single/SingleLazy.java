package headfirst.design.single;

/**
 *lazy
 */
public class SingleLazy {//ʹ�� volatile ����Ҫԭ��������һ�����ԣ���ָֹ���������Ż�
	private volatile static SingleLazy single;

	private SingleLazy() {}

	public static SingleLazy getSingle() {
		if (single == null) {
			//maybe same threads went into at the same time
			synchronized (SingleLazy.class) {
				if (single == null) {
					single = new SingleLazy();
				}
			}
		}
		return single;
	}
}
