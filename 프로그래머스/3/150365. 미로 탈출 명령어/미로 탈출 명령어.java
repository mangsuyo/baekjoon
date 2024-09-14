import java.util.*;

class Solution {

	int n;
	int m;
	int x;
	int y;
	int r;
	int c;
	int k;
	boolean flag = false;

	int[] dr = new int[] {-1, 1, 0, 0};
	int[] dc = new int[] {0, 0, -1, 1};
	char[] directions = new char[] {'d', 'l', 'r', 'u'};

	class Pair {
		int row;
		int col;
		String str;

		public Pair(int row, int col, String str) {
			this.row = row;
			this.col = col;
			this.str = str;
		}
	}

	public String solution(int n, int m, int x, int y, int r, int c, int k) {
		this.n = n;
		this.m = m;
		this.x = x - 1;
		this.y = y - 1;
		this.r = r - 1;
		this.c = c - 1;
		this.k = k;

		int dist = (Math.abs(this.x - this.r) + Math.abs(this.y - this.c));

		if(dist % 2 == 0){
			if(k % 2 != 0) return "impossible";
		}

		if(dist % 2 != 0){
			if(k % 2 == 0) return "impossible";
		}

		List<String> list = new ArrayList<>();
		dfs(new Pair(this.x, this.y, ""), 0, list);
		return list.isEmpty() ? "impossible" : list.get(0);
	}

	public boolean dfs(Pair p, int count, List<String> list) {

		if (count > k)
			return false;

		if (p.row < 0 || p.col < 0 || p.row >= n || p.col >= m)
			return false;

		if ((Math.abs(p.row - r) + Math.abs(p.col - c)) > k - count) {
			return false;
		}

		if (p.row == r && p.col == c && count == k) {
			list.add(p.str);
			return true;
		}

		if (dfs(new Pair(p.row + 1, p.col, p.str + "d"), count + 1, list))
			return true;
		if (dfs(new Pair(p.row, p.col - 1, p.str + "l"), count + 1, list))
			return true;
		if (dfs(new Pair(p.row, p.col + 1, p.str + "r"), count + 1, list))
			return true;
		return dfs(new Pair(p.row - 1, p.col, p.str + "u"), count + 1, list);
	}
}
