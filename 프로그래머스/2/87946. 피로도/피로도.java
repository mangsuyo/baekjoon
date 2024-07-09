import java.util.*;

class Solution {

	int answer = 0;

	public int solution(int k, int[][] dungeons) {
		boolean[] visited = new boolean[dungeons.length];
		backtracking(k, dungeons, visited, 0);
		return answer;
	}

	public void backtracking(int k, int[][] dungeons, boolean[] visited, int count) {

		if(count >= answer){
			answer = count;
		}

		for(int i = 0; i < dungeons.length; i++){
			if(!visited[i]) {
				int[] dungeonsInfo = dungeons[i];
				int needFatigue = dungeonsInfo[0];
				int useFatigue = dungeonsInfo[1];
				if (k >= needFatigue) {
					visited[i] = true;
					backtracking(k - useFatigue, dungeons, visited, count + 1);
					visited[i] = false;
				}
			}
		}
	}
}