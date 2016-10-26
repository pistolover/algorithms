package others;

public class Dijkstra {
	public static void main(String[] args) {
		int min;
		int[] book;
		int[] dis;
		int u;
		int n;
		int[][] e;

	}

	/**
	 * @param book
	 * @param dis
	 * @param u
	 * @param n
	 * @param e
	 * @return
	 */
	public static void dijkstra(int[] book, int[] dis, int u, int n, int[][] e) {
		int min;
		for (int i = 1; i <= n - 1; i++) {
			min = 888888888;
			for (int j = 1; j <= n; j++) {
				if (book[j] == 0 && dis[j] < min) {
					min = dis[j];
					u = j;// 找到相邻距离最小点
				}
			}

			book[u] = 1;

			for (int v = 1; v <= n; v++) {
				if (e[u][v] < 8888888) {
					if (dis[v] > dis[u] + e[u][v]) {
						dis[v] = dis[u] + e[u][v];
					}
				}
			}
		}
	}
}
