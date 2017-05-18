package future.http;

public class Bean {

	private int conCount;
	
	private int runingTime;
	
	
	public Bean(int conCount, int runingTime, String url) {
		super();
		this.conCount = conCount;
		this.runingTime = runingTime;
		this.url = url;
	}

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getConCount() {
		return conCount;
	}

	public void setConCount(int conCount) {
		this.conCount = conCount;
	}

	public int getRuningTime() {
		return runingTime;
	}

	public void setRuningTime(int runingTime) {
		this.runingTime = runingTime;
	}
	
	
	
}
