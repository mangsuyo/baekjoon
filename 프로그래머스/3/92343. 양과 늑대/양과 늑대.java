import java.util.*;

class Solution {

	Map<Integer, List<Integer>> graph;
	boolean[] visited;
	int maxSheep = 0;

	public int solution(int[] info, int[][] edges) {
		graph = new HashMap<>();
		for (int i = 0; i < edges.length; i++) {
			int start = edges[i][0];
			int end = edges[i][1];
			graph.putIfAbsent(start, new ArrayList<>());
			graph.get(start).add(end);
		}
		List<Integer> path = new ArrayList<>();
		path.add(0);
		visited = new boolean[info.length + 1];
		visited[0] = true;

		backtrack(path, info, 1, 0);
		System.out.println(path);
		return maxSheep;
	}

	void backtrack(List<Integer> path, int[] info, int sheep, int wolf) {
		if(wolf >= sheep) return;

		if(sheep > maxSheep) maxSheep = sheep;

		List<Integer> nextNodes = new ArrayList<>(path);

		for(int node: path){
			if(graph.get(node) != null){
				for(int next: graph.get(node)){
					if(!visited[next]){
						nextNodes.add(next);
					}
				}
			}
		}

		for(int next: nextNodes){
			if(!visited[next]){
				boolean isNextSheep = info[next] == 0;
				path.add(next);
				visited[next] = true;
				if(isNextSheep){
					backtrack(path, info, sheep + 1, wolf);
				}
				else{
					backtrack(path, info, sheep, wolf + 1);
				}
				visited[next] = false;
				path.remove(path.size() - 1);
			}
		}
	}
}
