import java.util.*;

class Solution {
	int answer = 0;

	public int solution(int k, int[][] dungeons) {
		int n = dungeons.length;
		boolean[] visited = new boolean[n];
		backtrack(k, dungeons, visited, 0);
		return answer;
	}

	private void backtrack(int k, int[][] dungeons, boolean[] visited, int count) {

		if(count >= answer){
			answer = count;
		}
		
		for(int i = 0; i < dungeons.length; i++){
			if(k >= dungeons[i][0]){
				if(!visited[i]) {
					k -= dungeons[i][1];
					visited[i] = true;
					backtrack(k, dungeons, visited, count + 1);
					k += dungeons[i][1];
					visited[i] = false;
				}
			}
		}
	}
}
