import java.util.*;

class Solution {
	public int solution(int k, int[][] dungeons) {
		List<Integer> answer = new ArrayList<>();
		List<List<Integer>> list = new ArrayList<>();

		boolean[] visited = new boolean[dungeons.length];

		backtracking(k, dungeons, list, answer, visited);

		return Collections.max(answer);
	}

	private void backtracking(int k, int[][] dungeons, List<List<Integer>> list,
		List<Integer> answer, boolean[] visited) {

		answer.add(list.size());

		for (int i = 0; i < dungeons.length; i++) {
			if (k >= dungeons[i][0] && !visited[i]) {
				list.add(Arrays.asList(dungeons[i][0], dungeons[i][1]));
				visited[i] = true;
				backtracking(k - dungeons[i][1], dungeons, list, answer, visited);
				visited[i] = false;
				list.remove(list.size() - 1);
			}
		}
	}
}
