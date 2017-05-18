package future.http;

public class Main {

	public static void main(String[] args) throws Exception {
		Bean bean = new Bean(200, 50,
				"http://api.my.le.com/vcm/api/list?jsonp=jQuery17102427951778883024_1495112259595&cid=2&type=video&rows=20&page=1&sort=&source=1&listType=1&xid=29511789&pid=10031905&ctype=cmt%2Cimg%2Cvote");

		HttpHandler handler = new HttpHandler(bean);
		handler.execute();
	}
}
