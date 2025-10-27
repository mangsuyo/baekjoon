import java.util.*;

class Solution {
    int n;
    boolean[] visited;
    Map<Integer, List<Integer>> graph;
    int maxSheep = 0;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        this.n = info.length;
        graph = new HashMap<>();
        for(int[] edge: edges){
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
        }
        visited = new boolean[n];
        visited[0] = true;
        backtrack(info, 1, 0);
        return maxSheep;
    }
    
    void backtrack(int[] info, int sheep, int wolf){        
        maxSheep = Math.max(maxSheep, sheep);
        List<Integer> nextNodes = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            if(visited[i]){
                if(graph.containsKey(i)){
                    for(int nextNode: graph.get(i)){
                        if(!visited[nextNode]){
                            nextNodes.add(nextNode);
                        }
                    }
                }
            }
        }
        
        for(int nextNode: nextNodes){
            int nextSheep = sheep;
            int nextWolf = wolf;
            if(info[nextNode] == 0){
                nextSheep += 1;
            }
            else if(info[nextNode] == 1){
                nextWolf += 1;
            }
            
            if(nextSheep > nextWolf){
                visited[nextNode] = true;
                backtrack(info, nextSheep, nextWolf);
                visited[nextNode] = false;
            }
        }
    }
}