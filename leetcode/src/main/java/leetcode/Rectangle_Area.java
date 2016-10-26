package leetcode;

/**
 * 
 * @author lqq
 *
 */
public class Rectangle_Area {
	
	public int computeArea(int A, int B, int C, int D, int E, int F, int G,int H) {
		//
		// //相离
		// if((E>=C || G<=A) && (H<=B || F>=D)){
		// int _area1 = (C-A>=0?(C-A):(A-C)) * (B-D>=0?(B-D):(D-B));
		// int _area2 = (G-E>=0?(G-D):(D-G)) * (H-F>=0?(H-F):(F-H));
		//
		// return _area1 + _area2;
		// }
		//
		// //包含
		// if((A<=E && E<=C && A<=G &&G<=C&&B<=F&&F<=D&&B<=H&&H<=D)){
		// int _area1 = (C-A>=0?(C-A):(A-C)) * (B-D>=0?(B-D):(D-B));
		// return _area1;
		// }else if((A>=E && E>=C && A>=G &&G>=C&&B>=F&&F>=D&&B>=H&&H>=D)){
		// int _area2 = (G-E>=0?(G-D):(D-G)) * (H-F>=0?(H-F):(F-H));
		// return _area2;
		// }
		//
		// //相交 分为一个点在里面和两个点在里面

		int area = (D - B) * (C - A) + (G - E) * (H - F); // 总面积

		int left = Math.max(A, E);
		int down = Math.max(B, F);

		int right = Math.min(G, C);
		int up = Math.min(D, H);

		if (up <= down || right <= left) {
			return area;
		}

		area = area - (right - left) * (up - down);

		return area;
	}
}
