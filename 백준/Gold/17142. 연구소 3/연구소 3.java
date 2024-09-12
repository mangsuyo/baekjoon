import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

	static int n;
	static int m;

	static class Pair {
		int row;
		int col;
		int second;

		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public Pair(int row, int col, int second) {
			this.row = row;
			this.col = col;
			this.second = second;
		}

		@Override
		public String toString() {
			return "Pair{" +
				"row=" + row +
				", col=" + col +
				", second=" + second +
				'}';
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		n = Integer.parseInt(size[0]);
		m = Integer.parseInt(size[1]);

		int[][] graph = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(line[j]);
			}
		}

		List<Pair> virusSpace = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (graph[i][j] == 2) {
					virusSpace.add(new Pair(i, j));
				}
			}
		}

		List<List<Pair>> candidateSpace = new ArrayList<>();

		backtrack(candidateSpace, new ArrayList<>(), virusSpace, 0);

		List<Integer> answer = new ArrayList<>();

		for (int i = 0; i < candidateSpace.size(); i++) {
			int time = (bfs(graph, candidateSpace.get(i)));
			answer.add(time);
		}

		int flag = 0;
		for(int i = 0; i < answer.size(); i++){
			if(answer.get(i) != -1){
				flag = 1;
			}
		}

		List<Integer> temp = new ArrayList<>();
		if(flag == 1){
			for(int i = 0; i < answer.size(); i++){
				if(answer.get(i) != -1){
					temp.add(answer.get(i));
				}
			}
			System.out.println(Collections.min(temp));
		}
		else{
			System.out.println(-1);
		}
	}

	static int bfs(int[][] original, List<Pair> virusSpace) {

		int[][] graph = new int[n][n];

		for (int i = 0; i < graph.length; i++) {
			graph[i] = original[i].clone();
		}

		int[] dr = new int[] {-1, 1, 0, 0};
		int[] dc = new int[] {0, 0, -1, 1};

		Queue<Pair> queue = new ArrayDeque<>();

		for (int i = 0; i < virusSpace.size(); i++) {
			queue.add(new Pair(virusSpace.get(i).row, virusSpace.get(i).col, 0));
		}

		boolean[][] visited = new boolean[n][n];

		for (int i = 0; i < virusSpace.size(); i++) {
			visited[virusSpace.get(i).row][virusSpace.get(i).col] = true;
		}

		while (!queue.isEmpty() && isEmptySpace(graph)) {
			Pair pair = queue.poll();
			int row = pair.row;
			int col = pair.col;
			int second = pair.second;
			for (int i = 0; i < 4; i++) {
				int nextR = row + dr[i];
				int nextC = col + dc[i];
				if (0 <= nextR && nextR < n && 0 <= nextC && nextC < n) {
					if (!visited[nextR][nextC] && graph[nextR][nextC] != 1) {
						visited[nextR][nextC] = true;
						queue.add(new Pair(nextR, nextC, second + 1));
						graph[nextR][nextC] = -1 * (second + 1);
					}
				}
			}
		}

		if(isEmptySpace(graph)) {
			return -1;
		}

		int area = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (graph[i][j] < area) {
					area = graph[i][j];
				}
			}
		}
		return (-1 * area);
	}

	static boolean isEmptySpace(int[][] graph){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(graph[i][j] == 0){
					return true;
				}
			}
		}
		return false;
	}

	static void backtrack(List<List<Pair>> candidateSpace, List<Pair> list, List<Pair> emptySpace, int start) {
		if (list.size() == m) {
			candidateSpace.add(new ArrayList<>(list));
			return;
		}

		for (int i = start; i < emptySpace.size(); i++) {
			list.add(emptySpace.get(i));
			backtrack(candidateSpace, list, emptySpace, i + 1);
			list.remove(list.size() - 1);
		}
	}
}
