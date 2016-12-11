package test;

public abstract class AbstractTemplate<T> implements Template {
	private String clusterName;

	private String indexName;

	private String typeName;

	private Client client;

	public AbstractTemplate(String... ags) {
		createClient(ags); 
	}

	public abstract void get(String key);

	public abstract T fetch(T t, int pageSize, int page);

	public abstract void delete();

	@Override
	public void createClient(String... params) {
		client = new Client(params);
	}

}

class ConTemplate<T> extends AbstractTemplate<T> {
	public ConTemplate(Client client) {
	}

	@Override
	public void get(String key) {

	}

	@Override
	public T fetch(T t, int pageSize, int page) {
		return null;
	}

	@Override
	public void delete() {

	}

}

interface Template {
	public void createClient(String... params);
}

class Client {

	public Client(String[] params) {
		// TODO Auto-generated constructor stub
	}
}
