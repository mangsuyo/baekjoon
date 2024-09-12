import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.*;

public class Main {

	static int n;
	static int m;

	static class Pair {
		int row;
		int col;

		public Pair(int first, int second) {
			this.row = first;
			this.col = second;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		n = Integer.parseInt(size[0]);
		m = Integer.parseInt(size[1]);

		int[][] graph = new int[n][m];

		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(line[j]);
			}
		}

		List<Pair> emptySpace = new ArrayList<>();
		List<Pair> virusSpace = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (graph[i][j] == 0) {
					emptySpace.add(new Pair(i, j));
				} else if (graph[i][j] == 2) {
					virusSpace.add(new Pair(i, j));
				}
			}
		}

		List<List<Pair>> candidateSpace = new ArrayList<>();

		backtrack(candidateSpace, new ArrayList<>(), emptySpace, 0);
		List<Integer> answer = new ArrayList<>();

		for (int i = 0; i < candidateSpace.size(); i++) {
			answer.add(bfs(graph, candidateSpace.get(i), virusSpace));
		}
		System.out.println(Collections.max(answer));
	}

	static int bfs(int[][] original, List<Pair> space, List<Pair> virusSpace) {

		int[][] graph = new int[n][m];

		for (int i = 0; i < graph.length; i++) {
			graph[i] = original[i].clone();
		}

		for (int i = 0; i < 3; i++) {
			int r = space.get(i).row;
			int c = space.get(i).col;
			graph[r][c] = 1;
		}

		int[] dr = new int[] {-1, 1, 0, 0};
		int[] dc = new int[] {0, 0, -1, 1};

		Queue<Pair> queue = new ArrayDeque<>(virusSpace);

		boolean[][] visited = new boolean[graph.length][graph[0].length];

		for (int i = 0; i < virusSpace.size(); i++) {
			visited[virusSpace.get(i).row][virusSpace.get(i).col] = true;
		}

		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			int row = pair.row;
			int col = pair.col;
			for (int i = 0; i < 4; i++) {
				int nextR = row + dr[i];
				int nextC = col + dc[i];
				if (0 <= nextR && nextR < n && 0 <= nextC && nextC < m) {
					if (!visited[nextR][nextC] && graph[nextR][nextC] == 0) {
						visited[nextR][nextC] = true;
						queue.add(new Pair(nextR, nextC));
						graph[nextR][nextC] = 2;
					}
				}
			}
		}

		int area = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (graph[i][j] == 0) {
					area += 1;
				}
			}
		}

		return area;
	}

	static void backtrack(List<List<Pair>> candidateSpace, List<Pair> list, List<Pair> emptySpace, int start) {
		if (list.size() == 3) {
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
